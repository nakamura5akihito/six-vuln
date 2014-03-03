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
 * @version $Id: CommentType.java 589 2013-06-11 02:37:32Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class CommentType
    implements Serializable
{

    private String  content;

    private String  voter;
    //{required}



    /**
     * Constructs an instance.
     */
    public CommentType()
    {
    }


    public CommentType(
                    final String content
                    )
    {
        setContent( content );
    }



    /**
     */
    public void setContent(
                    final String content
                    )
    {
        this.content = content;
    }


    public String getContent()
    {
        return content;
    }



    /**
     */
    public void setVoter(
                    final String voter
                    )
    {
        this.voter = voter;
    }


    public String getVoter()
    {
        return voter;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = 17;

        String  content = getContent();
        result = prime * result + ((content == null) ? 0 : content.hashCode());

        String  voter = getVoter();
        result = prime * result + ((voter == null) ? 0 : voter.hashCode());

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

        if (!(obj instanceof CommentType)) {
            return false;
        }

        if (super.equals( obj )) {
            CommentType  other = CommentType.class.cast( obj );
            final String   this_content =  this.getContent();
            final String  other_content = other.getContent();
            if (this_content == other_content
                            ||  (this_content != null  &&  this_content.equals( other_content ))) {
                final String   this_voter =  this.getVoter();
                final String  other_voter = other.getVoter();
                if (this_voter == other_voter
                                ||  (this_voter != null  &&  this_voter.equals( other_voter ))) {
                    return true;
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return            "[voter=" + getVoter()
                        + ", "      + getContent()
                        + "]";
    }

}
//
