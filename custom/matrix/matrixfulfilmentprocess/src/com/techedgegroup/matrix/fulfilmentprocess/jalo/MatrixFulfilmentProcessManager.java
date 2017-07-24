/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.techedgegroup.matrix.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.techedgegroup.matrix.fulfilmentprocess.constants.MatrixFulfilmentProcessConstants;

@SuppressWarnings("PMD")
public class MatrixFulfilmentProcessManager extends GeneratedMatrixFulfilmentProcessManager
{
	public static final MatrixFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (MatrixFulfilmentProcessManager) em.getExtension(MatrixFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
