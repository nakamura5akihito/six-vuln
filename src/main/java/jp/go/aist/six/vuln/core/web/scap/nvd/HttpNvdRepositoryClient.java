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
package jp.go.aist.six.vuln.core.web.scap.nvd;

import java.util.ArrayList;
import java.util.List;
import jp.go.aist.six.util.repository.CommonQueryParams;
import jp.go.aist.six.util.repository.ObjectNotFoundException;
import jp.go.aist.six.util.repository.QueryParams;
import jp.go.aist.six.util.repository.QueryResults;
import jp.go.aist.six.util.repository.RepositoryException;
import jp.go.aist.six.util.repository.View;
import jp.go.aist.six.util.web.HttpClient;
import jp.go.aist.six.util.web.HttpException;
import jp.go.aist.six.util.web.UrlUtil;
import jp.go.aist.six.vuln.model.scap.CveId;
import jp.go.aist.six.vuln.model.scap.cpe.CpeName;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.ItemType;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;



/**
 * A REST-style Web service client stub for the NVD repository.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: HttpNvdRepositoryClient.java 606 2013-06-13 06:10:20Z nakamura5akihito@gmail.com $
 */
public class HttpNvdRepositoryClient
    implements NvdRepository
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ = LoggerFactory.getLogger( HttpNvdRepositoryClient.class );


    private static final String  _PATH_DELIM_  = "/";
    private static final String  _PATH_NVD_V_  = "/nvd/v";
    private static final String  _PATH_NVD_CPE_  = "/nvd/cpe";



    private String  _baseUrl;

    private HttpClient  _client;



    /**
     * Constructor.
     */
    public HttpNvdRepositoryClient()
    {
    }



    /**
     *
     */
    public void setHttpClient(
                    final HttpClient client
                    )
    {
        _client = client;
    }


    public HttpClient _getHttpClient()
    {
        return _client;
    }



    /**
     */
    public void setBaseUrl(
                    final String url
                    )
    {
        String  slash = "/";
        _baseUrl = url;
        if (_baseUrl.endsWith( slash )) {
            _baseUrl = url.substring( 0, url.length() - 1 );
        }
    }


    public String getBaseUrl()
    {
        if (_baseUrl == null) {
            throw new HttpException( "base URL not configured" );
        }

        return _baseUrl;
    }



    private String  _vulnerabilityUrl;

    private String _getVulnerabilityUrl()
    {
        if (_vulnerabilityUrl == null) {
            _vulnerabilityUrl = getBaseUrl() + _PATH_NVD_V_;
        }

        return _vulnerabilityUrl;
    }


    private String _getVulnerabilityQueryUrl(
                    final QueryParams params
                    )
    {
        return _getVulnerabilityUrl() + UrlUtil.toString( params );
    }



    /**
     */
    private String  _cpeUrl;

    private String _getCpeUrl()
    {
        if (_cpeUrl == null) {
            _cpeUrl = getBaseUrl() + _PATH_NVD_CPE_;
        }

        return _cpeUrl;
    }


    private String _getCpeQueryUrl(
                    final QueryParams params
                    )
    {
        return _getCpeUrl() + UrlUtil.toString( params );
    }



    /**
     */
    private QueryParams _buildIdQueryParams(
                    final QueryParams  params
                    )
    {
        QueryParams  b_params = params;
        if (b_params == null) {
            b_params = new QueryParams();
        }

        b_params.set( CommonQueryParams.Key.VIEW, View.id.name() );

        return b_params;
    }



    private QueryParams _buildCountQueryParams(
                    final QueryParams  params
                    )
    {
        QueryParams  b_params = params;
        if (b_params == null) {
            b_params = new QueryParams();
        }

        b_params.set( CommonQueryParams.Key.VIEW, View.count.name() );

        return b_params;
    }




    /**
     */
    private QueryResults<String> _toStringIdResults(
                    final QueryResults<? extends Object>  id_results
                    )
    {
        List<String>  id_list = new ArrayList<String>();
        for (Object  cve_id : id_results.getElements()) {
            id_list.add( cve_id.toString() );
        }

        QueryResults<String>  query_results = new QueryResults<String>( id_list );
        query_results.setTimestamp( id_results.getTimestamp() );
        query_results.setTotalResults( id_results.getTotalResults() );
        query_results.setItemsPerPage( id_results.getItemsPerPage() );

        return query_results;
    }




    private <T> T _getObject(
                    final String url,
                    final Class<T> response_type,
                    final Object... uri_variables
                    )
    {
        T  object = null;
        try {
            object = _getHttpClient().getObject( url, response_type, uri_variables );
        } catch (RuntimeException ex) {
            Throwable  cause = ex.getCause();
            if (cause == null) {
                throw ex;
            } else {
                if (HttpStatusCodeException.class.isInstance( cause )) {
                    HttpStatusCodeException  sc_ex = (HttpStatusCodeException)cause;
                    HttpStatus  status = sc_ex.getStatusCode();
                    if (status == HttpStatus.NOT_FOUND) {
                        throw new ObjectNotFoundException( cause );
                    } else {
                        throw new RepositoryException( cause );
                    }
                } else {
                    throw ex;
                }
            }
        }

        return object;
    }



    private <T> String _postObject(
                    final String url,
                    final T object,
                    final Class<T> type
                    )
    {
        String  location = null;
        try {
            location = _getHttpClient().postObject( url, object, type );
        } catch (RuntimeException ex) {
            Throwable  cause = ex.getCause();
            if (cause == null) {
                throw ex;
            } else {
                if (HttpStatusCodeException.class.isInstance( cause )) {
                    throw new RepositoryException( cause );
                } else {
                    throw ex;
                }
            }
        }

        return location;
    }



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
        return _getObject(
                        _getVulnerabilityUrl() + _PATH_DELIM_ + id,
                        VulnerabilityType.class );

