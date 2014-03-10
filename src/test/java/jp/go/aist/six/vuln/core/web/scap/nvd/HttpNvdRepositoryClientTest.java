package jp.go.aist.six.vuln.core.web.scap.nvd;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import jp.go.aist.six.util.repository.ObjectNotFoundException;
import jp.go.aist.six.util.repository.QueryResults;
import jp.go.aist.six.vuln.NvdRepositoryQuery;
import jp.go.aist.six.vuln.NvdRepositoryQuery.QueryParamsAndCount;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.core.repository.scap.nvd.NvdRepositoryImplTest;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;
import org.junit.Rule;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;



/**
 * NOTE: This test works on the same host on which the database server is running.
 *
 * @author Aki Nakamura (nakamura5akihito@gmail.com)
 * @version 2014/03/10
 */
@RunWith( Enclosed.class )
public class HttpNvdRepositoryClientTest
{

    private final NvdRepository  _repository;




    public HttpNvdRepositoryClientTest()
    {
        _repository = SixVulnContext.Nvd.webClient().getRepository();
        assertThat( "NOT null", _repository, notNullValue() );
    }



    protected NvdRepository _getRepository()
    {
        return _repository;
    }



    /**
     */
    @RunWith( Theories.class )
    public static class WsVulnId
    extends HttpNvdRepositoryClientTest
    {
        @DataPoints
        public static String[] vulnIds()
        {
            return NvdRepositoryQuery.EXISTENT_VULN_IDS;
        }


        @Theory
        public void testFindVulnerabilityById(
                        final String id
                        )
        {
            VulnerabilityType  obj = _getRepository().findVulnerabilityById( id );
            assertThat( obj.getId(), is( id ) );

        }

    }
    //


    /**
     */
    @RunWith( Theories.class )
    public static class WsNonexistentVulnId
    extends NvdRepositoryImplTest
    {
        @DataPoints
        public static String[] vulnIds()
        {
            return NvdRepositoryQuery.NONEXISTENT_VULN_IDS;
        }


        @Rule
        public ExpectedException  expectedEx = ExpectedException.none();


        @Theory
        public void testFindVulnerabilityById(
                        final String id
                        )
        {
            expectedEx.expect( ObjectNotFoundException.class );
            _getRepository().findVulnerabilityById( id );
        }

    }
    //



    /**
     */
    @RunWith( Theories.class )
    public static class WsVulnIdPattern
    extends NvdRepositoryImplTest
    {
        @DataPoints
        public static QueryParamsAndCount[] vulnIdPatternsAndCounts()
        {
            return NvdRepositoryQuery.vulnIdPatternParamsAndCount().toArray( new QueryParamsAndCount[0] );
        }


        @Theory
        public void testFindVulnerability(
                        final QueryParamsAndCount params_and_count
                        )
        {
            System.out.println( "query: " + params_and_count.params );

            QueryResults<VulnerabilityType>  results = _getRepository().findVulnerability( params_and_count.params );
            System.out.println( "  result: #entries=" + results.size() );
            assertThat( results.size(), is( params_and_count.count ) );
            for (VulnerabilityType  v : results.getElements()) {
                System.out.println( "    vulnerability: id=" + v.getId() + ", cve=" + v.getCveId() );
//                assertThat( v.getCveId().substring( 0, 12 ), is( "CVE-2013-000" ) );
            }
        }

    }
    //



    /**
     */
    @RunWith( Theories.class )
    public static class WsCvssScore
    extends NvdRepositoryImplTest
    {
        @DataPoints
        public static QueryParamsAndCount[] vulnIdPatternsAndCounts()
        {
            return NvdRepositoryQuery.cvssParamsAndCount().toArray( new QueryParamsAndCount[0] );
        }


        @Theory
        public void testFindVulnerability(
                        final QueryParamsAndCount params_and_count
                        )
        {
            System.out.println( "query: " + params_and_count.params );

            QueryResults<VulnerabilityType>  results = _getRepository().findVulnerability( params_and_count.params );
            System.out.println( "  result: #entries=" + results.size() );
            assertThat( results.size(), is( params_and_count.count ) );
            for (VulnerabilityType  v : results.getElements()) {
                System.out.println( "    vulnerability: id=" + v.getId() + ", cvss=" + v.getCvss().getBaseMetrics().getScore() );
            }
        }

    }
    //

}
//
