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




/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ReferencesType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class ReferencesType
    implements Serializable
{

    private final Collection<Reference>  reference = new ArrayList<Reference>();
    //{1..*}



    /**
     * Constructs an instance.
     */
    public ReferencesType()
    {
    }



    /**
     */
    public Collection<Reference> getReference()
    {
        return reference;
    }


    public void setReference(
                    final Collection<? extends Reference> reference_list
                    )
    {
        if (reference_list != reference) {
            reference.clear();
            if (reference_list != null  &&  reference_list.size() > 0) {
                reference.addAll( reference_list );
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

        final Collection<Reference>  reference = getReference();
        result = prime * result + ((reference == null) ? 0 : reference.hashCode());

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

        if (!(obj instanceof ReferencesType)) {
            return false;
        }

        ReferencesType other = ReferencesType.class.cast( obj );
        final Collection<Reference> this_reference = this.getReference();
        final Collection<Reference> other_reference = other.getReference();
        if (this_reference == other_reference
                        || (this_reference != null
                                        && other_reference != null
                                        && this_reference.size() == other_reference.size() && this_reference.containsAll( other_reference ))) {
            return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[" + getReference()
                        + "]";
    }

}
//
