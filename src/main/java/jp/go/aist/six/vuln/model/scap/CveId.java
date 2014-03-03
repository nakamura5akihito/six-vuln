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
package jp.go.aist.six.vuln.model.scap;

import java.io.Serializable;
import java.util.Arrays;



/**
 * An CVE identifier.
 * The instances are immutable.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CveId.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public final class CveId
    implements Cloneable, Serializable
{

    public static final String  PREFIX = "CVE";

    public static final String  SEPARATOR = "-";


    ///////////////////////////////////////////////////////////////////////
    //  functions
    ///////////////////////////////////////////////////////////////////////

    /**
     */
    public static final int yearOf(
                    final String cve_id
                    )
    {
        if (cve_id == null) {
            throw new IllegalArgumentException( "empty CVE-ID (null)" );
        }

        String[]  components = cve_id.split( SEPARATOR );
        if (components.length != 3) {
            throw new IllegalArgumentException(
                            "insufficient or surplus components: " + cve_id );
        }

        int  year = Integer.valueOf( components[1] ).intValue();

        return year;
    }



    ///////////////////////////////////////////////////////////////////////
    //  instance definition
    ///////////////////////////////////////////////////////////////////////

    private String  _cve_id;


    private int     _year;
    private String  _sequence;



    /**
     * Constructor.
     */
    protected CveId()
    {
    }


    public CveId(
                    final String oval_id
                    )
    {
        _set( oval_id );
    }


    public CveId(
                    final String[] components
                    )
    {
        _set( components );
    }


    public CveId(
                    final int year,
                    final String sequence
                    )
    {
        this( PREFIX, year, sequence );
    }


    public CveId(
                    final String year,
                    final String sequence
                    )
    {
        this( PREFIX, year, sequence );
    }


    public CveId(
                    final String prefix,
                    final String year,
                    final String sequence
                    )
    {
        _set( prefix, year, sequence );
    }


    public CveId(
                    final String prefix,
                    final int year,
                    final String sequence
                    )
    {
        _set( prefix, year, sequence );
    }



    /**
     */
    private void _set(
                    final String cve_id
                    )
    {
        String[] components = null;
        components = cve_id.split( SEPARATOR );

        _set( components );
    }


    private void _set(
                    final String[] components
                    )
    {
        if (components.length == 2) {
            _set( PREFIX, components[0], components[1] );
        } else if (components.length == 3) {
            _set( components[0], components[1], components[2] );
        } else {
            throw new IllegalArgumentException(
                            "insufficient or surplus components: "
                                            + Arrays.toString( components ) );
        }
    }


    private void _set(
                    final String prefix,
                    final String year,
                    final String sequence
                    )
    {
        _set( prefix, Integer.valueOf( year ), sequence );
    }



    /**
     */
    private void _set(
                    final String prefix,
                    final int year,
                    final String sequence
                    )
    {
        _setPrefix( prefix );
        _setYear( year );
        _setSequence( sequence );

        _cve_id = PREFIX + SEPARATOR + year + SEPARATOR + sequence;
    }



    /**
     */
    private void _setPrefix(
                    final String prefix
                    )
    {
        if (PREFIX == prefix  ||  PREFIX.equals( prefix )) {
            //valid
        } else {
            throw new IllegalArgumentException( "empty or invalid prefix" );
        }
    }


    public String getPrefix()
    {
        return PREFIX;
    }



    /**
     */
    private void _setYear(
                    final int year
                    )
    {
        _year = year;
    }


    public int getYear()
    {
        return _year;
    }



    /**
     */
    private void _setSequence(
                    final String sequence
                    )
    {
        if (sequence == null  ||  sequence.length() == 0) {
            throw new IllegalArgumentException( "empty sequence" );
        }

        for (int  i = 0; i < sequence.length(); i++) {
            char  c = sequence.charAt( i );
            if ('0' <= c  &&  c <= '9') {
                //OK
            } else {
                throw new IllegalArgumentException( "invalid sequence: " + sequence );
            }
        }

        _sequence = sequence;
    }


    public String getSequence()
    {
        return _sequence;
    }



    /**
     * Returns the string representation.
     */
    public String value()
    {
        return _cve_id;
    }



    //**************************************************************
    //  Comparable
    //**************************************************************

//    @Override
//    public int compareTo(
//                    final OvalIdD o
//                    )
//    {
//        String  id1 = getValue();
//        String  id2 = o.getValue();
//
//        return id1.compareTo( id2 );
//    }



    //**************************************************************
    //  java.lang.Cloneable
    //**************************************************************

    @Override
    public Object clone()
    throws CloneNotSupportedException
    {
        return (new CveId( _year, _sequence ));
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public int hashCode()
    {
        final int  prime = 37;
        int  result = 17;

        result = prime * result + _year;
        result = prime * result + (_sequence == null ? 0 : _sequence.hashCode());

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

        if (!(obj instanceof CveId)) {
            return false;
        }

        CveId  other = (CveId)obj;
        String  other_id = other.toString();
        String   this_id = this.toString();
        if (this_id == other_id
                        ||  (this_id != null  &&  this_id.equalsIgnoreCase( other_id ))) {
            return true;
        }

        return false;
    }



    @Override
    public String toString()
    {
        return _cve_id;
    }

}
//
