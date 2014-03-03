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
package jp.go.aist.six.vuln.model.scap.cve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;




/**
 * Holds all hyperlink elements.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: RefsType.java 600 2013-06-12 07:48:30Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class RefsType
    implements Serializable
{

    private final Collection<RefType>  ref = new ArrayList<RefType>();
    //{0..*}



    /**
     * Constructs an instance.
     */
    public RefsType()
    {
    }



    /**
     */
    public Collection<RefType> getRef()
    {
        return ref;
    }


    public void setRef(
                    final Collection<RefType> ref_list
                    )
    {
        if (ref_list != ref) {
            ref.clear();
            if (ref_list != null) {
                ref.addAll( ref_list );
            }
        }
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final Collection<RefType>  product = getRef();

        result = prime * result + ((product == null) ? 0 : product.hashCode());

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

        if (!(obj instanceof RefsType)) {
            return false;
        }

        final RefsType  other = (RefsType)obj;

        final Collection<RefType>   this_ref =  this.getRef();
        final Collection<RefType>  other_ref = other.getRef();
        if (this_ref == other_ref
                        ||  (this_ref != null  &&  other_ref != null
                        &&  this_ref.size() == other_ref.size()
                        &&  this_ref.containsAll( other_ref ))) {
                return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[ref=" + getRef()
                        + "]";
    }

}
//
