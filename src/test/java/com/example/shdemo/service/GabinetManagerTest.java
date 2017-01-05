package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Gabinet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional

public class GabinetManagerTest {

	@Autowired
	SzpitalManager szpitalManager;
	
	private final static String BAD_NAZWA_1 = "Morfologia";
	private final static String BAD_OPIS_1 = "Podstawowe, diagnostyczne badanie krwi";
	private final static String BAD_KOSZT_1 = "50";
	
	private final static String BAD_NAZWA_2 = "Mycoplazma pneumoniae IgM";
	private final static String BAD_OPIS_2 = "Badanie na obecność bakterii";
	private final static String BAD_KOSZT_2 = "35";
	
	private final static String GAB_NUMER_1 = "22";
	private final static String GAB_PIETRO_1 = "parter";
	private final static String GAB_LEKARZ_1 = "Kowalski";
	
	private final static String GAB_NUMER_2 = "8";
	private final static String GAB_PIETRO_2 = "pierwsze";
	private final static String GAB_LEKARZ_2 = "Nowak";
	
	private final static String GAB_NUMER_3 = "99";
	private final static String GAB_PIETRO_3 = "drugie";
	private final static String GAB_LEKARZ_3 = "Ciemny";
	
	private final static String GAB_NUMER_4 = "35";
	private final static String GAB_PIETRO_4 = "dwunaste";
	private final static String GAB_LEKARZ_4 = "Jasny";
	
	
	
	@Test
	public void addGabinet() {

		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1,GAB_PIETRO_1,GAB_LEKARZ_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2,GAB_PIETRO_2,GAB_LEKARZ_2);

		szpitalManager.addGabinet(gabinet1);
		szpitalManager.addGabinet(gabinet2);

		List<Gabinet> gabinety = szpitalManager.getAllGabinety();
		Gabinet gabinetRetrieved;
		
		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(0).getId());		
		assertEquals(GAB_NUMER_1, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetRetrieved.getLekarz());

		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(1).getId());			
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());

	}
	
	
	
}
