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




/**
 *
 *
 * @author	Akihito Nakamura, AIST
 * @version $Id: EnvironmentalMetricsType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class EnvironmentalMetricsType
    extends MetricsType
{

    private Double  score;
//    private BigDecimal  score;
    //{xsd:decimal[0.0-10.0], 0..1}

    // environmentalVectorsGroup //
    //{0..1}
    private CollateralDamagePotentialType   collateralDamagePotential;
    private TargetDistributionType          targetDistribution;
    private CiaRequirementType              confidentialityRequirement;
    private CiaRequirementType              integrityRequirement;
    private CiaRequirementType              availabilityRequirement;

    /**
     * Data source the vector was obtained from.
     * Example: http://nvd.nist.gov or com.symantec.deepsight.
     */
    private String  source;
    //{xsd:anyURI}

    private String  generatedOnDatetime;
//    private Date  generatedOnDatetime;
    //{xsd:dateTime, 0..1}



    /**
     * Constructs an instance.
     */
    public EnvironmentalMetricsType()
    {
    }



    /**
     */
    public void setScore(
                    final Double score
                    )
    {
        this.score = score;
    }


    public Double getScore()
    {
        return score;
    }



    /**
     */
    public CollateralDamagePotentialType getCollateralDamagePotential()
    {
        return collateralDamagePotential;
    }


    public void setCollateralDamagePotential(
                    final CollateralDamagePotentialType collateralDamagePotential
                    )
    {
        this.collateralDamagePotential = collateralDamagePotential;
    }



    /**
     */
    public TargetDistributionType getTargetDistribution()
    {
        return targetDistribution;
    }


    public void setTargetDistribution(
                    final TargetDistributionType targetDistribution
                    )
    {
        this.targetDistribution = targetDistribution;
    }



    /**
     */
    public CiaRequirementType getConfidentialityRequirement()
    {
        return confidentialityRequirement;
    }


    public void setConfidentialityRequirement(
                    final CiaRequirementType confidentialityRequirement
                    )
    {
        this.confidentialityRequirement = confidentialityRequirement;
    }



    /**
     */
    public CiaRequirementType getIntegrityRequirement()
    {
        return integrityRequirement;
    }


    public void setIntegrityRequirement(
                    final CiaRequirementType integrityRequirement
                    )
    {
        this.integrityRequirement = integrityRequirement;
    }



    /**
     */
    public CiaRequirementType getAvailabilityRequirement()
    {
        return availabilityRequirement;
    }


    public void setAvailabilityRequirement(
                    final CiaRequirementType availabilityRequirement
                    )
    {
        this.availabilityRequirement = availabilityRequirement;
    }



    /**
     */
    public String getSource()
    {
        return source;
    }


    public void setSource(
                    final String source
                    )
    {
        this.source = source;
    }



    /**
     */
    public String getGeneratedOnDatetime()
    {
        return generatedOnDatetime;
    }


    public void setGeneratedOnDatetime(
                    final String datetime
                    )
    {
        generatedOnDatetime = datetime;
    }



    //*********************************************************************
    //  java.lang.Object
    //*********************************************************************

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = super.hashCode();

        final Double  score = getScore();
        final CollateralDamagePotentialType  collateralDamagePotential = getCollateralDamagePotential();
        final TargetDistributionType  targetDistribution = getTargetDistribution();
        final CiaRequirementType  confidentialityRequirement = getConfidentialityRequirement();
        final CiaRequirementType  integrityRequirement = getIntegrityRequirement();
        final CiaRequirementType  availabilityRequirement = getAvailabilityRequirement();
        final String  source = getSource();
        final String  generatedOnDatetime = getGeneratedOnDatetime();

