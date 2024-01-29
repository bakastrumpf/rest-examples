package com.iktpreobuka.restExamplesOne.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.restExamplesOne.entities.BankClientBean;

@RestController
@RequestMapping("/bankclients")
public class BankClientRestController {

	List<BankClientBean> clients = new ArrayList<BankClientBean>();

	// ubuduće će ovo biti u repozitorijumu
	// ali dok ne počnemo da radimo s repozitorijumima zasad ostaje ovde
	private List<BankClientBean> getDB() {
		// zatražiti bazu klijenata

		/*
		 * List<BankClientBean> clients2 = new ArrayList<BankClientBean>();
		 * clients2.add(new BankClientBean(1, "V", "D", "vd@uns.ac.rs"));
		 * clients2.add(new BankClientBean(2, "M", "S", "mssa@ff.uns.ac.rs"));
		 * clients2.add(new BankClientBean(3, "S", "TM", "stma@ff.uns.ac.rs")); return
		 * clients2;
		 */
		if (clients.size() == 0) {
			clients.add(new BankClientBean(1, "V", "D", "vd@uns.ac.rs", "21-03-1987", "P"));
			clients.add(new BankClientBean(2, "M", "S", "mss@ff.uns.ac.rs", "12-07-1968", "P"));
			clients.add(new BankClientBean(3, "S", "TM", "stm@ff.uns.ac.rs", "11-03-1954", "N"));
			clients.add(new BankClientBean(4, "Pera", "Ždera", "pzm@mail.ma", "21-09-1989", "P"));
			clients.add(new BankClientBean(5, "Mornar", "Popaj", "popeye@pmail.pm", "01-04-1934", "N"));
			clients.add(new BankClientBean(6, "Duh", "IzLampe", "duh@opopaj.duh", "19-06-1984", "P"));
			clients.add(new BankClientBean(7, "Tasmanijski", "Đavo", "tasman@djavo.crtac", "29-08-1887", "N"));
		}
		// vratiti klijente kao odgovor na zahtev
		return clients;

	}

