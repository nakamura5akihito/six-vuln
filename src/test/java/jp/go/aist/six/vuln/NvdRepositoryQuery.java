package jp.go.aist.six.vuln;

import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.util.repository.QueryParams;
import jp.go.aist.six.vuln.repository.scap.nvd.CpeItemQueryParams;
import jp.go.aist.six.vuln.repository.scap.nvd.VulnerabilityQueryParams;


public class NvdRepositoryQuery
{

//  Test data: 20140303_nvdcve-2.0-2014_six.xml
//
//  Vuln ID       CVSS
//  SIX-2014-0001 7.5
//  SIX-2014-0006 4.3
//  SIX-2014-0008 4.0
//  SIX-2014-0009 5.5
//  SIX-2014-0010 6.8
//  SIX-2014-0015 4.0
//  SIX-2014-0018 1.9
//  SIX-2014-0019 1.9
//  SIX-2014-0020 5.0
//  SIX-2014-0022 5.0


    public static final String[]  NONEXISTENT_VULN_IDS = {
        "SIX-0000-0000",
        "SIX-2014-0000"
    };



    public static final class PatternAndCount
    {
        public final String  pattern;
        public final int  count;

        public PatternAndCount( final String pattern, final int count )
        {
            this.pattern = pattern;
            this.count = count;
        }
    }


    public static final class QueryParamsAndCount
    {
        public final QueryParams  params;
        public final int  count;

        public QueryParamsAndCount(
                        final QueryParams params,
                        final int count
                        )
        {
            this.params = params;
            this.count = count;
        }
    }




    public static final PatternAndCount[]  VULN_ID_PATTERNS_AND_COUNTS = {
        new PatternAndCount( "SIX-*",         10 ),
        new PatternAndCount( "SIX-2014-*",    10 ),
        new PatternAndCount( "SIX-2014-000*",  4 )
    };

//    public static Collection<VulnerabilityQueryParams> vulnIdPatternParams()
//    {
//        Collection<VulnerabilityQueryParams>  params_list = new ArrayList<VulnerabilityQueryParams>();
//        for (String  pattern : VULN_ID_PATTERNS) {
//            VulnerabilityQueryParams  params = new VulnerabilityQueryParams();
//            params.setCve( pattern );
//            params_list.add( params );
//        }
//
//        return params_list;
//    }



    // CVSS patterns AND VULN ID like "SIX-*"
    public static final PatternAndCount[]  CVSS_PATTERNS_AND_COUNTS = {
        new PatternAndCount( "7.0,10.0", 1 ),
        new PatternAndCount( "3.0,7.0",  7 ),
        new PatternAndCount( ",3.0",     2 ),
    };


    public static Collection<QueryParamsAndCount> cvssParamsAndCount()
    {
        Collection<QueryParamsAndCount>  params_list = new ArrayList<QueryParamsAndCount>();
        for (PatternAndCount  pattern_and_count : CVSS_PATTERNS_AND_COUNTS) {
            VulnerabilityQueryParams  params = new VulnerabilityQueryParams();
            params.setCvss( pattern_and_count.pattern );
            params.setId( "SIX-*" );

            params_list.add( new QueryParamsAndCount( params, pattern_and_count.count ) );
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

