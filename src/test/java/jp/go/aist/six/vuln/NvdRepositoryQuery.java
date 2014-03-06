package jp.go.aist.six.vuln;

import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.vuln.repository.scap.nvd.CpeItemQueryParams;
import jp.go.aist.six.vuln.repository.scap.nvd.VulnerabilityQueryParams;


public class NvdRepositoryQuery
{

    public static final String[]  NONEXISTENT_VULN_IDS = {
        "SIX-0000-0000",
        "SIX-2014-0000"
    };



    public static final String[]  VULN_ID_PATTERNS = {
        "SIX-*",
        "SIX-2014-*",
        "SIX-2014-000*"
    };

    public static Collection<VulnerabilityQueryParams> vulnIdPatternParams()
    {
        Collection<VulnerabilityQueryParams>  params_list = new ArrayList<VulnerabilityQueryParams>();
        for (String  pattern : VULN_ID_PATTERNS) {
            VulnerabilityQueryParams  params = new VulnerabilityQueryParams();
            params.setCve( pattern );
            params_list.add( params );
        }

        return params_list;
    }



    public static final String[]  CVSS_PATTERNS = {
        "9.7,10.0"
    };

    public static Collection<VulnerabilityQueryParams> cvssPatternParams()
    {
        Collection<VulnerabilityQueryParams>  params_list = new ArrayList<VulnerabilityQueryParams>();
        for (String  pattern : CVSS_PATTERNS) {
            VulnerabilityQueryParams  params = new VulnerabilityQueryParams();
            params.setCvss( pattern );
            params_list.add( params );
        }

        return params_list;
    }


    //===============================================================================
    //  CPE dictionary
    //===============================================================================

    public static final String[]  NONEXISTENT_CPE_NAMES = {
        "cpe:/a:foooooo:barrrrrr:bazzzzzzzzz"
    };



    public static final String[]  CPE_NAME_PATTERNS = {
        "cpe:/a:mozilla:firefox:3.0.*"
    };

    public static Collection<CpeItemQueryParams> CPE_NAME_PATTERN_PARAMS()
    {
        Collection<CpeItemQueryParams>  params_list = new ArrayList<CpeItemQueryParams>();
        for (String  pattern : CPE_NAME_PATTERNS) {
            CpeItemQueryParams  params = new CpeItemQueryParams();
            params.setName( pattern );
            params_list.add( params );
        }

        return params_list;
    }


    public static final String[]  CPE_SERCH_TERMS = {
        "xcode"
    };

    public static Collection<CpeItemQueryParams> CPE_SEARCHTERM_PARAMS()
    {
        Collection<CpeItemQueryParams>  params_list = new ArrayList<CpeItemQueryParams>();
        for (String  term : CPE_SERCH_TERMS) {
            CpeItemQueryParams  params = new CpeItemQueryParams();
            params.setSearchTerms( term );
            params_list.add( params );
        }

        return params_list;
    }

}
//

