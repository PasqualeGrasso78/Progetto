/**
 *
 */
package com.techedgegroup.matrix.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateProfileForm;

import java.util.List;

import com.techedgegroup.matrix.facades.data.NoteData;


/**
 * @author pasquale
 *
 */
public class MatrixUpdateProfileForm extends UpdateProfileForm
{


	private String note;
	private List<NoteData> notes;
	private boolean isShadow = false;




	/**
	 * @return the note
	 */
	public String getNote()
	{
		return note;
	}

	/**
	 * @param note
	 *           the note to set
	 */
	public void setNote(final String note)
	{
		this.note = note;
	}

	/**
	 * @return the notes
	 */
	public List<NoteData> getNotes()
	{
		return notes;
	}

	/**
	 * @param notes
	 *           the notes to set
	 */
	public void setNotes(final List<NoteData> notes)
	{
		this.notes = notes;
	}

	/**
	 * @return the isShadow
	 */
	public boolean getIsShadow()
	{
		return isShadow;
	}

	/**
	 * @param isShadow
	 *           the isShadow to set
	 */
	public void setIsShadow(final boolean isShadow)
	{
		this.isShadow = isShadow;
	}



}
