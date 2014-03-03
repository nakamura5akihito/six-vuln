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
package jp.go.aist.six.vuln.core;

//import jp.go.aist.six.util.core.config.spring.SpringContext;
import jp.go.aist.six.util.core.config.spring.SpringContext;
import jp.go.aist.six.util.xml.XmlMapper;
import jp.go.aist.six.vuln.core.web.scap.nvd.HttpNvdRepositoryClient;
import jp.go.aist.six.vuln.repository.scap.cve.CveRepository;
import jp.go.aist.six.vuln.repository.scap.nvd.NvdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Application Context using Spring Framework.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: SixVulnContext.java 623 2013-06-17 10:05:24Z nakamura5akihito@gmail.com $
 */
public abstract class SixVulnContext
    extends SpringContext
{

    /**
     * Logger.
     */
    private static final Logger  _LOG_ = LoggerFactory.getLogger( SixVulnContext.class );



//TODO: Test this!!! Move this to the super class Context.
// Note that Environment has NO method to obtain a set of property keys or to iterate properties.
//    @Autowired
//    private Environment  _env;

//Example code:
    //ApplicationContext ctx = new GenericApplicationContext();
    //Environment env = ctx.getEnvironment();
    //boolean containsFoo = env.containsProperty("foo");



    /**
     * Constructs an instance.
     */
    protected SixVulnContext()
    {
    }


    protected SixVulnContext(
                    final String config_location
                    )
    {
        super( config_location, new String[] {
                            "classpath:jp/go/aist/six/vuln/core/six-vuln_defaults.properties",
                            "classpath:six-vuln.properties"
                            } );

//        super( config_location, new String[] {
//                        "six-vuln-default-properties",
//                        "six-vuln-properties"
//                    } );



//        _env = _getContext().getEnvironment();
//        if (_env != null) {
//            String  key = "six.vuln.datastore.engine";
//            String  value = _env.getProperty( key );
//            _LOG_.debug( "property by Spring Environment: " + key + "=" + value );
//        }
    }



    /**
     * Returns an XmlMapper instance which is dedicated to the Vulnerability domain model.
     *
     * @throws  ConfigurationException
     *  when it is NOT possible to create an instance.
     */
    public XmlMapper getXmlMapper()
    {
        XmlMapper  bean = getBean( XmlMapper.class );
        _LOG_.debug( "XMLMapper bean: " + bean.getClass() );

        return bean;
    }



//    /**
//     * Returns an NvdVulnerabilityRepository instance.
//     *
//     * @throws  ConfigurationException
//     *  when it is NOT possible to create an instance.
//     */
//    public NvdVulnerabilityRepository getNvdVulnerabilityRepository()
//    {
//        NvdVulnerabilityRepository  repository = null;
//        try {
//            repository = getBean( NvdVulnerabilityRepository.class );
//        } catch (Exception ex) {
//            throw new ConfigurationException( ex );
//        }
//
//        return repository;
//    }




    ///////////////////////////////////////////////////////////////////////
    //  CVE
    ///////////////////////////////////////////////////////////////////////

    public static class Cve
    extends SixVulnContext
    {

        private static BasicContext            _BASIC_CONTEXT_;
        private static RepositoryContext  _REPOSITORY_CONTEXT_;


        /**
         * Returns a basic context for data model handling.
         *
         * @return
         *  the basic context.
         */
        public static synchronized BasicContext basic()
        {
            if (_BASIC_CONTEXT_ == null) {
                _BASIC_CONTEXT_ = new BasicContext();
            }

            return _BASIC_CONTEXT_;
        }


        /**
         * Returns a context for applications using repository.
         *
         * @return
         *  the repository context.
         */
        public static synchronized RepositoryContext repository()
        {
            if (_REPOSITORY_CONTEXT_ == null) {
                _REPOSITORY_CONTEXT_ = new RepositoryContext();
            }

            return _REPOSITORY_CONTEXT_;
        }



        /**
         * Constructor.
         */
        protected Cve()
        {
        }


        protected Cve(
                        final String context_path
                        )
        {
            super( context_path );
        }



//        @Override
//        public XmlMapper getXmlMapper()
//        {
//            throw new UnsupportedOperationException();
//        }


        /**
         * Returns a CveRepository instance.
         */
        public CveRepository getRepository()
        {
            throw new UnsupportedOperationException();
        }



        /**
         * A basic context which supports XML handling only.
         */
        public static class BasicContext
        extends Nvd
        {
            public static final String  CONTEXT_PATH =
                            "jp/go/aist/six/vuln/core/six-vuln_context-cve-basic.xml";


            public BasicContext()
            {
                this( CONTEXT_PATH );
            }


            public BasicContext(
                            final String context_path
                            )
            {
                super( context_path );
            }


//            @Override
//            public NvdRepository getRepository()
//            {
//                throw new UnsupportedOperationException();
//            }
        }
        //basic



        /**
         */
        public static class RepositoryContext
        extends Cve
        {
            public static final String  CONTEXT_PATH =
                            "jp/go/aist/six/vuln/core/six-vuln_context-cve-repository.xml";


            public RepositoryContext()
            {
                this( CONTEXT_PATH );
            }


            public RepositoryContext(
                            final String context_path
                            )
            {
                super( context_path );
            }


            @Override
            public CveRepository getRepository()
            {
                CveRepository  repository = getBean( CveRepository.class );
                _LOG_.debug( "CveRepository bean: " + repository.getClass() );

                return repository;
            }
        }
        //repository

    }
    //Cve



    ///////////////////////////////////////////////////////////////////////
    //  NVD
    ///////////////////////////////////////////////////////////////////////

    public static class Nvd
    extends SixVulnContext
    {

        private static BasicContext            _BASIC_CONTEXT_;
        private static RepositoryContext  _REPOSITORY_CONTEXT_;
        private static WebServerContext   _WEB_SERVER_CONTEXT_;
        private static WebClientContext   _WEB_CLIENT_CONTEXT_;


        /**
         * Returns a basic context for data model handling.
         *
         * @return
         *  the basic context.
         */
        public static synchronized BasicContext basic()
        {
            if (_BASIC_CONTEXT_ == null) {
                _BASIC_CONTEXT_ = new BasicContext();
            }

            return _BASIC_CONTEXT_;
        }


        /**
         * Returns a context for applications using repository.
         *
         * @return
         *  the repository context.
         */
        public static synchronized RepositoryContext repository()
        {
            if (_REPOSITORY_CONTEXT_ == null) {
                _REPOSITORY_CONTEXT_ = new RepositoryContext();
            }

            return _REPOSITORY_CONTEXT_;
        }


        /**
         * Returns a context for web server applications.
         *
         * @return
         *  the web server context.
         */
        public static synchronized WebServerContext webServer()
        {
            if (_WEB_SERVER_CONTEXT_ == null) {
                _WEB_SERVER_CONTEXT_ = new WebServerContext();
            }

            return _WEB_SERVER_CONTEXT_;
        }



        /**
         * Returns a context for web client applications.
         *
         * @return
         *  the web client context.
         */
        public static synchronized WebClientContext webClient()
        {
            if (_WEB_CLIENT_CONTEXT_ == null) {
                _WEB_CLIENT_CONTEXT_ = new WebClientContext();
            }

            return _WEB_CLIENT_CONTEXT_;
        }



        /**
         * Constructor.
         */
        protected Nvd()
        {
        }


        protected Nvd(
                        final String context_path
                        )
        {
            super( context_path );
        }



//        @Override
//        public XmlMapper getXmlMapper()
//        {
//            throw new UnsupportedOperationException();
//        }


        /**
         * Returns an NvdRepository instance.
         */
        public NvdRepository getRepository()
        {
            throw new UnsupportedOperationException();
        }



        /**
         * A basic context which supports XML handling only.
         */
        public static class BasicContext
        extends Nvd
        {
            public static final String  CONTEXT_PATH =
                            "jp/go/aist/six/vuln/core/six-vuln_context-nvd-basic.xml";


            public BasicContext()
            {
                this( CONTEXT_PATH );
            }


            public BasicContext(
                            final String context_path
                            )
            {
                super( context_path );
            }


//            @Override
//            public NvdRepository getRepository()
//            {
//                throw new UnsupportedOperationException();
//            }
        }
        //



        /**
         */
        public static class RepositoryContext
        extends Nvd
        {
            public static final String  CONTEXT_PATH =
                            "jp/go/aist/six/vuln/core/six-vuln_context-nvd-repository.xml";


            public RepositoryContext()
            {
                this( CONTEXT_PATH );
            }


            public RepositoryContext(
                            final String context_path
                            )
            {
                super( context_path );
            }


            @Override
            public NvdRepository getRepository()
            {
                NvdRepository  repository = getBean( NvdRepository.class );
                _LOG_.debug( "NvdRepository bean: " + repository.getClass() );

                return repository;
            }
        }
        //



        /**
         */
        public static class WebServerContext
        extends RepositoryContext
        {
            public static final String  CONTEXT_PATH =
                            "jp/go/aist/six/vuln/core/six-vuln_context-nvd-web-server.xml";


            public WebServerContext()
            {
                super( CONTEXT_PATH );
            }

        }
        //


        /**
         */
        public static class WebClientContext
        extends Nvd
        {
            public static final String  CONTEXT_PATH =
                            "jp/go/aist/six/vuln/core/six-vuln_context-nvd-web-client.xml";


            public WebClientContext()
            {
                super( CONTEXT_PATH );
            }


            @Override
            public NvdRepository getRepository()
            {
                NvdRepository  repository = getBean( HttpNvdRepositoryClient.class );
                _LOG_.debug( "NvdRepository bean: " + repository.getClass() );

                return repository;
            }
        }
        //
    }
    //Nvd

}
//

