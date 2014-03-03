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
 * The GeneratorType complex type defines an element that is used
 * to hold information about when a particular document was compiled,
 * what version of the schema was used, what tool compiled the document,
 * and what version of that tools was used.
 * Additional generator information is also allowed although it is not
 * part of the official schema.
 * Individual organizations can place generator information that they feel
 * are important and these will be skipped during the validation.
 * All that this schema really cares about is that the stated
 * generator information is there.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: GeneratorType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class GeneratorType
    implements Serializable
{

    /**
     * The name of the application used to generate the dictionary file.
     */
    private String  product_name;
    //{0..1}


    /**
     * The version of the application used to generate the dictionary file.
     */
    private String  product_version;
    //{0..1}


    /**
     * The version of the schema that the document has been written
     * against and that should be used for validation.
     */
    private String  schema_version;
    //{xsd:decimal, 1..1}


    /**
     * When the particular document was compiled.
     * The format is yyyy-mm-ddThh:mm:ss.
     */
    private String  timestamp;
    //{xsd:dateTime, 1..1}



    /**
     * Constructor.
     */
    public GeneratorType()
    {
    }


    public GeneratorType(
                    final String schema_version,
                    final String timestamp
                    )
    {
        setSchemaVersion( schema_version );
        setTimestamp( timestamp );
    }



    /**
     */
    public void setProductName(
                    final String name
                    )
    {
        product_name = name;
    }


    /**
     */
    public String getProductName()
    {
        return product_name;
    }



    public void setProductVersion(
                    final String version
                    )
    {
        product_version = version;
    }


    public String getProductVersion()
    {
        return product_version;
    }



    public void setSchemaVersion(
                    final String version
                    )
    {
        schema_version = version;
    }


    public String getSchemaVersion()
    {
        return schema_version;
    }



    public void setTimestamp(
                    final String timestamp
                    )
    {
        this.timestamp = timestamp;
    }


    public String getTimestamp()
    {
        return timestamp;
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

        String  product_name = getProductName();
        result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());

        String  product_version = getProductVersion();
        result = prime * result + ((product_version == null) ? 0 : product_version.hashCode());

        String  schema_version = getSchemaVersion();
        result = prime * result + ((schema_version == null) ? 0 : schema_version.hashCode());

        String  timestamp = getTimestamp();
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());

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

        if (!(obj instanceof GeneratorType)) {
            return false;
        }

        GeneratorType  other = (GeneratorType)obj;
        String  other_ts = other.getTimestamp();
        String   this_ts =  this.getTimestamp();
        if (this_ts == other_ts
                        ||  (this_ts != null  &&  this_ts.equals( other_ts ))) {
            String  other_sv = other.getSchemaVersion();
            String   this_sv =  this.getSchemaVersion();
            if (this_sv == other_sv
                            ||  (this_sv != null  &&  this_sv.equals( other_sv ))) {
                String  other_pn = other.getProductName();
                String   this_pn =  this.getProductName();
                if (this_pn == other_pn
                                ||  (this_pn != null  &&  this_pn.equals( other_pn ))) {
                    String  other_pv = other.getProductVersion();
                    String   this_pv =  this.getProductVersion();
                    if (this_pv == other_pv
                                    ||  (this_pv != null  &&  this_pv.equals( other_pv ))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "[product_name=" + getProductName()
        + ", product_version=" + getProductVersion()
        + ", schema_version=" + getSchemaVersion()
        + ", timestamp=" + getTimestamp()
        + "]";
    }

}
//
