/**
 *
 */
package com.techedgegroup.matrix.core.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import com.techedgegroup.matrix.core.dao.CustomerRecordsDao;


/**
 * @author alessio
 *
 */
public class DefaultCustomerRecordsDao implements CustomerRecordsDao
{
	private FlexibleSearchService flexibleSearchService;


	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}


	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}


	@Override
	public CustomerModel findCustomerRecords(final String code)
	{

		final String queryString = "SELECT {p:" + CustomerModel.PK + "}" //
				+ "FROM {" + CustomerModel._TYPECODE + " AS p} "//
				+ "WHERE " + "{p:" + CustomerModel.CUSTOMERID + "}=?code ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", code);

		return (CustomerModel) flexibleSearchService.<CustomerModel> search(query).getResult();
	}

}
