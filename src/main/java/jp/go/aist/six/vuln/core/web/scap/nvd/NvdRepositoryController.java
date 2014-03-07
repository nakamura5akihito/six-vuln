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
import jp.go.aist.six.vuln.model.scap.cpe.CpeName;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.ItemType;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import jp.go.aist.six.vuln.repository.scap.nvd.CpeItemQueryParams;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;
import jp.go.aist.six.vuln.repository.scap.nvd.VulnerabilityQueryParams;
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
 * A REST-style Web service controller for the NVD repository.
 * The implementation uses the Spring MVC.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: NvdRepositoryController.java 606 2013-06-13 06:10:20Z nakamura5akihito@gmail.com $
 */
@Controller
public class NvdRepositoryController
{

    /**
     * Logger.
     */
    private static final Logger  _LOG_ = LoggerFactory.getLogger( NvdRepositoryController.class );


    // " " space = %20
    // "&" ampa  = %26
    // "*" astah = %2a
    // "." dot   = %2e
    // "/" slash = %2f



    @Autowired
    private NvdRepository  _nvd_repository;

    private XmlMapper  _xml_mapper;



    /**
     * Constructor.
     */
    public NvdRepositoryController()
    {
        _xml_mapper = SixVulnContext.Nvd.webServer().getXmlMapper();
    }



    /**
     */
    public void setNvdRepository(
                    final NvdRepository service
                    )
    {
        _nvd_repository = service;
    }


    protected NvdRepository _getNvdRepository()
    {
        return _nvd_repository;
    }



    /**
     */
    public void setNvdXmlMapper(
                    final XmlMapper xml_mapper
                    )
    {
        _xml_mapper = xml_mapper;
    }


    protected XmlMapper _getNvdXmlMapper()
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
            _getNvdXmlMapper().marshal( ex, writer );
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
    // Vulnerability
    //=====================================================================

    // GET: fetch one by ID
    //
    // EXAMPLES:
    // curl -v -X GET -HAccept:application/xml "http://localhost:8080/six-vuln/nvd/v/CVE-2010-0176"
    @RequestMapping(
                    method=RequestMethod.GET
                    ,value="/nvd/v/{id:.*}"
                    ,headers="Accept=application/xml"
    )
    public @ResponseBody VulnerabilityType findVulnerabilityById(
                    @PathVariable final String id
                    )
    {
        return _getNvdRepository().findVulnerabilityById( id );
    }



    // GET: query
    // test: curl -v -X GET -HAccept:application/xml "http://localhost:8080/six-vuln/nvd/v?cve=CVE-2013-0001"
    @RequestMapping(
                    method=RequestMethod.GET
                    ,value="/nvd/v"
                    ,headers="Accept=application/xml"
    )
    public @ResponseBody QueryResults<?> findVulnerability(
                    final VulnerabilityQueryParams params
                    )
    {
        _LOG_.debug( "query params=" + String.valueOf( params ) );
        View  view = _removeView( params );

//        String  view_value = (params == null ? null : params.get( CommonQueryParams.Key.VIEW ));
//        View  view = (view_value == null ? View.complete : View.valueOf( view_value ));
//        _LOG_.debug( "query view=" + String.valueOf( view ) );
//        params.remove( CommonQueryParams.Key.VIEW );

        QueryResults<?>  results = null;
        if (view == View.id) {
            results = _getNvdRepository().findVulnerabilityId( params );
        } else if (view == View.count) {
            long  count = _getNvdRepository().countVulnerability( params );
            results = new QueryResults<Void>();
            results.setTotalResults( count );
        } else {
            results = _getNvdRepository().findVulnerability( params );
        }

        return results;
    }



    //=====================================================================
    // CPE
    //=====================================================================

    private String _toFormalCpeName(
                    final String name
                    )
    {
        if (name == null) {
            return null;
        }

        if (name.startsWith( CpeName.PREFIX )) {
            return name;
        }

        return CpeName.PREFIX + name;
    }



    // GET: fetch one by ID
    //
    // EXAMPLES:
    // curl -v -X GET -HAccept:application/xml "http://localhost:8080/six-vuln/nvd/cpe/cpe:/a:mozilla:firefox:3.0.5"
    @RequestMapping(
                    method=RequestMethod.GET
                    ,value="/nvd/cpe/{name:.*}"
                    ,headers="Accept=application/xml"
    )
    public @ResponseBody ItemType findCpeItemByName(
                    @PathVariable final String name
                    )
    {
        return _getNvdRepository().findCpeItemByName( _toFormalCpeName( name ) );
    }



    // GET: query
    // test: curl -v -X GET -HAccept:application/xml "http://localhost:8080/six-vuln/nvd/cpe?name=firefox"
    @RequestMapping(
                    method=RequestMethod.GET
                    ,value="/nvd/cpe"
                    ,headers="Accept=application/xml"
    )
    public @ResponseBody QueryResults<?> findCpeItem(
                    final CpeItemQueryParams params
                    )
    {
        _LOG_.debug( "query params=" + String.valueOf( params ) );
        View  view = _removeView( params );

        QueryResults<?>  results = null;
        if (view == View.id) {
            results = _getNvdRepository().findCpeName( params );
        } else if (view == View.count) {
            long  count = _getNvdRepository().countCpeItem( params );
            results = new QueryResults<Void>();
            results.setTotalResults( count );
        } else {
            results = _getNvdRepository().findCpeItem( params );
        }

        return results;
    }

}
//

