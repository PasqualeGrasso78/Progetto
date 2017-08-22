
package com.techedgegroup.matrix.facades.customer.impl;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commercefacades.user.exceptions.PasswordMismatchException;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.TokenInvalidatedException;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;






/**
 * @author pasquale
 *
 */
public class MatrixDefaultCustomerFacade implements CustomerFacade
{
	private Converter<UserModel, CustomerData> customerConverter;
	private UserService userService;

	private static final Logger LOG = Logger.getLogger(MatrixDefaultCustomerFacade.class);


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commercefacades.customer.CustomerFacade#register(de.hybris.platform.commercefacades.user.data.
	 * RegisterData)
	 */
	@Override
	public void register(final RegisterData registerData)
			throws DuplicateUidException, UnknownIdentifierException, IllegalArgumentException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#forgottenPassword(java.lang.String)
	 */
	@Override
	public void forgottenPassword(final String uid)
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#updatePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void updatePassword(final String token, final String newPassword) throws TokenInvalidatedException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#getCurrentCustomer()
	 */
	@Override
	public CustomerData getCurrentCustomer() throws ConversionException
	{
		return getCustomerConverter().convert(getCurrentUser());
	}

	protected UserModel getCurrentUser()
	{
		return getUserService().getCurrentUser();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#getCurrentCustomerUid()
	 */
	@Override
	public String getCurrentCustomerUid()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#changeUid(java.lang.String, java.lang.String)
	 */
	@Override
	public void changeUid(final String newUid, final String currentPassword)
			throws DuplicateUidException, PasswordMismatchException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void changePassword(final String oldPassword, final String newPassword) throws PasswordMismatchException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commercefacades.customer.CustomerFacade#updateProfile(de.hybris.platform.commercefacades.user.data
	 * .CustomerData)
	 */
	@Override
	public void updateProfile(final CustomerData customerData) throws DuplicateUidException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commercefacades.customer.CustomerFacade#updateFullProfile(de.hybris.platform.commercefacades.user.
	 * data.CustomerData)
	 */
	@Override
	public void updateFullProfile(final CustomerData customerData) throws DuplicateUidException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#loginSuccess()
	 */
	@Override
	public void loginSuccess()
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#changeGuestToCustomer(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void changeGuestToCustomer(final String pwd, final String orderCode) throws DuplicateUidException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#generateGUID()
	 */
	@Override
	public String generateGUID()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#createGuestUserForAnonymousCheckout(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void createGuestUserForAnonymousCheckout(final String email, final String name) throws DuplicateUidException
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commercefacades.customer.CustomerFacade#updateCartWithGuestForAnonymousCheckout(de.hybris.platform
	 * .commercefacades.user.data.CustomerData)
	 */
	@Override
	public void updateCartWithGuestForAnonymousCheckout(final CustomerData guestCustomerData)
	{
		// YTODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.CustomerFacade#rememberMeLoginSuccessWithUrlEncoding(boolean,
	 * boolean)
	 */
	@Override
	public void rememberMeLoginSuccessWithUrlEncoding(final boolean languageEncoding, final boolean currencyEncoding)
	{
		// YTODO Auto-generated method stub

	}

	protected Converter<UserModel, CustomerData> getCustomerConverter()
	{
		return customerConverter;
	}

	@Required
	public void setCustomerConverter(final Converter<UserModel, CustomerData> customerConverter)
	{
		this.customerConverter = customerConverter;
	}

	protected UserService getUserService()
	{
		return userService;
	}


	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}







}
