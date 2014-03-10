package jp.go.aist.six.vuln.core.repository.scap.cve;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.util.xml.XmlMapper;
import jp.go.aist.six.vuln.CveList;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.core.TestUtil;
import jp.go.aist.six.vuln.model.scap.cve.Cve;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;
import jp.go.aist.six.vuln.repository.scap.cve.CveRepository;
import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;



/**
 * CVE Repository Test.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CveRepositoryImplTest.java 624 2013-06-18 07:29:10Z nakamura5akihito@gmail.com $
 */
@RunWith( Enclosed.class )
public class CveRepositoryImplTest
{

    private final XmlMapper  _xml_mapper;
    private final CveRepository   _repository;



    public CveRepositoryImplTest()
    {
        _xml_mapper = SixVulnContext.Cve.repository().getXmlMapper();
        assertThat( "NOT null", _xml_mapper, notNullValue() );

        _repository = SixVulnContext.Cve.repository().getRepository();
        assertThat( "NOT null", _repository, notNullValue() );
    }



    protected XmlMapper _getXmlMapper()
    {
        return _xml_mapper;
    }


    protected CveRepository _getRepository()
    {
        return _repository;
    }



    @Before
    public void setUp()
    throws Exception
    {
        File  dest_dir = TestUtil.newWokingDir( "cve/repository" );

        Collection<File>  xml_file_list = new ArrayList<File>();
        for (String  dir : CveList.SOURCE_XML_DIRS) {
            File  source_dir = new File( dir );
            Collection<File>  xml_file_sublist = TestUtil.prepareSourceXmlFiles( dest_dir, source_dir );
            xml_file_list.addAll( xml_file_sublist );
        }

        for (File  xml_file : xml_file_list) {
            Cve  cve_list = _getXmlMapper().unmarshal( new FileInputStream( xml_file ), Cve.class );
            for (ItemType  item : cve_list.getItem()) {
                _getRepository().saveCveItem( item );
            }
        }
    }



