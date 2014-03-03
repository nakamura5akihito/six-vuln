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
package jp.go.aist.six.vuln.model.scap.core;



/**
 * Type for a reference in the description of a CPE item.
 * This would normally be used to point to extra descriptive material,
 * or the supplier's web site, or the platform documentation.
 * It consists of a piece of text (intended to be human-readable)
 * and a URI (intended to be a URL, and point to a real resource).
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ReferenceType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class ReferenceType
    extends TextType
{

    private String  href;
    //{xsd:anyURI, optional}



    /**
     * Constructs an instance.
     */
    public ReferenceType()
    {
    }



    /**
     */
    public String getHref()
    {
        return href;
    }


    public void setHref(
                    final String href
                    )
    {
        this.href = href;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        String  href = getHref();
        result = prime * result + ((href == null) ? 0 : href.hashCode());

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

        if (!(obj instanceof ReferenceType)) {
            return false;
        }

        if (super.equals( obj )) {
            ReferenceType  other = ReferenceType.class.cast( obj );
            final String   this_href =  this.getHref();
            final String  other_href = other.getHref();
            if (this_href == other_href
                            ||  (this_href != null  &&  this_href.equals( other_href ))) {
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "ReferenceType[href=" + getHref()
                        + ", " + super.toString()
                        + "]";
    }

}
//ReferenceType
