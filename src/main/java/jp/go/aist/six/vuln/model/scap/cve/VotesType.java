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




/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: VotesType.java 588 2013-06-10 10:24:08Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class VotesType
    implements Serializable
{

    //{0..1}
    private VoteType  accept;
    private VoteType  modify;
    private VoteType  noop;
    private VoteType  recast;
    private VoteType  reject;
    private VoteType  reviewing;
    private VoteType  revote;



    /**
     * Constructs an instance.
     */
    public VotesType()
    {
    }



    /**
     */
    public VoteType getAccept()
    {
        return accept;
    }


    public void setAccept(
                    final VoteType vote
                    )
    {
        accept = vote;
    }



    /**
     */
    public VoteType getModify()
    {
        return modify;
    }


    public void setModify(
                    final VoteType vote
                    )
    {
        modify = vote;
    }


    /**
     */
    public VoteType getNoop()
    {
        return noop;
    }


    public void setNoop(
                    final VoteType vote
                    )
    {
        noop = vote;
    }


    /**
     */
    public void setRecast(
                    final VoteType vote
                    )
    {
        recast = vote;
    }


    public VoteType getRecast()
    {
        return recast;
    }



    /**
     */
    public VoteType getReject()
    {
        return reject;
    }


    public void setReject(
                    final VoteType vote
                    )
    {
        reject = vote;
    }



    /**
     */
    public VoteType getReviewing()
    {
        return reviewing;
    }


    public void setReviewing(
                    final VoteType vote
                    )
    {
        reviewing = vote;
    }



    /**
     */
    public VoteType getRevote()
    {
        return revote;
    }


    public void setRevote(
                    final VoteType vote
                    )
    {
        revote = vote;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = 17;

        final VoteType  accept = getAccept();
        final VoteType  modify = getModify();
        final VoteType  noop = getNoop();
        final VoteType  recast = getRecast();
        final VoteType  reject = getReject();
        final VoteType  reviewing = getReviewing();
        final VoteType  revote = getRevote();

        result = prime * result + ((accept == null) ? 0 : accept.hashCode());
        result = prime * result + ((modify == null) ? 0 : modify.hashCode());
        result = prime * result + ((noop == null) ? 0 : noop.hashCode());
        result = prime * result + ((recast == null) ? 0 : recast.hashCode());
        result = prime * result + ((reject == null) ? 0 : reject.hashCode());
        result = prime * result + ((reviewing == null) ? 0 : reviewing.hashCode());
        result = prime * result + ((revote == null) ? 0 : revote.hashCode());

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

        if (!(obj instanceof VotesType)) {
            return false;
        }

        VotesType  other = VotesType.class.cast( obj );
        final VoteType   this_accept =  this.getAccept();
        final VoteType  other_accept = other.getAccept();
        if (this_accept == other_accept
                        ||  (this_accept != null  &&  this_accept.equals( other_accept ))) {
            final VoteType   this_modify =  this.getModify();
            final VoteType  other_modify = other.getModify();
            if (this_modify == other_modify
                            ||  (this_modify != null  &&  this_modify.equals( other_modify ))) {
                final VoteType   this_noop =  this.getNoop();
                final VoteType  other_noop = other.getNoop();
                if (this_noop == other_noop
                                ||  (this_noop != null  &&  this_noop.equals( other_noop ))) {
                    final VoteType   this_recast =  this.getRecast();
                    final VoteType  other_recast = other.getRecast();
                    if (this_recast == other_recast
                                    ||  (this_recast != null  &&  this_recast.equals( other_recast ))) {
                        final VoteType   this_reject =  this.getReject();
                        final VoteType  other_reject = other.getReject();
                        if (this_reject == other_reject
                                        ||  (this_reject != null  &&  this_reject.equals( other_reject ))) {
                            final VoteType   this_reviewing =  this.getReviewing();
                            final VoteType  other_reviewing = other.getReviewing();
                            if (this_reviewing == other_reviewing
                                            ||  (this_reviewing != null  &&  this_reviewing.equals( other_reviewing ))) {
                                final VoteType   this_revote =  this.getRevote();
                                final VoteType  other_revote = other.getRevote();
                                if (this_revote == other_revote
                                                ||  (this_revote != null  &&  this_revote.equals( other_revote ))) {
                                    return true;
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
        return "[accept="                   + getAccept()
                        + ", modify="       + getModify()
                        + ", noop="         + getNoop()
                        + ", recast="       + getRecast()
                        + ", reject="       + getReject()
                        + ", reviewing="    + getReviewing()
                        + ", revote="       + getRevote()
                        + "]";
    }

}
//
