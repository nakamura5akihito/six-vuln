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
package jp.go.aist.six.vuln.core.repository.scap.nvd.morphia;

import java.util.HashMap;
import java.util.Map;
import jp.go.aist.six.util.core.repository.morphia.MorphiaQueryBuilder.CommonBuilder;
import jp.go.aist.six.util.core.repository.morphia.QueryBuilder;
import jp.go.aist.six.util.core.repository.morphia.QueryBuilderFactory;
import jp.go.aist.six.util.repository.CommonQueryParams;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.ItemType;
import jp.go.aist.six.vuln.model.scap.cvss.AccessComplexityEnumType;
import jp.go.aist.six.vuln.model.scap.cvss.AccessVectorEnumType;
import jp.go.aist.six.vuln.model.scap.cvss.AuthenticationEnumType;
import jp.go.aist.six.vuln.model.scap.cvss.CiaEnumType;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityReferenceCategoryEnumType;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import jp.go.aist.six.vuln.repository.scap.nvd.CpeItemQueryParams;
import jp.go.aist.six.vuln.repository.scap.nvd.VulnerabilityQueryParams;
import org.mongodb.morphia.query.Query;




/**
 * @author  Akihito Nakamura, AIST
 * @version $Id: NvdQueryBuilder.java 635 2013-06-28 08:30:33Z nakamura5akihito@gmail.com $
 */
