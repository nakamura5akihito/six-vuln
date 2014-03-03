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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;




/**
 * The Notes defines an element that consists of one or more child note elements.
 * It is assumed that each of these note elements are representative
 * of the same language as defined by their parent.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: NotesType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class NotesType
    implements Serializable
{

    private final Collection<TextType>  note = new ArrayList<TextType>();
    //{1..*}



    /**
     * Constructs an instance.
     */
    public NotesType()
    {
    }



    /**
     */
    public Collection<TextType> getNote()
    {
        return note;
    }


    public void setNote(
                    final Collection<? extends TextType> note_list
                    )
    {
        if (note_list != note) {
            note.clear();
            if (note_list != null) {
                note.addAll( note_list );
            }
        }
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = 17;

        final Collection<TextType>  note = getNote();
        result = prime * result + ((note == null) ? 0 : note.hashCode());

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

        if (!(obj instanceof NotesType)) {
            return false;
        }

        if (super.equals( obj )) {
            NotesType  other = NotesType.class.cast( obj );
            final Collection<TextType>   this_note =  this.getNote();
            final Collection<TextType>  other_note = other.getNote();
            if (this_note == other_note
                            ||  (this_note != null  &&  other_note != null
                            &&  this_note.size() == other_note.size()
                            &&  this_note.containsAll( other_note ))) {
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "NotesType[" + getNote()
                        + "]";
    }

}
//NotesType
