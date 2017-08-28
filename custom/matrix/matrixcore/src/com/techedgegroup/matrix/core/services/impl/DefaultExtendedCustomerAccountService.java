/**
 *
 */
package com.techedgegroup.matrix.core.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

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
	public void registerCustomer(final CustomerModel customerModel, final String password, final Boolean shadowCustomer,
			final String notes) throws DuplicateUidException
	{
		generateCustomerId(customerModel);
		if (password != null)
		{
			getUserService().setPassword(customerModel, password, getPasswordEncoding());
			final List<NoteModel> notesList = new LinkedList<NoteModel>();
			final NoteModel note = modelService.create(NoteModel.class);
			note.setDescription(notes);
			note.setCode(UUID.randomUUID().toString());

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
	public void register(final CustomerModel customerModel, final String password, final Boolean shadowCustomer,
			final String notes) throws DuplicateUidException
	{
		registerCustomer(customerModel, password, shadowCustomer, notes);
		getEventService().publishEvent(initializeEvent(new RegisterEvent(), customerModel));

	}


	@Override
	public void updateProfile(final CustomerModel customerModel, final String titleCode, final String name, final String login,
			final Boolean shadowCustomer, final String notes) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("customerModel", customerModel);

		customerModel.setUid(login);
		customerModel.setName(name);

		if (shadowCustomer)
		{
			addNewNote(customerModel, notes, shadowCustomer);
		}

		if (StringUtils.isNotBlank(titleCode))
		{
			customerModel.setTitle(getUserService().getTitleForCode(titleCode));
		}
		else
		{
			customerModel.setTitle(null);
		}
		internalSaveCustomer(customerModel);
	}

	/*
	 * public void updateProfile(final CustomerModel customerModel, final String address, final String city, final String
	 * postalCode, final String phoneNumber, final String login) {
	 *
	 * validateParameterNotNullStandardMessage("customerModel", customerModel);
	 *
	 * customerModel.setUid(login); customerModel.setName(name); if (StringUtils.isNotBlank(titleCode)) {
	 * customerModel.setTitle(getUserService().getTitleForCode(titleCode)); } else { customerModel.setTitle(null); }
	 * internalSaveCustomer(customerModel);
	 *
	 * }
	 */

	public void addNewNote(final CustomerModel customerModel, final String note, final Boolean shadowCustomer)
	{
		final NoteModel newNotes = modelService.create(NoteModel.class);
		newNotes.setDescription(note);
		newNotes.setCode(UUID.randomUUID().toString());

		final List<NoteModel> notesList = customerModel.getNotes();
		notesList.add(newNotes);

		customerModel.setNotes(notesList);
		customerModel.setShadowCustomer(shadowCustomer);
	}


}
