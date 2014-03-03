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
package jp.go.aist.six.vuln.model.scap.cve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;




/**
 * Cve is the top level element of the CVE List provided by MITRE.
 * It represents holds all CVE Items.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: Cve.java 600 2013-06-12 07:48:30Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class Cve
    implements Serializable
{

    private final Collection<ItemType>  item = new ArrayList<ItemType>();
    //{1..*}

    private String  schemaVersion;
    //{optional}



    /**
     * Constructs an instance.
     */
    public Cve()
    {
    }



    /**
     */
    public Collection<ItemType> getItem()
    {
        return item;
    }


    public void setItem(
                    final Collection<ItemType> item_list
                    )
    {
        if (item_list != item) {
            item.clear();
            if (item_list != null) {
                item.addAll( item_list );
            }
        }
    }



    /**
     */
    public String getSchemaVersion()
    {
        return schemaVersion;
    }


    public void setSchemaVersion(
                    final String schemaVersion
                    )
    {
        this.schemaVersion = schemaVersion;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final Collection<ItemType>  item = getItem();
        result = prime * result + ((item == null) ? 0 : item.hashCode());

        //schemaVersion is NOT taken into account.

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

        if (!(obj instanceof Cve)) {
            return false;
        }

        final Cve  other = (Cve)obj;

        final Collection<ItemType>   this_item =  this.getItem();
        final Collection<ItemType>  other_item = other.getItem();
        if (this_item == other_item
                        ||  (this_item != null  &&  other_item != null
                        &&  this_item.size() == other_item.size()
                        &&  this_item.containsAll( other_item ))) {
            //schemaVersion is NOT taken into account.
            return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return            "[schemaVersion=" + getSchemaVersion()
                        + ", item="         + getItem()
                        + "]";
    }

}
//
