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




/**
 * The logical-test element appears as a child of a platform element, and may
 * also be nested to create more complex logical tests.
 * The content consists of one or more elements: fact-ref, and logical-test children
 * are permitted.
 * The operator to be applied, and optional negation of the test, are given as attributes.
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: LogicalTestType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class LogicalTestType
    implements Serializable
{

    private final Collection<LogicalTestType>  logicalTest = new ArrayList<LogicalTestType>();
    //{0..*}

    private final Collection<FactRefType>  factRef = new ArrayList<FactRefType>();
    //{0..*}


    private OperatorEnumeration  operator;
    //{required}

    private boolean  negate;
    //{required}



    /**
     *
     */
    public LogicalTestType()
    {
    }



    /**
     */
    public Collection<LogicalTestType> getLogicalTest()
    {
        return logicalTest;
    }


    public void setLogicalTest(
                    final Collection<? extends LogicalTestType> logicalTest_list
                    )
    {
        if (logicalTest_list != logicalTest) {
            logicalTest.clear();
            if (logicalTest_list != null) {
                logicalTest.addAll( logicalTest_list );
            }
        }
    }



    /**
     */
    public Collection<FactRefType> getFactRef()
    {
        return factRef;
    }


    public void setFactRef(
                    final Collection<? extends FactRefType> factRef_list
                    )
    {
        if (factRef_list != factRef) {
            factRef.clear();
            if (factRef_list != null) {
                factRef.addAll( factRef_list );
            }
        }
    }



    /**
     */
    public OperatorEnumeration getOperator()
    {
        return operator;
    }


    public void setOperator(
                    final OperatorEnumeration operator
                    )
    {
        this.operator = operator;
    }



    /**
     */
    public boolean isNegate()
    {
        return negate;
    }


    public void setNegate(
                    final boolean negate
                    )
    {
        this.negate = negate;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final Collection<LogicalTestType>  logicalTest = getLogicalTest();
        final Collection<FactRefType>  factRef = getFactRef();
        final OperatorEnumeration  operator = getOperator();
        final boolean  negate = isNegate();

        result = prime * result + ((logicalTest == null) ? 0 : logicalTest.hashCode());
        result = prime * result + ((factRef == null) ? 0 : factRef.hashCode());
        result = prime * result + ((operator == null) ? 0 : operator.hashCode());
        result = prime * result + ((negate) ? 0 : 1);

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

        if (!(obj instanceof LogicalTestType)) {
            return false;
        }

        LogicalTestType other = (LogicalTestType)obj;

        final Collection<LogicalTestType>   this_logicalTest =  this.getLogicalTest();
        final Collection<LogicalTestType>  other_logicalTest = other.getLogicalTest();
        if (this_logicalTest == other_logicalTest
                        ||  (this_logicalTest != null  &&  other_logicalTest != null
                        &&  this_logicalTest.size() == other_logicalTest.size()
                        &&  this_logicalTest.containsAll( other_logicalTest ))) {
            final Collection<FactRefType>   this_factRef =  this.getFactRef();
            final Collection<FactRefType>  other_factRef = other.getFactRef();
            if (this_factRef == other_factRef
                            ||  (this_factRef != null  &&  other_factRef != null
                            &&  this_factRef.size() == other_factRef.size()
                            &&  this_factRef.containsAll( other_factRef ))) {
                if (this.getOperator() == other.getOperator()) {
                    if (this.isNegate() == other.isNegate()) {
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
        return "LogicalTestType[logical-test="  + getLogicalTest()
                        + ", fact-ref="         + getFactRef()
                        + ", operator="         + getOperator()
                        + ", negate="           + isNegate()
                        + "]";
    }

}
//LogicalTestType
