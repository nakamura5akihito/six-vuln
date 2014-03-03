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
package jp.go.aist.six.vuln.core.web.scap.cve;

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
import jp.go.aist.six.vuln.model.scap.cpe.CpeName;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;
import jp.go.aist.six.vuln.repository.scap.cve.CveRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;



/**
 * A REST-style Web service client stub for the CVE repository.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: HttpCveRepositoryClient.java 609 2013-06-13 07:49:54Z nakamura5akihito@gmail.com $
 */
public class HttpCveRepositoryClient
    implements CveRepository
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ = LoggerFactory.getLogger( HttpCveRepositoryClient.class );


    private static final String  _PATH_DELIM_  = "/";
    private static final String  _PATH_CVE_  = "/cve";



    private String  _baseUrl;

    private HttpClient  _client;



    /**
     * Constructor.
     */
    public HttpCveRepositoryClient()
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



    /**
     */
    private String  _cveUrl;

    private String _getCveUrl()
    {
        if (_cveUrl == null) {
            _cveUrl = getBaseUrl() + _PATH_CVE_;
        }

        return _cveUrl;
    }


    private String _getCveQueryUrl(
                    final QueryParams params
                    )
    {
        return _getCveUrl() + UrlUtil.toString( params );
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
    //  implements CveRepository
    //*********************************************************************

    //=====================================================================
    //  CPE Dictionary
    //=====================================================================

    public ItemType findCveItemByName(
                    final String name
                    )
    {
        ItemType  p_object = _getObject(
                        _getCveUrl() + _PATH_DELIM_ + name,
                        ItemType.class );
        return p_object;
    }



    public QueryResults<ItemType> findCveItem()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<ItemType>  query_results = _getObject(
                        _getCveUrl(),
                        QueryResults.class );

        return query_results;
    }



    public QueryResults<ItemType> findCveItem(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<ItemType>  query_results = _getObject(
                        _getCveQueryUrl( params ),
                        QueryResults.class );

        return query_results;
    }



    public QueryResults<String> findCveName()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<CpeName>  query_results = _getObject(
                        _getCveQueryUrl( _buildIdQueryParams( null ) ),
                        QueryResults.class );

        return _toStringIdResults( query_results );
    }



    public QueryResults<String> findCveName(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<CpeName>  query_results = _getObject(
                        _getCveQueryUrl( _buildIdQueryParams( params ) ),
                        QueryResults.class );

        return _toStringIdResults( query_results );
    }



    public long countCveItem()
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<Void>  query_results = _getObject(
                        _getCveQueryUrl( _buildCountQueryParams( null ) ),
                        QueryResults.class );

        return query_results.getTotalResults();
    }



    public long countCveItem(
                    final QueryParams params
                    )
    {
        @SuppressWarnings( "unchecked" )
        QueryResults<Void>  query_results = _getObject(
                        _getCveQueryUrl( _buildCountQueryParams( params ) ),
                        QueryResults.class );

        return query_results.getTotalResults();
    }



    public String saveCveItem(
                    final ItemType item
                    )
    {
        String  url = _getCveUrl();
        String  location = _postObject( url, item, ItemType.class );

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
