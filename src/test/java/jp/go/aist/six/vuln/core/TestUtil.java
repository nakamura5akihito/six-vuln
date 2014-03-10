package jp.go.aist.six.vuln.core;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import jp.go.aist.six.util.IoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author  Akihito Nakamura, AIST
 * @version $Id: TestUtil.java 594 2013-06-11 09:41:25Z nakamura5akihito@gmail.com $
 */
public class TestUtil
{
  /**
   * Logger.
   */
    private static final Logger  _LOG_ = LoggerFactory.getLogger( TestUtil.class );



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



    ////////////////////////////////////////////////////////////////////////////
    //  prepare source XML files
    ////////////////////////////////////////////////////////////////////////////

    public static Collection<File> prepareSourceXmlFiles(
                    final File dest_dir,
                    final File source_dir
                    )
    throws Exception
    {
        _LOG_.debug( "source dir=" + source_dir + ", dest dir=" + dest_dir );
        Collection<File>  all_xml_files = new ArrayList<File>();

        File[]  xml_file_list = listXmlFiles( source_dir );
        all_xml_files.addAll( Arrays.asList( xml_file_list ) );

        File[]  zip_file_list = TestUtil.listZipXmlFiles( source_dir );
        for (File  zip_file : zip_file_list) {
            Collection<File>  xml_files = unzipXml( dest_dir, zip_file );
            all_xml_files.addAll( xml_files );
        }

        return all_xml_files;
    }


    // e.g. a.xml.zip
    public static Collection<File> unzipXml(
                    final File dest_dir,
                    final File source_zip_file
                    )
                    throws Exception
    {
        _LOG_.debug( "zip file: " + source_zip_file );
        ZipFile  zip_file = new ZipFile( source_zip_file );
        Collection<File>  xml_files = new ArrayList<File>();

        try {
            // (working_dir)/a.xml.zip/
            String  zip_file_name = zip_file.getName();
            File  unzip_dir = new File( dest_dir, zip_file_name );
            unzip_dir.mkdirs();

            Enumeration<? extends ZipEntry>  zip_entries = zip_file.entries();
            while (zip_entries.hasMoreElements()) {
                ZipEntry  zip_entry = zip_entries.nextElement();
                String  zip_entry_name = zip_entry.getName();
                _LOG_.debug( "zip entry: " + zip_entry_name );
                if (zip_entry_name.endsWith( "/" )) {
                    File  unzip_subdir = new File( unzip_dir, zip_entry_name );
                    unzip_subdir.mkdirs();
                    // (working_dir)/a.xml.zip/x/y/z/
                } else if (zip_entry_name.endsWith( ".xml" )) {
                    File  output_file = new File( unzip_dir, zip_entry_name );
                    InputStream  zip_entry_is = zip_file.getInputStream( zip_entry );
                    IoUtil.copy( zip_entry_is, output_file );
                    xml_files.add( output_file );
                    // (working_dir)/a.xml.zip/x/y/z/p.xml
                }
            }
        } finally {
            try {
                zip_file.close();
            } catch (IOException ex) {
                //negligible
            }
        }

        return xml_files;
    }



    ////////////////////////////////////////////////////////////////////////////
    //  tmp working dir
    ////////////////////////////////////////////////////////////////////////////


    private static String  _TMP_DIRPATH_ = null;

    public static File newWokingDir(
                    final String subdir
                    )
    {
        if (_TMP_DIRPATH_ == null) {
            _TMP_DIRPATH_ = System.getProperty( "java.io.tmpdir" );
        }
        File  dir = new File( _TMP_DIRPATH_, subdir );
        dir.mkdirs();

        return dir;
    }

}
//
