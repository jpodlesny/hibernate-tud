package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.*;

public interface SzpitalManager{
	
	void addBadanie(Badanie badanie);
	void deleteBadanie(Badanie badanie);
	void editBadanie(Badanie badanie1, Badanie badanie2);
	
	Badanie getOneBadanie(long id);
	List<Badanie> getAllBadania();
	List<Badanie> kosztBadanie(String koszt);
	List<Gabinet> getAllGabinetyFromBadanie(Badanie badanie);

	
	void addGabinet(Gabinet gabinet);
	Gabinet getOneGabinet(long id);
	List<Gabinet> getAllGabinety();
	
}