//        VulnerabilityType  p_object = _getHttpClient().getObject(
//                        _getVulnerabilityUrl() + _PATH_DELIM_ + id,
//                        VulnerabilityType.class );
//        return p_object;
    }



    public QueryResults<VulnerabilityType> findVulnerability()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<VulnerabilityType>  query_results = _getObject(
                        _getVulnerabilityUrl(),
                        QueryResults.class );
//        QueryResults<VulnerabilityType>  query_results = _getHttpClient().getObject(
//                        _getVulnerabilityUrl(),
//                        QueryResults.class );

        return query_results;
    }



    public QueryResults<VulnerabilityType> findVulnerability(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<VulnerabilityType>  query_results = _getObject(
                        _getVulnerabilityQueryUrl( params ),
                        QueryResults.class );
//        QueryResults<VulnerabilityType>  query_results = _getHttpClient().getObject(
//                        _getVulnerabilityQueryUrl( params ),
//                        QueryResults.class );

        return query_results;
    }



    public QueryResults<String> findVulnerabilityId()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<CveId>  query_results = _getObject(
                        _getVulnerabilityQueryUrl( _buildIdQueryParams( null ) ),
                        QueryResults.class );
//        QueryResults<CveId>  query_results = _getHttpClient().getObject(
//                        _getVulnerabilityQueryUrl( _buildIdQueryParams( null ) ),
//                        QueryResults.class );

        return _toStringIdResults( query_results );
    }



    public QueryResults<String> findVulnerabilityId(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<CveId>  query_results = _getObject(
                        _getVulnerabilityQueryUrl( _buildIdQueryParams( params ) ),
                        QueryResults.class );
//        QueryResults<CveId>  query_results = _getHttpClient().getObject(
//                        _getVulnerabilityQueryUrl( _buildIdQueryParams( params ) ),
//                        QueryResults.class );

        return _toStringIdResults( query_results );
    }



    public long countVulnerability()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<Void>  query_results = _getObject(
                        _getVulnerabilityQueryUrl( _buildCountQueryParams( null ) ),
                        QueryResults.class );
