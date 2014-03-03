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
 * @version $Id: TextType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class TextType
    implements Serializable
{

    private String  content;

    private String  lang;
    //{optional}



    /**
     * Constructs an instance.
     */
    public TextType()
    {
    }


    public TextType(
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
    public void setLang(
                    final String lang
                    )
    {
        this.lang = lang;
    }


    public String getLang()
    {
        return lang;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = 17;

//        String  lang = getLang();
//        result = prime * result + ((lang == null) ? 0 : lang.hashCode());

        String  content = getContent();
        result = prime * result + ((content == null) ? 0 : content.hashCode());

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

        if (!(obj instanceof TextType)) {
            return false;
        }

        TextType other = TextType.class.cast( obj );
        final String this_content = this.getContent();
        final String other_content = other.getContent();
        if (this_content == other_content
                        || (this_content != null && this_content.equals( other_content ))) {
            return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "[lang=" + getLang()
                        + ", " + getContent()
                        + "]";
    }

}
//
