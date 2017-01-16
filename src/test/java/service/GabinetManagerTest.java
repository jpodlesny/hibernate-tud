package service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import service.SzpitalManager;
import domain.Gabinet;

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
	private final static String GAB_LEKARZ_4 = "Kowalski";
	
	
	
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
	
	
	@Test
	public void deleteGabinet() {

		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1,GAB_PIETRO_1,GAB_LEKARZ_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2,GAB_PIETRO_2,GAB_LEKARZ_2);

		szpitalManager.addGabinet(gabinet1);
		szpitalManager.addGabinet(gabinet2); 
		
		int size = szpitalManager.getAllGabinety().size(); 
		
		Gabinet del = szpitalManager.getAllGabinety().get(0);
		
		szpitalManager.deleteGabinet(del);
		
		List<Gabinet> gabinety = szpitalManager.getAllGabinety();
		Gabinet gabinetRetrieved;
		
		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(0).getId());		
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());
		
		assertEquals(size-1, gabinety.size());
				
		
	}
	
	
	@Test
	public void editGabinet() {

		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1,GAB_PIETRO_1,GAB_LEKARZ_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2,GAB_PIETRO_2,GAB_LEKARZ_2);

		szpitalManager.addGabinet(gabinet1);
		szpitalManager.addGabinet(gabinet2); 
		
		Gabinet gab1 = szpitalManager.getOneGabinet(szpitalManager.getAllGabinety().get(0).getId());
		Gabinet gab2 = gab1;
		
		gab2.setNumer(GAB_NUMER_3);
		gab2.setPietro(GAB_PIETRO_3);
		gab2.setLekarz(GAB_LEKARZ_3);
		szpitalManager.editGabinet(gab1, gab2);
		
		List<Gabinet> gabinety = szpitalManager.getAllGabinety();
		Gabinet gabinetRetrieved;
		
		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(0).getId());		
		assertEquals(GAB_NUMER_3, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_3, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_3, gabinetRetrieved.getLekarz());
		
		gabinetRetrieved = szpitalManager.getOneGabinet(gabinety.get(1).getId());		
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());


		
	}
	
	
	@Test
	public void gabinetyPoLekarzach() {

		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1,GAB_PIETRO_1,GAB_LEKARZ_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2,GAB_PIETRO_2,GAB_LEKARZ_2);
		Gabinet gabinet3 = new Gabinet(GAB_NUMER_3,GAB_PIETRO_3,GAB_LEKARZ_3);
		Gabinet gabinet4 = new Gabinet(GAB_NUMER_4,GAB_PIETRO_4,GAB_LEKARZ_4);

		szpitalManager.addGabinet(gabinet1);
		szpitalManager.addGabinet(gabinet2);
		szpitalManager.addGabinet(gabinet3);
		szpitalManager.addGabinet(gabinet4);
		
		List<Gabinet> gabinety = szpitalManager.lekarzGabinet(GAB_LEKARZ_4);
		long idGabinetu;
		Gabinet gabinetRetrieved;
		
		idGabinetu = gabinety.get(0).getId(); 
		gabinetRetrieved = szpitalManager.getOneGabinet(idGabinetu);
		assertEquals(GAB_NUMER_1, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetRetrieved.getLekarz());

		idGabinetu = gabinety.get(1).getId();
		gabinetRetrieved = szpitalManager.getOneGabinet(idGabinetu);
		assertEquals(GAB_NUMER_4, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_4, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_4, gabinetRetrieved.getLekarz());
		
	}
	
}
