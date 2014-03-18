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
package jp.go.aist.six.vuln.repository.scap.nvd;

import java.io.File;
import java.io.FilenameFilter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A description of the NVD Data Feed and utility functions.
 * We define the "feed ID" as one of the 4-digit year, "recent", or "modified"
 * which identify the feed.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: NvdDataFeedInstaller.java 621 2013-06-14 07:30:05Z nakamura5akihito@gmail.com $
 * @see <a href="http://nvd.nist.gov/">National Vulnerability Database (NVD)</a>
 */
public class NvdDataFeed
{

    /**
     * Logger.
     */
    private static final Logger  _LOG_ = LoggerFactory.getLogger( NvdDataFeed.class );


    /**
     * Instantiation is unnecessary.
     */
    protected NvdDataFeed()
    {
    }



    //=====================================================================
    //  NVD/CVE XML Feed
    //=====================================================================

    public static final String  RECENT_FEED_TYPE = "recent";
    public static final String  MODIFIED_FEED_TYPE = "modified";
    public static final int  OLDEST_FEED_YEAR = 2002;


    /**
     * Returns a list of all the feed years, from OLDEST_FEED_YEAR to the current year.
     * The returned list is like "2002, 2003, ..., 2014", if the current year is 2014.
     *
     * @return
     *  a list of all the feed years.
     */
    public static List<Integer> listFeedYears()
    {
        final int  currentYear = Calendar.getInstance().get( Calendar.YEAR );
        List<Integer>  types = new ArrayList<Integer>();
        for (int  year = OLDEST_FEED_YEAR; year <= currentYear; year++) {
            types.add( year );
        }

        return Collections.unmodifiableList( types );
    }


    public static List<String> listYealyFeedIds()
    {
        List<String>  types = new ArrayList<String>();
        for (Integer  year : listFeedYears()) {
            types.add( year.toString() );
        }

        return Collections.unmodifiableList( types );
    }


    /**
     * A list of all the NVD feed IDs.
     */
    public static Collection<String>  ALL_YEARLY_FEED_IDS = listYealyFeedIds();



    /**
     */
    private static Collection<String> _createAllFeedIds()
    {
        Collection<String>  types = new ArrayList<String>();
        types.addAll( listYealyFeedIds() );
        types.add( RECENT_FEED_TYPE );
        types.add( MODIFIED_FEED_TYPE );

        return Collections.unmodifiableCollection( types );
    }


    /**
     * A list of all the NVD feed IDs.
     */
    private static Collection<String>  _ALL_FEED_IDS_ = _createAllFeedIds();



    /**
     * NVD Data Feed version 2.0 URL pattern.
     * "{0}" will be replaced by 4-digit years, "recent", or "modified".
     */
    public static final String  XML_FEED_URL_PATTERN
    = "http://static.nvd.nist.gov/feeds/xml/cve/nvdcve-2.0-{0}.xml";


    /**
     * Returns the URL of an XML Feed of the specified type.
     * The type is one of: "recent", "modified", "2002", "2003",...
     *
     * @param   feed_id
     *  the XML Feed ID.
     * @return
     *  the feed URL.
     */
    public static String buildXmlFeedUrl(
                    final String feed_id
                    )
    {
        if (_ALL_FEED_IDS_.contains( feed_id )) {
            // OK
        } else {
            throw new IllegalArgumentException( "unknown XML Feed ID: " + feed_id );
        }

        return MessageFormat.format( XML_FEED_URL_PATTERN, feed_id );
    }



    /**
     * Returns the URLs of all the yearly feeds; from 2002 to the current year.
     */
    public static List<String> listYearlyXmlFeedUrls()
    {
        List<String>  url_list = new ArrayList<String>();

        for (String  year : listYealyFeedIds()) {
            String  url = buildXmlFeedUrl( year );
            url_list.add( url );
        }

        return url_list;
    }



    //===============================================================================
    //  file processing
    //===============================================================================

    /**
     * NVD Data Feed version 2.0 file name pattern.
     * "{0}" will be replaced by 4-digit years, "recent", or "modified".
     */
    public static final String  XML_FEED_FILENAME_PATTERN
    = "nvdcve-2.0-{0}.xml";


    /**
     * Creates yearly feed IDs regex matching pattern string.
     * For example, "2002|2003|...|2014".
     *
     * @return
     */
    private static String _createYearlyXmlFeedIdsRegex()
    {
        StringBuilder  s = new StringBuilder();
        s.append( "(" );
        for (String  year_id : listYealyFeedIds()) {
            if (s.length() > 1) {
                s.append( "|" );
            }
            s.append( year_id );
        }
        s.append( ")" );

        return s.toString();
    }


    /**
     * Returns the files whose file names match the valid feed file name pattern.
     */
    public static List<File> listYearlyXmlFeedFiles(
                    final File dir
                    )
    {
        String  feed_ids_regex = _createYearlyXmlFeedIdsRegex();
        _LOG_.trace( "yearly feed IDs regex: " + feed_ids_regex );
        String  filename_regex = MessageFormat.format( XML_FEED_FILENAME_PATTERN, feed_ids_regex );

        File[]  files = dir.listFiles( new XmlFeedFilenameFilter( filename_regex ) );
        if (files == null  ||  files.length == 0) {
            return Collections.emptyList();
        }

        return (new ArrayList<File>( Arrays.asList( files ) ));
    }



    /**
     * A file name filter for XML files.
     * The XML files are identified using the ".xml" extension.
     */
    private static class XmlFeedFilenameFilter
    implements FilenameFilter
    {

        private final Pattern  _pattern;


        public XmlFeedFilenameFilter(
                        final String regex
                        )
        {
            _pattern = Pattern.compile( regex );
        }


        @Override
        public boolean accept(
                        final File dir,
                        final String name
                        )
        {
            return (_pattern.matcher( name ).matches());
        }

    }
    //

}
//NvdDataFeed
