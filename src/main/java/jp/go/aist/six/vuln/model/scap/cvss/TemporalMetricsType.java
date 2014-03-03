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
 * @version $Id: TemporalMetricsType.java 529 2013-04-15 07:08:54Z nakamura5akihito@gmail.com $
 */
public class TemporalMetricsType
    extends MetricsType
{

    /**
     * The temporal score is the temporal multiplier times the base score.
     */
    private Double  score;
//    private BigDecimal  score;
    //{xsd:decimal[0.0-10.0], 0..1}

    /**
     * The temporal multiplier is a number between zero and one.
     * Refer the CVSS standard for computation.
     */
    private Double  temporalMultiplier;
//    private BigDecimal  temporalMultiplier;
    //{0..1}


    // baseVectorsGroup //
    //{0..1}
    private ExploitabilityType  exploitability;
    private RemediationLevelType  remediationLevel;
    private ConfidenceType  reportConfidence;

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
    public TemporalMetricsType()
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
    public void setTemporalMultiplier(
                    final Double temporalMultiplier
                    )
    {
        this.temporalMultiplier = temporalMultiplier;
    }


    public Double getTemporalMultiplier()
    {
        return temporalMultiplier;
    }



    /**
     */
    public ExploitabilityType getExploitability()
    {
        return exploitability;
    }


    public void setExploitability(
                    final ExploitabilityType exploitability
                    )
    {
        this.exploitability = exploitability;
    }



    /**
     */
    public RemediationLevelType getRemediationLevel()
    {
        return remediationLevel;
    }


    public void setRemediationLevel(
                    final RemediationLevelType remediationLevel
                    )
    {
        this.remediationLevel = remediationLevel;
    }



    /**
     */
    public ConfidenceType getReportConfidence()
    {
        return reportConfidence;
    }


    public void setReportConfidence(
                    final ConfidenceType reportConfidence
                    )
    {
        this.reportConfidence = reportConfidence;
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
        final Double  temporalMultiplier = getTemporalMultiplier();
        final ExploitabilityType  exploitability = getExploitability();
        final RemediationLevelType  remediationLevel = getRemediationLevel();
        final ConfidenceType  reportConfidence = getReportConfidence();
        final String  source = getSource();
        final String  generatedOnDatetime = getGeneratedOnDatetime();

        if (score == null) {
            result = prime * result;
        } else {
            long  long_score = Double.doubleToLongBits( score );
            result = prime * result + (int)(Double.doubleToLongBits( long_score ) ^ (long_score >>> 32));
        }

        if (score == null) {
            result = prime * result;
        } else {
            long  long_temporalMultiplier = Double.doubleToLongBits( temporalMultiplier );
            result = prime * result + (int)(Double.doubleToLongBits( long_temporalMultiplier ) ^ (long_temporalMultiplier >>> 32));
        }

        result = prime * result + ((exploitability == null) ? 0 : exploitability.hashCode());
        result = prime * result + ((remediationLevel == null) ? 0 : remediationLevel.hashCode());
        result = prime * result + ((reportConfidence == null) ? 0 : reportConfidence.hashCode());
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

        if (!(obj instanceof TemporalMetricsType)) {
            return false;
        }

        if (super.equals( obj )) {
            final TemporalMetricsType  other = (TemporalMetricsType)obj;

            final Double   this_score =  this.getScore();
            final Double  other_score = other.getScore();
            if (this_score == other_score
                            ||  (this_score != null  &&  this_score.equals( other_score ))) {
                final Double   this_temporalMultiplier =  this.getTemporalMultiplier();
                final Double  other_temporalMultiplier = other.getTemporalMultiplier();
                if (this_temporalMultiplier == other_temporalMultiplier
                                ||  (this_temporalMultiplier != null  &&  this_temporalMultiplier.equals( other_temporalMultiplier ))) {
                    final ExploitabilityType   this_exploitability =  this.getExploitability();
                    final ExploitabilityType  other_exploitability = other.getExploitability();
                    if (this_exploitability == other_exploitability
                                    ||  (this_exploitability != null  &&  this_exploitability.equals( other_exploitability ))) {
                        final RemediationLevelType   this_remediationLevel =  this.getRemediationLevel();
                        final RemediationLevelType  other_remediationLevel = other.getRemediationLevel();
                        if (this_remediationLevel == other_remediationLevel
                                        ||  (this_remediationLevel != null  &&  this_remediationLevel.equals( other_remediationLevel ))) {
                            final ConfidenceType   this_reportConfidence =  this.getReportConfidence();
                            final ConfidenceType  other_reportConfidence = other.getReportConfidence();
                            if (this_reportConfidence == other_reportConfidence
                                            ||  (this_reportConfidence != null  &&  this_reportConfidence.equals( other_reportConfidence ))) {
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

        return false;
    }



    @Override
    public String toString()
    {
        return "TemporalMetricsType[=" + super.toString()
                        + ", score="                    + getScore()
                        + ", temporalMultiplier="       + getTemporalMultiplier()
                        + ", exploitability="           + getExploitability()
                        + ", remediationLevel="         + getRemediationLevel()
                        + ", reportConfidence="         + getReportConfidence()
                        + ", source="                   + getSource()
                        + ", generatedOnDatetime="      + getGeneratedOnDatetime()
                        + "]";
    }

}
//TemporalMetricsType
