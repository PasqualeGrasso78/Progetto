/**
 *
 */
package com.techedgegroup.matrix.core.services;

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author alessio
 *
 */
public interface CustomerRegordsService
{
	CustomerModel getCustomerForCode(String code);
}
