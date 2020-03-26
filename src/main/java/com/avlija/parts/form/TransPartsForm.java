package com.avlija.parts.form;
 
public class TransPartsForm {

    private String sifra;
    private int amount;
 
    public TransPartsForm() {
 
    }
 
    public TransPartsForm(String sifra, int amount) {
        this.sifra = sifra;
        this.amount = amount;
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
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


}