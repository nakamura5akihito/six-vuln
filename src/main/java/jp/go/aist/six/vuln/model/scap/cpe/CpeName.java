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
package jp.go.aist.six.vuln.model.scap.cpe;

import java.util.Arrays;
import java.util.Iterator;




/**
 * The CPE Name structure.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CpeName.java 536 2013-04-19 02:21:46Z nakamura5akihito@gmail.com $
 */
public final class CpeName
    implements Iterable<String>
{

//    /**
//     * Logger.
//     */
//    private static Log  _LOG = LogFactory.getLog( CpeName.class );



    public static final int  MAX_COMPONENTS = 7;
    public static final String  EMPTY = "";


    public static final String  PREFIX = "cpe:/";

    private static final int  _INDEX_PART_     = 0;
    private static final int  _INDEX_VENDOR_   = 1;
    private static final int  _INDEX_PRODUCT_  = 2;
    private static final int  _INDEX_VERSION_  = 3;
    private static final int  _INDEX_UPDATE_   = 4;
    private static final int  _INDEX_EDITION_  = 5;
    private static final int  _INDEX_LANGUAGE_ = 6;


    private String  _cpename;
    private final String[]  _components = new String[MAX_COMPONENTS];



    /**
     * Constructor.
     */
    public CpeName()
    {
    }


    /**
     * Constructor.
     */
    public CpeName(
                    final String cpename
                    )
    {
        setName( cpename );
    }



    /**
     */
    public void setName(
                    final String cpename
                    )
    {
        if (cpename == null  ||  !cpename.startsWith( PREFIX )) {
            throw new IllegalArgumentException(
                            "invalid CPE name: " + cpename );
        }

        String[]  components = cpename.substring( 5 ).split( ":" );
        // _PREFIX_ "cpe:/".length = 5
//        if (_LOG.isTraceEnabled()) {
//            _LOG.trace( "components=" + Arrays.toString( components ) );
//        }

        if (components.length > MAX_COMPONENTS) {
            throw new IllegalArgumentException(
                            "invalid CPE name: " + cpename );
        }

        _cpename = cpename;

        int  size = components.length;
        for (int  i = 0;  i < size; i++) {
            _setComponent( i, components[i] );
        }
        for (int  i = 0;  i < MAX_COMPONENTS; i++) {
            if (i < size) {
                _setComponent( i, components[i] );
            } else {
                _setComponent( i, EMPTY );
            }
        }
    }



    /**
     */
    public String getName()
    {
        return _cpename;
    }



    /**
     *
     */
    private void _setComponent(
                    final int index,
                    final String value
                    )
    {
        _components[index] = value;
    }



    /**
     */
    private String _getComponent(
                    final int index
                    )
    {
        final String  value = _components[index];
        return (value == null ? EMPTY : value);
    }



    /**
     */
    public String getPart()
    {
        return _getComponent( _INDEX_PART_ );
    }


    /**
     */
    public void setPart(
                    final String part
                    )
    {
        _setComponent( _INDEX_PART_, CpePart.valueOf( part ).getName() );
    }



    /**
     */
    public String getVendor()
    {
        return _getComponent( _INDEX_VENDOR_ );
    }


    /**
     */
    public void setVendor(
                    final String vendor
                    )
    {
        _setComponent( _INDEX_VENDOR_, vendor );
    }



    /**
     */
    public String getProduct()
    {
        return _getComponent( _INDEX_PRODUCT_ );
    }


    /**
     */
    public void setProduct(
                    final String product
                    )
    {
        _setComponent( _INDEX_PRODUCT_, product );
    }



    /**
     */
    public String getVersion()
    {
        return _getComponent( _INDEX_VERSION_ );
    }


    /**
     */
    public void setVersion(
                    final String version
                    )
    {
        _setComponent( _INDEX_VERSION_, version );
    }



    /**
     */
    public String getUpdate()
    {
        return _getComponent( _INDEX_UPDATE_ );
    }


    /**
     */
    public void setUpdate(
                    final String update
                    )
    {
        _setComponent( _INDEX_UPDATE_, update );
    }



    /**
     */
    public String getEdition()
    {
        return _getComponent( _INDEX_EDITION_ );
    }


    /**
     */
    public void setEdition(
                    final String edition
                    )
    {
        _setComponent( _INDEX_EDITION_, edition );
    }



    /**
     */
    public String getLanguage()
    {
        return _getComponent( _INDEX_LANGUAGE_ );
    }


    /**
     */
    public void setLanguage(
                    final String language
                    )
    {
        _setComponent( _INDEX_LANGUAGE_, language );
    }



    //**************************************************************
    //  Iterable
    //**************************************************************

    public Iterator<String> iterator()
    {
        return Arrays.asList( _components ).iterator();
    }



    //**************************************************************
    //  java.lang.Object
    //**************************************************************

    @Override
    public String toString()
    {
        return getName();
    }

}
// CpeNameStructure
