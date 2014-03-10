package jp.go.aist.six.vuln.core;

import java.io.File;
import jp.go.aist.six.vuln.core.repository.scap.nvd.NvdDataFeedInstaller;



/**
 */
public class NvdRepositoryTestDataInstaller
{

    public static final NvdRepositoryTestDataInstaller  INSTANCE = new NvdRepositoryTestDataInstaller();


    public static void installNvd()
    throws Exception
    {
        INSTANCE._installNvd();
    }




    public static final String[]  NVD_SOURCE_XML_DIRS = new String[] {
        "src/test/resources/nvd/xml/six"
    };


    private boolean  _nvdInstalled = false;



    private NvdRepositoryTestDataInstaller()
    {
    }



    private synchronized void _installNvd()
                    throws Exception
    {
        if (_nvdInstalled) {
            return;
        }

        NvdDataFeedInstaller  installer = new NvdDataFeedInstaller();
        for (String  dir_path : NVD_SOURCE_XML_DIRS) {
            File[]  xml_file_list = TestUtil.listXmlFiles( dir_path );
            for (File  xml_file : xml_file_list) {
                installer.execute( new String[] { xml_file.getCanonicalPath() } );
            }
        }

        _nvdInstalled = true;
    }

}
//
