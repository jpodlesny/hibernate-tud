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

import com.example.shdemo.domain.Badanie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BadanieManagerTest {

	@Autowired
	SzpitalManager szpitalManager;
	
	private final static String BAD_NAZWA_1 = "Morfologia";
	private final static String BAD_OPIS_1 = "Podstawowe, diagnostyczne badanie krwi";
	private final static String BAD_KOSZT_1 = "50";
	
	private final static String BAD_NAZWA_2 = "Mycoplazma pneumoniae IgM";
	private final static String BAD_OPIS_2 = "Badanie na obecność bakterii";
	private final static String BAD_KOSZT_2 = "35";
	
	private final static String BAD_NAZWA_3 = "Wskaznik protrombinowy- INR";
	private final static String BAD_OPIS_3 = "Zbadanie koagulacji";
	private final static String BAD_KOSZT_3 = "250";
	
	private final static String BAD_NAZWA_4 = "Bilirubina calkowita";
	private final static String BAD_OPIS_4 = "Sprawdzenie stezenia bilirubiny we krwi";
	private final static String BAD_KOSZT_4 = "100";
	
	@Test
	public void addBadanie(){
		
		Badanie badanie1 = new Badanie(BAD_NAZWA_1,BAD_OPIS_1,BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2,BAD_OPIS_2,BAD_KOSZT_2);
		
		szpitalManager.addBadanie(badanie1);
		szpitalManager.addBadanie(badanie2);
		
		List<Badanie> badania = szpitalManager.getAllBadania();
		long idBadanie;
		idBadanie = badania.get(1).getId();
		
		Badanie badanieRetrieved = szpitalManager.getOneBadanie(idBadanie);

		assertEquals(BAD_NAZWA_2, badanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_2, badanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_2, badanieRetrieved.getKoszt());
		
	}
	
	@Test
	public void deleteBadanie() {

		Badanie badanie1 = new Badanie(BAD_NAZWA_1,BAD_OPIS_1,BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2,BAD_OPIS_2,BAD_KOSZT_2);
		
		szpitalManager.addBadanie(badanie1);
		szpitalManager.addBadanie(badanie2);
		
		szpitalManager.deleteBadanie(badanie1);
		
		List<Badanie> badania = szpitalManager.getAllBadania();
		long idBadania;
		idBadania = badania.get(0).getId();
		
		Badanie badanieRetrieved = szpitalManager.getOneBadanie(idBadania);
		assertEquals(BAD_NAZWA_2, badanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_2, badanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_2, badanieRetrieved.getKoszt());

	}
	
	@Test
	public void editBadanie() {

		Badanie badanie1 = new Badanie(BAD_NAZWA_1,BAD_OPIS_1,BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2,BAD_OPIS_2,BAD_KOSZT_2);
		Badanie badanie3 = new Badanie(BAD_NAZWA_3,BAD_OPIS_3,BAD_KOSZT_3);
		
		szpitalManager.addBadanie(badanie1);
		szpitalManager.addBadanie(badanie2);
		
		List<Badanie> badania = szpitalManager.getAllBadania();
		long idBadania;
		idBadania = badania.get(1).getId();
		
		szpitalManager.editBadanie(badanie2, badanie3);
		Badanie badanieRetrieved = szpitalManager.getOneBadanie(idBadania);
		assertEquals(BAD_NAZWA_3, badanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_3, badanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_3, badanieRetrieved.getKoszt());

	}
	
	
	
	
	
}
