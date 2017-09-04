/**
 *
 */
package com.techedgegroup.matrix.core.services;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.techedgegroup.matrix.core.model.NoteModel;


/**
 * @author alessio
 *
 */
public interface ExtendedCustomerAccountService
{
	/**
	 * @param customerModel
	 * @param password
	 * @param shadowCustomer
	 * @param notes
	 * @throws DuplicateUidException
	 */
	void registerCustomer(CustomerModel customerModel, String password, Boolean shadowCustomer, String notes)
			throws DuplicateUidException;

	/**
	 * @param customerModel
	 * @param password
	 * @param shadowCustomer
	 * @param notes
	 * @throws DuplicateUidException
	 */
	void register(CustomerModel customerModel, String password, Boolean shadowCustomer, String notes) throws DuplicateUidException;

	public void updateProfile(final CustomerModel customerModel, final String titleCode, final String name, final String login,
			final Boolean shadowCustomer, final String notes) throws DuplicateUidException;

	/**
	 * @param customer
	 * @param uid
	 * @param isShadow
	 * @param note
	 */
	void addNewNote(CustomerModel customer, String uid, Boolean isShadow, String note);

	/**
	 * @param customerModel
	 * @param note
	 * @param shadowCustomer
	 */
	void addNote(CustomerModel customerModel, String note, Boolean shadowCustomer);

	/**
	 * @param currentCustomer
	 * @return
	 */
	List<NoteModel> getNoteEntries(CustomerModel currentCustomer);

	/**
	 * @param currentCustomer
	 * @param noteModel
	 */
	void deleteNoteEntry(CustomerModel currentCustomer, NoteModel noteModel);

}
