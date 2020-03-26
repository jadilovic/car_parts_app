package com.avlija.parts.repository;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import com.avlija.parts.entities.Parts;
import com.avlija.parts.exception.PartsTransactionException;
import com.avlija.parts.model.PartsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public class PartsDAO {
 
    @Autowired
    private EntityManager entityManager;
      
 
    public PartsDAO() {
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