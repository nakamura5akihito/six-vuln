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
package jp.go.aist.six.vuln.repository.scap.nvd;

import jp.go.aist.six.util.repository.QueryParams;
import jp.go.aist.six.util.repository.QueryResults;
import jp.go.aist.six.vuln.model.scap.cpe.dictionary.ItemType;
import jp.go.aist.six.vuln.model.scap.vulnerability.VulnerabilityType;




/**
 * The prescription of the NVD repository interface.
 * This repository provides the NVD/CVE vulnerability entries and
 * the CPE dictionary items.
 *
 * @author  Akihito Nakamura, AIST
 * @version $Id: NvdRepository.java 606 2013-06-13 06:10:20Z nakamura5akihito@gmail.com $
 * @see <a href="http://nvd.nist.gov/">National Vulnerability Database (NVD)</a>
 */
public interface NvdRepository
{

    //=====================================================================
    // Vulnerability
    //=====================================================================

    /**
     * Returns the vulnerability entry of the specified ID.
     *
     * @param   id
     *  the ID of the vulnerability.
     */
    public VulnerabilityType findVulnerabilityById( String id );



//    /**
//     * Returns the vulnerability detail information of the specified ID.
//     *
//     * @param   id
//     *  the ID of the vulnerability.
//     */
//    public VulnerabilityDetail getVulnerabilityDetail( String id )
//        throws VulnServiceException;


    /**
     * Returns all the vulnerability entries in the database.
     *
     * @return
     *  all the vulnerability entries.
     */
    public QueryResults<VulnerabilityType> findVulnerability();



    /**
     * Searches for the vulnerability entries that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the found vulnerability entries.
     */
    public QueryResults<VulnerabilityType> findVulnerability( QueryParams params );



    /**
     * Returns all the vulnerability IDs.
     *
     * @return
     *  the found vulnerability IDs.
     */
    public QueryResults<String> findVulnerabilityId();



    /**
     * Searches for the vulnerability IDs that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the found vulnerability IDs.
     */
    public QueryResults<String> findVulnerabilityId( QueryParams params );



    /**
     * Returns the number of vulnerability entries in the database.
     *
     * @return
     *  the number of vulnerability entries.
     */
    public long countVulnerability();



    /**
     * Returns the number of vulnerability entries that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     */
    public long countVulnerability( QueryParams params );



    /**
     * Saves the vulnerability entry, either inserting or updating the existing one.
     *
     * @param   vulnerability
     *  the vulnerability entry to save.
     * @return
     *  the ID of the vulnerability.
     */
    public String saveVulnerability( VulnerabilityType vulnerability );



    //=====================================================================
    // CPE
    //=====================================================================

    /**
     * Returns the CPE item of the specified name.
     *
     * @param   name
     *  the name of the CPE item.
     */
    public ItemType findCpeItemByName( String name );



    /**
     * Returns all the CPE items in the database.
     *
     * @return
     *  all the CPE items.
     */
    public QueryResults<ItemType> findCpeItem();



    /**
     * Searches for the CPE items that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the found CPE items.
     */
    public QueryResults<ItemType> findCpeItem( QueryParams params );



    /**
     * Returns all the CPE names.
     *
     * @return
     *  the found CPE names.
     */
    public QueryResults<String> findCpeName();



    /**
     * Searches for the CPE names that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the found CPE names.
     */
    public QueryResults<String> findCpeName( QueryParams params );



    /**
     * Returns the number of CPE items in the database.
     *
     * @return
     *  the number of CPE items.
     */
    public long countCpeItem();



    /**
     * Returns the number of CPE items that match the specified query parameters.
     *
     * @param   params
     *  the query parameters.
     * @return
     *  the number of found CPE items.
     */
    public long countCpeItem( QueryParams params );



    /**
     * Saves the CPE item, either inserting or updating the existing one.
     *
     * @param   item
     *  the CPE item to save.
     * @return
     *  the name of the CPE item.
     */
    public String saveCpeItem( ItemType item );

}
//

