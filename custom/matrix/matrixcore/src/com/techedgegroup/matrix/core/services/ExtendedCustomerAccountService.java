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
	void registerCustomer(CustomerModel customerModel, String password, boolean shadowCustomer, java.util.List<NoteModel> notes)
			throws DuplicateUidException;

	/**
	 * @param customerModel
	 * @param password
	 * @param shadowCustomer
	 * @param notes
	 * @throws DuplicateUidException
	 */
	void register(CustomerModel customerModel, String password, boolean shadowCustomer, List<NoteModel> notes)
			throws DuplicateUidException;
}
