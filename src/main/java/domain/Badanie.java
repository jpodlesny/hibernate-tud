package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Badanie.getOneBadanie", query = "Select b from Badanie b where b.id = :id"),
	@NamedQuery(name = "Badanie.getAllBadania", query = "Select b from Badanie b"),
	@NamedQuery(name = "Badanie.kosztBadanie", query = "Select b from Badanie b where b.koszt = :koszt")
})
public class Badanie {
	
	private long id;
	private String nazwa;
	private String opis;
	private String koszt;
	private List<Gabinet> gabinety = new ArrayList<Gabinet>();
	
	public Badanie(){
		
	}
	
	public Badanie(String nazwa, String opis, String koszt){
		super();
		this.nazwa = nazwa;
		this.opis = opis;
		this.koszt = koszt;
	}

	public Badanie(String nazwa, String opis, String koszt, List<Gabinet> gabinety){
		super();
		this.nazwa = nazwa;
		this.opis = opis;
		this.koszt = koszt;
		this.gabinety = gabinety;
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
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getKoszt() {
		return koszt;
	}

	public void setKoszt(String koszt) {
		this.koszt = koszt;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Gabinet> getGabinety() {
		return gabinety;
	}
	public void setGabinety(List<Gabinet> gabinety) {
		this.gabinety = gabinety;
	}
	
}
