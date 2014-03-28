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
package jp.go.aist.six.vuln.core.repository.scap.cve;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import jp.go.aist.six.util.xml.XmlMapper;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.model.scap.cve.Cve;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;
import jp.go.aist.six.vuln.repository.scap.cve.CveRepository;



/**
 * A CVE List installer.
 * This reads the XML format CVE list and saves the content entries to the database.
 *
 * <p>Options:
 * </p>
 * <ul>
 *   <li>URL(s):
 *   Obtains XML from the specified location(s).
 *   </li>
 *   <li>file path(es):
 *   Reads XML from the specified file(s).
 *   </li>
 * </ul>
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: NvdDataFeedInstaller.java 621 2013-06-14 07:30:05Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org">CVE - Common Vulnerabilities and Exposures (CVE)</a>
 */
public class CveListInstaller
{


    public static void main(
                    final String[] args
                    )
    {
        CveListInstaller  installer = new CveListInstaller();
        installer.execute( args );
    }



    public static final String  CVE_XML_URL = "http://cve.mitre.org/data/downloads/allitems.xml";

    private final PrintStream  _msg_stream = System.out;



    /**
     */
    public CveListInstaller()
    {
    }



    /**
     * Executes the installer.
     */
    public void execute(
                    final String[] options
                    )
    {
        _printMessage( _START_MESSAGE_ );

        String[]  cve_source_list = options;
        if (options == null  ||  options.length == 0) {
            cve_source_list = new String[] { CVE_XML_URL };
        }

        for (String  cve_source : cve_source_list) {
            _printMessage( "*** install: " + cve_source );
            try {
                _install( cve_source );
            } catch (Exception ex) {
                _printMessage( "@@@ ERROR: " + cve_source + " - " + ex.getMessage() );
                ex.printStackTrace( _msg_stream );
                _printMessage( "" );
//                _printMessage( "...skip" );
            }
        }
    }



    /**
     * Installs the CVE list to the database.
     * The location of the CVE list is a file path or URL.
     *
     * @param   cve_list_location
     *  the location of the CVE list.
     */
    private void _install(
                    final String cve_list_location
                    )
    throws Exception
    {
        URL  url = null;
        try {
            url = new URL( cve_list_location );
        } catch (MalformedURLException ex) {
            url = null;
        }

        if (url == null) {
            //local filepath
            File  file = new File( cve_list_location );
            _install( file );
        } else {
            _install( url );
        }
    }



    /**
     * Installs the CVE list to the database.
     *
     * @param   cve_list
     *  the file containing the CVE list.
     */
    private void _install(
                    final File cve_list
                    )
    throws Exception
    {
        _install( cve_list.getCanonicalPath(), new FileInputStream( cve_list ) );
    }



    /**
     * Installs the CVE list to the database.
     *
     * @param   cve_list
     *  the URL of the CVE list.
     */
    private void _install(
                    final URL cve_list
                    )
    throws Exception
    {
        _install( cve_list.toString(), cve_list.openStream() );
    }



    /**
     * Installs the CVE list to the database.
     *
     * @param   cve_list_location
     *  the location of the CVE list.
     * @param   xml_stream
     *  the stream which the CVE list XML is read from.
     */
    private void _install(
                    final String cve_list_location,
                    final InputStream xml_stream
                    )
    throws Exception
    {
        _printMessage( "install CVE list: " + cve_list_location );

        XmlMapper  xml_mapper = SixVulnContext.Cve.repository().getXmlMapper();
        CveRepository  repository = SixVulnContext.Cve.repository().getRepository();

        long  timestamp_begin, timestamp_end;

        _printMessage( "unmarshalling XML..." );
        timestamp_begin = System.currentTimeMillis();
        Cve  cve = xml_mapper.unmarshal( xml_stream, Cve.class );
        timestamp_end = System.currentTimeMillis();
        _printMessage( "...unmarshalling done: "
                        + (timestamp_end - timestamp_begin) + " (ms), " + cve_list_location );

        _printMessage( "  - #entries=" + cve.getItem().size() );

        timestamp_begin = System.currentTimeMillis();
        for (ItemType  item : cve.getItem()) {
//            _printMessage( "saving CVE entry: " + item.getName() );
            repository.saveCveItem( item );
        }
        timestamp_end = System.currentTimeMillis();
        _printMessage( "...installation of CVE list COMPLETED: "
                        + (timestamp_end - timestamp_begin) + " (ms), " + cve_list_location );
    }



    //=====================================================================
    //  message
    //=====================================================================

    private static final String[]  _START_MESSAGE_
    = new String[] {
        "------------------------------------------------",
        "CVE List Installer",
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
//CveListInstaller
