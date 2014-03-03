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
package jp.go.aist.six.vuln.model.scap.cpe.dictionary.metadata;

import jp.go.aist.six.vuln.model.scap.cpe.dictionary.AnyItemMetadata;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.CpeStatusEnumType;




/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ItemMetadata.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class ItemMetadata
    extends AnyItemMetadata
//    implements Serializable
{

    private StatusHistoryType  status_history;
    //{0..1}


    private Long  nvd_id;
    //{required}


    private Long  deprecated_by_nvd_id;
    //{optional}


    private CpeStatusEnumType  status;
    //{required}


    private String  modification_date;
    //{xsd:dateTime, required}



    /**
     * Constructor.
     */
    public ItemMetadata()
    {
    }



    public StatusHistoryType getStatusHistory()
    {
        return status_history;
    }



    public void setStatusHistory(
                    final StatusHistoryType status_history
                    )
    {
        this.status_history = status_history;
    }



    public Long getNvdId()
    {
        return nvd_id;
    }



    public void setNvdId(
                    final Long nvd_id
                    )
    {
        this.nvd_id = nvd_id;
    }



    public Long getDeprecatedByNvdId()
    {
        return deprecated_by_nvd_id;
    }



    public void setDeprecatedByNvdId(
                    final Long deprecated_by_nvd_id
                    )
    {
        this.deprecated_by_nvd_id = deprecated_by_nvd_id;
    }



    public CpeStatusEnumType getStatus()
    {
        return status;
    }



    public void setStatus(
                    final CpeStatusEnumType status
                    )
    {
        this.status = status;
    }



    public String getModificationDate()
    {
        return modification_date;
    }



    public void setModificationDate(
                    final String modification_date
                    )
    {
        this.modification_date = modification_date;
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public String toString()
    {
        return "[status-history=" + getStatusHistory()
                + ", nvd-id=" + getNvdId()
                + ", deprecated-by-nvd-id=" + getDeprecatedByNvdId()
                + ", status=" + getStatus()
                + ", modification-date=" + getModificationDate()
                + "]";
    }

}
//
