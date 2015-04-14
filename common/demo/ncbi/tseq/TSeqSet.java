
package demo.ncbi.tseq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tSeq"
})
@XmlRootElement(name = "TSeqSet")
public class TSeqSet {

    @XmlElement(name = "TSeq")
    protected List<TSeq> tSeq;

    /**
     * Gets the value of the tSeq property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tSeq property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTSeq().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSeq }
     * 
     * 
     */
    public List<TSeq> getTSeq() {
        if (tSeq == null) {
            tSeq = new ArrayList<TSeq>();
        }
        return this.tSeq;
    }


	public static TSeqSet eFetch(int...ids) throws java.io.IOException
		{
		StringBuilder sb=new StringBuilder("http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nuccore&retmode=xml&rettype=fasta&id=");
		for(int i=0;i< ids.length;++i)
			{
			if(i>0) sb.append(",");
			sb.append(String.valueOf(ids[i]));
			}
		return eFetch(new java.net.URL(sb.toString()));
		}

	public static TSeqSet eFetch(java.net.URL url) throws java.io.IOException
		{
		java.io.InputStream in=null;
		try
			{
			in = url.openStream();
			return eFetch(in);
			}
		finally
			{
			if(in!=null) try{in.close();}catch(java.io.IOException err){}
			}
		}



	public static TSeqSet eFetch(java.io.InputStream in) throws java.io.IOException
		{
		try
			{
			javax.xml.bind.JAXBContext jc = javax.xml.bind.JAXBContext.newInstance("demo.ncbi.tseq");
			javax.xml.bind.Unmarshaller u = jc.createUnmarshaller();
			return (TSeqSet)u.unmarshal(in);
			}
		catch( javax.xml.bind.JAXBException err)
			{
			throw new java.io.IOException(err);
			}
		}

	public void print(java.io.OutputStream out) throws java.io.IOException
		{
		try
			{
			javax.xml.bind.JAXBContext jc = javax.xml.bind.JAXBContext.newInstance("demo.ncbi.tseq");
			javax.xml.bind.Marshaller m= jc.createMarshaller();
			/** echo the sequence to stdout */
			m.marshal(this,out);
			out.flush();
			}
		catch( javax.xml.bind.JAXBException err)
			{
			throw new java.io.IOException(err);
			}
		}
}
