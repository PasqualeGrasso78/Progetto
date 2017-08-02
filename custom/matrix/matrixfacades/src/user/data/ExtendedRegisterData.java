/**
 *
 */
package user.data;

import de.hybris.platform.commercefacades.user.data.RegisterData;


public class ExtendedRegisterData extends RegisterData
{

	private Boolean shadowCustomer;
	private String notes;

	/**
	 * @return the shadowCustomer
	 */
	public Boolean getShadowCustomer()
	{
		return shadowCustomer;
	}

	/**
	 * @param shadowCustomer
	 *           the shadowCustomer to set
	 */
	public void setShadowCustomer(final Boolean shadowCustomer)
	{
		this.shadowCustomer = shadowCustomer;
	}

	/**
	 * @return the notes
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @param string
	 *           the notes to set
	 */
	public void setNotes(final String string)
	{
		this.notes = string;
	}
}
