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
 * Holds individual hyperlink element.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: RefType.java 592 2013-06-11 03:23:47Z nakamura5akihito@gmail.com $
 * @see <a href="http://cve.mitre.org/">CVE (Common Vulnerabilities and Exposures)</a>
 */
public class RefType
    implements Serializable
{

    private String  content;

    private String  source;
    //{required}

    private String  url;
    //{optional, type="xsd:anyURI"}



    /**
     * Constructs an instance.
     */
    public RefType()
    {
    }


    public RefType(
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
    public void setSource(
                    final String source
                    )
    {
        this.source = source;
    }


    public String getSource()
    {
        return source;
    }



    /**
     */
    public String getUrl()
    {
        return url;
    }


    public void setUrl(
                    final String url
                    )
    {
//        if (url != null) {
//            //validation
//            try {
//                new URL( url );
//            } catch (MalformedURLException ex) {
//                throw new IllegalArgumentException( ex );
//            }
//        }

        this.url = url;
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

        String  source = getSource();
        result = prime * result + ((source == null) ? 0 : source.hashCode());

        String  url = getUrl();
        result = prime * result + ((url == null) ? 0 : url.hashCode());

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

        if (!(obj instanceof RefType)) {
            return false;
        }

        if (super.equals( obj )) {
            RefType  other = RefType.class.cast( obj );
            final String   this_content =  this.getContent();
            final String  other_content = other.getContent();
            if (this_content == other_content
                            ||  (this_content != null  &&  this_content.equals( other_content ))) {
                final String   this_source =  this.getSource();
                final String  other_source = other.getSource();
                if (this_source == other_source
                                ||  (this_source != null  &&  this_source.equals( other_source ))) {
                    final String   this_url =  this.getUrl();
                    final String  other_url = other.getUrl();
                    if (this_url == other_url
                                    ||  (this_url != null  &&  this_url.equals( other_url ))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return            "[source="    + getSource()
                        + ", url="      + getUrl()
                        + ", "          + getContent()
                        + "]";
    }

}
//
