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
package jp.go.aist.six.vuln.model.scap.cpe;

import java.io.Serializable;
import java.util.HashMap;



/**
 * The enumeration of part component in the CPE name.
 *
 * <p>The following codes are defined for CPE 2.0.
 * </p>
 * <ul>
 *   <li>h: hardware part</li>
 *   <li>o: operating system part</li>
 *   <li>a: application part</li>
 * </ul>
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CpePart.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 * @see     "Common Platform Enumeration (CPE) - Specification"
 */
public class CpePart
    implements Serializable
{

    private static final String  _HARDWARE_              = "h";
    private static final String  _OPERATING_SYSTEM_      = "o";
    private static final String  _APPLICATION_           = "a";
    private static final String  _HARDWARE_CAP_          = "H";
    private static final String  _OPERATING_SYSTEM_CAP_  = "O";
    private static final String  _APPLICATION_CAP_       = "A";


    public static final CpePart  HARDWARE          = new CpePart( _HARDWARE_ );
    public static final CpePart  OPERATING_SYSTEM  = new CpePart( _OPERATING_SYSTEM_  );
    public static final CpePart  APPLICATION       = new CpePart( _APPLICATION_  );



    private static HashMap<String, CpePart> _INIT_()
    {
        HashMap<String, CpePart>  map = new HashMap<String, CpePart>();
        map.put( _HARDWARE_,              HARDWARE          );
        map.put( _OPERATING_SYSTEM_,      OPERATING_SYSTEM  );
        map.put( _APPLICATION_,           APPLICATION       );
        map.put( _HARDWARE_CAP_,          HARDWARE          );
        map.put( _OPERATING_SYSTEM_CAP_,  OPERATING_SYSTEM  );
        map.put( _APPLICATION_CAP_,       APPLICATION       );
        return map;
    }

    private static final HashMap<String, CpePart>  _INSTANCES_ = _INIT_();




    /**
     */
    public static CpePart valueOf(
                    final String name
                    )
    {
        CpePart  flag = null;
        if (name != null) {
            flag = _INSTANCES_.get( name );
        }

        if (flag == null) {
            throw new IllegalArgumentException( "invalid CPE part: " + name );
        }

        return flag;
    }



    private String  _name = null;



    /**
     */
    private CpePart(
                    final String name
                    )
    {
        _name = name;
    }



    /**
     */
    public String getName()
    {
        return _name;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public String toString()
    {
        return getName();
    }

}
// CpePart
