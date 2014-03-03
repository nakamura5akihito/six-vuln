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
import jp.go.aist.six.vuln.model.scap.cpe.CpePart;



/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ProductComponentType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class ProductComponentType
    extends ComponentType
{

    private final Collection<VersionComponentType>  version = new ArrayList<VersionComponentType>();
    //{0..*}


    private CpePart  part;
    //{scap-core:cpePartComponentPatternType, required}




    /**
     * Constructor.
     */
    public ProductComponentType()
    {
    }


    public ProductComponentType(
                    final String value
                    )
    {
        super( value );
    }



    /**
     */
    public void setVersion(
                    final Collection<VersionComponentType> version_list
                    )
    {
        if (version_list != version) {
            version.clear();
            if (version_list != null  &&  version_list.size() > 0) {
                version.addAll( version_list );
            }
        }
    }


    /**
     */
    public Collection<VersionComponentType> getVersion()
    {
        return version;
    }



    /**
     */
    public void setPart(
                    final CpePart part
                    )
    {
        this.part = part;
    }


    /**
     */
    public CpePart getPart()
    {
        return part;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        Collection<VersionComponentType>  version = getVersion();
        result = prime * result + ((version == null) ? 0 : version.hashCode());

        CpePart  part = getPart();
        result = prime * result + ((part == null) ? 0 : part.hashCode());

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

        if (!(obj instanceof ProductComponentType)) {
            return false;
        }

        if (super.equals( obj )) {
            ProductComponentType  other = (ProductComponentType)obj;
            final CpePart   this_part =  this.getPart();
            final CpePart  other_part = other.getPart();
            if (this_part == other_part
                            ||  (this_part != null  &&  this_part.equals( other_part ))) {
                final Collection<VersionComponentType>   this_version =  this.getVersion();
                final Collection<VersionComponentType>  other_version = other.getVersion();
                if (this_version == other_version
                                ||  (this_version != null  &&  other_version != null
                                &&  this_version.size() == other_version.size()
                                &&  this_version.containsAll( other_version ))) {
                    return true;
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[version=" + getVersion()
                        + ", part=" + getPart()
                        + super.toString() + "]";
    }

}
//
