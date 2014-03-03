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
 * @version $Id: VersionComponentType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class VersionComponentType
    extends ComponentType
{

    private final Collection<UpdateComponentType>  update = new ArrayList<UpdateComponentType>();
    //{0..*}



    /**
     * Constructor.
     */
    public VersionComponentType()
    {
    }


    public VersionComponentType(
                    final String value
                    )
    {
        super( value );
    }



    /**
     */
    public void setUpdate(
                    final Collection<? extends UpdateComponentType> update_list
                    )
    {
        if (update_list != update) {
            update.clear();
            if (update_list != null  &&  update_list.size() > 0) {
                update.addAll( update_list );
            }
        }
    }


    /**
     */
    public Collection<UpdateComponentType> getUpdate()
    {
        return update;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        Collection<UpdateComponentType>  update = getUpdate();
        result = prime * result + ((update == null) ? 0 : update.hashCode());

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

        if (!(obj instanceof VersionComponentType)) {
            return false;
        }

        if (super.equals( obj )) {
            VersionComponentType  other = (VersionComponentType)obj;
            final Collection<UpdateComponentType>   this_update =  this.getUpdate();
            final Collection<UpdateComponentType>  other_update = other.getUpdate();
            if (this_update == other_update
                            ||  (this_update != null  &&  other_update != null
                            &&  this_update.size() == other_update.size()
                            &&  this_update.containsAll( other_update ))) {
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[update=" + getUpdate()
                        + ", " + super.toString() + "]";
    }

}
//
