package com.techedgegroup.matrix.facades.facade.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.techedgegroup.matrix.core.services.ExtendedCustomerAccountService;
import com.techedgegroup.matrix.facades.facade.ExtendedCustomerFacade;

import user.data.ExtendedRegisterData;


public class DefaultExtendedCustomerFacade extends DefaultCustomerFacade implements ExtendedCustomerFacade
{
	private ExtendedCustomerAccountService extendedCustomerAccountService;

	@Override
	public void register(final ExtendedRegisterData registerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));

		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		final TitleModel title = getUserService().getTitleForCode(registerData.getTitleCode());
		newCustomer.setTitle(title);
		setUidForRegister(registerData, newCustomer);
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		getExtendedCustomerAccountService().register(newCustomer, registerData.getPassword(), registerData.getShadowCustomer(),
				registerData.getNotes());
	}

	/**
	 * @return
	 */
	private ExtendedCustomerAccountService getExtendedCustomerAccountService()
	{
		// YTODO Auto-generated method stub
		return extendedCustomerAccountService;
	}

	/**
	 * @param extendedCustomerAccountService
	 *           the extendedCustomerAccountService to set
	 */
	public void setExtendedCustomerAccountService(final ExtendedCustomerAccountService extendedCustomerAccountService)
	{
		this.extendedCustomerAccountService = extendedCustomerAccountService;
	}

}
