package com.techedgegroup.matrix.facades.data;

import de.hybris.platform.commercefacades.user.data.CustomerData;


/**
 * @author alessio
 *
 */


public class ExtendedCustomerData extends CustomerData
{
	private String address;
	private String town;
	private String pCode;
	private String number;

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address
	 *           the address to set
	 */
	public void setAddress(final String address)
	{
		this.address = address;
	}

	/**
	 * @return the town
	 */
	public String getTown()
	{
		return town;
	}

	/**
	 * @param town
	 *           the town to set
	 */
	public void setTown(final String town)
	{
		this.town = town;
	}

	/**
	 * @return the pCode
	 */
	public String getpCode()
	{
		return pCode;
	}

	/**
	 * @param pCode
	 *           the pCode to set
	 */
	public void setpCode(final String pCode)
	{
		this.pCode = pCode;
	}

	/**
	 * @return the number
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * @param number
	 *           the number to set
	 */
	public void setNumber(final String number)
	{
		this.number = number;
	}



}
