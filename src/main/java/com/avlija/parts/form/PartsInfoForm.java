package com.avlija.parts.form;

public class PartsInfoForm {

	private String sifra;
	private String grupa;
	private String naziv;
	private String marka;
	private String automobil;
	private String modelauta;
	private int godina;
	private int kolicina;
	private String cijena;
	private boolean edit;
	
	public PartsInfoForm() {
	}
	
	public PartsInfoForm(String sifra, String grupa, String naziv, String marka, String automobil, String modelauta,
			int godina, int kolicina, String cijena, boolean edit) {
		this.sifra = sifra;
		this.grupa = grupa;
		this.naziv = naziv;
		this.marka = marka;
		this.automobil = automobil;
		this.modelauta = modelauta;
		this.godina = godina;
		this.kolicina = kolicina;
		this.cijena = cijena;
		this.edit = edit;
	}
	
	
	/**
	 * @return the edit
	 */
	public boolean isEdit() {
		return edit;
	}

	/**
	 * @param edit the edit to set
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
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
	 * @return the automobil
	 */
	public String getAutomobil() {
		return automobil;
	}
	/**
	 * @param automobil the automobil to set
	 */
	public void setAutomobil(String automobil) {
		this.automobil = automobil;
	}
	/**
	 * @return the modelauta
	 */
	public String getModelauta() {
		return modelauta;
	}
	/**
	 * @param modelauta the modelauta to set
	 */
	public void setModelauta(String modelauta) {
		this.modelauta = modelauta;
	}
	/**
	 * @return the godina
	 */
	public int getGodina() {
		return godina;
	}
	/**
	 * @param godina the godina to set
	 */
	public void setGodina(int godina) {
		this.godina = godina;
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
	public String getCijena() {
		return cijena;
	}
	/**
	 * @param cijena the cijena to set
	 */
	public void setCijena(String cijena) {
		this.cijena = cijena;
	}
}
