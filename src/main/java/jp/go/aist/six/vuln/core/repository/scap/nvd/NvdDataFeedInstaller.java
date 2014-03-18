/**
 * SIX VULN - http://code.google.com/p/six-vuln/
 * Copyright (C) 2006
 *   National Institute of Advanced Industrial Science and Technology (AIST)
 *   Registration Number: H20PRO-863
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.go.aist.six.vuln.core.repository.scap.nvd;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import jp.go.aist.six.util.xml.XmlMapper;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.model.scap.nvd.Nvd;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdDataFeed;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;



/**
 * An NVD Data Feed installer.
 * This reads the XML feeds and saves the content vulnerability entries to the database.
 *
 * <p>Options:
 * </p>
 * <ul>
 *   <li>-2002, -2003,..., -2014, -recent, -modified:
 *   Obtains data feed(s) from NIST. Each option indicates the data file.
 *   </li>
 *   <li>URL(s):
 *   Obtains data feed(s) from the specified location(s).
 *   </li>
 *   <li>file path(es):
 *   Reads data feed(s) from the specified file(s).
 *   </li>
 * </ul>
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: NvdDataFeedInstaller.java 621 2013-06-14 07:30:05Z nakamura5akihito@gmail.com $
 * @see <a href="http://nvd.nist.gov/">National Vulnerability Database (NVD)</a>
 */
public class NvdDataFeedInstaller
{


    public static void main(
                    final String[] args
                    )
    {
        NvdDataFeedInstaller  installer = new NvdDataFeedInstaller();
        installer.execute( args );
    }



    private final PrintStream  _msg_stream = System.out;



    /**
     */
    public NvdDataFeedInstaller()
    {
    }



    /**
     * Executes the installer.
     */
    public void execute(
                    final String[] feed_options
                    )
    {
        _printMessage( _START_MESSAGE_ );

        String[]  data_feed_list = feed_options;
        if (feed_options == null  ||  feed_options.length == 0) {
            data_feed_list = NvdDataFeed.listYearlyXmlFeedUrls().toArray( new String[0] );
//            feed_list = getAllYearlyXmlFeedUrls().toArray( new String[0] );
        }

        for (String  data_feed : data_feed_list) {
            _printMessage( "*** install: " + data_feed );
            try {
                _install( data_feed );
            } catch (Exception ex) {
                _printMessage( "@@@ ERROR: " + data_feed + " - " + ex.getMessage() );
                ex.printStackTrace( _msg_stream );
                _printMessage( "" );
//                _printMessage( "...skip" );
            }
        }
    }



    /**
     * Installs the XML Feed to the data store.
     * The location of the Feed is a file path or URL.
     *
     * @param   data_feed
     *  the location of the XML Feed.
     * @return
     *  the persistent ID of the installed Nvd object.
     */
    private void _install(
                    final String data_feed
                    )
    throws Exception
    {
        if (data_feed.startsWith( "-" )) {
            String  feed_id = data_feed.substring( 1 );
            String  url = NvdDataFeed.buildXmlFeedUrl( feed_id );
            _install( new URL( url ) );
        } else {
            URL  url = null;
            try {
                url = new URL( data_feed );
            } catch (MalformedURLException ex) {
                url = null;
            }

            if (url == null) {
                //local filepath
                File  file = new File( data_feed );
                _install( file );
            } else {
                _install( url );
            }
        }
    }



    /**
     * Installs the XML Feed to the data store.
     *
     * @param   data_feed_file
     *  the file containing the XML Feed.
     * @return
     *  the persistent ID of the installed Nvd object.
     */
    private void _install(
                    final File data_feed_file
                    )
    throws Exception
    {
        _install( data_feed_file.getCanonicalPath(), new FileInputStream( data_feed_file ) );
    }



    /**
     * Installs the XML Feed to the data store.
     *
     * @param   data_feed_url
     *  the URL of the XML Feed.
     * @return
     *  the persistent ID of the installed Nvd object.
     */
    private void _install(
                    final URL data_feed_url
                    )
    throws Exception
    {
        _install( data_feed_url.toString(), data_feed_url.openStream() );
    }



