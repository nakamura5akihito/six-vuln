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
 * @version $Id: VoteType.java 588 2013-06-10 10:24:08Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class VoteType
    implements Serializable
{

    private String  content;

    private String  count;
    //{required}



    /**
     * Constructs an instance.
     */
    public VoteType()
    {
    }


    public VoteType(
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
    public void setCount(
                    final String count
                    )
    {
        this.count = count;
    }


    public String getCount()
    {
        return count;
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

        String  count = getCount();
        result = prime * result + ((count == null) ? 0 : count.hashCode());

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

        if (!(obj instanceof VoteType)) {
            return false;
        }

        if (super.equals( obj )) {
            VoteType  other = VoteType.class.cast( obj );
            final String   this_content =  this.getContent();
            final String  other_content = other.getContent();
            if (this_content == other_content
                            ||  (this_content != null  &&  this_content.equals( other_content ))) {

                final String   this_count =  this.getCount();
                final String  other_count = other.getCount();
                if (this_count == other_count
                                ||  (this_count != null  &&  this_count.equals( other_count ))) {
                    return true;
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[count=" + getCount()
                        + ", " + getContent()
                        + "]";
    }

}
//
