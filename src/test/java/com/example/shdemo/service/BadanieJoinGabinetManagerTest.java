package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Badanie;
import com.example.shdemo.domain.Gabinet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BadanieJoinGabinetManagerTest {

	@Autowired
	SzpitalManager szpitalManager;
	
	private final static String BAD_NAZWA_1 = "Morfologia";
	private final static String BAD_OPIS_1 = "Podstawowe, diagnostyczne badanie krwi";
	private final static String BAD_KOSZT_1 = "50";
	
	private final static String GAB_NUMER_1 = "22";
	private final static String GAB_PIETRO_1 = "parter";
	private final static String GAB_LEKARZ_1 = "Kowalski";
	
	private final static String GAB_NUMER_2 = "8";
	private final static String GAB_PIETRO_2 = "pierwsze";
	private final static String GAB_LEKARZ_2 = "Nowak";
	
	private final static String GAB_NUMER_3 = "99";
	private final static String GAB_PIETRO_3 = "drugie";
	private final static String GAB_LEKARZ_3 = "Ciemny";
	
	
	@Test
	public void AddGabinetByBadanie() {

		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1,GAB_PIETRO_1,GAB_LEKARZ_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2,GAB_PIETRO_2,GAB_LEKARZ_2);

		szpitalManager.addGabinet(gabinet1);
		szpitalManager.addGabinet(gabinet2);

		List<Gabinet> gabinetyPob = Arrays.asList(szpitalManager.getAllGabinety().get(0));

		Badanie badanie1 = new Badanie(BAD_NAZWA_1,BAD_OPIS_1,BAD_KOSZT_1,gabinetyPob);		
		szpitalManager.addBadanie(badanie1);
		
		List<Gabinet> gabinety;
		Gabinet gabinetRetrieved;

		gabinety = szpitalManager.getAllGabinetyFromBadanie(badanie1);
		gabinetRetrieved = gabinety.get(0);		
		assertEquals(GAB_NUMER_1, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetRetrieved.getLekarz());

	}
	
	@Test
	public void editGabinetByBadanie() {

		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1,GAB_PIETRO_1,GAB_LEKARZ_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2,GAB_PIETRO_2,GAB_LEKARZ_2);

		szpitalManager.addGabinet(gabinet1);
		szpitalManager.addGabinet(gabinet2);

		List<Gabinet> gabinetyPob = Arrays.asList(szpitalManager.getAllGabinety().get(0));

		Badanie badanie1 = new Badanie(BAD_NAZWA_1,BAD_OPIS_1,BAD_KOSZT_1,gabinetyPob);		
		szpitalManager.addBadanie(badanie1);
		
		List<Gabinet> gabinety;
		gabinety = szpitalManager.getAllGabinetyFromBadanie(badanie1);

		assertEquals(GAB_NUMER_1, gabinety.get(0).getNumer());
		assertEquals(GAB_PIETRO_1, gabinety.get(0).getPietro());
		assertEquals(GAB_LEKARZ_1, gabinety.get(0).getLekarz());

		gabinety = szpitalManager.getAllGabinety();
		Gabinet gabinetRetrieved;
		
		Gabinet gab1 = gabinety.get(0);
		Gabinet gab2 = gab1;
		gab2.setNumer(GAB_NUMER_3);
		gab2.setPietro(GAB_PIETRO_3);
		gab2.setLekarz(GAB_LEKARZ_3);
		szpitalManager.editGabinet(gab1, gab2);

				
		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(0).getId());		
		assertEquals(GAB_NUMER_3, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_3, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_3, gabinetRetrieved.getLekarz());

		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(1).getId());		
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());

		gabinety = szpitalManager.getAllGabinetyFromBadanie(badanie1);
		gabinetRetrieved = gabinety.get(0);		
		assertEquals(GAB_NUMER_3, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_3, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_3, gabinetRetrieved.getLekarz());

	}
	
	public void checkDeleteGabinetyByBadanie() {

		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1,GAB_PIETRO_1,GAB_LEKARZ_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2,GAB_PIETRO_2,GAB_LEKARZ_2);

		szpitalManager.addGabinet(gabinet1);
		szpitalManager.addGabinet(gabinet2);

		List<Gabinet> gabinetyPob = Arrays.asList(szpitalManager.getAllGabinety().get(0));

		Badanie badanie1 = new Badanie(BAD_NAZWA_1,BAD_OPIS_1,BAD_KOSZT_1,gabinetyPob);		
		szpitalManager.addBadanie(badanie1);
		
		List<Gabinet> gabinety;
		gabinety = szpitalManager.getAllGabinetyFromBadanie(badanie1);
		
		szpitalManager.deleteGabinet(gabinety.get(0));
		
		gabinety = szpitalManager.getAllGabinety();
		Gabinet gabinetRetrieved;
				
		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(0).getId());		
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());

		gabinety = szpitalManager.getAllGabinetyFromBadanie(badanie1);
		gabinetRetrieved = gabinety.get(0);		
		assertEquals(0, gabinety.size());
	}
	
	
}
