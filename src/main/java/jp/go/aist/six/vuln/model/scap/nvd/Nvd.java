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
package jp.go.aist.six.vuln.model.scap.nvd;

import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.util.persist.Persistable;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;
import org.mongodb.morphia.annotations.Id;



/**
 * The root element of the NVD CVE feed.
 * Multiple "entry" child elements describe specific NVD CVE entries.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: Nvd.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class Nvd
    implements Persistable<String>
{

    //MongoDB
    @Id
    private String  _pid;


    /**
     * CVE entries.
     */
    private final Collection<VulnerabilityType>  entry = new ArrayList<VulnerabilityType>();
    //{0..*}


    /**
     * The schema version number supported by the feed.
     */
    private double  nvdXmlVersion;
//    private BigDecimal  nvdXmlVersion;
    //{xsd:decimal, required}


    /**
     * The date the feed was generated.
     */
    private String  pubDate;
//    private Date  pubDate;
    //{xsd:dateTime, required}



    /**
     * Constructor.
     */
    public Nvd()
    {
    }



    /**
     */
    public Collection<VulnerabilityType> getEntry()
    {
        return entry;
    }


    public void setEntry(
                    final Collection<? extends VulnerabilityType> entry_list
                    )
    {
        if (entry_list != entry) {
            entry.clear();
            if (entry_list != null) {
                entry.addAll( entry_list );
            }
        }
    }



    /**
     */
    public void setNvdXmlVersion(
                    final double nvdXmlVersion
                    )
    {
        this.nvdXmlVersion = nvdXmlVersion;
    }


    /**
     */
    public double getNvdXmlVersion()
    {
        return nvdXmlVersion;
    }



    /**
     */
    public void setPubDate(
                    final String pubDate
                    )
    {
        this.pubDate = pubDate;
    }


    /**
     */
    public String getPubDate()
    {
        return pubDate;
    }



    //*********************************************************************
    //  implements Persistable
    //*********************************************************************

    @Override
    public void setPersistentID(
                    final String pid
                    )
    {
        _pid = pid;
    }


    @Override
    public String getPersistentID()
    {
        return _pid;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public String toString()
    {
        return "Nvd[nvd_xml_version=" + getNvdXmlVersion()
                        + ", pub_date=" + getPubDate()
                        + ", #entry=" + getEntry().size()
                        + "]";
    }

}
// Nvd
