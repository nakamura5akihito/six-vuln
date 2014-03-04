package jp.go.aist.six.vuln.core.xml.scap.cve;

import java.io.File;
import jp.go.aist.six.vuln.CveList;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.core.TestUtil;
import jp.go.aist.six.vuln.core.XmlTestBase;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


/**
 * @author  Akihito Nakamura, AIST
 * @version $Id: CveXmlMapperImplTest.java 604 2013-06-12 08:14:04Z nakamura5akihito@gmail.com $
 */
@RunWith( Enclosed.class )
public class CveXmlMapperImplTest
{

    /**
     */
    @RunWith( Theories.class )
    public static class CveListXmlLocalFile
    extends XmlTestBase
    {

        public CveListXmlLocalFile()
        {
            super( SixVulnContext.Cve.basic(), "cve/local-file" );
        }


        @DataPoints
        public static String[] cveListFileDirs()
        {
            return CveList.XML_FILE_DIRS;
        }


        @Theory
        public void testCveListXmlMapping(
                        final String dir_path
                        )
        throws Exception
        {
            File[]  xml_file_list = TestUtil.listXmlFiles( dir_path );
            for (File  source_xml_file : xml_file_list) {
                _testXmlMapping( source_xml_file );
            }

            File[]  jar_file_list = TestUtil.listZipXmlFiles( dir_path );
            for (File  source_jar_xml_file : jar_file_list) {
                _testZipXmlMapping( source_jar_xml_file );
            }
        }

    }
    //CveListXmlLocalFile

}
//
