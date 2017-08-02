/**
 *
 */
package com.techedgegroup.matrix.core.user.data;

import de.hybris.platform.commercefacades.user.data.RegisterData;

import java.util.List;

import com.techedgegroup.matrix.core.model.NoteModel;


public class ExtendedRegisterData extends RegisterData
{

	private boolean shadowCustomer;

	private List<NoteModel> notes;

	/**
	 * @return the shadowCustomer
	 */
	public boolean isShadowCustomer()
	{
		return shadowCustomer;
	}

	/**
	 * @param shadowCustomer
	 *           the shadowCustomer to set
	 */
	public void setShadowCustomer(final boolean shadowCustomer)
	{
		this.shadowCustomer = shadowCustomer;
	}

	/**
	 * @return the notes
	 */
	public List<NoteModel> getNotes()
	{
		return notes;
	}

	/**
	 * @param notes
	 *           the notes to set
	 */
	public void setNotes(final List<NoteModel> notes)
	{
		this.notes = notes;
	}
}
