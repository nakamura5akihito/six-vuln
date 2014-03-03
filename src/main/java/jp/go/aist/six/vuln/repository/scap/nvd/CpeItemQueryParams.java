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
package jp.go.aist.six.vuln.repository.scap.nvd;

import jp.go.aist.six.util.repository.CommonQueryParams;




/**
 * Query parameters for CPE dictionary items.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: CpeItemQueryParams.java 530 2013-04-15 07:24:29Z nakamura5akihito@gmail.com $
 */
public class CpeItemQueryParams
    extends CommonQueryParams
{

    public static class Key
    extends CommonQueryParams.Key
    {
        public static final String  NAME                = "name";    // pattern list
    }
    //



    /**
     * Constructs an instance.
     */
    public CpeItemQueryParams()
    {
//        setOrder( DEFAULT_ORDER );
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

}
//

