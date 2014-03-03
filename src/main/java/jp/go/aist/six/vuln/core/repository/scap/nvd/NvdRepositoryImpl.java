/**
 * SIX VULN - http://code.google.com/p/six-vuln/
 * Copyright (C) 2006
 *   National Institute of Advanced Industrial Science and Technology (AIST)
 *   Registration Number: H20PRO-863
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.go.aist.six.vuln.core.repository.scap.nvd;

import java.util.List;
import jp.go.aist.six.util.repository.Datastore;
import jp.go.aist.six.util.repository.QueryParams;
import jp.go.aist.six.util.repository.QueryResults;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.ItemType;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;



/**
 * An implementation of NvdRepository using Morphia/MongoDB.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: NvdRepositoryImpl.java 606 2013-06-13 06:10:20Z nakamura5akihito@gmail.com $
 * @see <a href="http://nvd.nist.gov/">National Vulnerability Database (NVD)</a>
 */
public class NvdRepositoryImpl
    implements NvdRepository
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ =
//        LoggerFactory.getLogger( MongoOvalDefinitionRepository.class );



    private Datastore  _datastore;



    /**
     * Constructor.
     */
    public NvdRepositoryImpl()
    {
    }



    /**
     *
     */
    public void setDatastore(
                    final Datastore datastore
                    )
    {
        _datastore = datastore;
    }


    protected Datastore _getDatastore()
    {
        return _datastore;
    }



//    /**
//     */
//    protected static <T> QueryResults<T> _buildQueryResults(
//                    final QueryParams params,
//                    final List<T> elements
//                    )
//    {
//        QueryResults<T>  r = _buildQueryResults( elements );
//
//        if (params != null) {
//            String  key = CommonQueryParams.Key.COUNT;
//            if (params.containsKey( key )) {
//                r.setItemsPerPage( (long)r.size() );
//            }
//
//            key = CommonQueryParams.Key.START_INDEX;
//            if (params.containsKey( key )) {
//                int  index = params.getAsInt( key );
//                r.setStartIndex( (long)index );
//            }
//        }
//
//        return r;
//    }
//
//
//    protected static <T> QueryResults<T> _buildQueryResults(
//                    final List<T> elements
//                    )
//    {
//        return new QueryResults<T>( elements );
//    }




    //*********************************************************************
    //  implements NvdRepository
    //*********************************************************************

    //=====================================================================
    // Vulnerability
    //=====================================================================

    public VulnerabilityType findVulnerabilityById(
                    final String id
                    )
    {
        VulnerabilityType  p_object = _getDatastore().findById( VulnerabilityType.class, id );
        return p_object;
    }



    public QueryResults<VulnerabilityType> findVulnerability()
    {
        List<VulnerabilityType>  p_list = _getDatastore().find( VulnerabilityType.class );
        return new QueryResults<VulnerabilityType>( p_list );
    }



    public QueryResults<VulnerabilityType> findVulnerability(
                    final QueryParams params
                    )
    {
        List<VulnerabilityType>  p_list = _getDatastore().find( VulnerabilityType.class, params );
        return new QueryResults<VulnerabilityType>( p_list );
    }



    public QueryResults<String> findVulnerabilityId()
    {
        List<String>  p_list = _getDatastore().findId( VulnerabilityType.class );
        return new QueryResults<String>( p_list );
    }



    public QueryResults<String> findVulnerabilityId(
                    final QueryParams params
                    )
    {
        List<String>  p_list = _getDatastore().findId( VulnerabilityType.class, params );
        return new QueryResults<String>( p_list );
    }



    public long countVulnerability()
    {
        long  count = _getDatastore().count( VulnerabilityType.class );
        return count;
    }



    public long countVulnerability(
                    final QueryParams params
                    )
    {
        long  count = _getDatastore().count( VulnerabilityType.class, params );
        return count;
    }



    public String saveVulnerability(
                    final VulnerabilityType vulnerability
                    )
    {
        String  p_id = _getDatastore().save( VulnerabilityType.class, vulnerability );
        return p_id;
    }



    //=====================================================================
    //  CPE Dictionary
    //=====================================================================

    public ItemType findCpeItemByName(
                    final String name
                    )
    {
        ItemType  p_object = _getDatastore().findById( ItemType.class, name );
        return p_object;
    }



    public QueryResults<ItemType> findCpeItem()
    {
        List<ItemType>  p_list = _getDatastore().find( ItemType.class );
        return new QueryResults<ItemType>( p_list );
    }



    public QueryResults<ItemType> findCpeItem(
                    final QueryParams params
                    )
    {
        List<ItemType>  p_list = _getDatastore().find( ItemType.class, params );
        return new QueryResults<ItemType>( p_list );
    }



    public QueryResults<String> findCpeName()
    {
        List<String>  p_list = _getDatastore().findId( ItemType.class );
        return new QueryResults<String>( p_list );
    }



    public QueryResults<String> findCpeName(
                    final QueryParams params
                    )
    {
        List<String>  p_list = _getDatastore().findId( ItemType.class, params );
        return new QueryResults<String>( p_list );
    }



    public long countCpeItem()
    {
        long  count = _getDatastore().count( ItemType.class );
        return count;
    }



    public long countCpeItem(
                    final QueryParams params
                    )
    {
        long  count = _getDatastore().count( ItemType.class, params );
        return count;
    }



    public String saveCpeItem(
                    final ItemType item
                    )
    {
        String  p_id = _getDatastore().save( ItemType.class, item );
        return p_id;
    }

}
//
