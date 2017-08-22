package com.techedgegroup.matrix.facades.facade;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;

import user.data.ExtendedRegisterData;


/**
 * @author alessio
 *
 */
public interface ExtendedCustomerFacade
{
	public void register(final ExtendedRegisterData registerData) throws DuplicateUidException;
	/*
	 * /**
	 *
	 * @param extendedCustomerData
	 *
	 * @throws DuplicateUidException
	 */
	//	void updateProfile(ExtendedCustomerData extendedCustomerData) throws DuplicateUidException;


}
