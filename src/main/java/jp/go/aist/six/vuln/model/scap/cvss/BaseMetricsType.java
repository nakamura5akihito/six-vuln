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
 * @version $Id: BaseMetricsType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class BaseMetricsType
    extends MetricsType
{

    /**
     * Base severity score assigned to a vulnerability by a source.
     */
    private Double  score;
//    private BigDecimal  score;
    //{xsd:decimal[0.0-10.0], 0..1}

    /**
     * Base exploit sub-score assigned to a vulnerability by a source.
     */
    private Double  exploitSubscore;
//    private BigDecimal  exploitSubscore;
    //{0..1}

    /**
     * Base impact sub-score assigned to a vulnerability by a source.
     */
    private Double  impactSubscore;
//    private BigDecimal  impactSubscore;
    //{0..1}


    // baseVectorsGroup //
    //{0..1}
    private AccessVectorType  accessVector;
    private AccessComplexityType  accessComplexity;
    private AuthenticationType  authentication;
    private CiaType  confidentialityImpact;
    private CiaType  integrityImpact;
    private CiaType  availabilityImpact;

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
    public BaseMetricsType()
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
    public void setExploitSubscore(
                    final Double exploitSubscore
                    )
    {
        this.exploitSubscore = exploitSubscore;
    }


    public Double getExploitSubscore()
    {
        return exploitSubscore;
    }



    /**
     */
    public Double getImpactSubscore()
    {
        return impactSubscore;
    }


    public void setImpactSubscore(
                    final Double impactSubscore
                    )
    {
        this.impactSubscore = impactSubscore;
    }



    /**
     */
    public AccessVectorType getAccessVector()
    {
        return accessVector;
    }


    public void setAccessVector(
                    final AccessVectorType accessVector
                    )
    {
        this.accessVector = accessVector;
    }



    /**
     */
    public AccessComplexityType getAccessComplexity()
    {
        return accessComplexity;
    }


    public void setAccessComplexity(
                    final AccessComplexityType accessComplexity
                    )
    {
        this.accessComplexity = accessComplexity;
    }



    /**
     */
    public AuthenticationType getAuthentication()
    {
        return authentication;
    }


    public void setAuthentication(
                    final AuthenticationType authentication
                    )
    {
        this.authentication = authentication;
    }



    /**
     */
    public CiaType getConfidentialityImpact()
    {
        return confidentialityImpact;
    }


    public void setConfidentialityImpact(
                    final CiaType confidentialityImpact
                    )
    {
        this.confidentialityImpact = confidentialityImpact;
    }



    /**
     */
    public CiaType getIntegrityImpact()
    {
        return integrityImpact;
    }


    public void setIntegrityImpact(
                    final CiaType integrityImpact
                    )
    {
        this.integrityImpact = integrityImpact;
    }



    /**
     */
    public CiaType getAvailabilityImpact()
    {
        return availabilityImpact;
    }


    public void setAvailabilityImpact(
                    final CiaType availabilityImpact
                    )
    {
        this.availabilityImpact = availabilityImpact;
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
        final Double  exploitSubscore = getExploitSubscore();
        final Double  impactSubscore = getImpactSubscore();
        final AccessVectorType  accessVector = getAccessVector();
        final AccessComplexityType  accessComplexity = getAccessComplexity();
        final AuthenticationType  authentication = getAuthentication();
        final CiaType  confidentialityImpact = getConfidentialityImpact();
        final CiaType  integrityImpact = getIntegrityImpact();
        final CiaType  availabilityImpact = getAvailabilityImpact();
        final String  source = getSource();
        final String  generatedOnDatetime = getGeneratedOnDatetime();

//        result = prime * result + ((score == null) ? 0 : score.hashCode());
        if (score == null) {
            result = prime * result;
        } else {
            long  long_score = Double.doubleToLongBits( score );
            result = prime * result + (int)(Double.doubleToLongBits( long_score ) ^ (long_score >>> 32));
        }

        if (exploitSubscore == null) {
            result = prime * result;
        } else {
            long  long_exploitSubscore = Double.doubleToLongBits( exploitSubscore );
            result = prime * result + (int)(Double.doubleToLongBits( long_exploitSubscore ) ^ (long_exploitSubscore >>> 32));
        }

        if (impactSubscore == null) {
            result = prime * result;
        } else {
            long  long_impactSubscore = Double.doubleToLongBits( impactSubscore );
            result = prime * result + (int)(Double.doubleToLongBits( long_impactSubscore ) ^ (long_impactSubscore >>> 32));
        }

        result = prime * result + ((accessVector == null) ? 0 : accessVector.hashCode());
        result = prime * result + ((accessComplexity == null) ? 0 : accessComplexity.hashCode());
        result = prime * result + ((authentication == null) ? 0 : authentication.hashCode());
        result = prime * result + ((confidentialityImpact == null) ? 0 : confidentialityImpact.hashCode());
        result = prime * result + ((integrityImpact == null) ? 0 : integrityImpact.hashCode());
        result = prime * result + ((availabilityImpact == null) ? 0 : availabilityImpact.hashCode());
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

        if (!(obj instanceof BaseMetricsType)) {
            return false;
        }

        if (super.equals( obj )) {
            final BaseMetricsType  other = (BaseMetricsType)obj;

//            final BigDecimal   this_score =  this.getScore();
//            final BigDecimal  other_score = other.getScore();
//            if (this_score == other_score
//                            ||  (this_score != null  &&  this_score.equals( other_score ))) {
            final Double   this_score =  this.getScore();
            final Double  other_score = other.getScore();
            if (this_score == other_score
                            ||  (this_score != null  &&  this_score.equals( other_score ))) {
                final Double   this_exploitSubscore =  this.getExploitSubscore();
                final Double  other_exploitSubscore = other.getExploitSubscore();
                if (this_exploitSubscore == other_exploitSubscore
                                ||  (this_exploitSubscore != null  &&  this_exploitSubscore.equals( other_exploitSubscore ))) {
                    final Double   this_impactSubscore =  this.getImpactSubscore();
                    final Double  other_impactSubscore = other.getImpactSubscore();
                    if (this_impactSubscore == other_impactSubscore
                                    ||  (this_impactSubscore != null  &&  this_impactSubscore.equals( other_impactSubscore ))) {
                        final AccessVectorType   this_accessVector =  this.getAccessVector();
                        final AccessVectorType  other_accessVector = other.getAccessVector();
                        if (this_accessVector == other_accessVector
                                        ||  (this_accessVector != null  &&  this_accessVector.equals( other_accessVector ))) {
                            final AccessComplexityType   this_accessComplexity =  this.getAccessComplexity();
                            final AccessComplexityType  other_accessComplexity = other.getAccessComplexity();
                            if (this_accessComplexity == other_accessComplexity
                                            ||  (this_accessComplexity != null  &&  this_accessComplexity.equals( other_accessComplexity ))) {
                                final AuthenticationType   this_authentication =  this.getAuthentication();
                                final AuthenticationType  other_authentication = other.getAuthentication();
                                if (this_authentication == other_authentication
                                                ||  (this_authentication != null  &&  this_authentication.equals( other_authentication ))) {
                                    final CiaType   this_confidentialityImpact =  this.getConfidentialityImpact();
                                    final CiaType  other_confidentialityImpact = other.getConfidentialityImpact();
                                    if (this_confidentialityImpact == other_confidentialityImpact
                                                    ||  (this_confidentialityImpact != null  &&  this_confidentialityImpact.equals( other_confidentialityImpact ))) {
                                        final CiaType   this_integrityImpact =  this.getIntegrityImpact();
                                        final CiaType  other_integrityImpact = other.getIntegrityImpact();
                                        if (this_integrityImpact == other_integrityImpact
                                                        ||  (this_integrityImpact != null  &&  this_integrityImpact.equals( other_integrityImpact ))) {
                                            final CiaType   this_availabilityImpact =  this.getAvailabilityImpact();
                                            final CiaType  other_availabilityImpact = other.getAvailabilityImpact();
                                            if (this_availabilityImpact == other_availabilityImpact
                                                            ||  (this_availabilityImpact != null  &&  this_availabilityImpact.equals( other_availabilityImpact ))) {
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
                }
            }
        }

        return false;
    }



    @Override
    public String toString()
    {
        return "BaseMetricsType[=" + super.toString()
                        + ", score="                    + getScore()
                        + ", exploitSubscore="          + getExploitSubscore()
                        + ", impactSubscore="           + getImpactSubscore()
                        + ", accessVector="             + getAccessVector()
                        + ", accessComplexity="         + getAccessComplexity()
                        + ", authentication="           + getAuthentication()
                        + ", confidentialityImpact="    + getConfidentialityImpact()
                        + ", integrityImpact="          + getIntegrityImpact()
                        + ", availabilityImpact="       + getAvailabilityImpact()
                        + ", source="                   + getSource()
                        + ", generatedOnDatetime="      + getGeneratedOnDatetime()
                        + "]";
    }

}
//BaseMetricsType
