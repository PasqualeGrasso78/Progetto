/**
 *
 */
package com.techedgegroup.matrix.facades.data;

import java.io.Serializable;

/**
 * @author pasquale
 *
 */
public class NoteData implements Serializable
{
	private String code;
	private String description;

	/**
	 * @param code
	 * @param description
	 */
	public NoteData(final String code, final String description)
	{
		super();
		this.code = code;
		this.description = description;
	}

	public NoteData()
	{
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *           the description to set
	 */
	public void setDescription(final String description)
	{
		this.description = description;
	}
}
