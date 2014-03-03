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
package jp.go.aist.six.vuln.repository.scap.cve;

import jp.go.aist.six.util.repository.QueryParams;
import jp.go.aist.six.util.repository.QueryResults;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;




/**
 * The prescription of the NVD repository interface.
 * This repository provides the NVD/CVE vulnerability entries and
 * the CVE dictionary items.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: CveRepository.java 606 2013-06-13 06:10:20Z nakamura5akihito@gmail.com $
 * @see <a href="http://nvd.nist.gov/">National Vulnerability Database (NVD)</a>
 */
public interface CveRepository
{

    //=====================================================================
    // CVE
    //=====================================================================

    /**
     * Returns the CVE item of the specified name.
     *
     * @param   name
     *  the name of the CVE item.
     */
    public ItemType findCveItemByName( String name );



    /**
     * Returns all the CVE items in the database.
     *
     * @return
     *  all the CVE items.
     */
    public QueryResults<ItemType> findCveItem();



    /**
     * Searches for the CVE items that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the found CVE items.
     */
    public QueryResults<ItemType> findCveItem( QueryParams params );



    /**
     * Returns all the CVE names.
     *
     * @return
     *  the found CVE names.
     */
    public QueryResults<String> findCveName();



    /**
     * Searches for the CVE names that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the found CVE names.
     */
    public QueryResults<String> findCveName( QueryParams params );



    /**
     * Returns the number of CVE items in the database.
     *
     * @return
     *  the number of CVE items.
     */
    public long countCveItem();



    /**
     * Returns the number of CVE items that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the number of found CVE items.
     */
    public long countCveItem( QueryParams params );



    /**
     * Saves the CVE item, either inserting or updating the existing one.
     *
     * @param   item
     *  the CVE item to save.
     * @return
     *  the name of the CVE item.
     */
    public String saveCveItem( ItemType item );

}
//

