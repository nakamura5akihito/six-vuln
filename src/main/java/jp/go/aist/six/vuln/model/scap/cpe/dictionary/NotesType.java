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
import jp.go.aist.six.vuln.model.scap.vulnerability.ToolConfigurationType;




/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: NotesType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class NotesType
    implements Serializable
{

    private final Collection<String>  note = new ArrayList<String>();
    //{1..*}


    private String  lang;



    /**
     * Constructs an instance.
     */
    public NotesType()
    {
    }



    /**
     */
    public Collection<String> getNote()
    {
        return note;
    }


    public void setNote(
                    final Collection<String> note_list
                    )
    {
        if (note_list != note) {
            note.clear();
            if (note_list != null  &&  note_list.size() > 0) {
                note.addAll( note_list );
            }
        }
    }



    /**
     */
    public String getLang()
    {
        return lang;
    }


    public void setLang(
                    final String lang
                    )
    {
        this.lang = lang;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final Collection<String>  product = getNote();

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

        if (!(obj instanceof ToolConfigurationType)) {
            return false;
        }

        final NotesType  other = (NotesType)obj;
        final Collection<String>   this_product =  this.getNote();
        final Collection<String>  other_product = other.getNote();
        if (this_product == other_product
                        ||  (this_product != null  &&  other_product != null
                        &&  this_product.size() == other_product.size()
                        &&  this_product.containsAll( other_product ))) {
                return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[note=" + getNote()
                        + "]";
    }

}
//
