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
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CommentsType.java 600 2013-06-12 07:48:30Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class CommentsType
    implements Serializable
{

    private final Collection<CommentType>  comment = new ArrayList<CommentType>();
    //{0..*}



    /**
     * Constructs an instance.
     */
    public CommentsType()
    {
    }



    /**
     */
    public Collection<CommentType> getComment()
    {
        return comment;
    }


    public void setComment(
                    final Collection<CommentType> comment_list
                    )
    {
        if (comment_list != comment) {
            comment.clear();
            if (comment_list != null) {
                comment.addAll( comment_list );
            }
        }
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final Collection<CommentType>  comment = getComment();

        result = prime * result + ((comment == null) ? 0 : comment.hashCode());

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

        if (!(obj instanceof CommentsType)) {
            return false;
        }

        final CommentsType  other = (CommentsType)obj;

        final Collection<CommentType>   this_comment =  this.getComment();
        final Collection<CommentType>  other_comment = other.getComment();
        if (this_comment == other_comment
                        ||  (this_comment != null  &&  other_comment != null
                        &&  this_comment.size() == other_comment.size()
                        &&  this_comment.containsAll( other_comment ))) {
                return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[comment=" + getComment()
                        + "]";
    }

}
//