public class NvdQueryBuilder
    extends CommonBuilder
    implements QueryBuilderFactory
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ = LoggerFactory.getLogger( NvdQueryBuilder.class );



    //*********************************************************************
    //  implements QueryBuilderFactory
    //*********************************************************************


    @Override
    public QueryBuilder newBuilder(
                    final Class<?> type
                    )
    {
        if (VulnerabilityType.class.isAssignableFrom( type )) {
            return VulnerabilityBuilder.INSTANCE;
        } else if (ItemType.class.isAssignableFrom( type )) {
                return CpeItemBuilder.INSTANCE;
        }

        return CommonBuilder.INSTANCE;
    }



    /**
     * Constructor.
     */
    protected NvdQueryBuilder()
    {
    }



    /**
     * f=a
     * f=a,b,c
     * f=x*
     * f=x*,y
     * f=x*,y,z*
     */
    protected static class OvalPatternListHandler
    extends PatternListHandler
    {
        public static final PatternListHandler INSTANCE = new OvalPatternListHandler();

        public static final String  OVAL_SYSTEM = "http://oval.mitre.org/XMLSchema/oval-definitions-5";



        public OvalPatternListHandler()
        {
        }


        @Override
        public void build(
                        final Query<?> query,
                        final String field,
                        final String value
                        )
        {
            if (_isEmpty( value )) {
                return;
            }

            super.build( query, field, value );
            FilterHandler.INSTANCE.build( query, "assessmentCheck.system", OVAL_SYSTEM );
        }
    }
    //



    ///////////////////////////////////////////////////////////////////////
    //  QueryBuilder variations
    ///////////////////////////////////////////////////////////////////////

    /**
     * vuln:vulnerability
     */
    public static class VulnerabilityBuilder
    extends CommonBuilder
    {
        public static final VulnerabilityBuilder  INSTANCE = new VulnerabilityBuilder();


        protected static Map<String, String> _createFieldMapping()
        {
            Map<String, String>  mapping = new HashMap<String, String>();

            //common//
            mapping.put( CommonQueryParams.Key.SEARCH_TERMS,            "summary" );

            //vulnerability//
            mapping.put( VulnerabilityQueryParams.Key.ID,               "id" );
            mapping.put( VulnerabilityQueryParams.Key.CVE,              "cveId" );
            mapping.put( VulnerabilityQueryParams.Key.CVSS,             "cvss.baseMetrics.score" );
            mapping.put( VulnerabilityQueryParams.Key.ACCESS_VECTOR,    "cvss.baseMetrics.accessVector.content" );
            mapping.put( VulnerabilityQueryParams.Key.ACCESS_COMPLEXITY, "cvss.baseMetrics.accessComplexity.content" );
            mapping.put( VulnerabilityQueryParams.Key.AUTHENTICATION,   "cvss.baseMetrics.authentication.content" );
            mapping.put( VulnerabilityQueryParams.Key.CONFIDENCE,       "cvss.baseMetrics.confidentialityImpact.content" );
            mapping.put( VulnerabilityQueryParams.Key.INTEGIRITY,       "cvss.baseMetrics.integrityImpact.content" );
            mapping.put( VulnerabilityQueryParams.Key.AVAILABILITY,     "cvss.baseMetrics.availabilityImpact.content" );
            mapping.put( VulnerabilityQueryParams.Key.REF_SOURCE,       "references.source" );
            mapping.put( VulnerabilityQueryParams.Key.REF_NAME,         "references.reference.content" );
            mapping.put( VulnerabilityQueryParams.Key.REF_TYPE,         "references.referenceType" );
            mapping.put( VulnerabilityQueryParams.Key.SOFTWARE,         "vulnerableSoftwareList.product" );
            mapping.put( VulnerabilityQueryParams.Key.CWE,              "cwe.id" );

            mapping.put( VulnerabilityQueryParams.Key.OVAL,             "assessmentCheck.name" );

            mapping.put( VulnerabilityQueryParams.Key.SUMMARY,          "summary" );

            return mapping;
        }


        private static final Map<String, String>  _FIELDS_ = _createFieldMapping();



        protected static Map<String, Handler> _createHandlers()
        {
            Handler  cia_enum_handler = new EnumListHandler<CiaEnumType>( CiaEnumType.class );

            Map<String, Handler>  mapping = CommonBuilder._createHandlerMapping();

            //common//
            mapping.put( CommonQueryParams.Key.SEARCH_TERMS,            SearchTermsHandler2.INSTANCE );
//          mapping.put( CommonQueryParams.Key.SEARCH_TERMS,            SearchTermsHandler.INSTANCE ); //slower

            //vulnerability//
            mapping.put( VulnerabilityQueryParams.Key.ID,                   PatternListHandler.INSTANCE );
            mapping.put( VulnerabilityQueryParams.Key.CVE,                  PatternListHandler.INSTANCE );
            mapping.put( VulnerabilityQueryParams.Key.CVSS,                 DoubleRangeHandler.INSTANCE );
            mapping.put( VulnerabilityQueryParams.Key.ACCESS_VECTOR,        new EnumListHandler<AccessVectorEnumType>( AccessVectorEnumType.class ) );
            mapping.put( VulnerabilityQueryParams.Key.ACCESS_COMPLEXITY,    new EnumListHandler<AccessComplexityEnumType>( AccessComplexityEnumType.class ) );
            mapping.put( VulnerabilityQueryParams.Key.AUTHENTICATION,       new EnumListHandler<AuthenticationEnumType>( AuthenticationEnumType.class ) );
            mapping.put( VulnerabilityQueryParams.Key.CONFIDENCE,           cia_enum_handler );
            mapping.put( VulnerabilityQueryParams.Key.INTEGIRITY,           cia_enum_handler );
            mapping.put( VulnerabilityQueryParams.Key.AVAILABILITY,         cia_enum_handler );
            mapping.put( VulnerabilityQueryParams.Key.REF_SOURCE,           HasAnyOfHandler.INSTANCE );
            mapping.put( VulnerabilityQueryParams.Key.REF_NAME,             PatternListHandler.INSTANCE );
            mapping.put( VulnerabilityQueryParams.Key.REF_TYPE,             new EnumListHandler<VulnerabilityReferenceCategoryEnumType>( VulnerabilityReferenceCategoryEnumType.class ));
            mapping.put( VulnerabilityQueryParams.Key.SOFTWARE,             SearchTermsHandler2.INSTANCE );
            mapping.put( VulnerabilityQueryParams.Key.CWE,                  PatternListHandler.INSTANCE );

            mapping.put( VulnerabilityQueryParams.Key.OVAL,                 OvalPatternListHandler.INSTANCE );

            mapping.put( VulnerabilityQueryParams.Key.SUMMARY,              ContainsHandler.INSTANCE );

            return mapping;
        }


        private static final Map<String, Handler>  _HANDLERS_ = _createHandlers();



        public VulnerabilityBuilder()
        {
        }



        @Override
        protected Map<String, Handler> _handlerMapping()
        {
            return _HANDLERS_;
        }


        @Override
        protected Map<String, String> _fieldMapping()
        {
            return _FIELDS_;
        }

    }
    //VulnerabilityBuilder



    /**
     * cpe_dic:cpe_item
     */
    public static class CpeItemBuilder
    extends CommonBuilder
    {
        public static final CpeItemBuilder  INSTANCE = new CpeItemBuilder();


        protected static Map<String, String> _createFieldMapping()
        {
            Map<String, String>  mapping = new HashMap<String, String>();

            //common//
            mapping.put( CommonQueryParams.Key.SEARCH_TERMS,    "title.content" );

            // cpe item //
            mapping.put( CpeItemQueryParams.Key.NAME,           "name" );

            return mapping;
        }


        private static final Map<String, String>  _FIELDS_ = _createFieldMapping();



        protected static Map<String, Handler> _createHandlers()
        {
            Map<String, Handler>  mapping = CommonBuilder._createHandlerMapping();

            //common//
            mapping.put( CommonQueryParams.Key.SEARCH_TERMS,        SearchTermsHandler2.INSTANCE );
//          mapping.put( CommonQueryParams.Key.SEARCH_TERMS,        SearchTermsHandler.INSTANCE ); //slower

            // cpe item //
            mapping.put( CpeItemQueryParams.Key.NAME,               PatternListHandler.INSTANCE );

            return mapping;
        }


        private static final Map<String, Handler>  _HANDLERS_ = _createHandlers();



        public CpeItemBuilder()
        {
        }



        @Override
        protected Map<String, Handler> _handlerMapping()
        {
            return _HANDLERS_;
        }


        @Override
        protected Map<String, String> _fieldMapping()
        {
            return _FIELDS_;
        }

    }
    //CpeItemBuilder

}
//

