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



//    /**
//     */
//    public static class SaveNvdDataFeedLocalFile
//    {
//
//        public static String[] nvdDataFeedFilepathList()
//        {
//            List<String>  filepath_list = new ArrayList<String>();
//            for (String  filename : NvdDataFeed.FILE_NAMES) {
//                filepath_list.add( NvdDataFeed.FILE_DIR_20130226 + "/" + filename );
//            }
//
//            return filepath_list.toArray( new String[0] );
//        }
//
//
//
//        @Test
//        public void testInstall()
//        throws Exception
//        {
//            NvdDataFeedInstaller  installer = new NvdDataFeedInstaller();
//            installer.execute( nvdDataFeedFilepathList() );
//        }
//
//    }
//    //local file






//    public static final String  DB_URI = "mongodb://localhost";
//    public static final String  DB_NAME = "NvdXmlFeedInstallerTest";



//    @Test
//    public void testExecute()
//    {
//        final String[]  feed_pathes = new String[] {
//                        "src/test/resources/nvd_data_feed_20130226/nvdcve-2.0-2013.xml"
////                        "src/test/resources/nvd_data_feed_20130226/nvdcve-2.0-2010.xml"
//        };
//
//        NvdDataFeedInstaller  installer = new NvdDataFeedInstaller();
//        installer.execute( feed_pathes );
//    }



//    @Before
//    public void ensureDbIsEmpty()
//                    throws UnknownHostException, InterruptedException
//    {
//        _LOG_.debug( "ensuring db is empty...: " + DB_NAME );
//
//        Mongo  mongo = null;
//        try {
//            while (mongo == null) {
//                mongo = new MongoURI( DB_URI ).connect();
//                Thread.sleep( 250 );
//            }
//
//            List<String>  db_names = mongo.getDatabaseNames();
//            if (db_names != null  &&  db_names.contains( DB_NAME )) {
//                _LOG_.debug( "dropping db...: " + DB_NAME );
//                mongo.dropDatabase( DB_NAME );
//                _LOG_.debug( "...db dropped: " + DB_NAME );
//            }
//        } catch (MongoException ex) {
//            _LOG_.error( ex.getMessage(), ex );
//        } finally {
//            mongo.close();
//        }
//
//        _LOG_.debug( "...db is empty: " + DB_NAME );
//    }

}
