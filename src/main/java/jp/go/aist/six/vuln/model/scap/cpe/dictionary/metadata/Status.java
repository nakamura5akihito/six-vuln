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

import java.io.Serializable;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.CpeStatusEnumType;



/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: Status.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class Status
    implements Serializable
{

    private String  content;
    //{base="meta:cpeStatusEnumType"}


    private String  date;
    //{xsd:dateTime, required}



    /**
     */
    public Status()
    {
    }



    /**
     */
    public String getContent()
    {
        return content;
    }


    public void setContent(
                    final String content
                    )
    {
        if (content != null) {
            //validation
            CpeStatusEnumType.valueOf( content );
        }

        this.content = content;
    }



    /**
     */
    public String getDate()
    {
        return date;
    }


    public void setDate(
                    final String date
                    )
    {
        this.date = date;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public String toString()
    {
        return "[date=" + getDate()
                        + ", " + getContent()
                        + "]";
    }

}
//Status

