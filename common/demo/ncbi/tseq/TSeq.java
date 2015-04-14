
package demo.ncbi.tseq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tSeqSeqtype",
    "tSeqGi",
    "tSeqAccver",
    "tSeqSid",
    "tSeqLocal",
    "tSeqTaxid",
    "tSeqOrgname",
    "tSeqDefline",
    "tSeqLength",
    "tSeqSequence"
})
@XmlRootElement(name = "TSeq")
public class TSeq {

    @XmlElement(name = "TSeq_seqtype", required = true)
    protected TSeqSeqtype tSeqSeqtype;
    @XmlElement(name = "TSeq_gi")
    protected String tSeqGi;
    @XmlElement(name = "TSeq_accver")
    protected String tSeqAccver;
    @XmlElement(name = "TSeq_sid")
    protected String tSeqSid;
    @XmlElement(name = "TSeq_local")
    protected String tSeqLocal;
    @XmlElement(name = "TSeq_taxid")
    protected String tSeqTaxid;
    @XmlElement(name = "TSeq_orgname")
    protected String tSeqOrgname;
    @XmlElement(name = "TSeq_defline", required = true)
    protected String tSeqDefline;
    @XmlElement(name = "TSeq_length", required = true)
    protected String tSeqLength;
    @XmlElement(name = "TSeq_sequence", required = true)
    protected String tSeqSequence;

    /**
     * Gets the value of the tSeqSeqtype property.
     * 
     * @return
     *     possible object is
     *     {@link TSeqSeqtype }
     *     
     */
    public TSeqSeqtype getTSeqSeqtype() {
        return tSeqSeqtype;
    }

    /**
     * Sets the value of the tSeqSeqtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSeqSeqtype }
     *     
     */
    public void setTSeqSeqtype(TSeqSeqtype value) {
        this.tSeqSeqtype = value;
    }

    /**
     * Gets the value of the tSeqGi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqGi() {
        return tSeqGi;
    }

    /**
     * Sets the value of the tSeqGi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqGi(String value) {
        this.tSeqGi = value;
    }

    /**
     * Gets the value of the tSeqAccver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqAccver() {
        return tSeqAccver;
    }

    /**
     * Sets the value of the tSeqAccver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqAccver(String value) {
        this.tSeqAccver = value;
    }

    /**
     * Gets the value of the tSeqSid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqSid() {
        return tSeqSid;
    }

    /**
     * Sets the value of the tSeqSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqSid(String value) {
        this.tSeqSid = value;
    }

    /**
     * Gets the value of the tSeqLocal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqLocal() {
        return tSeqLocal;
    }

    /**
     * Sets the value of the tSeqLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqLocal(String value) {
        this.tSeqLocal = value;
    }

    /**
     * Gets the value of the tSeqTaxid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqTaxid() {
        return tSeqTaxid;
    }

    /**
     * Sets the value of the tSeqTaxid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqTaxid(String value) {
        this.tSeqTaxid = value;
    }

    /**
     * Gets the value of the tSeqOrgname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqOrgname() {
        return tSeqOrgname;
    }

    /**
     * Sets the value of the tSeqOrgname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqOrgname(String value) {
        this.tSeqOrgname = value;
    }

    /**
     * Gets the value of the tSeqDefline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqDefline() {
        return tSeqDefline;
    }

    /**
     * Sets the value of the tSeqDefline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqDefline(String value) {
        this.tSeqDefline = value;
    }

    /**
     * Gets the value of the tSeqLength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqLength() {
        return tSeqLength;
    }

    /**
     * Sets the value of the tSeqLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqLength(String value) {
        this.tSeqLength = value;
    }

    /**
     * Gets the value of the tSeqSequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSeqSequence() {
        return tSeqSequence;
    }

    /**
     * Sets the value of the tSeqSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSeqSequence(String value) {
        this.tSeqSequence = value;
    }

	  @XmlTransient
		public String getFastaHeader()
			{
			return "gi:"+getTSeqGi()+"|len:"+getTSeqLength()+"|"+getTSeqDefline();
			}
		
		public int length()
		  {
		  return getTSeqSequence().length();
		  }

    @Override
    public String toString()
		{
		return getFastaHeader();
		}
}
