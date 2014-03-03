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
import org.exolab.castor.mapping.GeneralizedFieldHandler;



/**
 * A Castor FieldHandler for the NVD date/time.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: NvdDatetimeHandler.java 530 2013-04-15 07:24:29Z nakamura5akihito@gmail.com $
 */
public class NvdDatetimeHandler
    extends GeneralizedFieldHandler
{

//    /**
//     * Logger.
//     */
//    private static final Logger  _LOG_ = LoggerFactory.getLogger( NvdDatetimeHandler.class );



//    /**
//     * The date/time formatter and parser.
//     */
//    private SimpleDateFormat  _formatter;
//
//
//    /**
//     * The default date format pattern.
//     */
//  public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
//TODO: timezone in NVD XML feed looks like "2012-09-24T10:15:00.000-04:00".
    // (case 1) "-04:00" can't be parsed using Java SimpleDateFormat's "Z" pattern letter. It should be "-0400".
    // (case 2) Some datetime values do not contain millisecond values.
//  public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
//  public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";


    private final NvdDatetime  _xml_datetime;


    /**
     * Constructor.
     */
    public NvdDatetimeHandler()
    {
        _xml_datetime = new NvdDatetime();
    }



    ////////////////////////////////////////////////////////////////
    //  extends GeneralizedFieldHandler
    ////////////////////////////////////////////////////////////////

    // from Date to String
    @Override
    public Object convertUponGet( final Object value )
    {
        if (value == null) {
            return null;
        }

        String  s = _xml_datetime.formatXml( (Date)value );

        return s;
    }



    // from String to Date
    //
    @Override
    public Object convertUponSet( final Object value )
    {
        Date  date = null;
        if (value != null) {
            date = _xml_datetime.parseXml( (String)value );
        }

        return date;
    }



    @Override
    public Class<Date> getFieldType()
    {
        return Date.class;
    }



    @Override
    public Object newInstance( final Object parent )
        throws IllegalStateException
    {
        return null;
    }

}
// NvdDatetimeHandler
