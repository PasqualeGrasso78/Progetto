/**
 *
 */
package com.techedgegroup.matrix.core.services;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;


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

	public void addNewNote(final CustomerModel customerModel, final String note, final Boolean shadowCustomer);

	/**
	 * @param customer
	 * @param address
	 * @param city
	 * @param posteCode
	 * @param phoneNumber
	 * @param uid
	 */
	//void updateProfile(CustomerModel customer, String address, String city, String posteCode, String phoneNumber, String uid);
}
