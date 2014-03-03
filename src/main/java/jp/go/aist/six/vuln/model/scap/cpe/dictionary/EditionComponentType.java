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
 * @version $Id: EditionComponentType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class EditionComponentType
    extends ComponentType
{

    private final Collection<ComponentType>  language = new ArrayList<ComponentType>();
    //{0..*}



    /**
     * Constructor.
     */
    public EditionComponentType()
    {
    }


    public EditionComponentType(
                    final String value
                    )
    {
        super( value );
    }



    /**
     */
    public void setLanguage(
                    final Collection<? extends ComponentType> language_list
                    )
    {
        if (language_list != language) {
            language.clear();
            if (language_list != null  &&  language_list.size() > 0) {
                language.addAll( language_list );
            }
        }
    }


    /**
     */
    public Collection<ComponentType> getLanguage()
    {
        return language;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        Collection<ComponentType>  language = getLanguage();
        result = prime * result + ((language == null) ? 0 : language.hashCode());

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

        if (!(obj instanceof EditionComponentType)) {
            return false;
        }

        if (super.equals( obj )) {
            EditionComponentType  other = (EditionComponentType)obj;
            final Collection<ComponentType>   this_language =  this.getLanguage();
            final Collection<ComponentType>  other_language = other.getLanguage();
            if (this_language == other_language
                            ||  (this_language != null  &&  other_language != null
                            &&  this_language.size() == other_language.size()
                            &&  this_language.containsAll( other_language ))) {
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[language=" + getLanguage()
                        + ", " + super.toString() + "]";
    }

}
//
