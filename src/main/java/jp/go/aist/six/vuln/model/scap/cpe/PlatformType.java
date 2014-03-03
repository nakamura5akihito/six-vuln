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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.vuln.model.scap.core.TextType;




/**
 * The platform represents the description or qualifications of a particular IT platform type.
 * The platform is defined by the logical-test.
 * The id attribute holds a locally unique name for the platform.
 * There is no defined format for this id, it just has to be
 * unique to the containing language document.
 *
 * <p>
 * The optional title element may appear as a child to a platform element.
 * It provides a human-readable title for it.
 * To support uses intended for multiple languages, this element supports the 'xml:lang' attribute.
 * At most one title element can appear for each language.
 * </p>
 * <p>
 * The optional remark element may appear as a child of a platform element.
 * It provides some additional description. Zero or more remark elements may appear.
 * To support uses intended for multiple languages, this element supports the 'xml:lang' attribute.
 * There can be multiple remarks for a single language.
 * </p>
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: PlatformType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class PlatformType
    implements Serializable
{

    private final Collection<TextType>  title = new ArrayList<TextType>();
    //{0..*}

    private final Collection<TextType>  remark = new ArrayList<TextType>();
    //{0..*}

    private LogicalTestType  logicalTest;
    //{1..1}

//    @Field( "id" )  //spring-data-mongodb
    private String  id;
    //{xsd:anyURI, required}



    /**
     * Constructs an instance.
     */
    public PlatformType()
    {
    }



    /**
     */
    public Collection<TextType> getTitle()
    {
        return title;
    }


    public void setTitle(
                    final Collection<? extends TextType> title_list
                    )
    {
        if (title_list != title) {
            title.clear();
            if (title_list != null) {
                title.addAll( title_list );
            }
        }
    }



    /**
     */
    public Collection<TextType> getRemark()
    {
        return remark;
    }


    public void setRemark(
                    final Collection<? extends TextType> remark_list
                    )
    {
        if (remark_list != remark) {
            remark.clear();
            if (remark_list != null) {
                remark.addAll( remark_list );
            }
        }
    }



    /**
     */
    public LogicalTestType getLogicalTest()
    {
        return logicalTest;
    }


    public void setLogicalTest(
                    final LogicalTestType logicalTest
                    )
    {
        this.logicalTest = logicalTest;
    }



    /**
     */
    public String getId()
    {
        return id;
    }


    public void setId(
                    final String id
                    )
    {
        this.id = id;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final Collection<TextType>  title = getTitle();
        final Collection<TextType>  remark = getRemark();
        final LogicalTestType  logicalTest = getLogicalTest();
        final String  id = getId();

        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((logicalTest == null) ? 0 : logicalTest.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());

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

        if (!(obj instanceof PlatformType)) {
            return false;
        }

        PlatformType  other = (PlatformType)obj;

        final Collection<TextType>   this_title =  this.getTitle();
        final Collection<TextType>  other_title = other.getTitle();
        if (this_title == other_title
                        ||  (this_title != null  &&  other_title != null
                        &&  this_title.size() == other_title.size()
                        &&  this_title.containsAll( other_title ))) {
            final Collection<TextType>   this_remark =  this.getRemark();
            final Collection<TextType>  other_remark = other.getRemark();
            if (this_remark == other_remark
                            ||  (this_remark != null  &&  other_remark != null
                            &&  this_remark.size() == other_remark.size()
                            &&  this_remark.containsAll( other_remark ))) {
                final LogicalTestType   this_logicalTest =  this.getLogicalTest();
                final LogicalTestType  other_logicalTest = other.getLogicalTest();
                if (this_logicalTest == other_logicalTest
                                ||  (this_logicalTest != null  &&  this_logicalTest.equals( other_logicalTest ))) {
                    final String   this_id =  this.getId();
                    final String  other_id = other.getId();
                    if (this_id == other_id
                                    ||  (this_id != null  &&  this_id.equals( other_id ))) {
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
        return "PlatformType[title="        + getTitle()
                        + ", remark="       + getRemark()
                        + ", logical-test=" + getLogicalTest()
                        + ", id="           + getId()
                        + "]";
    }

}
//PlatformType
