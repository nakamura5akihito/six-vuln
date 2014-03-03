package jp.go.aist.six.vuln.core;

import java.io.File;
import java.io.FilenameFilter;



/**
 * @author  Akihito Nakamura, AIST
 * @version $Id: TestUtil.java 594 2013-06-11 09:41:25Z nakamura5akihito@gmail.com $
 */
public class TestUtil
{

    /**
     * Creates a file list from directory path and/or file path.
     */
    public static File[] listXmlFiles(
                    final String dir_path
                    )
    throws Exception
    {
        return listXmlFiles( new File( dir_path ) );
    }


    public static File[] listXmlFiles(
                    final File dir
                    )
    throws Exception
    {
        File[]  list = dir.listFiles( new XmlFilenameFilter() );

        return list;
    }



    public static File[] listZipXmlFiles(
                    final String dir_path
                    )
    throws Exception
    {
        return listZipXmlFiles( new File( dir_path ) );
    }


    public static File[] listZipXmlFiles(
                    final File dir
                    )
    throws Exception
    {
        File[]  list = dir.listFiles( new ZipXmlFilenameFilter() );

        return list;
    }




    /**
     */
    public static class XmlFilenameFilter
    implements FilenameFilter
    {

        public XmlFilenameFilter()
        {
        }


        @Override
        public boolean accept(
                        final File dir,
                        final String name
                        )
        {
            return name.endsWith( ".xml" );
        }

    }
    //XmlFilenameFilter



    /**
     */
    public static class ZipXmlFilenameFilter
    implements FilenameFilter
    {

        public ZipXmlFilenameFilter()
        {
        }


        @Override
        public boolean accept(
                        final File dir,
                        final String name
                        )
        {
            return (name.endsWith( ".xml.jar" )  ||  name.endsWith( ".xml.zip" ));
        }

    }
    //

}
//
