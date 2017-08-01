/**
 *
 */
package com.techedgegroup.matrix.core.services.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import com.techedgegroup.matrix.core.dao.CustomerRecordsDao;
import com.techedgegroup.matrix.core.services.CustomerRegordsService;


/**
 * @author alessio
 *
 */
public class DefaultCustomerRecordsService implements CustomerRegordsService
{
	private CustomerRecordsDao customerRecordsDao;

	@Override
	public CustomerModel getCustomerForCode(final String code)
	{
		final CustomerModel result = customerRecordsDao.findCustomerRecords(code);

		if (result == null)
		{

			throw new UnknownIdentifierException("Customer with code '" + code + "' not found!");

		}

		return result;
	}

	public void setCustomerRecordsDao(final CustomerRecordsDao customerRecordsDao)
	{
		this.customerRecordsDao = customerRecordsDao;
	}

}
