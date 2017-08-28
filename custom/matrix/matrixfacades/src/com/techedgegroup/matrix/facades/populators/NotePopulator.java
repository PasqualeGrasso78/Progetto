/**
 *
 */
package com.techedgegroup.matrix.facades.populators;

import de.hybris.platform.converters.Populator;

import org.springframework.util.Assert;

import com.techedgegroup.matrix.core.model.NoteModel;
import com.techedgegroup.matrix.facades.data.NoteData;


/**
 * @author pasquale
 *
 */
public class NotePopulator<SOURCE extends NoteModel, TARGET extends NoteData> implements Populator<SOURCE, TARGET>
{

	@Override
	public void populate(final SOURCE source, final TARGET target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setDescription(source.getDescription());

	}
}
