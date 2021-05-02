package es.unex.pi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Favorite {
	
	private long idu;
	private long idp;
	
	public long getIdu() {
		return idu;
	}

	public void setIdu(long idu) {
		this.idu = idu;
	}

	public long getIdp() {
		return idp;
	}
	
	public void setIdp(long idp) {
		this.idp = idp;
	}

	@Override
	public String toString() {
		return "Favorite [idu=" + idu + ", idp=" + idp + "]";
	}
	
}
