/*
 * TestClient.java
 *
 * Created on May 13, 2007, 12:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.session;

/**
 *
 * @author karthikeyan
 */

import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.Debug;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class HLCTestClient {
    private static String memberId;
    private static String priceId;
    private static String userId;
    private static String meetingId;
    private static String val;
    Vector vObj;
    /** Creates a new instance of TestClient */
    public HLCTestClient() {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vector vObj = new Vector();
        Collection col;
        ArrayList array;
        ArrayList array1;
         String name = "ejb/HLCMahanadhiSessionBean";
         try {
                Debug.print("jndi name :"+name);
                
                Context jndiContext = getInitialContext();
                Debug.print("\n after InitialContext Beginning emp Client...\n");
                Object obj=jndiContext.lookup(name);
                HLCMahanadhiSessionRemoteHome home = (HLCMahanadhiSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCMahanadhiSessionRemoteHome.class);
                HLCMahanadhiSessionRemote remote = home.create();
                Debug.print("\n after create ...\n");
                
                ArrayList txDet = new ArrayList();
                Date lstdate = new Date();
                
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
                lstdate = (Date)sdf.parse("5/13/2007");
                txDet = remote.listAccTxnDetails(lstdate,false);
                HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();
                
                for(int i=0;i<txDet.size();i++)
                {
                    accTxnVO = new HLCAccTransactionVO();
                    accTxnVO = (HLCAccTransactionVO)txDet.get(i);
                    
                    Debug.print("accTxnVO.toString() :"+accTxnVO.toString());
                }                                
                
        } catch (Exception ex) {
            System.err.println("Caught an exception."+ex.getMessage());
            ex.printStackTrace();
        }
    }    
    
    public static Context getInitialContext()
        throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
    
}