    /**
     */
    @RunWith( Theories.class )
    public static class CveId
    extends CveRepositoryImplTest
    {
        @DataPoints
        public static String[] cveNames()
        {
            String[]  names = new String[] {
                        "CVE-1999-0001",
                        "CVE-2000-0001",
                        "CVE-2001-0001"
            };

            return names;
        }



        @Theory
        public void testFindCveItemByName(
                        final String cve_name
                        )
        {
            ItemType  obj = _getRepository().findCveItemByName( cve_name );
            assertThat( obj.getName(), is( cve_name ) );
        }

    }
    //



//    //=====================================================================
//    //  CVE List
//    //=====================================================================
//
//    /**
//     */
//    @RunWith( Theories.class )
//    public static class SaveCveListLocalFile
//    extends TestBase
//    {
//
//        @DataPoints
//        public static String[] cveListFileDirs()
//        {
//            return CveList.FILE_DIRS;
//        }
//
//
//        @Theory
//        public void testSaveCveItem(
//                        final String dir_path
//                        )
//        throws Exception
//        {
//            File[]  xml_file_list = TestUtil.listXmlFiles( dir_path );
//
//            long  timestamp_begin, timestamp_end;
//            for (File  in_xml_file : xml_file_list) {
//                System.out.println( "CVE List file: " + in_xml_file );
//                System.out.println( "file length (bytes): " + in_xml_file.length() );
//
//                /* (1) unmarshal */
//                timestamp_begin = System.currentTimeMillis();
//                Cve  cve_list = _xml_mapper.unmarshal( new FileInputStream( in_xml_file ), Cve.class );
//                timestamp_end = System.currentTimeMillis();
//                System.out.println( "unmarshalled (ms): " + (timestamp_end - timestamp_begin) );
//                System.out.println( "#items: " + cve_list.getItem().size() );
//
//                /* (2) save */
//                timestamp_begin = System.currentTimeMillis();
//                for (ItemType  item : cve_list.getItem()) {
//                    _repository.saveCveItem( item );
//                }
//                timestamp_end = System.currentTimeMillis();
//                System.out.println( "all items saved (ms): " + (timestamp_end - timestamp_begin) );
//            }
//        }
//
//    }
//    //save
//
//
//
//    /**
//     */
//    @RunWith( Theories.class )
//    public static class FindCveItemByName
//    extends TestBase
//    {
//
//        @DataPoints
//        public static String[] cveNames()
//        {
//            String[]  names = new String[] {
//                        "CVE-1999-0001",
//                        "CVE-2000-0001",
//                        "CVE-2001-0001"
//            };
//
//            return names;
//        }
//
//
//
//        @Theory
//        public void testFindCveItemByName(
//                        final String name
//                        )
//        throws Exception
//        {
//            long  timestamp_begin, timestamp_end;
//            System.out.println( "CVE name: " + name );
//
//            timestamp_begin = System.currentTimeMillis();
//            ItemType  item = _repository.findCveItemByName( name );
//            timestamp_end = System.currentTimeMillis();
//            System.out.println( "findCveItemByName (ms): " + (timestamp_end - timestamp_begin) );
//            System.out.println( "item: " + item );
//        }
//
//    }
//    //findByName
//
//
//
//    /**
//     */
//    public static class FindAndCount
//    extends TestBase
//    {
//
//        @Test
//        public void testFindCveItem()
//        throws Exception
//        {
//            long  timestamp_begin, timestamp_end;
//
//            //(1) findCveItem
//            timestamp_begin = System.currentTimeMillis();
//            QueryResults<ItemType>  results = _repository.findCveItem();
//            timestamp_end = System.currentTimeMillis();
//            List<ItemType>  item_list = results.getElements();
//            System.out.println( "findCveItem (ms): " + (timestamp_end - timestamp_begin) );
//            System.out.println( "#items: " + item_list.size() );
//
//            //(2) findCveName
//            timestamp_begin = System.currentTimeMillis();
//            QueryResults<String>  name_results = _repository.findCveName();
//            timestamp_end = System.currentTimeMillis();
//            List<String>  name_list = name_results.getElements();
//            System.out.println( "findCveName (ms): " + (timestamp_end - timestamp_begin) );
//            System.out.println( "#names: " + name_list.size() );
//
//            //(3) findCveName
//            timestamp_begin = System.currentTimeMillis();
//            long  count = _repository.countCveItem();
//            timestamp_end = System.currentTimeMillis();
//            System.out.println( "countCveItem (ms): " + (timestamp_end - timestamp_begin) );
//            System.out.println( "#items: " + count );
//
//            //assertion
//            assertThat( (item_list.size() == count), is( true ) );
//            assertThat( (name_list.size() == count), is( true ) );
//        }
//
//    }
//    //find
//
//
//
//    /**
//     */
//    @RunWith( Theories.class )
//    public static class FindByQuery
//    extends TestBase
//    {
//
//        @DataPoints
//        public static QueryParams[] queryParams()
//        {
//            //name //
//            CveItemQueryParams  params_11 = new CveItemQueryParams();
//            params_11.setName( "CVE-2012-0001" );
//            CveItemQueryParams  params_12 = new CveItemQueryParams();
//            params_12.setName( "CVE-2012-000*,CVE-2012-010*" );
//
//            //phase
//            CveItemQueryParams  params_21 = new CveItemQueryParams();
//            params_21.setPhase( SimplePhaseEnumType.Interim );
//
//            //ref source
//            CveItemQueryParams  params_31 = new CveItemQueryParams();
//            params_31.setRefSource( "AUSCERT" );
//            CveItemQueryParams  params_32 = new CveItemQueryParams();
//            params_32.setRefSource( "EEYE,AUSCERT" );
//
//            //ref
//            CveItemQueryParams  params_41 = new CveItemQueryParams();
//            params_41.setRef( "MS10-002" );
//            CveItemQueryParams  params_42 = new CveItemQueryParams();
//            params_42.setRef( "ADV-2009-02,ADV-2009-12" );
//
//            //desc
//            CveItemQueryParams  params_51 = new CveItemQueryParams();
//            params_51.setRef( "CSRF" );
//            CveItemQueryParams  params_52 = new CveItemQueryParams();
//            params_52.setRef( "phpMyAdmin,PHPSelect" );
//
//            QueryParams[]  names = new QueryParams[] {
//                            params_11,
//                            params_12,
//                            params_21,
//                            params_31,
//                            params_32,
//                            params_41,
//                            params_42,
//                            params_51,
//                            params_52
//            };
//
//            return names;
//        }
//
//
//
//        @Theory
//        public void testFindCveItemByName(
//                        final QueryParams params
//                        )
//        throws Exception
//        {
//            System.out.println( "query params: " + params );
//
//            long  timestamp_begin, timestamp_end;
//
//            //(1) findCveItem
//            timestamp_begin = System.currentTimeMillis();
//            QueryResults<ItemType>  results = _repository.findCveItem( params );
//            timestamp_end = System.currentTimeMillis();
//            List<ItemType>  item_list = results.getElements();
//            System.out.println( "findCveItem (ms): " + (timestamp_end - timestamp_begin) );
//            System.out.println( "#items: " + item_list.size() );
//
//            //(2) findCveName
//            timestamp_begin = System.currentTimeMillis();
//            QueryResults<String>  name_results = _repository.findCveName( params );
//            timestamp_end = System.currentTimeMillis();
//            List<String>  name_list = name_results.getElements();
//            System.out.println( "findCveName (ms): " + (timestamp_end - timestamp_begin) );
//            System.out.println( "#names: " + name_list.size() );
//
//            //(3) findCveName
//            timestamp_begin = System.currentTimeMillis();
//            long  count = _repository.countCveItem( params );
//            timestamp_end = System.currentTimeMillis();
//            System.out.println( "countCveItem (ms): " + (timestamp_end - timestamp_begin) );
//            System.out.println( "#items: " + count );
//
//            if (name_list.size() < 100) {
//                System.out.println( "<<< names: " + name_list );
//            }
//
//            //assertion
//            assertThat( (item_list.size() == count), is( true ) );
//            assertThat( (name_list.size() == count), is( true ) );
//        }
//
//    }
//    //findByQuery

}
//
