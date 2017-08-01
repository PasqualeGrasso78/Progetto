/**
 *
 */
package com.techedgegroup.matrix.core.dao;

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author alessio
 *
 */
public interface CustomerRecordsDao
{
	public CustomerModel findCustomerRecords(String code);
}
