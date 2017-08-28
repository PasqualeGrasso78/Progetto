/**
 *
 */
package com.techedgegroup.matrix.core.services;

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author alessio
 *
 */
public interface CustomerRecordsService
{
	CustomerModel getCustomerForCode(String code);

}
