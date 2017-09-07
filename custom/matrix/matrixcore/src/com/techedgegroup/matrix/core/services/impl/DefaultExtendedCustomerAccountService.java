/**
 *
 */
package com.techedgegroup.matrix.core.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.techedgegroup.matrix.core.model.NoteModel;
import com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService;


/**
 * @author alessio
 *
 */
public class DefaultExtendedCustomerAccountService extends DefaultCustomerAccountService implements ExtendedCustomerAccountService
{



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

	@Deprecated
	@Override
	public void updateProfile(final CustomerModel customerModel, final String titleCode, final String name, final String login,
			final Boolean shadowCustomer) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("customerModel", customerModel);

		customerModel.setUid(login);
		customerModel.setName(name);
		customerModel.setShadowCustomer(shadowCustomer);

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

	@Override
	public void addNote(final CustomerModel customerModel, final String note, final Boolean shadowCustomer)
	{
		final NoteModel newNotes = modelService.create(NoteModel.class);
		newNotes.setDescription(note);
		newNotes.setCode(UUID.randomUUID().toString());

		final List<NoteModel> notesList = customerModel.getNotes();
		final List<NoteModel> note2 = Lists.newArrayList(notesList);


		note2.add(newNotes);

		customerModel.setNotes(note2);
		customerModel.setShadowCustomer(shadowCustomer);
	}

	@Override
	public void addNewNote(final CustomerModel customer, final String uid, final Boolean isShadow, final String note)
	{
		customer.setUid(uid);
		addNote(customer, note, isShadow);
		try
		{
			internalSaveCustomer(customer);
		}
		catch (final DuplicateUidException e)
		{
			// YTODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<NoteModel> getNoteEntries(final CustomerModel customerModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		final List<NoteModel> noteModels = new ArrayList<NoteModel>();

		for (final NoteModel note : customerModel.getNotes())
		{
			noteModels.add(note);

		}

		return noteModels;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService#deleteAddressEntry(de.hybris.platform.core.
	 * model.user.CustomerModel, com.techedgegroup.matrix.core.model.NoteModel)
	 */
	@Override
	public void deleteNoteEntry(final CustomerModel customerModel, final NoteModel noteModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(noteModel, "Address model cannot be null");

		if (customerModel.getNotes().contains(noteModel))
		{

			getModelService().remove(noteModel);
			getModelService().refresh(customerModel);

		}
		else
		{
			throw new IllegalArgumentException(
					"Note " + noteModel + " does not belong to the customer " + customerModel + " and will not be removed.");
		}

	}


}
