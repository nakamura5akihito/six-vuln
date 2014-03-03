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



/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CheckType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class CheckType
    implements Serializable
{

    private String  content;

    private String  system;
    //{xsd:anyURI, required}

    private String  href;
    //{xsd:anyURI, optional}



    /**
     * Constructs an instance.
     */
    public CheckType()
    {
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
    public String getSystem()
    {
        return system;
    }


    public void setSystem(
                    final String system
                    )
    {
        this.system = system;
    }



    /**
     */
    public String getHref()
    {
        return href;
    }


    public void setHref(
                    final String href
                    )
    {
        this.href = href;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = super.hashCode();

        String  content = getContent();
        result = prime * result + ((content == null) ? 0 : content.hashCode());

        String  system = getSystem();
        result = prime * result + ((system == null) ? 0 : system.hashCode());

        String  href = getHref();
        result = prime * result + ((href == null) ? 0 : href.hashCode());

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

        if (!(obj instanceof CheckType)) {
            return false;
        }

        CheckType other = CheckType.class.cast( obj );
        final String this_content = this.getContent();
        final String other_content = other.getContent();
        if (this_content == other_content
                        || (this_content != null && this_content.equals( other_content ))) {
            final String this_href = this.getHref();
            final String other_href = other.getHref();
            if (this_href == other_href
                            || (this_href != null && this_href.equals( other_href ))) {
                final String this_system = this.getSystem();
                final String other_system = other.getSystem();
                if (this_system == other_system
                                || (this_system != null && this_system.equals( other_system ))) {
                    return true;
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[system=" + getSystem()
                        + ", href=" + getHref()
                        + ", " + getContent()
                        + "]";
    }

}
//
