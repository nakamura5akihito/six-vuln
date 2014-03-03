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
 * @version $Id: UpdateComponentType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class UpdateComponentType
    extends ComponentType
{

    private final Collection<EditionComponentType>  edition = new ArrayList<EditionComponentType>();
    //{0..*}



    /**
     * Constructor.
     */
    public UpdateComponentType()
    {
    }


    public UpdateComponentType(
                    final String value
                    )
    {
        super( value );
    }



    /**
     */
    public void setEdition(
                    final Collection<? extends EditionComponentType> edition_list
                    )
    {
        if (edition_list != edition) {
            edition.clear();
            if (edition_list != null  && edition_list.size() > 0) {
                edition.addAll( edition_list );
            }
        }
    }


    /**
     */
    public Collection<EditionComponentType> getEdition()
    {
        return edition;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        Collection<EditionComponentType>  edition = getEdition();
        result = prime * result + ((edition == null) ? 0 : edition.hashCode());

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

        if (super.equals( obj )) {
            UpdateComponentType  other = (UpdateComponentType)obj;
            final Collection<EditionComponentType>   this_edition =  this.getEdition();
            final Collection<EditionComponentType>  other_edition = other.getEdition();
            if (this_edition == other_edition
                            ||  (this_edition != null  &&  other_edition != null
                            &&  this_edition.size() == other_edition.size()
                            &&  this_edition.containsAll( other_edition ))) {
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[edition=" + getEdition()
                        + ", " + super.toString() + "]";
    }

}
//
