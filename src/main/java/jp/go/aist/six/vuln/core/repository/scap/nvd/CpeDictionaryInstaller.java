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
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.GeneratorType;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.ItemType;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.ListType;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;



/**
 * A CPE dictionary data installer.
 * This reads the data XML and saves the content CPE items to the database.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CpeDictionaryInstaller.java 542 2013-04-19 03:18:16Z nakamura5akihito@gmail.com $
 * @see <a href="http://nvd.nist.gov/">National Vulnerability Database (NVD)</a>
 */
public class CpeDictionaryInstaller
{


    public static void main(
                    final String[] args
                    )
    {
        CpeDictionaryInstaller  installer = new CpeDictionaryInstaller();
        installer.execute( args );
    }



    /**
     * The URI of the CPE dictionary data XML.
     */
    public static final String  DIC_URI =
        "http://static.nvd.nist.gov/feeds/xml/cve/official-cpe-dictionary_v2.2.xml";


    private final PrintStream  _msg_stream = System.out;



    /**
     */
    public CpeDictionaryInstaller()
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

        String  location = null;
        if (options == null  ||  options.length == 0) {
            location = DIC_URI;
        } else {
            location = options[0];
        }

        _printMessage( "*** install: " + location );
        try {
            _install( location );
        } catch (Exception ex) {
            _printMessage( "@@@ ERROR: " + location + " - " + ex.getMessage() );
            ex.printStackTrace( _msg_stream );
            _printMessage( "" );
        }
    }



    /**
     * The location of the XML file is a file path or URI.
     *
     * @param   resource_location
     *  the location of the XML Feed.
     * @return
     *  the persistent ID of the installed Nvd object.
     */
    private void _install(
                    final String resource_location
                    )
    throws Exception
    {
        URL  url = null;
        try {
            url = new URL( resource_location );
        } catch (MalformedURLException ex) {
            url = null;
        }

        if (url == null) {
            //local filepath
            File  file = new File( resource_location );
            _install( file );
        } else {
            _install( url );
        }
    }



    /**
     *
     * @param   file
     *  the file containing the CPE dictionary.
     */
    private void _install(
                    final File file
                    )
    throws Exception
    {
        _install( file.getCanonicalPath(), new FileInputStream( file ) );
    }



    /**
     * @param   url
     *  the URL of the CPE dictionary.
     */
    private void _install(
                    final URL url
                    )
    throws Exception
    {
        _install( url.toString(), url.openStream() );
    }



    /**
     * @param   stream
     *  the stream which the CPE dictionary is read from.
     */
    private void _install(
                    final String source,
                    final InputStream stream
                    )
    throws Exception
    {
        _printMessage( "install CPE dictionary: " + source );

        XmlMapper  xml = SixVulnContext.Nvd.repository().getXmlMapper();
        NvdRepository  service = SixVulnContext.Nvd.repository().getRepository();

        _printMessage( "unmarshalling XML..." );
        ListType  dic = xml.unmarshal( stream, ListType.class );
        _printMessage( "...unmarshalling DONE: " + source );

        GeneratorType  generator = dic.getGenerator();

        _printMessage( "  - schema version=" + generator.getSchemaVersion() );
        _printMessage( "  - timestamp=" + generator.getTimestamp() );
        _printMessage( "  - product name=" +  generator.getProductName() );
        _printMessage( "  - product version=" +  generator.getProductVersion() );
        _printMessage( "  - #items=" + dic.getCpeItem().size() );

        for (ItemType  item : dic.getCpeItem()) {
            _printMessage( "saving item: name=" + item.getName() );
            service.saveCpeItem( item );
        }
        _printMessage( "...installation of Cpe dictionary COMPLETED: " + source );
    }



    //=====================================================================
    //  message
    //=====================================================================

    private static final String[]  _START_MESSAGE_
    = new String[] {
        "------------------------------------------------",
        "CPE Dictionary Installer, SIX VULN, version 1.0.0",
        "copyright (C) 2006-2010 AIST",
        "AIST Registered Number H18PRO-538",
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
//
