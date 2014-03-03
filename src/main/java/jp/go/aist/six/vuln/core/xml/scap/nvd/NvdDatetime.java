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
package jp.go.aist.six.vuln.core.xml.scap.nvd;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: NvdDatetime.java 530 2013-04-15 07:24:29Z nakamura5akihito@gmail.com $
 */
public class NvdDatetime
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ = LoggerFactory.getLogger( NvdDatetime.class );



    private final DatatypeFactory  _factory;



    /**
     * Constructor.
     */
    public NvdDatetime()
    {
        try {
            _factory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
            throw new RuntimeException( ex );
        }
    }



    /**
     * Returns the lexical representation of the specified date/time object.
     * The format is specified in W3C XML Schema specification.
     * If the value is null, this method simply returns null.
     *
     * @param   value
     *  the date/time.
     * @return
     *  the lexical representation of the specified date/time.
     */
    public String formatXml(
                    final Date value
                    )
    {
        if (value == null) {
            return null;
        }

        GregorianCalendar  cal = new GregorianCalendar();
        cal.setTime( value );
        XMLGregorianCalendar  xmlcal = _factory.newXMLGregorianCalendar( cal );

        return xmlcal.toXMLFormat();
    }



    /**
     * Parses the specified lexical date/time.
     * The format is specified in W3C XML Schema specification.
     * If the given value is null, this method returns null.
     *
     * @param   value
     *  lexical date/time in XML.
     * @return
     *  the date/time object.
     */
    public Date parseXml(
                    final String value
                    )
    {
        Date  date = null;
        if (value != null) {
            XMLGregorianCalendar  xmlcal = _factory.newXMLGregorianCalendar( value );
            date = xmlcal.toGregorianCalendar().getTime();
        }

        return date;
    }

}
//
