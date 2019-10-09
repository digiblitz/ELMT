/*
 * EJBHomeGetter.java
 *
 * Created on August 13, 2006, 3:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;

import com.hlcmro.display.HLCVaigaiStatelessRemoteHome;
import javax.naming.*;
import javax.ejb.*;
import javax.rmi.*;

/**
 *
 * @author suresh
 */
public final class HLCEJBHomeGetter {
    
    public static HLCVaigaiStatelessRemoteHome getAccountHome() throws NamingException {
        InitialContext initial = new InitialContext();
        Object objref = initial.lookup(HLCEJBAllJNDIs.MRODISPLAYFORM_EJBHOME);

        return (HLCVaigaiStatelessRemoteHome) PortableRemoteObject.narrow(objref,
            HLCVaigaiStatelessRemoteHome.class);
    }
    
}