//      result = prime * result + ((score == null) ? 0 : score.hashCode());
        if (score == null) {
            result = prime * result;
        } else {
            long  long_score = Double.doubleToLongBits( score );
            result = prime * result + (int)(Double.doubleToLongBits( long_score ) ^ (long_score >>> 32));
        }

        result = prime * result + ((collateralDamagePotential == null) ? 0 : collateralDamagePotential.hashCode());
        result = prime * result + ((targetDistribution == null) ? 0 : targetDistribution.hashCode());
        result = prime * result + ((confidentialityRequirement == null) ? 0 : confidentialityRequirement.hashCode());
        result = prime * result + ((integrityRequirement == null) ? 0 : integrityRequirement.hashCode());
        result = prime * result + ((availabilityRequirement == null) ? 0 : availabilityRequirement.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((generatedOnDatetime == null) ? 0 : generatedOnDatetime.hashCode());

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

        if (!(obj instanceof EnvironmentalMetricsType)) {
            return false;
        }

        if (super.equals( obj )) {
            final EnvironmentalMetricsType  other = (EnvironmentalMetricsType)obj;

            final Double   this_score =  this.getScore();
            final Double  other_score = other.getScore();
            if (this_score == other_score
                            ||  (this_score != null  &&  this_score.equals( other_score ))) {
                final CollateralDamagePotentialType   this_collateralDamagePotential =  this.getCollateralDamagePotential();
                final CollateralDamagePotentialType  other_collateralDamagePotential = other.getCollateralDamagePotential();
                if (this_collateralDamagePotential == other_collateralDamagePotential
                                ||  (this_collateralDamagePotential != null  &&  this_collateralDamagePotential.equals( other_collateralDamagePotential ))) {
                    final TargetDistributionType   this_targetDistribution =  this.getTargetDistribution();
                    final TargetDistributionType  other_targetDistribution = other.getTargetDistribution();
                    if (this_targetDistribution == other_targetDistribution
                                    ||  (this_targetDistribution != null  &&  this_targetDistribution.equals( other_targetDistribution ))) {
                        final CiaRequirementType   this_confidentialityRequirement =  this.getConfidentialityRequirement();
                        final CiaRequirementType  other_confidentialityRequirement = other.getConfidentialityRequirement();
                        if (this_confidentialityRequirement == other_confidentialityRequirement
                                        ||  (this_confidentialityRequirement != null  &&  this_confidentialityRequirement.equals( other_confidentialityRequirement ))) {
                            final CiaRequirementType   this_integrityRequirement =  this.getIntegrityRequirement();
                            final CiaRequirementType  other_integrityRequirement = other.getIntegrityRequirement();
                            if (this_integrityRequirement == other_integrityRequirement
                                            ||  (this_integrityRequirement != null  &&  this_integrityRequirement.equals( other_integrityRequirement ))) {
                                final CiaRequirementType   this_availabilityRequirement =  this.getAvailabilityRequirement();
                                final CiaRequirementType  other_availabilityRequirement = other.getAvailabilityRequirement();
                                if (this_availabilityRequirement == other_availabilityRequirement
                                                ||  (this_availabilityRequirement != null  &&  this_availabilityRequirement.equals( other_availabilityRequirement ))) {
                                    final String   this_source =  this.getSource();
                                    final String  other_source = other.getSource();
                                    if (this_source == other_source
                                                    ||  (this_source != null  &&  this_source.equals( other_source ))) {
                                        final String   this_generatedOnDatetime =  this.getGeneratedOnDatetime();
                                        final String  other_generatedOnDatetime = other.getGeneratedOnDatetime();
                                        if (this_generatedOnDatetime == other_generatedOnDatetime
                                                        ||  (this_generatedOnDatetime != null  &&  this_generatedOnDatetime.equals( other_generatedOnDatetime ))) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "EnvironmentalMetricsType[=" + super.toString()
                        + ", score="                    + getScore()
                        + ", accessVector="             + getCollateralDamagePotential()
                        + ", accessComplexity="         + getTargetDistribution()
                        + ", authentication="           + getConfidentialityRequirement()
                        + ", confidentialityImpact="    + getIntegrityRequirement()
                        + ", availabilityImpact="       + getAvailabilityRequirement()
                        + ", source="                   + getSource()
                        + ", generatedOnDatetime="      + getGeneratedOnDatetime()
                        + "]";
    }

}
//EnvironmentalMetricsType
