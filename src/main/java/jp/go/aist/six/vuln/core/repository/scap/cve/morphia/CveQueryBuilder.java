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
package jp.go.aist.six.vuln.core.repository.scap.cve.morphia;

import java.util.HashMap;
import java.util.Map;
import jp.go.aist.six.util.core.repository.morphia.MorphiaQueryBuilder.CommonBuilder;
import jp.go.aist.six.util.core.repository.morphia.QueryBuilder;
import jp.go.aist.six.util.core.repository.morphia.QueryBuilderFactory;
import jp.go.aist.six.util.repository.CommonQueryParams;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;
import jp.go.aist.six.vuln.repository.scap.cve.CveItemQueryParams;




/**
 * @author  Akihito Nakamura, AIST
 * @version $Id: CveQueryBuilder.java 607 2013-06-13 06:45:28Z nakamura5akihito@gmail.com $
 */
public class CveQueryBuilder
    extends CommonBuilder
    implements QueryBuilderFactory
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ = LoggerFactory.getLogger( CveQueryBuilder.class );



    //*********************************************************************
    //  implements QueryBuilderFactory
    //*********************************************************************


    public QueryBuilder newBuilder(
                    final Class<?> type
                    )
    {
        if (ItemType.class.isAssignableFrom( type )) {
                return CveItemBuilder.INSTANCE;
        }

        return CommonBuilder.INSTANCE;
    }



    /**
     * Constructor.
     */
    protected CveQueryBuilder()
    {
    }



    ///////////////////////////////////////////////////////////////////////
    //  QueryBuilder variations
    ///////////////////////////////////////////////////////////////////////

    /**
     * cve:entry
     */
    public static class CveItemBuilder
    extends CommonBuilder
    {
        public static final CveItemBuilder  INSTANCE = new CveItemBuilder();


        protected static Map<String, String> _createFieldMapping()
        {
            Map<String, String>  mapping = new HashMap<String, String>();

            //common//
            mapping.put( CommonQueryParams.Key.SEARCH_TERMS,    "desc" );

            // cpe item //
            mapping.put( CveItemQueryParams.Key.ID,             "name" );
            mapping.put( CveItemQueryParams.Key.NAME,           "name" );
            mapping.put( CveItemQueryParams.Key.PHASE,          "phase.content" );
            mapping.put( CveItemQueryParams.Key.REF_SOURCE,     "refs.ref.source" );
            mapping.put( CveItemQueryParams.Key.REF,            "refs.ref.content" );
            mapping.put( CveItemQueryParams.Key.DESC,           "desc" );

            return mapping;
        }


        private static final Map<String, String>  _FIELDS_ = _createFieldMapping();



        protected static Map<String, Handler> _createHandlers()
        {
            Map<String, Handler>  mapping = CommonBuilder._createHandlerMapping();

            //common//
            mapping.put( CommonQueryParams.Key.SEARCH_TERMS,        SearchTermsHandler2.INSTANCE );
//          mapping.put( CommonQueryParams.Key.SEARCH_TERMS,        SearchTermsHandler.INSTANCE ); //slower

            // cve item //
            mapping.put( CveItemQueryParams.Key.ID,                 PatternListHandler.INSTANCE );
            mapping.put( CveItemQueryParams.Key.NAME,               PatternListHandler.INSTANCE );
            mapping.put( CveItemQueryParams.Key.PHASE,              HasAnyOfHandler.INSTANCE );
//            mapping.put( CveItemQueryParams.Key.PHASE,              new EnumListHandler<SimplePhaseEnumType>( SimplePhaseEnumType.class ) );
            mapping.put( CveItemQueryParams.Key.REF_SOURCE,         HasAnyOfHandler.INSTANCE );
            mapping.put( CveItemQueryParams.Key.REF,                SearchTermsHandler2.INSTANCE );
            mapping.put( CveItemQueryParams.Key.DESC,               SearchTermsHandler2.INSTANCE );

            return mapping;
        }


        private static final Map<String, Handler>  _HANDLERS_ = _createHandlers();



        public CveItemBuilder()
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
    //CveItemBuilder

}
//

