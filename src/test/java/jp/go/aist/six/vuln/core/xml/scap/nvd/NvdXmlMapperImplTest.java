package jp.go.aist.six.vuln.core.xml.scap.nvd;

import java.io.File;
import jp.go.aist.six.vuln.NvdDataFeed;
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
 * @version $Id: NvdXmlMapperImplTest.java 598 2013-06-12 07:10:04Z nakamura5akihito@gmail.com $
 */
@RunWith( Enclosed.class )
public class NvdXmlMapperImplTest
{

    /**
     */
    @RunWith( Theories.class )
    public static class NvdDataFeedXmlLocalFile
    extends XmlTestBase
    {

        public NvdDataFeedXmlLocalFile()
        {
            super( SixVulnContext.Nvd.basic(), "nvd/local-file" );
        }


        @DataPoints
        public static String[] nvdDataFeedDirs()
        {
            return NvdDataFeed.SOURCE_XML_DIRS;
        }


        @Theory
        public void testNvdDataFeedXmlMapping(
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
    //NvdDataFeedLocalFile

}
//