    /**
     * Installs the XML Feed to the data store.
     *
     * @param   xml_stream
     *  the stream which the XML Feed is read from.
     * @return
     *  the persistent ID of the installed Nvd object.
     */
    private void _install(
                    final String data_feed_name,
                    final InputStream xml_stream
                    )
    throws Exception
    {
        _printMessage( "install NVD Data Feed: " + data_feed_name );

        XmlMapper  xml_mapper = SixVulnContext.Nvd.repository().getXmlMapper();
        NvdRepository  repository = SixVulnContext.Nvd.repository().getRepository();

        long  timestamp_begin, timestamp_end;

        _printMessage( "unmarshalling XML..." );
        timestamp_begin = System.currentTimeMillis();
        Nvd  nvd = xml_mapper.unmarshal( xml_stream, Nvd.class );
        timestamp_end = System.currentTimeMillis();
        _printMessage( "...unmarshalling done: "
                        + (timestamp_end - timestamp_begin) + " (ms), " + data_feed_name );

//        String  pubDate = IsoDate.format( nvd.getPubDate() );
        _printMessage( "  - pub_date=" +  nvd.getPubDate() );
        _printMessage( "  - nvd_xml_version=" + nvd.getNvdXmlVersion() );
        _printMessage( "  - #entries=" + nvd.getEntry().size() );

        timestamp_begin = System.currentTimeMillis();
        for (VulnerabilityType  v : nvd.getEntry()) {
            _printMessage( "saving vulnerability entry: id=" + v.getId() );
            repository.saveVulnerability( v );
        }
        timestamp_end = System.currentTimeMillis();
        _printMessage( "...installation of NVD Data Feed COMPLETED: "
                        + (timestamp_end - timestamp_begin) + " (ms), " + data_feed_name );
    }



//    //=====================================================================
//    //  NVD/CVE XML Feed
//    //=====================================================================
//
//    public static final String  RECENT_FEED_TYPE = "recent";
//    public static final String  MODIFIED_FEED_TYPE = "modified";
//    public static final int  MINIMUM_FEED_YEAR = 2002;
//
//    /**
//     */
//    private static Collection<String> _createYearlyFeedTypes()
//    {
//        final int  currentYear = Calendar.getInstance().get( Calendar.YEAR );
//        Collection<String>  types = new ArrayList<String>();
//        for (int  year = MINIMUM_FEED_YEAR; year <= currentYear; year++) {
//            types.add( String.valueOf( year ) );
//        }
//
//        return Collections.unmodifiableCollection( types );
//    }
//
//
//
//    /**
//     */
//    private static Collection<String> _createAllFeedTypes()
//    {
//        Collection<String>  types = new ArrayList<String>();
//        types.addAll( _createYearlyFeedTypes() );
//        types.add( RECENT_FEED_TYPE );
//        types.add( MODIFIED_FEED_TYPE );
//
//        return Collections.unmodifiableCollection( types );
//    }
//
//
//    private static Collection<String>  _ALL_FEED_TYPES_ = _createAllFeedTypes();
//
//
//
//    /**
//     * version 2.0 URL pattern.
//     * "{0}" is replaced by 4-digit years, "recent", or "modified".
//     */
//    public static final String  XML_FEED_URL_PATTERN
//    = "http://static.nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-{0}.xml";
//
//
//    /**
//     * Returns the URL of an XML Feed of the specified type.
//     * The type is one of: "recent", "modified", "2002", "2003",...
//     *
//     * @param   type
//     *  the XML Feed type.
//     * @return
//     *  the URL.
//     */
//    public String buildXmlFeedUrl(
//                    final String type
//                    )
//    {
//        if (_ALL_FEED_TYPES_.contains( type )) {
//            // OK
//        } else {
//            throw new IllegalArgumentException( "unknown XML Feed type: " + type );
//        }
//
//        return MessageFormat.format( XML_FEED_URL_PATTERN, type );
//    }
//
//
//
//    /**
//     * Returns the URLs of all the yearly Feeds; from 2002 to this year.
//     */
//    public List<String> getAllYearlyXmlFeedUrls()
//    {
//        List<String>  locations = new ArrayList<String>();
//
//        for (String  year : _createYearlyFeedTypes()) {
//            String  url = buildXmlFeedUrl( year );
//            locations.add( url );
//        }
//
//        return locations;
//    }



    //=====================================================================
    //  message
    //=====================================================================

    private static final String[]  _START_MESSAGE_
    = new String[] {
        "------------------------------------------------",
        "NVD XML Feed Installer",
        "copyright (C) 2006-2010 AIST",
        "AIST Registered Number H18PRO-538",
        "https://github.com/nakamura5akihito/six-vuln",
        "------------------------------------------------",
    };



    /**
     */
    private void _printMessage(
                    final String message
                    )
    {
        _msg_stream.println( message );
        _msg_stream.flush();
    }



    /**
     */
    private void _printMessage(
                    final String[] lines
                    )
    {
        for (String  line : lines) {
            _printMessage( line);
        }
    }

}
//NvdDataFeedInstaller
