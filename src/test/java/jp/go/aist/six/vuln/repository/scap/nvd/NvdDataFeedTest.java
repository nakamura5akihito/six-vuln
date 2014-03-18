package jp.go.aist.six.vuln.repository.scap.nvd;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.File;
import java.util.List;
import org.junit.Test;


public class NvdDataFeedTest
{

    @Test
    public void testListYearlyFeedIds()
    {
        List<String>  list = NvdDataFeed.listYealyFeedIds();
        System.out.println( "yearly feed ID list=" + list );
        assertThat( list, notNullValue() );
        assertThat( list.get( 0 ), is( String.valueOf( NvdDataFeed.OLDEST_FEED_YEAR ) ) );
    }



    @Test
    public void testListYearlyFeedUrls()
    {
        List<String>  list = NvdDataFeed.listYearlyXmlFeedUrls();
        System.out.println( "yearly feed XML URL list=" + list );
        assertThat( list, notNullValue() );
//        assertThat( list.get( 0 ), is( String.valueOf( NvdDataFeed.OLDEST_FEED_YEAR ) ) );
    }



    private static final String  _XML_FEED_2014_FILENAME_ = "nvdcve-2.0-2014.xml";

    @Test
    public void testListYearlyXmlFeedFiles()
    {
        File  dir = new File( "src/test/resources/nvd/xml/nist" );
        assertTrue( dir.exists() );

        List<File>  list = NvdDataFeed.listYearlyXmlFeedFiles( dir );
        System.out.println( "yearly feed XML file list=" + list );
        assertThat( list, notNullValue() );

        boolean  file_found = false;
        for (File  file : list) {
            String  filename = file.getName();
            if (filename.equals( _XML_FEED_2014_FILENAME_ )) {
                file_found = true;
                break;
            }
        }
        assertTrue( file_found );
    }

}
//
