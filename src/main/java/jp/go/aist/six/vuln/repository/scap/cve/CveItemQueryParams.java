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
package jp.go.aist.six.vuln.repository.scap.cve;

import jp.go.aist.six.util.repository.CommonQueryParams;
import jp.go.aist.six.vuln.model.scap.cve.SimplePhaseEnumType;




/**
 * Query parameters for CVE items.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: CveItemQueryParams.java 607 2013-06-13 06:45:28Z nakamura5akihito@gmail.com $
 */
public class CveItemQueryParams
    extends CommonQueryParams
{

    public static class Key
    extends CommonQueryParams.Key
    {
        public static final String  ID                  = "id";     //alias of NAME
        public static final String  NAME                = "name";   //pattern list
        public static final String  PHASE               = "phase";  //enum, list
        public static final String  REF_SOURCE          = "refSource";
        public static final String  REF                 = "ref";

        public static final String  DESC                = "desc"; //searchTerms
    }
    //



    /**
     * Constructs an instance.
     */
    public CveItemQueryParams()
    {
//        setOrder( DEFAULT_ORDER );
    }



    /**
     */
    public void setId(
                    final String id
                    )
    {
        set( Key.NAME, id );
    }


    public String getId()
    {
        return get( Key.NAME );
    }



    /**
     */
    public void setName(
                    final String name
                    )
    {
        set( Key.NAME, name );
    }


    public String getName()
    {
        return get( Key.NAME );
    }



    /**
     */
    public void setPhase(
                    final String phase
                    )
    {
        set( Key.PHASE, phase );
    }


    public void setPhase(
                    final SimplePhaseEnumType phase
                    )
    {
        if (phase == null) {
            remove( Key.PHASE );
        } else {
            set( Key.PHASE, phase.name() );
        }
    }


    public String getPhase()
    {
        return get( Key.PHASE );
    }



    /**
     */
    public void setRefSource(
                    final String ref_source
                    )
    {
        set( Key.REF_SOURCE, ref_source );
    }


    public String getRefSource()
    {
        return get( Key.REF_SOURCE );
    }



    /**
     */
    public void setRef(
                    final String ref
                    )
    {
        set( Key.REF, ref );
    }


    public String getRef()
    {
        return get( Key.REF );
    }



    /**
     */
    public void setDesc(
                    final String desc
                    )
    {
        set( Key.DESC, desc );
    }


    public String getDesc()
    {
        return get( Key.DESC );
    }

}
//

