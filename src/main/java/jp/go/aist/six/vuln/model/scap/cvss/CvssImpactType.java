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
package jp.go.aist.six.vuln.model.scap.cvss;

import java.io.Serializable;



/**
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: CvssImpactType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class CvssImpactType
    implements Serializable
{

    private BaseMetricsType  baseMetrics;
    //{1..1}

    private EnvironmentalMetricsType  environmentalMetrics;
    //{0..1}

    private TemporalMetricsType  temporalMetrics;
    //{0..1}



    /**
     * Constructs an instance.
     */
    public CvssImpactType()
    {
    }



    /**
     */
    public void setBaseMetrics(
                    final BaseMetricsType baseMetrics
                    )
    {
        this.baseMetrics = baseMetrics;
    }


    public BaseMetricsType getBaseMetrics()
    {
        return baseMetrics;
    }



    /**
     */
    public EnvironmentalMetricsType getEnvironmentalMetrics()
    {
        return environmentalMetrics;
    }


    public void setEnvironmentalMetrics(
                    final EnvironmentalMetricsType environmentalMetrics
                    )
    {
        this.environmentalMetrics = environmentalMetrics;
    }



    /**
     */
    public TemporalMetricsType getTemporalMetrics()
    {
        return temporalMetrics;
    }


    public void setTemporalMetrics(
                    final TemporalMetricsType temporalMetrics
                    )
    {
        this.temporalMetrics = temporalMetrics;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 17;

        final BaseMetricsType  baseMetrics = getBaseMetrics();
        final EnvironmentalMetricsType  environmentalMetrics = getEnvironmentalMetrics();
        final TemporalMetricsType  temporalMetrics = getTemporalMetrics();

        result = prime * result + ((baseMetrics == null) ? 0 : baseMetrics.hashCode());
        result = prime * result + ((environmentalMetrics == null) ? 0 : environmentalMetrics.hashCode());
        result = prime * result + ((temporalMetrics == null) ? 0 : temporalMetrics.hashCode());

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

        if (!(obj instanceof CvssImpactType)) {
            return false;
        }

        final CvssImpactType  other = (CvssImpactType)obj;

        final BaseMetricsType   this_baseMetrics =  this.getBaseMetrics();
        final BaseMetricsType  other_baseMetrics = other.getBaseMetrics();
        if (this_baseMetrics == other_baseMetrics
                        ||  (this_baseMetrics != null  &&  this_baseMetrics.equals( other_baseMetrics ))) {
            final EnvironmentalMetricsType   this_environmentalMetrics =  this.getEnvironmentalMetrics();
            final EnvironmentalMetricsType  other_environmentalMetrics = other.getEnvironmentalMetrics();
            if (this_environmentalMetrics == other_environmentalMetrics
                            ||  (this_environmentalMetrics != null  &&  this_environmentalMetrics.equals( other_environmentalMetrics ))) {
                final TemporalMetricsType   this_temporalMetrics =  this.getTemporalMetrics();
                final TemporalMetricsType  other_temporalMetrics = other.getTemporalMetrics();
                if (this_temporalMetrics == other_temporalMetrics
                                ||  (this_temporalMetrics != null  &&  this_temporalMetrics.equals( other_temporalMetrics ))) {
                    return true;
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "CvssImpactType[baseMetrics="        + getBaseMetrics()
                        + ", environmentalMetrics=" + getEnvironmentalMetrics()
                        + ", temporalMetrics="      + getTemporalMetrics()
                        + "]";
    }

}
//CvssImpactType
