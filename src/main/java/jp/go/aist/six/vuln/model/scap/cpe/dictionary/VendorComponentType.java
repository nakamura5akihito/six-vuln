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
 * @version $Id: VendorComponentType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class VendorComponentType
    extends ComponentType
{

    private final Collection<ProductComponentType>  product = new ArrayList<ProductComponentType>();
    //{0..*}



    /**
     * Constructor.
     */
    public VendorComponentType()
    {
    }


    /**
     * Constructor.
     */
    public VendorComponentType(
                    final String value
                    )
    {
        super( value );
    }



    /**
     */
    public void setProduct(
                    final Collection<? extends ProductComponentType> product_list

                    )
    {
        if (product_list != product) {
            product.clear();
            if (product_list != null  &&  product_list.size() > 0) {
                product.addAll( product_list );
            }
        }
    }


    /**
     */
    public Collection<ProductComponentType> getProduct()
    {
        return product;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        Collection<ProductComponentType>  product = getProduct();
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

        if (!(obj instanceof VendorComponentType)) {
            return false;
        }

        if (super.equals( obj )) {
            VendorComponentType  other = (VendorComponentType)obj;
            final Collection<ProductComponentType>   this_product =  this.getProduct();
            final Collection<ProductComponentType>  other_product = other.getProduct();
            if (this_product == other_product
                            ||  (this_product != null  &&  other_product != null
                            &&  this_product.size() == other_product.size()
                            &&  this_product.containsAll( other_product ))) {
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[product=" + getProduct()
                        + ", " + super.toString() + "]";
    }

}
//
