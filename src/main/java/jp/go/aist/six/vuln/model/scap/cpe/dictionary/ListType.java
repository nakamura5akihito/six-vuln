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

import java.util.ArrayList;
import java.util.Collection;
import jp.go.aist.six.util.persist.Persistable;
import org.mongodb.morphia.annotations.Id;




/**
 * The ListType complex type defines an element that is used to hold
 * a collection of individual items.
 * The required generator section provides information about
 * when the definition file was compiled and under what version.
 * Additional elements not part of the CPE namespace are allowed and
 * are just skipped by validation.
 * In essence, a dictionary file can contain additional information
 * that user can choose to use or not, but this information is not required
 * to be used or understood.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: ListType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class ListType
    implements Persistable<String>
{

    //MongoDB
    @Id
    private String  _pid;


    /**
     */
    private GeneratorType  generator;
    //{0..1}


    /**
     */
    private final Collection<ItemType>  cpe_item = new ArrayList<ItemType>();
    //{1..*}


    private ComponentTree  _componentTree = new ComponentTree();
//    private Collection<AdditionalListElement>  _additionalElements
//    = new ArrayList<AdditionalListElement>();
    //{xsd:any, 0..*}


    /**
     * Constructor.
     */
    public ListType()
    {
    }



    /**
     */
    public void setGenerator(
                    final GeneratorType generator
                    )
    {
        this.generator = generator;
    }


    /**
     */
    public GeneratorType getGenerator()
    {
        return generator;
    }



    /**
     */
    public Collection<ItemType> getCpeItem()
    {
        return cpe_item;
    }


    /**
     */
    public void setCpeItem(
                    final Collection<? extends ItemType> item_list
                    )
    {
        if (item_list != cpe_item) {
            cpe_item.clear();
            if (item_list != null  &&  item_list.size() > 0) {
                cpe_item.addAll( item_list );
            }
        }
    }



    /**
     */
    public void setComponentTree(
                    final ComponentTree tree
                    )
    {
        _componentTree = tree;
    }


    /**
     */
    public ComponentTree getComponentTree()
    {
        return _componentTree;
    }



//    /**
//     */
//    public void setAdditionalElements(
//                    final Collection<AdditionalListElement> elements
//                    )
//    {
//        if (elements != _additionalElements) {
//            _additionalElements.clear();
//            if (elements != null  &&  elements.size() > 0) {
//                _additionalElements.addAll( elements );
//            }
//        }
//    }
//
//
//    /**
//     */
//    public Collection<AdditionalListElement> getAdditionalElements()
//    {
//        return _additionalElements;
//    }



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
        return "[generator=" + getGenerator()
                        + "]";
    }

}
//
