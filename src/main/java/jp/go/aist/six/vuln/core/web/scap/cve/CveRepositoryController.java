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

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.go.aist.six.util.repository.CommonQueryParams;
import jp.go.aist.six.util.repository.ObjectNotFoundException;
import jp.go.aist.six.util.repository.QueryParams;
import jp.go.aist.six.util.repository.QueryResults;
import jp.go.aist.six.util.repository.View;
import jp.go.aist.six.util.xml.XmlMapper;
import jp.go.aist.six.vuln.core.SixVulnContext;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;
import jp.go.aist.six.vuln.repository.scap.cve.CveItemQueryParams;
import jp.go.aist.six.vuln.repository.scap.cve.CveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;



/**
 * A REST-style Web service controller for the CVE repository.
 * The implementation uses the Spring MVC.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: CveRepositoryController.java 608 2013-06-13 07:37:39Z nakamura5akihito@gmail.com $
 */
@Controller
public class CveRepositoryController
{

    /**
     * Logger.
     */
    private static final Logger  _LOG_ = LoggerFactory.getLogger( CveRepositoryController.class );


    // " " space = %20
    // "&" ampa  = %26
    // "*" astah = %2a
    // "." dot   = %2e
    // "/" slash = %2f



    @Autowired
    private CveRepository  _cve_repository;

    private XmlMapper  _xml_mapper;



    /**
     * Constructor.
     */
    public CveRepositoryController()
    {
        _xml_mapper = SixVulnContext.Nvd.webServer().getXmlMapper();
    }



    /**
     */
    public void setCveRepository(
                    final CveRepository service
                    )
    {
        _cve_repository = service;
    }


    protected CveRepository _getCveRepository()
    {
        return _cve_repository;
    }



    /**
     */
    public void setCveXmlmapper(
                    final XmlMapper xml_mapper
                    )
    {
        _xml_mapper = xml_mapper;
    }


    protected XmlMapper _getCveXmlMapper()
    {
        return _xml_mapper;
    }




    /**
     */
    private View _removeView(
                    final QueryParams params
                    )
    {
        String  view_key = CommonQueryParams.Key.VIEW;

        String  view_value = (params == null ? null : params.get( view_key ));
        View  view = (view_value == null ? View.complete : View.valueOf( view_value ));
        _LOG_.debug( "query params view=" + view.name() );
        params.remove( view_key );

        return view;
    }



    //////////////////////////////////////////////////////////////////////
    //  REST support methods
    //////////////////////////////////////////////////////////////////////

    /**
     * Builds a location URI from the specified request URL
     * and the created object ID.
     */
    protected URI _buildResourceLocation(
                    final HttpServletRequest request,
                    final String id
                    )
    {
        String  requestUrl = request.getRequestURL().toString();
        URI  uri = new UriTemplate( "{requestUrl}/{id}" ).expand( requestUrl, id );
//        _LOG_.debug( "Location: " + uri.toASCIIString() );

        return uri;
    }



    //////////////////////////////////////////////////////////////////////
    // Exception Handlers, HTTP Status Code
    //////////////////////////////////////////////////////////////////////

    /**
     * Responds to the HTTP request with exception.
     * NOTE: Currently, only application/xml content type is supported.
     */
    private void _responseWithException(
                    final Exception ex,
                    final HttpStatus http_status,
                    final HttpServletResponse response
                    )
    {
        _LOG_.error( "response with exception: " + ex.getClass().getName()
                        + " - " + ex.getMessage() );

        response.setContentType( MediaType.APPLICATION_XML.toString() );
        response.setStatus( http_status.value() );

        Writer  writer = null;
        try {
            writer = response.getWriter();
            _getCveXmlMapper().marshal( ex, writer );
        } catch (IOException io_ex) {
            _LOG_.error( "ERROR in exception handling: " + io_ex );
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (Exception ig_ex) {
                    _LOG_.warn( "ERROR in exception handling: " + ig_ex );
                }
            }
        }
    }



    // 404: Not Found
    @ExceptionHandler( ObjectNotFoundException.class )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    public void handleObjectNotFoundException(
                    final ObjectNotFoundException ex,
                    final HttpServletResponse response
                    )
    {
        _responseWithException( ex, HttpStatus.NOT_FOUND, response );
    }


    // 500: Internal Server Error
    @ExceptionHandler( Exception.class )
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    public void handleDefaultException(
                    final Exception ex,
                    final HttpServletResponse response
                    )
    {
        _responseWithException( ex, HttpStatus.INTERNAL_SERVER_ERROR, response );
    }



    ///////////////////////////////////////////////////////////////////////
    //  REST WS API
    ///////////////////////////////////////////////////////////////////////

    //=====================================================================
    // CVE
    //=====================================================================

    // GET: fetch one by ID
    //
    // EXAMPLES:
    // curl -v -X GET -HAccept:application/xml "http://localhost:8080/six-vuln/cve/CVE-2012-0001"
    @RequestMapping(
                    method=RequestMethod.GET
                    ,value="/cve/{name:.*}"
                    ,headers="Accept=application/xml"
    )
    public @ResponseBody ItemType findCveItemByName(
                    @PathVariable final String name
                    )
    {
        return _getCveRepository().findCveItemByName( name );
    }



    // GET: query
    // test: curl -v -X GET -HAccept:application/xml "http://localhost:8080/six-vuln/cve?desc=XSS"
    @RequestMapping(
                    method=RequestMethod.GET
                    ,value="/cve"
                    ,headers="Accept=application/xml"
    )
    public @ResponseBody QueryResults<?> findCveItem(
                    final CveItemQueryParams params
                    )
    {
        _LOG_.debug( "query params=" + String.valueOf( params ) );
        View  view = _removeView( params );

        QueryResults<?>  results = null;
        if (view == View.id) {
            results = _getCveRepository().findCveName( params );
        } else if (view == View.count) {
            long  count = _getCveRepository().countCveItem( params );
            results = new QueryResults<Void>();
            results.setTotalResults( count );
        } else {
            results = _getCveRepository().findCveItem( params );
        }

        return results;
    }

}
//

