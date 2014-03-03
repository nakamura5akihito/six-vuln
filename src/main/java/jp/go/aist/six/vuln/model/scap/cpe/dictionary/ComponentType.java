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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.vuln.model.scap.core.ReferenceType;
import jp.go.aist.six.vuln.model.scap.core.TextType;



/**
 * The prototype of a single CPE component.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ComponentType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class ComponentType
    implements Serializable
{

    /**
     * Human readable format of CPE component name.
     * Capitalization, XML allowed punctuation, spaces allowed.
     */
    private final Collection<TextType>  title = new ArrayList<TextType>();
    //{0..*}


    private final Collection<ReferenceType>  reference = new ArrayList<ReferenceType>();
    //{0..*}


    /**
     * The component's text value.
     */
    private String  vlaue;
    //{xsd:token, required}




    /**
     * Constructor.
     */
    public ComponentType()
    {
    }


    public ComponentType(
                    final String value
                    )
    {
        setValue( value );
    }



    /**
     */
    public Collection<TextType> getTitle()
    {
        return title;
    }


    /**
     */
    public void setTitle(
                    final Collection<? extends TextType> title_list
                    )
    {
        if (title_list != title) {
            title.clear();
            if (title_list != null  && title_list.size() > 0) {
                title.addAll( title_list );
            }
        }
    }



    /**
     */
    public Collection<ReferenceType> getReference()
    {
        return reference;
    }


    /**
     */
    public void setReference(
                    final Collection<? extends ReferenceType> reference_list
                    )
    {
        if (reference_list != reference) {
            reference.clear();
            if (reference_list != null  && reference_list.size() > 0) {
                reference.addAll( reference_list );
            }
        }
    }



    /**
     */
    public void setValue(
                    final String value
                    )
    {
        vlaue = value;
    }


    /**
     */
    public String getValue()
    {
        return vlaue;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = 17;

        // NOT related to the equality!!!
//      Collection<Text>  title = getTitle();
//      result = prime * result + ((title == null) ? 0 : title.hashCode());

        String  value = getValue();
        result = prime * result + ((value == null) ? 0 : value.hashCode());

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

        if (!(obj instanceof ComponentType)) {
            return false;
        }

        ComponentType  other = (ComponentType)obj;
        String  other_value = other.getValue();
        String   this_value =  this.getValue();
        if (this_value == other_value
                        ||  (this_value != null  &&  this_value.equals( other_value ))) {
            return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "title=" + getTitle()
             + ", reference=" + getReference()
             + ", value=" + getValue()
                ;
    }

}
//
