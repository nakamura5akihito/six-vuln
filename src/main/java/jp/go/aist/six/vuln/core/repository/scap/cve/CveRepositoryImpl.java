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
package jp.go.aist.six.vuln.core.repository.scap.cve;

import java.util.List;
import jp.go.aist.six.util.repository.Datastore;
import jp.go.aist.six.util.repository.QueryParams;
import jp.go.aist.six.util.repository.QueryResults;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;
import jp.go.aist.six.vuln.repository.scap.cve.CveRepository;



/**
 * An implementation of CveRepository using Morphia/MongoDB.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: CveRepositoryImpl.java 606 2013-06-13 06:10:20Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class CveRepositoryImpl
    implements CveRepository
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ = LoggerFactory.getLogger( CveRepositoryImpl.class );



    private Datastore  _datastore;



    /**
     * Constructor.
     */
    public CveRepositoryImpl()
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




    //*********************************************************************
    //  implements NvdRepository
    //*********************************************************************

    //=====================================================================
    //  CPE Dictionary
    //=====================================================================

    public ItemType findCveItemByName(
                    final String name
                    )
    {
        ItemType  p_object = _getDatastore().findById( ItemType.class, name );
        return p_object;
    }



    public QueryResults<ItemType> findCveItem()
    {
        List<ItemType>  p_list = _getDatastore().find( ItemType.class );
        return new QueryResults<ItemType>( p_list );
    }



    public QueryResults<ItemType> findCveItem(
                    final QueryParams params
                    )
    {
        List<ItemType>  p_list = _getDatastore().find( ItemType.class, params );
        return new QueryResults<ItemType>( p_list );
    }



    public QueryResults<String> findCveName()
    {
        List<String>  p_list = _getDatastore().findId( ItemType.class );
        return new QueryResults<String>( p_list );
    }



    public QueryResults<String> findCveName(
                    final QueryParams params
                    )
    {
        List<String>  p_list = _getDatastore().findId( ItemType.class, params );
        return new QueryResults<String>( p_list );
    }



    public long countCveItem()
    {
        long  count = _getDatastore().count( ItemType.class );
        return count;
    }



    public long countCveItem(
                    final QueryParams params
                    )
    {
        long  count = _getDatastore().count( ItemType.class, params );
        return count;
    }



    public String saveCveItem(
                    final ItemType item
                    )
    {
        String  p_id = _getDatastore().save( ItemType.class, item );
        return p_id;
    }

}
//
