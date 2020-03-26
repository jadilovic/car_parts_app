package com.avlija.parts.form;

import java.sql.Date;
import java.time.LocalDateTime;

public class FormCommand {
    private String orderNum;
 
    private String sifra;
    
    private String toDate;
    
    private String fromDate;
 
    private String datetimeField;
 
    private boolean edit;
 
    private boolean singleCheckboxField;
    
    private String colorField;
 
    private String[] multiCheckboxSelectedValues;
 
    private String radioButtonSelectedValue;
    private String dropdownSelectedValue;
	
public FormCommand() {
	
}

public FormCommand(String orderNum, String sifra, String datetimeField, boolean singleCheckboxField, boolean edit, String colorField,
		String[] multiCheckboxSelectedValues, String radioButtonSelectedValue, String dropdownSelectedValue) {
	super();
	this.orderNum = orderNum;
	this.sifra = sifra;
	this.datetimeField = datetimeField;
	this.colorField = colorField;
	this.singleCheckboxField = singleCheckboxField;
	this.edit = edit;
	this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
	this.radioButtonSelectedValue = radioButtonSelectedValue;
	this.dropdownSelectedValue = dropdownSelectedValue;
}

/**
 * @return the orderNum
 */
public String getOrderNum() {
	return orderNum;
}

/**
 * @param orderNum the orderNum to set
 */
public void setOrderNum(String orderNum) {
	this.orderNum = orderNum;
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
 * @return the datetimeField
 */
public String getDatetimeField() {
	return datetimeField;
}

/**
 * @param datetimeField the datetimeField to set
 */
public void setDatetimeField(String datetimeField) {
	this.datetimeField = datetimeField;
}

/**
 * @return the colorField
 */
public String getColorField() {
	return colorField;
}

/**
 * @param colorField the colorField to set
 */
public void setColorField(String colorField) {
	this.colorField = colorField;
}

/**
 * @return the singleCheckboxField
 */
public boolean isSingleCheckboxField() {
	return singleCheckboxField;
}

/**
 * @param singleCheckboxField the singleCheckboxField to set
 */
public void setSingleCheckboxField(boolean singleCheckboxField) {
	this.singleCheckboxField = singleCheckboxField;
}

/**
 * @return the multiCheckboxSelectedValues
 */
public String[] getMultiCheckboxSelectedValues() {
	return multiCheckboxSelectedValues;
}

/**
 * @param multiCheckboxSelectedValues the multiCheckboxSelectedValues to set
 */
public void setMultiCheckboxSelectedValues(String[] multiCheckboxSelectedValues) {
	this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
}

/**
 * @return the radioButtonSelectedValue
 */
public String getRadioButtonSelectedValue() {
	return radioButtonSelectedValue;
}

/**
 * @param radioButtonSelectedValue the radioButtonSelectedValue to set
 */
public void setRadioButtonSelectedValue(String radioButtonSelectedValue) {
	this.radioButtonSelectedValue = radioButtonSelectedValue;
}

/**
 * @return the dropdownSelectedValue
 */
public String getDropdownSelectedValue() {
	return dropdownSelectedValue;
}

/**
 * @param dropdownSelectedValue the dropdownSelectedValue to set
 */
public void setDropdownSelectedValue(String dropdownSelectedValue) {
	this.dropdownSelectedValue = dropdownSelectedValue;
}

public String getToDate() {
	return toDate;
}

public void setToDate(String toDate) {
	this.toDate = toDate;
}

public String getFromDate() {
	return fromDate;
}

public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}

public boolean isEdit() {
	return edit;
}

public void setEdit(boolean edit) {
	this.edit = edit;
}



}