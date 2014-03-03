package jp.go.aist.six.vuln.core.xml.scap.cve;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import jp.go.aist.six.util.xml.XmlMapper;
import jp.go.aist.six.vuln.CveList;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.core.TestUtil;
import org.junit.Before;
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

    protected XmlMapper  _xml_mapper;
    protected File  _tmp_dir = null;



    protected void _init(
                    final String tmp_subdir
                    )
    {
        _xml_mapper = SixVulnContext.Cve.basic().getXmlMapper();

        String  tmp_dirpath = System.getProperty( "java.io.tmpdir" );
        _tmp_dir = new File( tmp_dirpath, "six-vuln/cve/" + tmp_subdir );
        _tmp_dir.mkdirs();
    }



    /**
     */
    @RunWith( Theories.class )
    public static class CveListXmlLocalFile
    extends CveXmlMapperImplTest
    {

        @Before
        public void setUp()
        {
            _init( "local-file" );
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

            long  timestamp_begin, timestamp_end;
            for (File  in_xml_file : xml_file_list) {
                System.out.println( "CVE List file: " + in_xml_file );
                System.out.println( "length (bytes): " + in_xml_file.length() );

                /* (1) unmarshal */
                timestamp_begin = System.currentTimeMillis();
                Object  obj = _xml_mapper.unmarshal( new FileInputStream( in_xml_file ) );
                timestamp_end = System.currentTimeMillis();
                System.out.println( "unmarshalled (ms): " + (timestamp_end - timestamp_begin) );

                /* (2) marshal */
                File  out_xml_file = new File( _tmp_dir, "unmarshalled_" + in_xml_file.getName() );
                timestamp_begin = System.currentTimeMillis();
                _xml_mapper.marshal( obj, new FileWriter( out_xml_file ) );
                timestamp_end = System.currentTimeMillis();
                System.out.println( "marshalled (ms): " + (timestamp_end - timestamp_begin) );

                /* (3) unmarshal */
                timestamp_begin = System.currentTimeMillis();
                obj = _xml_mapper.unmarshal( new FileInputStream( out_xml_file ) );
                timestamp_end = System.currentTimeMillis();
                System.out.println( "unmarshalled (ms): " + (timestamp_end - timestamp_begin) );
            }
        }

    }
    //LocalFile

}
//
