/**
 *
 */
package com.techedgegroup.matrix.facades.facade;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.user.impl.DefaultUserFacade;
import de.hybris.platform.core.model.user.CustomerModel;

import com.techedgegroup.matrix.core.model.NoteModel;
import com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService;
import com.techedgegroup.matrix.facades.data.NoteData;


/**
 * @author alessio
 *
 */
public class ExtendedDefaultUserFacade extends DefaultUserFacade
{

	private ExtendedCustomerAccountService extendedCustomerAccountService;

	@SuppressWarnings("unlikely-arg-type")
	public void removeNote(final NoteData noteData)
	{
		validateParameterNotNullStandardMessage("noteData", noteData);
		final CustomerModel currentCustomer = getCurrentUserForCheckout();
		for (final NoteModel noteModel : getExtendedCustomerAccountService().getNoteEntries(currentCustomer))
		{
			//if (noteData.getCode().equals(noteModel.getPk()))
			if (noteData.getCode().equals(noteModel.getCode()))
			{
				getExtendedCustomerAccountService().deleteNoteEntry(currentCustomer, noteModel);
				break;
			}


		}
	}

	/**
	 * @return the extendedCustomerAccountService
	 */
	public ExtendedCustomerAccountService getExtendedCustomerAccountService()
	{
		return extendedCustomerAccountService;
	}

	/**
	 * @param extendedCustomerAccountService
	 *           the extendedCustomerAccountService to set
	 */
	public void setExtendedCustomerAccountService(final ExtendedCustomerAccountService extendedCustomerAccountService)
	{
		this.extendedCustomerAccountService = extendedCustomerAccountService;
	}



}
