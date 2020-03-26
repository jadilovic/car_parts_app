package com.avlija.parts.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordernum", updatable = false, nullable = false)
	private Long ordernum;
	
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date orderdate;
	
	@Column
	private String sifra;
	@Column
	private String naziv;
	@Column
	private String marka;
	@Column
	private String grupa;
	@Column
	private int kolicina;
	@Column
	private float cijena;
	@Column
	private float ukupno;

	public Orders() {
	}

	public Orders(Date orderdate, String sifra, String naziv, String marka, String grupa, int kolicina,
			float cijena, float ukupno) {
		super();
		this.orderdate = orderdate;
		this.sifra = sifra;
		this.naziv = naziv;
		this.marka = marka;
		this.grupa = grupa;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.ukupno = ukupno;
	}
	
	

	public Orders(Long ordernum, Date orderdate, String sifra, String naziv, String marka, String grupa, int kolicina,
			float cijena, float ukupno) {
		super();
		this.ordernum = ordernum;
		this.orderdate = orderdate;
		this.sifra = sifra;
		this.naziv = naziv;
		this.marka = marka;
		this.grupa = grupa;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.ukupno = ukupno;
	}

	/**
	 * @return the ordernum
	 */
	public Long getOrdernum() {
		return ordernum;
	}

	/**
	 * @param ordernum the ordernum to set
	 */
	public void setOrdernum(Long ordernum) {
		this.ordernum = ordernum;
	}

	/**
	 * @return the orderdate
	 */
	public Date getOrderdate() {
		return orderdate;
	}

	/**
	 * @param orderdate the orderdate to set
	 */
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	/**
	 * @return the sifra
	 */
	public String getSifra() {
		return sifra;
	}

	/**
	 * @param sifra the sifra to set
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	/**
	 * @return the naziv
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * @param naziv the naziv to set
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * @return the marka
	 */
	public String getMarka() {
		return marka;
	}

	/**
	 * @param marka the marka to set
	 */
	public void setMarka(String marka) {
		this.marka = marka;
	}

	/**
	 * @return the grupa
	 */
	public String getGrupa() {
		return grupa;
	}

	/**
	 * @param grupa the grupa to set
	 */
	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}

	/**
	 * @return the kolicina
	 */
	public int getKolicina() {
		return kolicina;
	}

	/**
	 * @param kolicina the kolicina to set
	 */
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	/**
	 * @return the cijena
	 */
	public float getCijena() {
		return cijena;
	}

	/**
	 * @param cijena the cijena to set
	 */
	public void setCijena(float cijena) {
		this.cijena = cijena;
	}

	/**
	 * @return the ukupno
	 */
	public float getUkupno() {
		return ukupno;
	}

	/**
	 * @param ukupno the ukupno to set
	 */
	public void setUkupno(float ukupno) {
		this.ukupno = ukupno;
	}

	

}
