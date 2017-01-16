package service;

import java.util.List;

import domain.*;

public interface SzpitalManager{
	
	void addBadanie(Badanie badanie);
	void deleteBadanie(Badanie badanie);
	void editBadanie(Badanie badanie1, Badanie badanie2);
	
	Badanie getOneBadanie(long id);
	List<Badanie> getAllBadania();
	List<Badanie> kosztBadanie(String koszt);
	List<Gabinet> getAllGabinetyFromBadanie(Badanie badanie);

	
	
	void addGabinet(Gabinet gabinet);
	void deleteGabinet(Gabinet gabinet);
	void editGabinet(Gabinet gabinet1, Gabinet gabinet2);
	
	Gabinet getOneGabinet(long id);
	List<Gabinet> getAllGabinety();
	List<Gabinet> lekarzGabinet(String lekarz);
	
}
