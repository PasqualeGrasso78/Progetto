/**
 *
 */
package com.techedgegroup.matrix.core.services.impl;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.techedgegroup.matrix.core.model.NoteModel;
import com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService;


/**
 * @author alessio
 *
 */
public class DefaultExtendedCustomerAccountService extends DefaultCustomerAccountService implements ExtendedCustomerAccountService
{

	private List<NoteModel> note;

	@Resource(name = "modelService")
	ModelService modelService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService#registerCustomer(de.hybris.platform.core.model.
	 * user.CustomerModel, java.lang.String, java.lang.Boolean, java.util.List)
	 */
	@Override
	public void registerCustomer(CustomerModel customerModel, String password, Boolean shadowCustomer, String notes)
			throws DuplicateUidException
	{
		generateCustomerId(customerModel);
		if (password != null)
		{
			getUserService().setPassword(customerModel, password, getPasswordEncoding());
			List<NoteModel> notesList = new LinkedList<NoteModel>();
			NoteModel note = modelService.create(NoteModel.class);
			note.setDescription(notes);
			note.setCode("0");

			notesList.add(note);
			customerModel.setNotes(notesList);
			customerModel.setShadowCustomer(shadowCustomer);
		}
		internalSaveCustomer(customerModel);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService#register(de.hybris.platform.core.model.user.
	 * CustomerModel, java.lang.String, java.lang.Boolean, java.util.List)
	 */
	@Override
	public void register(CustomerModel customerModel, String password, Boolean shadowCustomer, String notes)
			throws DuplicateUidException
	{
		registerCustomer(customerModel, password, shadowCustomer, notes);
		getEventService().publishEvent(initializeEvent(new RegisterEvent(), customerModel));

	}



}
