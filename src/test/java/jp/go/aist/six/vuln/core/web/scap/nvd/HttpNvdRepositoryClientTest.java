package jp.go.aist.six.vuln.core.web.scap.nvd;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import jp.go.aist.six.vuln.NvdRepositoryQuery;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
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

}
//
