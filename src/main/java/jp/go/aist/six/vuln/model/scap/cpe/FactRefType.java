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




/**
 * The fact-ref element appears as a child of a logical-test element.
 * It is simply a reference to a CPE Name that always evaluates to a Boolean result.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: FactRefType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class FactRefType
    implements Serializable
{

    private String  name;
    //{cpe:namePattern, required}


    public FactRefType()
    {
    }



    /**
     */
    public String getName()
    {
        return name;
    }


    public void setName(
                    final String name
                    )
    {
        this.name = name;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final String  name = getName();

        result = prime * result + ((name == null) ? 0 : name.hashCode());

        return result;
    }



    @Override
    public boolean equals(
                    final Object obj
                    )
    {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FactRefType)) {
            return false;
        }

        FactRefType other = (FactRefType)obj;

        final String   this_name =  this.getName();
        final String  other_name = other.getName();
        if (this_name == other_name
                        ||  (this_name != null  &&  this_name.equals( other_name ))) {
            return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "FactRefType[name="           + getName()
                        + "]";
    }

}
//FactRefType