	// TODO GET - vrati sve klijente - /bankclients/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<BankClientBean> getAll() {
		/*
		 * // zatražiti od baze sve klijente List<BankClientBean> clients = new
		 * ArrayList<BankClientBean>(); clients.add(new BankClientBean(1, "Vladimir",
		 * "Dimitrieski", "vd@uns.ac.rs")); clients.add(new BankClientBean(2, "Marija",
		 * "Savic", "mss@ff.uns.ac.rs")); clients.add(new BankClientBean(3, "Stiv",
		 * "The maniac", "stm@ff.uns.ac.rs")); // vratiti klijente kao odgovor na zahtev
		 * return clients;
		 */
		return getDB();
	}

	// TODO GET - vrati jednog klijenta - /bankclients/{id}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BankClientBean getOne(@PathVariable Integer id) {

		/*
		 * JAKO LOŠE NAPISAN KOD: if(id.equals(1)){ return new BankClientBean(1,
		 * "Vladimir", "Dimitrieski", "vd@uns.ac.rs")); } else if(id.equals(2)) { return
		 * new BankClientBean(2, "Marija", "Savic", "mss@ff.uns.ac.rs")); } return null;
		 */

		for (BankClientBean bcb : getDB()) {
			if (bcb.getId().equals(id))
				return bcb;
		}
		return null;
	}

	// TODO POST - napravi novog korisnika - /bankclients/
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public BankClientBean createClientBean(@RequestBody BankClientBean newBcb) {
		List<BankClientBean> clients = getDB();
		newBcb.setId(new Random().nextInt());
		clients.add(newBcb);
		return newBcb;
	}

	// TODO PUT - izmeni korisnika - /bankclients/{id}
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public BankClientBean changeClient(@RequestBody BankClientBean changedBcb, @PathVariable Integer id) {
		for (BankClientBean bcb : getDB()) {
			if (bcb.getId().equals(id)) {

				/*
				 * bcb.setEmail(changedBcb.getEmail()); bcb.setName(changedBcb.getName());
				 * bcb.setSurname(changedBcb.getSurname());
				 */
				if (changedBcb.getEmail() != null) {
					bcb.setEmail(changedBcb.getEmail());
				}
				if (changedBcb.getName() != null) {
					bcb.setName(changedBcb.getName());
				}
				if (changedBcb.getSurname() != null) {
					bcb.setSurname(changedBcb.getSurname());
				}
				return bcb;
			}
		}
		return null;

		/*
		 * public BankClientBean changeList(@RequestBody BankClientBean
		 * changedBcb, @PathVariable Integer id) { for (BankClientBean bcb : getDB()) {
		 * if (bcb.getId().equals(id)) { bcb.setEmail(changedBcb.getEmail()); if
		 * (changedBcb.getName() != null) bcb.setName(changedBcb.getName()); if
		 * (changedBcb.getSurname() != null) bcb.setSurname(changedBcb.getSurname());
		 * return bcb; } } return null;
		 */
	}

	// TODO DELETE - izbrisi klijenta - /bankclients/{id}
	@RequestMapping(method = RequestMethod.DELETE, value = "/b/{id}")
	public BankClientBean betterDeleteClient(@PathVariable Integer id) {
		List<BankClientBean> clients = getDB();
		// u pozadini, FOREACH jeste ITERATOR, ali ume da zbuni DELETE jer ide kroz
		// listu i briše elemente 1 po 1
		// ovde bi radilo jer bismo brisali samo jednog
		// opcija 1: u petlji FOREACH nađemo klijenta, izađemo iz petlje i obrišemo ga
		// opcija 2: naći ga po ID pa onda obrisati
		Iterator<BankClientBean> it = clients.iterator();
		while (it.hasNext()) {
			BankClientBean bcb = it.next();
			if (bcb.getId().equals(id)) {
				it.remove();
				return bcb;
			}
		}
		return null;

		/*
		 * // neće raditi zbog remove u FOR petlji // možda bolje raditi preko findOne
		 * // ne bismo smeli brisati iz liste kroz koju prolazimo pomoću FOREACH //
		 * česta greška
		 * 
		 * @RequestMapping(method = RequestMethod.DELETE, value = "/d/{id}") public
		 * BankClientBean deleteClient(@PathVariable Integer id) { List<BankClientBean>
		 * clients = getDB(); for (BankClientBean bcb : clients) { if
		 * (bcb.getId().equals(id)) { clients.remove(bcb); return bcb; } } return null;
		 */

		/*
		 * treća mogućnost za brisanje: STREAM
		 * 
		 * @RequestMapping(method = RequestMethod.DELETE, value = "/s/{id}") public
		 * BankClientBean streamDeleteClient(@PathVariable Integer id) {
		 * List<BankClientBean> clients = getDB().stream().filter(x ->
		 * x.getId().equals(id)).findFirst().get(); if(bcb != null){
		 * clients.remove(bcb); return bcb; } return null; }
		 */

	}

	// TODO GET - pronaći korisnike sa zadatim imenom i prezimenom
	// /findByNameAndSurname
	// {name}/{surname} tehnički bi moglo, vraća listu ako postoji više
	// korisnika s istim imenom i prezimenom
	// findByNameAndSurname?name=da&surname=da
	@RequestMapping(method = RequestMethod.GET, value = "/findByNameAndSurname")
	public List<BankClientBean> findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
		List<BankClientBean> retList = new ArrayList<>();
		List<BankClientBean> clients = getDB();
		for (BankClientBean bcb : clients) {
			// if (bcb.getName().equals(name) && bcb.getSurname().equals(surname)) {
			if (bcb.getName().equalsIgnoreCase(name) && bcb.getSurname().equalsIgnoreCase(surname)) {
				retList.add(bcb);
			}
		}
		return retList;
	}

	// ZADATAK 1.1
	// endpoint koji iz liste klijenata banke uzima samo email adrese svih klijenata i vraća listu email adresa 
	// TODO GET - vratiti mejlove - /emails
	@RequestMapping(method = RequestMethod.GET, value = "/emails")
	public List<String> getEmails() {
		// pravimo listu klijenata
		List<BankClientBean> clientsFromDB = getDB();
		// pravimo listu mejlova
		List<String> emails = new ArrayList<String>();
		// prolazimo kroz listu - FOR
		for (BankClientBean bcb : clientsFromDB) {
			// pronađene mejlove dodajemo u listu mejlova koju ćemo prikazati
			emails.add(bcb.getEmail());
		}
		return emails;
	}

	// ZADATAK 1.2
	// • endpoint koji vraća listu koja sadrži imena klijenata, čije ime počinje na slovo prosleđeno kao parametar
	// • putanja /clients/{firstLetter} 
	@RequestMapping(method = RequestMethod.GET, value = "/clients/{firstLetter}")
	public List<String> startsWith(@PathVariable String firstLetter) {
		// pravimo povratnu listu
		List<String> retStr = new ArrayList<String>();
		// prolazimo kroz sve korisnike
		for (BankClientBean bcb : getDB()) {
			// ako nečije ime počinje tim slovom, ide u listu
			if (bcb.getName().startsWith(firstLetter)) {
				retStr.add(bcb.getName());
			}
		}
		return retStr;
	}
	
	// ZADATAK 1.3
	// endpoint koji vraća listu koja sadrži imena i prezimena klijenata, čije ime počinje na slovo koje je prosleđeno kao
	// parametar i čije prezime počinje na slovo koje je prosleđeno kao parametar 
	// • putanja /clients/firstLetters 
	@RequestMapping(method = RequestMethod.GET, value = "/clients/{name}/{surname}")
	public List<String> startsWithInitials(@PathVariable String name, @PathVariable String surname) {
		List<BankClientBean> clients = getDB();
		List<String> found = new ArrayList<>();
		for (BankClientBean bcb : clients) {
			if (bcb.getName().startsWith(name) && bcb.getSurname().startsWith(surname)) {
				found.add(bcb.getName() + " " + bcb.getSurname());
			}
		}
		return found;
	}
	
	// ZADATAK 1.4
	// endpoint koji vraća listu koja sadrži imena klijenata, koja su sortirana u redosledu koji je prosleđen kao parameter 
	// putanja /clients/sort/{order}
	
	@RequestMapping(value = "/sort/{order}", method = RequestMethod.GET)
	public List<BankClientBean> sortByOrder(@PathVariable String name, @PathVariable String order) {
		// ORDER je parametar koji ima vrednost A (ascending) ili D (descending)
		// pravimo listu klijenata
		// izvlačimo njihova imena
		// prosleđujemo ORDER kao parametar za sortiranje
		// sortiramo imena A ili D
		
		/*
		List<BankClientBean> clients = getDB();
		List<String> found = new ArrayList<>();
		Collections.sort((List<BankClientBean>) list);
		for(<BankClientBean> sorted : list) {
			if (bcb.getName().equals(name))
		}
		*/
		return null;
	}
	
	
	// ZADATAK 2.1
	// endpoint koji u listi klijenata banke, svakom klijentu postavlja polje bonitet na 
	// ‘P’ (pozitivan) ako je klijent mlađi od 65 godina ili ‘N’ negativan ako je klijent stariji od 65 godina 
	// putanja /clients/bonitet 
	// u klasu BankClientBean dodati atribute datum rođenja i bonitet 
	
	
	
	// ZADATAK 2.2
	// endpoint koji briše klijenta iz liste klijenta ukoliko klijent nema jednu od vrednosti: ime, prezime, email 
	// putanja /clients/delete 
	
	
	
	// ZADATAK 2.3
	// endpoint koji vraća ukupan broj klijenata u listi klijenata koji imaju manje od broja godina koje je prosleđeno kao parametar 
	// putanja /clients/countLess/{years} 
	
	
	
	// ZADATAK 2.4
	// endpoint koji prosečan broj godina klijenata iz liste klijenata 
	// putanja /clients/averageYears
	
	
	
	// ZADATAK 3.1
	// endpoint koji omogućuje izmenu mesta stanovanja klijenta 
	// putanja /clients/changelocation/{clientId}
	// u klasu BankClientBean dodati atribut grad 
	// novu vrednost mesta stanovanja proslediti kao QueryParameter 
	
	
	
	// ZADATAK 3.2
	// endpoint koji vraća klijente banke koji žive u gradu koji je prosleđen kao parametar 
	// putanja /clients/from/{city}
	
	
	
	// ZADATAK 3.3
	// endpoint koji vraća klijente banke koji žive u gradu koji je prosleđen
	// kao parametar i čiji je broj godina ispod broja prosleđenog kao drugi parametar 
	// putanja /clients/findByCityAndAge
	
	
	// ZADATAK 4
	// Izmeniti zadatke 2.1, 3.4 i 3.5 iz prethodne teme tako da korisnik prosleđuje odgovarajuće parametre 
	// ZADATAK 4.1
	// endpoint koji vraća „Hello yourName!“, gde yourName prosleđeno kao parametar 
	// putanja /greetings/{name}
	
	
	
	// ZADATAK 4.2
	// endpoint koji vraća sumu prvih n brojeva 
	// putanja /sumaNiza/{n} 
	
	
	
	// ZADATAK 4.3
	// endpoint koji predstavlja englesko-srpski rečnik i koji za reč na srpskom vrati odgovarajući prevod na engleski jezik 
	// putanja /recnik/{trazena_rec} 
	// DODATNO: ukoliko za traženu reč ne postoji prevod, tada ispisati „Rec trazena_rec ne postoji u recniku.“
	

}