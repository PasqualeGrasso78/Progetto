/**
 *
 */
package com.techedgegroup.matrix.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;


/**
 * @author pasquale
 *
 */
public class RegisterFormCustomer extends RegisterForm
{
	private Boolean isShadow = false;
	private String notes;



	/**
	 * @return the isShadow
	 */
	public Boolean getIsShadow()
	{
		return isShadow;
	}

	/**
	 * @param isShadow
	 *           the isShadow to set
	 */
	public void setIsShadow(final Boolean isShadow)
	{
		this.isShadow = isShadow;
	}

	/**
	 * @return the notes
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @param notes
	 *           the notes to set
	 */
	public void setNotes(final String notes)
	{
		this.notes = notes;
	}

	/**
	 * @return the news
	 */



}
