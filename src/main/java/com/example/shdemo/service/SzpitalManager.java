package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.*;

public interface SzpitalManager {
	
	void addBadanie(Badanie badanie);
	Badanie getOneBadanie(long id);
	List<Badanie> getAllBadania();

}
