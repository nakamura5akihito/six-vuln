package jp.go.aist.six.vuln.core.repository.scap.nvd;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import jp.go.aist.six.util.repository.ObjectNotFoundException;
import jp.go.aist.six.vuln.NvdRepositoryQuery;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;
import org.junit.Rule;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;



/**
 *
 *
 * @author Aki Nakamura (nakamura5akihito@gmail.com)
 * @version 2014/03/06
 */
@RunWith( Enclosed.class )
public class NvdRepositoryImplTest
{

//    private XmlMapper  _xml_mapper;
    private final NvdRepository   _repository;



    public NvdRepositoryImplTest()
    {
//        _xml_mapper = SixVulnContext.Nvd.repository().getXmlMapper();
//        assertThat( "NOT null", _xml_mapper, notNullValue() );

        _repository = SixVulnContext.Nvd.repository().getRepository();
        assertThat( "NOT null", _repository, notNullValue() );
    }



    protected NvdRepository _getRepository()
    {
        return _repository;
    }



    /**
     */
    @RunWith( Theories.class )
    public static class NonexistentId
    extends NvdRepositoryImplTest
    {
        public NonexistentId()
        {
        }


        @DataPoints
        public static String[] nonexistentCveIds()
        {
            return NvdRepositoryQuery.NONEXISTENT_CVE_IDS;
        }


        @Rule
        public ExpectedException  expectedEx = ExpectedException.none();


        @Theory
        public void testFindVulnerabilityByNonexistentId(
                        final String id
                        )
        {
            expectedEx.expect( ObjectNotFoundException.class );
            _getRepository().findVulnerabilityById( id );
        }

    }
    //

}
//

