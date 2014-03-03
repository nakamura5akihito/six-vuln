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
package jp.go.aist.six.vuln.model.scap.patch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.vuln.model.scap.core.CheckReferenceType;
import jp.go.aist.six.vuln.model.scap.core.NotesType;
import jp.go.aist.six.vuln.model.scap.core.ReferenceType;
import jp.go.aist.six.vuln.model.scap.core.TextType;




/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: PatchType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class PatchType
    implements Serializable
{

    /**
     * Human-formatted title for the patch.
     * If none given, then duplicate of the name.
     */
    private TextType  title;
    //{0..1, scap_core:textType}

    private final Collection<ReferenceType>  references = new ArrayList<ReferenceType>();
    //{0..1}
    //reference{1..*}

    private final Collection<NotesType>  notes = new ArrayList<NotesType>();
    //{0..*}

    private final Collection<CheckReferenceType>  check = new ArrayList<CheckReferenceType>();
    //{0..*}

    /**
     * Patches that superseded by the referenced patch.
     */
    private final Collection<PatchType>  supersedes = new ArrayList<PatchType>();
    //{0..*}

    /**
     * Patches that supersede the patch comprising the current XML document.
     */
    private final Collection<PatchType>  supersededBy = new ArrayList<PatchType>();
    //{0..*}

    /**
     * Identifier unique within the XML document for the given patch.
     */
    private Double  identifier;
    //{xsd:double, required}

    /**
     * Vendor supplied name for the patch.
     * Will use lower case and underscores for spaces, consistent with CPE naming conventions.
     */
    private String  name;
    //{required}

    /**
     * Boolean value.
     * True of patch is superseded. False if not.
     */
    private Boolean  superseded;
    //{required}

    /**
     * Indicates that a patch should not be used -- regardless of supersession.
     */
    private Boolean  deprecated;
    //{optional}




    /**
     */
    public TextType getTitle()
    {
        return title;
    }


    public void setTitle(
                    final TextType title
                    )
    {
        this.title = title;
    }



    /**
     */
    public Collection<ReferenceType> getReferences()
    {
        return references;
    }


    public void setReferences(
                    final Collection<? extends ReferenceType> reference_list
                    )
    {
        if (reference_list != references) {
            references.clear();
            if (reference_list != null) {
                references.addAll( reference_list );
            }
        }
    }



    /**
     */
    public Collection<NotesType> getNotes()
    {
        return notes;
    }


    public void setNotes(
                    final Collection<? extends NotesType> notes_list
                    )
    {
        if (notes_list != notes) {
            notes.clear();
            if (notes_list != null) {
                notes.addAll( notes_list );
            }
        }
    }



    /**
     */
    public Collection<CheckReferenceType> getCheck()
    {
        return check;
    }


    public void setCheck(
                    final Collection<? extends CheckReferenceType> check_list
                    )
    {
        if (check_list != check) {
            check.clear();
            if (check_list != null) {
                check.addAll( check_list );
            }
        }
    }



    /**
     */
    public Collection<PatchType> getSupersedes()
    {
        return supersedes;
    }


    public void setSupersedes(
                    final Collection<? extends PatchType> supersedes_list
                    )
    {
        if (supersedes_list != supersedes) {
            supersedes.clear();
            if (supersedes_list != null) {
                supersedes.addAll( supersedes_list );
            }
        }
    }



    /**
     */
    public Collection<PatchType> getSupersededBy()
    {
        return supersededBy;
    }


    public void setSupersededBy(
                    final Collection<? extends PatchType> supersededBy_list
                    )
    {
        if (supersededBy_list != supersededBy) {
            supersededBy.clear();
            if (supersededBy_list != null) {
                supersededBy.addAll( supersededBy_list );
            }
        }
    }



    /**
     */
    public Double getIdentifier()
    {
        return identifier;
    }


    public void setIdentifier(
                    final Double identifier
                    )
    {
        this.identifier = identifier;
    }



    /**
     */
    public String getName()
    {
        return name;
    }


    public void setName(
                    final String name
                    )
    {
        this.name = name;
    }



    /**
     */
    public Boolean isSuperseded()
    {
        return superseded;
    }


    public void setSuperseded(
                    final Boolean superseded
                    )
    {
        this.superseded = superseded;
    }



    /**
     */
    public Boolean isDeprecated()
    {
        return deprecated;
    }


    public void setDeprecated(
                    final Boolean deprecated
                    )
    {
        this.deprecated = deprecated;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final TextType  title = getTitle();
        final Collection<ReferenceType>  references = getReferences();
        final Collection<NotesType>  notes = getNotes();
        final Collection<CheckReferenceType>  check = getCheck();
        final Collection<PatchType>  supersedes = getSupersedes();
        final Collection<PatchType>  supersededBy = getSupersededBy();
        final Double  identifier = getIdentifier();
        final String  name = getName();
        final Boolean  superseded = isSuperseded();
        final Boolean  deprecated = isDeprecated();

        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((references == null) ? 0 : references.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((check == null) ? 0 : check.hashCode());
        result = prime * result + ((supersedes == null) ? 0 : supersedes.hashCode());
        result = prime * result + ((supersededBy == null) ? 0 : supersededBy.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((superseded == null) ? 0 : superseded.hashCode());
        result = prime * result + ((deprecated == null) ? 0 : deprecated.hashCode());

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

        if (!(obj instanceof PatchType)) {
            return false;
        }

        final PatchType  other = (PatchType)obj;

        final TextType   this_title =  this.getTitle();
        final TextType  other_title = other.getTitle();
        if (this_title == other_title
                        ||  (this_title != null  &&  this_title.equals( other_title ))) {
            final Collection<ReferenceType>   this_references =  this.getReferences();
            final Collection<ReferenceType>  other_references = other.getReferences();
            if (this_references == other_references
                            ||  (this_references != null  &&  other_references != null
                            &&  this_references.size() == other_references.size()
                            &&  this_references.containsAll( other_references ))) {
                final Collection<NotesType>   this_notes =  this.getNotes();
                final Collection<NotesType>  other_notes = other.getNotes();
                if (this_notes == other_notes
                                ||  (this_notes != null  &&  other_notes != null
                                &&  this_notes.size() == other_notes.size()
                                &&  this_notes.containsAll( other_notes ))) {
                    final Collection<CheckReferenceType>   this_check =  this.getCheck();
                    final Collection<CheckReferenceType>  other_check = other.getCheck();
                    if (this_check == other_check
                                    ||  (this_check != null  &&  other_check != null
                                    &&  this_check.size() == other_check.size()
                                    &&  this_check.containsAll( other_check ))) {
                        final Collection<PatchType>   this_supersedes =  this.getSupersedes();
                        final Collection<PatchType>  other_supersedes = other.getSupersedes();
                        if (this_supersedes == other_supersedes
                                        ||  (this_supersedes != null  &&  other_supersedes != null
                                        &&  this_supersedes.size() == other_supersedes.size()
                                        &&  this_supersedes.containsAll( other_supersedes ))) {
                            final Collection<PatchType>   this_supersededBy =  this.getSupersededBy();
                            final Collection<PatchType>  other_supersededBy = other.getSupersededBy();
                            if (this_supersededBy == other_supersededBy
                                            ||  (this_supersededBy != null  &&  other_supersededBy != null
                                            &&  this_supersededBy.size() == other_supersededBy.size()
                                            &&  this_supersededBy.containsAll( other_supersededBy ))) {
                                final Double   this_identifier =  this.getIdentifier();
                                final Double  other_identifier = other.getIdentifier();
                                if (this_identifier == other_identifier) {
                                    final String   this_name =  this.getName();
                                    final String  other_name = other.getName();
                                    if (this_name == other_name
                                                    ||  (this_name != null  &&  this_name.equals( other_name ))) {
                                        if (this.isSuperseded() == other.isSuperseded()) {
                                            if (this.isDeprecated() == other.isDeprecated()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "PatchType[title="           + getTitle()
                        + ", references="   + getReferences()
                        + ", notes="        + getNotes()
                        + ", check="        + getCheck()
                        + ", supersedes="   + getSupersedes()
                        + ", supersededBy=" + getSupersededBy()
                        + ", identifier="   + getIdentifier()
                        + ", name="         + getName()
                        + ", superseded="   + isSuperseded()
                        + ", deprecated="   + isDeprecated()
                        + "]";
    }

}
//PatchType
