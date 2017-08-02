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
}
