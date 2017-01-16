package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Gabinet.getOneGabinet", query = "Select g from Gabinet g where g.id = :id"),
	@NamedQuery(name = "Gabinet.getAllGabinety", query = "Select g from Gabinet g"),
	@NamedQuery(name = "Gabinet.lekarzGabinet", query = "Select g from Gabinet g where g.lekarz = :lekarz")
})
public class Gabinet {

	private long id;
	private String numer;
	private String pietro;
	private String lekarz;
	
	public Gabinet(){
		
	}
	
	public Gabinet(String numer, String pietro, String lekarz){
		super();
		this.numer = numer;
		this.pietro = pietro;
		this.lekarz = lekarz;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false)
	public String getNumer() {
		return numer;
	}

	public void setNumer(String numer) {
		this.numer = numer;
	}

	public String getPietro() {
		return pietro;
	}

	public void setPietro(String pietro) {
		this.pietro = pietro;
	}

	public String getLekarz() {
		return lekarz;
	}

	public void setLekarz(String lekarz) {
		this.lekarz = lekarz;
	}
	
	
	
	
	
}
