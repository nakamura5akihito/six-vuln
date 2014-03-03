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
package jp.go.aist.six.vuln.model.scap.cpe.dictionary;

import java.util.ArrayList;
import java.util.Collection;



/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ComponentTree.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class ComponentTree
//    extends AdditionalListElement
//    extends AbstractPersistable
{

    private final Collection<VendorComponentType>  _vendor = new ArrayList<VendorComponentType>();
    //{0..*}



    /**
     * Constructor.
     */
    public ComponentTree()
    {
    }



    /**
     */
    public void setVendor(
                    final Collection<VendorComponentType> vendor
                    )
    {
        if (vendor != _vendor) {
            _vendor.clear();
            if (vendor != null) {
                _vendor.addAll( vendor );
            }
        }
    }


    /**
     */
    public Collection<VendorComponentType> getVendor()
    {
        return _vendor;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        Collection<VendorComponentType>  vendor = getVendor();
        result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());

        return result;
    }



    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(
                    final Object obj
                    )
    {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ComponentTree)) {
            return false;
        }

        ComponentTree  other = (ComponentTree)obj;
        Collection<VendorComponentType>  other_vendor = other.getVendor();
        Collection<VendorComponentType>   this_vendor =  this.getVendor();
        if (this_vendor == other_vendor
                        ||  (this_vendor != null  &&  this_vendor.equals( other_vendor ))) {
            return true;
        }

        return false;
    }



    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ComponentTree["
                        + getVendor()
                        + "]";
    }

}
// ComponentTree
