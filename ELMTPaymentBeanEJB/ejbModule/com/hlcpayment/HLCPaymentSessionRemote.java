/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.hlcpayment;

import com.hlccommon.util.HLCPaymentDetailVO;
import javax.ejb.EJBObject;
import java.rmi.*;

/**
 * This is the remote interface for PaymentSession enterprise bean.
 */
public interface HLCPaymentSessionRemote extends EJBObject, HLCPaymentSessionRemoteBusiness {
     public boolean createPayment(HLCPaymentDetailVO objPayDet) throws RemoteException;

    public boolean updateDeclinePaymentStatus(HLCPaymentDetailVO objPayDet) throws RemoteException;
     
}
