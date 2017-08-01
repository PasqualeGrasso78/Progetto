/**
 *
 */
package com.techedgegroup.matrix.core.services.impl;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.core.model.user.CustomerModel;

import com.techedgegroup.matrix.core.model.NoteModel;
import com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService;


/**
 * @author alessio
 *
 */
public class DefaultExtendedCustomerAccountService extends DefaultCustomerAccountService implements ExtendedCustomerAccountService
{

	@Override
	public void registerCustomer(final CustomerModel customerModel, final String password, final boolean shadowCustomer,
			final java.util.List<NoteModel> notes) throws DuplicateUidException
	{
		generateCustomerId(customerModel);
		if (password != null)
		{
			getUserService().setPassword(customerModel, password, getPasswordEncoding());
			customerModel.setNotes(notes);
			customerModel.setShadowCustomer(shadowCustomer);
		}
		internalSaveCustomer(customerModel);
	}

	@Override
	public void register(final CustomerModel customerModel, final String password, final boolean shadowCustomer,
			final java.util.List<NoteModel> notes) throws DuplicateUidException
	{
		registerCustomer(customerModel, password, shadowCustomer, notes);
		getEventService().publishEvent(initializeEvent(new RegisterEvent(), customerModel));
	}



}
