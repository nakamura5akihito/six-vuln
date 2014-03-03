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

import jp.go.aist.six.util.persist.Persistable;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;



/**
 * Definition of the CVE entry.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ItemType.java 599 2013-06-12 07:30:16Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
@Entity( "cve.item" )  //Morphia: MongoDB collection name
public class ItemType
    implements Persistable<String>
//    implements Serializable
{

    private StatusEnumType  status;
    //{1..1}

    private SpecificPhaseType  phase;
    //{0..1}

    private String  desc;
    //{1..1}

    private RefsType  refs;
    //{1..1}

    private VotesType  votes;
    //{0..1}

    private CommentsType  comments;
    //{0..1}

    private TypeEnumType  type;
    //{required}

    @Id //Morphia: MongoDB document ID
    private String  name;
    //{required}

    private String  seq;
    //{required}



    /**
     * Constructs an instance.
     */
    public ItemType()
    {
    }



    /**
     */
    public StatusEnumType getStatus()
    {
        return status;
    }


    public void setStatus(
                    final StatusEnumType status
                    )
    {
        this.status = status;
    }



    /**
     */
    public SpecificPhaseType getPhase()
    {
        return phase;
    }


    public void setPhase(
                    final SpecificPhaseType phase
                    )
    {
        this.phase = phase;
    }



    /**
     */
    public String getDesc()
    {
        return desc;
    }


    public void setDesc(
                    final String desc
                    )
    {
        this.desc = desc;
    }



    /**
     */
    public RefsType getRefs()
    {
        return refs;
    }


    public void setRefs(
                    final RefsType refs
                    )
    {
        this.refs = refs;
    }



    /**
     */
    public VotesType getVotes()
    {
        return votes;
    }


    public void setVotes(
                    final VotesType votes
                    )
    {
        this.votes = votes;
    }



    /**
     */
    public CommentsType getComments()
    {
        return comments;
    }


    public void setComments(
                    final CommentsType comments
                    )
    {
        this.comments = comments;
    }



    /**
     */
    public TypeEnumType getType()
    {
        return type;
    }


    public void setType(
                    final TypeEnumType type
                    )
    {
        this.type = type;
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
    public String getSeq()
    {
        return seq;
    }


    public void setSeq(
                    final String seq
                    )
    {
        this.seq = seq;
    }



    //*********************************************************************
    //  Persistable
    //*********************************************************************

    @Override
    public void setPersistentID(
                    final String id
                    )
    {
        setName( id );
    }


    @Override
    public String getPersistentID()
    {
        return getName();
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        String  name = getName();
        result = prime * result + ((name == null) ? 0 : name.hashCode());

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

        if (!(obj instanceof ItemType)) {
            return false;
        }

        final ItemType  other = (ItemType)obj;

        final String   this_name =  this.getName();
        final String  other_name = other.getName();
        if (this_name == other_name
                        ||  (this_name != null  &&  this_name.equals( other_name ))) {
            return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return            "[type="          + getType()
                        + ", name="         + getName()
                        + ", seq="          + getSeq()
                        + ", status="       + getStatus()
                        + ", phase="        + getPhase()
                        + ", desc="         + getDesc()
                        + ", refs="         + getRefs()
                        + ", votes="        + getVotes()
                        + ", comments="     + getComments()
                        + "]";
    }

}
//
