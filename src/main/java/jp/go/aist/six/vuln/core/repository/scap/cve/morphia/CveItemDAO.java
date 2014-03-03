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
package jp.go.aist.six.vuln.core.repository.scap.cve.morphia;

import jp.go.aist.six.util.core.repository.morphia.BaseDAO;
import jp.go.aist.six.vuln.model.scap.cve.ItemType;
import org.mongodb.morphia.Datastore;



/**
 * @author  Akihito Nakamura, AIST
 * @version $Id: CveItemDAO.java 602 2013-06-12 07:55:35Z nakamura5akihito@gmail.com $
 */
public class CveItemDAO
    extends BaseDAO<ItemType, String>
{

    /**
     */
    public CveItemDAO(
                    final Datastore ds
                    )
    {
        super( ItemType.class, ds );
    }

}
//

