package com.avlija.parts.validation;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import com.avlija.parts.entities.Parts;
import com.avlija.parts.exception.PartsTransactionException;
import com.avlija.parts.model.PartsInfo;

import ch.qos.logback.core.joran.conditional.ElseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public class PartsValidation {
 
    @Autowired
    private EntityManager entityManager;
      
 
    public PartsValidation() {
    }
    
    public void checkSifra(String sifra) throws PartsTransactionException {
    	if(sifra == "") {
    		throw new PartsTransactionException("Unesite broj sifre koja mora biti tacno 13 karaktera.");
    	} else if(findById(sifra) != null) {
    		throw new PartsTransactionException("Sifra: '" + sifra + "', vec postoji.");
    	} else if(sifra.length() != 13) {
    		throw new PartsTransactionException("Duzina sifre: '" + sifra + "' nije odgovarajuca. Sifra mora biti tacno 13 karaktera.");
    	} else if(!sifra.matches("[0-9]+")){
    		throw new PartsTransactionException("Sifra: '" + sifra + "' ne sadrzi sve brojeve. Sifra mora biti niz brojeva.");
    	}
    }
 
	public void checkNazivMarkaModel(String naziv) throws PartsTransactionException {
    	if(naziv == "") {
    		throw new PartsTransactionException("Unesite naziv ili marku autodijela, ili model auta.");
    	} else if(naziv.length() < 4 || naziv.length() > 25) {
    		throw new PartsTransactionException("Naziv autodijela, model auta ili marka na smije biti manji od 4 ili veci od 25 karaktera.");
    	}
	}
	
	public void checkCijena(float cijena) throws PartsTransactionException {
    	if(cijena <= 0) {
    		throw new PartsTransactionException("Cijena ne moze biti nula ili u minusu");
    	} else if(cijena == 0.25) {
    		throw new PartsTransactionException("Pogresno unesena cijena.");
    	}
	}
    
    public Parts findById(String sifra) { 
        return this.entityManager.find(Parts.class, sifra);
    }
 
    public List<Parts> listPartsInfo() {
        String sql = "Select new " + PartsInfo.class.getName() //
                + "(e.sifra, e.grupa, e.naziv, e.marka, e.automobil, e.modelauta, e.godina, e.kolicina, e.cijena) " //
                + " from " + Parts.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, PartsInfo.class);
        return query.getResultList();
    }
 
    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY )
    public void trans(String sifra, int amount) throws PartsTransactionException {
        Parts part = this.findById(sifra);
        if (part == null) {
            throw new PartsTransactionException("Dio nije pronadjen " + sifra);
        }
        int novaKolicina = part.getKolicina() + amount;
        if (part.getKolicina() + amount < 0) {
            throw new PartsTransactionException(
                    "Kolicina dijelova pod sifrom '" + sifra + "' nije dovoljna (" + part.getKolicina() + ")");
        }
        part.setKolicina(novaKolicina);
    }
 
    // Do not catch BankTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW, 
                        rollbackFor = PartsTransactionException.class)
    public void sell(String sifra, int amount) throws PartsTransactionException {
        trans(sifra, -amount);
    }
 
    // Do not catch BankTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW, 
                        rollbackFor = PartsTransactionException.class)
    public void buy(String sifra, int amount) throws PartsTransactionException {
        trans(sifra, amount);
    }

}