//        QueryResults<Void>  query_results = _getHttpClient().getObject(
//                        _getVulnerabilityQueryUrl( _buildCountQueryParams( null ) ),
//                        QueryResults.class );

        return query_results.getTotalResults();
    }



    public long countVulnerability(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<Void>  query_results = _getObject(
                        _getVulnerabilityQueryUrl( _buildCountQueryParams( params ) ),
                        QueryResults.class );
//        QueryResults<Void>  query_results = _getHttpClient().getObject(
//                        _getVulnerabilityQueryUrl( _buildCountQueryParams( params ) ),
//                        QueryResults.class );

        return query_results.getTotalResults();
    }



    public String saveVulnerability(
                    final VulnerabilityType vulnerability
                    )
    {
        String  url = _getVulnerabilityUrl();
        String  location = _postObject( url, vulnerability, VulnerabilityType.class );
//      String  location = _getHttpClient().postObject( url, vulnerability, VulnerabilityType.class );

        String  id = null;
        if (location.startsWith( url )) {
            id = url.substring( url.length() + 1 );
        } else {
            throw new HttpException( "unexpected location: " + url );
        }

        return id;
    }



    //=====================================================================
    //  CPE Dictionary
    //=====================================================================

    private String _toUriCpeName(
                    final String name
                    )
    {
        if (name == null) {
            return null;
        }

        if (name.startsWith( CpeName.PREFIX )) {
            return name.substring( CpeName.PREFIX.length() );
        }

        return name;
    }


    public ItemType findCpeItemByName(
                    final String name
                    )
    {
        ItemType  p_object = _getObject(
                        _getCpeUrl() + _PATH_DELIM_ + _toUriCpeName( name ),
                        ItemType.class );
//        ItemType  p_object = _getHttpClient().getObject(
//                        _getCpeUrl() + _PATH_DELIM_ + _toUriCpeName( name ),
//                        ItemType.class );
        return p_object;
    }



    public QueryResults<ItemType> findCpeItem()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<ItemType>  query_results = _getObject(
                        _getCpeUrl(),
                        QueryResults.class );
//        QueryResults<ItemType>  query_results = _getHttpClient().getObject(
//                        _getCpeUrl(),
//                        QueryResults.class );

        return query_results;
    }



    public QueryResults<ItemType> findCpeItem(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<ItemType>  query_results = _getObject(
                        _getCpeQueryUrl( params ),
                        QueryResults.class );
//        QueryResults<ItemType>  query_results = _getHttpClient().getObject(
//                        _getCpeQueryUrl( params ),
//                        QueryResults.class );

        return query_results;
    }



    public QueryResults<String> findCpeName()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<CpeName>  query_results = _getObject(
                        _getCpeQueryUrl( _buildIdQueryParams( null ) ),
                        QueryResults.class );
//        QueryResults<CpeName>  query_results = _getHttpClient().getObject(
//                        _getCpeQueryUrl( _buildIdQueryParams( null ) ),
//                        QueryResults.class );

        return _toStringIdResults( query_results );
    }



    public QueryResults<String> findCpeName(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<CpeName>  query_results = _getObject(
                        _getCpeQueryUrl( _buildIdQueryParams( params ) ),
                        QueryResults.class );
//        QueryResults<CpeName>  query_results = _getHttpClient().getObject(
//                        _getCpeQueryUrl( _buildIdQueryParams( params ) ),
//                        QueryResults.class );

        return _toStringIdResults( query_results );
    }



    public long countCpeItem()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<Void>  query_results = _getObject(
                        _getCpeQueryUrl( _buildCountQueryParams( null ) ),
                        QueryResults.class );
//        QueryResults<Void>  query_results = _getHttpClient().getObject(
//                        _getCpeQueryUrl( _buildCountQueryParams( null ) ),
//                        QueryResults.class );

        return query_results.getTotalResults();
    }



    public long countCpeItem(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<Void>  query_results = _getObject(
                        _getCpeQueryUrl( _buildCountQueryParams( params ) ),
                        QueryResults.class );
//        QueryResults<Void>  query_results = _getHttpClient().getObject(
//                        _getCpeQueryUrl( _buildCountQueryParams( params ) ),
//                        QueryResults.class );

        return query_results.getTotalResults();
    }



    public String saveCpeItem(
                    final ItemType item
                    )
    {
        String  url = _getCpeUrl();
        String  location = _postObject( url, item, ItemType.class );
//      String  location = _getHttpClient().postObject( url, item, ItemType.class );

        String  id = null;
        if (location.startsWith( url )) {
            id = url.substring( url.length() + 1 );
        } else {
            throw new HttpException( "unexpected location: " + url );
        }

        return id;
    }

}
//
