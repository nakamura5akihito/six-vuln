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
import jp.go.aist.six.util.persist.Persistable;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;



/**
 * The CpeItem denotes a single CPE Name.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ItemType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
@Entity( "nvd.cpedic.item" )                  //Morphia
public class ItemType
    implements Persistable<String>
{

    @Id //MongoDB
    private String  _id;


    private final Collection<TextType>  title = new ArrayList<TextType>();
    //{1..*}


    private final Collection<NotesType>  notes = new ArrayList<NotesType>();
    //{0..*}


    private ReferencesType  references;
    //{0..1}


    private final Collection<CheckType>  check = new ArrayList<CheckType>();
    //{0..*}


    private String  name;
    //{cpe_dict:namePattern, required}


    public static final Boolean  DEFAULT_DEPRECATED = Boolean.FALSE;

    private Boolean  deprecated;
    //{optional, default="false"}


    private String  deprecated_by;
    //{cpe_dict:namePattern, optional}


    private String  deprecation_date;
    //{xsd:dateTime, optional}


    private final Collection<AnyItemMetadata>  any = new ArrayList<AnyItemMetadata>();
    //{0..*}



    /**
     * Constructor.
     */
    public ItemType()
    {
    }


    /**
     * Constructor.
     */
    public ItemType(
                    final String name
                    )
    {
        setName( name );
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
    public Collection<NotesType> getNotes()
    {
        return notes;
    }


    /**
     */
    public void setNotes(
                    final Collection<? extends NotesType> notes_list
                    )
    {
        if (notes_list != notes) {
            notes.clear();
            if (notes_list != null  && notes_list.size() > 0) {
                notes.addAll( notes_list );
            }
        }
    }



    /**
     */
    public void setReferences(
                    final ReferencesType references
                    )
    {
        this.references = references;
    }


    /**
     */
    public ReferencesType getReferences()
    {
        return references;
    }



    /**
     */
    public Collection<CheckType> getCheck()
    {
        return check;
    }


    /**
     */
    public void setCheck(
                    final Collection<? extends CheckType> check_list
                    )
    {
        if (check_list != check) {
            check.clear();
            if (check_list != null  && check_list.size() > 0) {
                check.addAll( check_list );
            }
        }
    }



    /**
     */
    public void setName(
                    final String name
                    )
    {
        this.name = name;
    }


    /**
     */
    public String getName()
    {
        return name;
    }



    /**
     */
    public void setDeprecated(
                    final Boolean deprecated
                    )
    {
        this.deprecated = deprecated;
    }


    public Boolean getDeprecated()
    {
        return deprecated;
    }


    public static Boolean deprecated(
                    final ItemType e
                    )
    {
        Boolean  deprecated = e.getDeprecated();
        return (deprecated == null ? DEFAULT_DEPRECATED : deprecated);
    }



    /**
     */
    public void setDeprecatedBy(
                    final String deprecated_by
                    )
    {
        this.deprecated_by = deprecated_by;
    }


    /**
     */
    public String getDeprecatedBy()
    {
        return deprecated_by;
    }



    /**
     */
    public void setDeprecationDate(
                    final String deprecation_date
                    )
    {
        this.deprecation_date = deprecation_date;
    }


    /**
     */
    public String getDeprecationDate()
    {
        return deprecation_date;
    }



    /**
     */
    public Collection<AnyItemMetadata> getAny()
    {
        return any;
    }


    /**
     */
    public void setAny(
                    final Collection<? extends AnyItemMetadata> any_list
                    )
    {
        if (any_list != any) {
            any.clear();
            if (any_list != null  && any_list.size() > 0) {
                any.addAll( any_list );
            }
        }
    }



    //**************************************************************
    //  MongoDB/Morphia Lifecycle
    //**************************************************************

//    @SuppressWarnings( "unused" )
    @PrePersist
    private void _assignPersistentID()
    {
        String  pid = getPersistentID();
        if (pid == null) {
            pid = getName();
            setPersistentID( pid );
        }
    }



    //*********************************************************************
    //  Persistable
    //*********************************************************************

    @Override
    public void setPersistentID(
                    final String pid
                    )
    {
        _id = pid;
    }


    @Override
    public String getPersistentID()
    {
        return _id;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    /**
     */
    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = 17;

        String  name = getName();
        result = prime * result + ((name == null) ? 0 : name.hashCode());

        // NOT related to the equalty!!!
//        Collection<CpeTitle>  title = getTitle();
//        result = prime * result + ((title == null) ? 0 : title.hashCode());

        return result;
    }



    /**
     */
    @Override
    public boolean equals(
                    final Object obj
                    )
    {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ItemType)) {
            return false;
        }

        ItemType  other = (ItemType)obj;
        String  other_name = other.getName();
        String   this_name =  this.getName();
        if (this_name == other_name
                        ||  (this_name != null  &&  this_name.equals( other_name ))) {
            return true;
        }

        return false;
    }



    /**
     */
    @Override
    public String toString()
    {
        return "[name=" + getName()
                        + ", deprecated=" + getDeprecated()
                        + ", deprecated_by=" + getDeprecatedBy()
                        + ", deprecation_date=" + getDeprecationDate()
        		+ ", title=" + getTitle()
                + ", notes=" + getNotes()
                + ", references=" + getReferences()
                + ", check=" + getCheck()
                + ", any=" + getAny()
                + "]";
    }

}
//
