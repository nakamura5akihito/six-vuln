package jp.go.aist.six.vuln.core.repository.scap.nvd;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.vuln.NvdDataFeed;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.core.XmlTestBase;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;



/**
 */
@RunWith( Enclosed.class )
public class NvdDataFeedInstallerTest
{

    public static void testInstall(
                    final String[] source_xml_list
                    )
                    throws Exception
    {
        NvdDataFeedInstaller  installer = new NvdDataFeedInstaller();
        installer.execute( source_xml_list );
    }




    /**
     */
    @RunWith( Theories.class )
    public static class NvdDataFeedInstallerLocalFile
    extends XmlTestBase
    {

        public NvdDataFeedInstallerLocalFile()
        {
            super( SixVulnContext.Nvd.basic(), "nvd/repository/local-file" );
        }


        @DataPoints
        public static String[] nvdDataFeedDirs()
        {
            return NvdDataFeed.SOURCE_XML_DIRS;
        }


        @Theory
        public void testNvdDataFeedRepositoryInstall(
                        final String dir_path
                        )
        throws Exception
        {
            Collection<File>  xml_file_list = _prepareSourceXmlFiles( new File( dir_path ) );
            Collection<String>  xml_filepath_list = new ArrayList<String>();
            for (File  xml_file : xml_file_list) {
                xml_filepath_list.add( xml_file.getCanonicalPath() );
            }
            testInstall( xml_filepath_list.toArray( new String[0]) );
        }

    }
    //

}
//
