/**
 *
 */
package com.techedgegroup.matrix.facades.user.converters.populator;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.techedgegroup.matrix.core.model.NoteModel;
import com.techedgegroup.matrix.facades.data.NoteData;


/**
 * @author pasquale
 *
 */
public class MatrixCustomerPopulator extends CustomerPopulator
{


	private Converter<NoteModel, NoteData> noteConverter;


	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		if (source.getSessionCurrency() != null)
		{
			target.setCurrency(getCurrencyConverter().convert(source.getSessionCurrency()));
		}
		if (source.getSessionLanguage() != null)
		{
			target.setLanguage(getLanguageConverter().convert(source.getSessionLanguage()));
		}
		/*
		 * if (source.getNotes() != null) {
		 * 
		 * //target.setNotes(getNoteConverter().convert(source.getNotes()));
		 * target.setNote(getNoteConverter().convert(source.getNotes().get(0)).getDescription()); }
		 */


		final String[] names = getCustomerNameStrategy().splitName(source.getName());
		if (names != null)
		{
			target.setFirstName(names[0]);
			target.setLastName(names[1]);
		}

		final TitleModel title = source.getTitle();
		if (title != null)
		{
			target.setTitleCode(title.getCode());
		}
		final List<NoteModel> notesListModel = source.getNotes();
		final List<NoteData> notesListData = new ArrayList<NoteData>();
		NoteData noteData;

		if (!notesListModel.isEmpty())
		{
			for (final NoteModel n : notesListModel)
			{
				noteData = new NoteData();
				noteData.setCode(n.getCode());
				noteData.setDescription(n.getDescription());
				notesListData.add(noteData);
			}
			target.setNotes(notesListData);

		}
		{
			target.setName(source.getName());
		}
		setUid(source, target);
	}





	/**
	 * @param noteConverter
	 *           the noteConverter to set
	 */
	@Required
	public void setNoteConverter(final Converter<NoteModel, NoteData> noteConverter)
	{
		this.noteConverter = noteConverter;
	}

	/**
	 * @return the noteConverter
	 */
	protected Converter<NoteModel, NoteData> getNoteConverter()
	{
		return noteConverter;
	}



}
