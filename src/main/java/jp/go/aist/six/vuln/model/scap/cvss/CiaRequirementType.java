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
package jp.go.aist.six.vuln.model.scap.cvss;

import java.io.Serializable;




/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CiaRequirementType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class CiaRequirementType
    implements Serializable
//    extends VectorAttributeGroup
{

    private String  content;
//    private CiaRequirementEnumType  content;


    //*** vectorAttributeGroup ***//
    /**
     * Indicates if the vector has been approximated
     * as the result of an upgrade from a previous CVSS version.
     */
    private Boolean  approximated;
    //{optional, default="boolean"}

    public static final boolean  DEFAULT_APPROXIMATED = false;



    /**
     * Constructs an instance.
     */
    public CiaRequirementType()
    {
    }



    /**
     */
    public String getContent()
    {
        return content;
    }


    public void setContent(
                    final String content
                    )
    {
        if (content != null) {
            // validation
            CiaRequirementEnumType.valueOf( content );
        }

        this.content = content;
    }



    //*** vectorAttributeGroup ***//

    /**
     */
    public void setApproximated(
                    final Boolean approximated
                    )
    {
        this.approximated = approximated;
    }


    public Boolean isApproximated()
    {
        return approximated;
    }


    public static boolean approximated(
                    final CiaRequirementType obj
//                    final VectorAttributeGroup vectorAttributeGroup
                    )
    {
        if (obj == null) {
            throw new IllegalArgumentException( "null object" );
        }

        final Boolean  approximated = obj.isApproximated();
        return (approximated == null ? DEFAULT_APPROXIMATED : approximated);
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final boolean  approximated = approximated( this );
        result = prime * result + ((approximated) ? 0 : 1);

        final String  content = getContent();
        result = prime * result + ((content == null) ? 0 : content.hashCode());

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

        if (!(obj instanceof CiaRequirementType)) {
            return false;
        }

        final CiaRequirementType  other = (CiaRequirementType)obj;
        if (approximated( this ) == approximated( other )) {
            final String   this_content =  this.getContent();
            final String  other_content = other.getContent();
            if (this_content == other_content
                            ||  (this_content != null  &&  this_content.equals( other_content ))) {
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "CiaRequirementType[approximated=" + isApproximated()
                        + ", content=" + getContent()
                        + "]";
    }

}
//CiaRequirementType
