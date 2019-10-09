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
package com.systinet.info;


import com.form.SystinetRequestVo;
import com.form.SystinetUserRegVo;
import com.systinet.dao.SystinetDao;


import java.text.SimpleDateFormat;
import javax.ejb.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.*;
import java.security.GeneralSecurityException;
import java.security.NoSuchProviderException;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.transaction.*;
import org.idoox.wasp.SecurityHelper;
import org.idoox.security.Credentials;
/*import org.systinet.platform.rest.Client;
import org.systinet.platform.rest.ClientException;
import org.systinet.platform.rest.Source;
import org.systinet.platform.sdm.SdmClientConstants;
import org.systinet.platform.sdm.SdmClientHelper;*/

import com.hp.systinet.lifecycle.remote.ArtifactGovernanceService;
import com.hp.systinet.lifecycle.remote.GovernanceServiceFactory;
import com.hp.systinet.lifecycle.remote.ProcessManagementService;
//import com.hp.systinet.lifecycle.remote.VotingDefinition;
//import com.hp.systinet.lifecycle.remote.model.ApprovalVoter;
import com.hp.systinet.lifecycle.remote.model.GovernanceProcess;
import com.hp.systinet.lifecycle.remote.model.GovernanceStatus;
import com.hp.systinet.lifecycle.remote.model.Stage;
import com.hp.systinet.lifecycle.remote.model.StageCategory;
import com.hp.systinet.lifecycle.remote.model.Task;
//import com.hp.systinet.lifecycle.remote.model.Task;
//import com.hp.systinet.lifecycle.remote.model.Principal;
//import com.hp.systinet.lifecycle.remote.model.Validation;
import com.hp.systinet.lifecycle.remote.model.VotingStatus;
import com.form.DemoProperties;
//import org.systinet.platform.sdm.xsd.artifact.HpsoaBpelProcessArtifact;
import org.systinet.platform.sdm.xsd.artifact.BusinessServiceArtifact;
import org.systinet.platform.sdm.xsd.artifact.DocumentationArtifact;
import org.systinet.platform.sdm.xsd.artifact.HpsoaBpelProcessArtifact;
import org.systinet.platform.sdm.xsd.artifact.HpsoaProcessArtifact;
import org.systinet.platform.sdm.xsd.artifact.WebServiceArtifact;
import org.systinet.platform.sdm.xsd.artifact.WsdlArtifact;
import org.systinet.platform.sdm.xsd.group.DefinitionGroup;
import org.systinet.platform.sdm.xsd.group.DescriptionGroup;
import org.systinet.platform.sdm.xsd.group.DocumentationGroup;
import org.systinet.platform.sdm.xsd.group.NameGroup;
import org.systinet.platform.sdm.xsd.group.R_hpsoaProcessImplGroup;
import org.systinet.platform.sdm.xsd.group.ServiceGroup;
import org.systinet.platform.sdm.xsd.property.Definition;
import org.systinet.platform.sdm.xsd.property.Documentation;
import org.systinet.platform.sdm.xsd.property.Location;
import org.systinet.platform.sdm.xsd.property.Name;
import org.systinet.platform.sdm.xsd.property.Criticality;
import org.systinet.platform.sdm.xsd.property.Description;
import org.systinet.platform.sdm.xsd.property.R_hpsoaProcessImpl;
import org.systinet.platform.sdm.xsd.property.R_serviceType;
import org.systinet.platform.sdm.xsd.property.Service;
//import org.systinet.platform.sdm.xsd.property.ServiceName;
//import org.systinet.platform.sdm.xsd.property.ServiceNamespace;
import org.systinet.platform.sdm.xsd.property.TargetNamespace;
import org.systinet.platform.sdm.xsd.property.Version;
import org.systinet.platform.sdm.SdmClientConstants;
import org.systinet.platform.rest.Client;
//import org.systinet.platform.rest.ClientException;
import org.systinet.platform.rest.Source;
import org.systinet.wasp.Wasp;
//import org.idoox.security.Credentials;
//import org.idoox.wasp.SecurityHelper;
//import org.idoox.wasp.WaspSecurity;
//import org.systinet.wasp.Wasp;
//import org.systinet.wasp.webservice.ServiceClient;
//imports for repository client
//import com.hp.systinet.lang.Pair;
import com.hp.systinet.repository.remote.client.RepositoryClient;
import com.hp.systinet.repository.remote.client.impl.RepositoryClientFactory;
import com.hp.systinet.repository.sdm.ArtifactBase;
//import com.hp.systinet.repository.sdm.desc.ArtifactDescriptor;
//import com.hp.systinet.repository.sdm.desc.PropertyDescriptor;
//import com.hp.systinet.repository.sdm.properties.PropertyValue;
import com.hp.systinet.repository.sdm.properties.Relation;
import com.hp.systinet.repository.sdm.properties.Uuid;
import com.hp.systinet.repository.sdm.propertytypes.Category;
//import common.DemoProperties;
import java.util.HashSet;
import java.util.Set;







/**
 * This is the bean class for the kaverystatelessBean enterprise bean.
 * Created Aug 24, 2006 6:34:06 PM
 * @author harmohan
 */

public class SystinetStatelessBean implements SessionBean, SystinetStatelessRemoteBusiness{
    private SessionContext context;
    private InitialContext ic = null;
    
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    
    public void ejbCreate()
    {
    	
    }
    // </editor-fold>
    
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
   //=========================== BPM/WS start=======================================
    
    String soa_urlIP="http://198.71.58.51:8080/soa";
    
	 public boolean insertSystinetUser(SystinetUserRegVo vo) throws RemoteException{
		 System.out.println("kaverystateless Bean insertSystinetUser");
	    
	       boolean Inser = false;
	       try{
	    	   System.out.println("login Name==="+vo.getLoginName());
	    	   System.out.println("email     ==="+vo.getEmailId());
	    	   System.out.println("password  ==="+vo.getPassword());
	    	   System.out.println("full Name==="+vo.getFullName());
	    	   System.out.println("def packa==="+vo.getDefLanguage());
	    	   System.out.println("descript ==="+vo.getDescription());
	    	   System.out.println("business Name==="+vo.getBusinessName());
	    	   System.out.println("phone ==="+vo.getPhoneNo());
	    	   System.out.println("phone1 ==="+vo.getMobileNo());
	    	   System.out.println("city ==="+vo.getCity());
	    	   System.out.println("state ==="+vo.getState());
	    	   System.out.println("country ==="+vo.getCountry());
	    	   System.out.println("zip==="+vo.getZip());
	    	   System.out.println("userpro==="+vo.getUserProfile());
	    	 	    	   
	    	   
	       }catch(Exception e){
	          System.out.println("Exception while calling insertSystinetUser():" + e.getMessage());  
	       }  
	       return Inser;
	 }
	 
	 
	 public  ArrayList Stagename(String lifecycleId,String artifactId)  throws RemoteException{
		 System.out.println("kaverystateless Bean insertSystinetUser");
		 ArrayList list=new ArrayList();
		 
		 String artifacttype="hpsoaProcessArtifact";
		 SystinetDao da0=new SystinetDao();
		 
		// artifacttype
		 list= da0.Stagename(lifecycleId,artifactId);
		 
		 
		 return list;
	 }
	 
	 
	 
	 public  ArrayList viewWsBPMList(String reqName)  throws RemoteException{
		 System.out.println("kaverystateless Bean insertSystinetUser");
		 ArrayList list=new ArrayList();
		
		 SystinetDao da0=new SystinetDao();
		 if(reqName.equalsIgnoreCase("WS")){
	
			 String artifacttype="businessServiceArtifact";
			 list= da0.listWsBpm(artifacttype);
     	
			
	       
		 }
		 else if(reqName.equalsIgnoreCase("BPM"))
		 {
			
			 String artifacttype="hpsoaProcessArtifact"; 	
	       	 
			 list= da0.listWsBpm(artifacttype);
			      
			 
		 }
		 return list;
	 }
	 
	 public boolean chgStatusWSBPM(String lifecycleId, String artifactId) throws RemoteException{
		 System.out.println("kaverystateless Bean chgStatusWSBPM");
	    
		  boolean update = false;
	       try{
	    	   
	    	   SystinetDao da0=new SystinetDao();
	    	   
	    	   
	    	   update=da0.chgStatusWSBPM(lifecycleId,artifactId);
	   
	    	 	    	   
	    	   
	       }catch(Exception e){
	          System.out.println("Exception while calling chgStatusWSBPM():" + e.getMessage());  
	       }  
	       return update;
	 }
	 
	
	 
	 
	 public boolean insertRequestValues(SystinetRequestVo vo,String reqName) throws RemoteException{
		 System.out.println("kaverystateless Bean insertRequestValues");
	    
		  boolean Inser = false;
	       try{
	    	   
	    	   
	    	   /**
	    	    * 1. in table tblwsbpmlist  the artifct id need to modify with the based on the artifact created.
				* 2. when submit the form it should call requestapproval method and the sttus has to be updte as pending
	    	    * 
	    	    */
	    	   SystinetDao da0=new SystinetDao();
	           if(reqName.equalsIgnoreCase("WS")){
	          
	           // call requestapproval method
	            System.out.println("ws method call");
	           
	           Inser=da0.insertBundlesReq(vo,reqName);
	           
	         
	           
	           }
	           
	           
	           else if(reqName.equalsIgnoreCase("BP"))
	        {
	            System.out.println("ws method call");
	            
	            
	            Inser=da0.insertBundlesReq(vo,reqName);
	           //read businessprocesslifecycle table based on the artifactUuid (vo.getReqUuid)and insert into voting table 
	           //where requestName will be "modify" 
	           // and the first stage of the life cycle will be in "pending" status.
	           //then read voting table base on the conon the condition and populate in
	      
	        }
	    	 	    	   
	    	   
	       }catch(Exception e){
	          System.out.println("Exception while calling insertRequestValues():" + e.getMessage());  
	       }  
	       return Inser;
	 }
	 
	 //===========================BPM/WS end===============================================
 
	 
	 public  ArrayList viewBPReqList(String date, String typeReq,String RrqId,String reqPri)  throws RemoteException{
		 System.out.println("kaverystateless Bean viewBPReqList");
		 ArrayList lst = new ArrayList();		
		
		 try{
	    	   
	    	   SystinetDao da0=new SystinetDao();
	    	   
	    	   
	    	   lst=da0.listRequestView(date, typeReq, RrqId, reqPri);
	   
	    	 	    	   
	    	   
	       }catch(Exception e){
	          System.out.println("Exception while calling insertRequestValues():" + e.getMessage());  
	       }  
 	
	       
		 return lst;
		
	 }
	 
	 
	/* public boolean insertLifecycleprocess (ArrayList vo) throws RemoteException{
		 System.out.println("kaverystateless Bean insertSystinetUser");
	    
	       boolean Inser = false;
	       try{
	    	   ArrayList artifactName=vo;
	    	   
	    	   if (artifactName != null && artifactName.size() != 0) { 
	    	    	 Iterator evotesartifactName = artifactName.iterator();
	    	    	 //String [] userType = {ID, name };
	    	    	 while (evotesartifactName.hasNext()) {
	    	             String strType[] = (String[]) evotesartifactName.next();
	    	         	
	    		    String lifecycleProName=strType[0];
	    	    	String numofStage=strType[1];
	    	    	String dateofCreation=strType[2];
	    	    	String createdBy=strType[3];
	    	    	String stage=strType[4];
	    	    	String input=strType[5];
	    	    	String process=strType[6];
	    	    	String control=strType[7];
	    	    	String output=strType[8];
	    	    	String descripition=strType[9];
	    	        String addArtifacttype=strType[10]; 	
	    	        String inputID=strType[11]; 	
	    	        
	    	        
	    	        System.out.println("Bean classed called =========> artifactName"+artifactName);
	    	    	System.out.println("lifecycleProName"+lifecycleProName+"  numofStage"+numofStage+"  dateofCreation"+dateofCreation+"    createdBy"+createdBy+"   stage"+stage+"  input"+input+"   process"+process+"   control"+control+"   output"+output+"    descripition"+descripition);
	    	    	 }
	    	   }
	    	         	

	    
	    	   
	       }catch(Exception e){
	          System.out.println("Exception while calling insertSystinetUser():" + e.getMessage());  
	       }  
	       return Inser;
	 }
	   */
	 
	 //===============Dhivya:Here:Lifecycle creation==================
		 public boolean addStageToProcess (String processId, StageCategory frmStageName, Collection<StageCategory> toStageName) throws RemoteException, MalformedURLException{
			 System.out.println("systinetStatelessBean addStageToProcess");
		    
		       boolean addOutput = false;
		       
		       String ENDPOINT_BASE;
				String USER_DEMO_NAME;
				String USER_DEMO_PASSWORD;
				
				ProcessManagementService service;
				
				 GovernanceProcess govProObjs;
				
			//	StageCategory initialStage = new StageCategory("Candidate","uddi:systinet.com:soa:model:taxonomies:lifecycleStages:candidate");
				
					ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
							soa_urlIP);
			USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
					"admin");
			USER_DEMO_PASSWORD = DemoProperties.getProperty(
					"platform.demos.user.password", "adminadmin");

			service= GovernanceServiceFactory.createProcessManagementService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
		       try{
		    	   
		    	   if (processId != null) { 
		    		   	    		   
		    		    govProObjs=service.addStageToProcess(processId, frmStageName, toStageName);    	    	
		    		    
		    		    List<Stage> stageList=govProObjs.getStages();
		    		    
		    		    if(stageList!=null && stageList.size()!=0){
		 	    		   
		    		    	addOutput=true;   
		 	    	   }
		    		    
		    	   }
		    	 	    	          	    	   
		       }catch(Exception e){
		          System.out.println("Exception while calling addStageToProcess():" + e.getMessage());  
		       }  
		       return addOutput;
		 }
		 
		 public String createLifecycleinSystinet (String lifecycleProName,String descripition,String rootArtifact,Collection<String> subArti,StageCategory initialStageName) throws RemoteException, MalformedURLException{
				          	 
		 String output="";
			 
		 String ENDPOINT_BASE;
			String USER_DEMO_NAME;
			String USER_DEMO_PASSWORD;
			
			ProcessManagementService service;
			
			 GovernanceProcess govProObj;
				
				ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
						soa_urlIP);
		USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
				"admin");
		USER_DEMO_PASSWORD = DemoProperties.getProperty(
				"platform.demos.user.password", "adminadmin");

		service= GovernanceServiceFactory.createProcessManagementService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);

		 
		System.out.println("initialStageName:statelessBean"+initialStageName);
		
		govProObj =service.createProcess(lifecycleProName, descripition, rootArtifact, subArti, initialStageName);
		
		
		 String processId=govProObj.getUuid();
		 
		 System.out.println("processId"+processId);
			
			if(processId!=null){
				service.removeStageFromProcess(processId,initialStageName);	
			
			}
			return processId;
		       }
		 
		 public boolean publishProcess (String processId) throws RemoteException, MalformedURLException{
			 System.out.println("systinetStatelessBean publishProcess");
		    
		       boolean addOutput = false;
		       
		       String ENDPOINT_BASE;
				String USER_DEMO_NAME;
				String USER_DEMO_PASSWORD;
				
				ProcessManagementService service;
				
				 GovernanceProcess govProObjs;
				
			//	StageCategory initialStage = new StageCategory("Candidate","uddi:systinet.com:soa:model:taxonomies:lifecycleStages:candidate");
				
					ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
							soa_urlIP);
			USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
					"admin");
			USER_DEMO_PASSWORD = DemoProperties.getProperty(
					"platform.demos.user.password", "adminadmin");

			service= GovernanceServiceFactory.createProcessManagementService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
		       try{
		    	   
		    	   if (processId != null) { 
		    		   	    		   
		    		    govProObjs=service.publishProcess(processId);    	    	
		    		    		    	    		    
		    	   }
		    	 	    	          	    	   
		       }catch(Exception e){
		          System.out.println("Exception while calling publishProcess():" + e.getMessage());  
		       }  
		       return addOutput;
		 }
		 
	
		 public boolean setvotingDetails (String processId, StageCategory frmStageName, VotingStatus votedef) throws RemoteException, MalformedURLException{
			 System.out.println("systinetStatelessBean setvotingDetails");
		    
		       boolean votoutput = false;
		       
		       String ENDPOINT_BASE;
				String USER_DEMO_NAME;
				String USER_DEMO_PASSWORD;
				
				ProcessManagementService service;
				
				 GovernanceProcess govProObjs;
				
			//	StageCategory initialStage = new StageCategory("Candidate","uddi:systinet.com:soa:model:taxonomies:lifecycleStages:candidate");
				
					ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
							soa_urlIP);
			USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
					"admin");
			USER_DEMO_PASSWORD = DemoProperties.getProperty(
					"platform.demos.user.password", "adminadmin");

			service= GovernanceServiceFactory.createProcessManagementService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
		       try{
		    	   
		    	   
		    	   
		    	   if (processId != null && votedef!=null) { 
		    		   	    		   
		    		    govProObjs=service.setVotingToProcess(processId,frmStageName,votedef);    	    			    				    		   
		    	   }
		    	 	    	          	    	   
		       }catch(Exception e){
		          System.out.println("Exception while calling setvotingDetails():" + e.getMessage());  
		       }  
		       return votoutput;
		 }	
		 
		 public boolean setTaskDetails (String processId, StageCategory frmStageName, Task taskDef) throws RemoteException, MalformedURLException{
			 System.out.println("systinetStatelessBean setTaskDetails");
		    
		       boolean votoutput = false;
		       
		       String ENDPOINT_BASE;
				String USER_DEMO_NAME;
				String USER_DEMO_PASSWORD;
				
				ProcessManagementService service;
				
				 GovernanceProcess govProObjs;
				
			//	StageCategory initialStage = new StageCategory("Candidate","uddi:systinet.com:soa:model:taxonomies:lifecycleStages:candidate");
				
					ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
							soa_urlIP);
			USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
					"admin");
			USER_DEMO_PASSWORD = DemoProperties.getProperty(
					"platform.demos.user.password", "adminadmin");

			service= GovernanceServiceFactory.createProcessManagementService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
		       try{
		    	   
		    	   
		    	   
		    	   if (processId != null && taskDef!=null) { 
		    		   	    		   
		    		    govProObjs=service.addTaskToProcess(processId,frmStageName,taskDef);    	    			    				    		   
		    	   }
		    	 	    	          	    	   
		       }catch(Exception e){
		          System.out.println("Exception while calling setTaskDetails():" + e.getMessage());  
		       }  
		       return votoutput;
		 }		 
		 
		 
		 
		 
		  public String getArtifactUUid(String artifactName) throws RemoteException{
			   System.out.println("SystinetStatelessBean getArtifactUUid()");
			     
			    String artiuuid = null;
			        try{
			        	if(artifactName!= null){
			        		
			         SystinetDao da0=new SystinetDao();
			         			     			         
			         artiuuid=da0.getArtifactUUid(artifactName);
			    
			        	}
			         
			        }catch(Exception e){
			           System.out.println("Exception while calling getArtifactUUid():" + e.getMessage());  
			        }  
			        return artiuuid;
			  }	 
		  
		  public boolean isLifecycleExist(String lifecycleId) throws RemoteException{
			   System.out.println("SystinetStatelessBean isLifecycleExist()");
			     
			   boolean result=false;
			        try{
			        	if(lifecycleId!= null){
			        		
			         SystinetDao da0=new SystinetDao();
			         			     			         
			         result=da0.isLifecycleExist(lifecycleId);
			    
			        	}
			         
			        }catch(Exception e){
			           System.out.println("Exception while calling isLifecycleExist():" + e.getMessage());  
			        }  
			        return result;
			  }	 
		 
		  public ArrayList getLifecycleProc(String artifactType) throws RemoteException{
			   System.out.println("SystinetStatelessBean getLifecycleProc()");
			     
			    ArrayList lifecycleList=null;
			        try{
			        	if(artifactType!= null){
			        		
			         SystinetDao da0=new SystinetDao();
			         			     			         
			         lifecycleList=da0.getLifecycleProc(artifactType);
			    
			        	}
			         
			        }catch(Exception e){
			           System.out.println("Exception while calling getLifecycleProc():" + e.getMessage());  
			        }  
			        return lifecycleList;
			  }	  
	
		  
		  public boolean startGovern (String artifactUuid, String lifecyleId) throws RemoteException, MalformedURLException{
				 System.out.println("systinetStatelessBean startGovern");
			    
			       boolean startGov = false;
			       
			       String ENDPOINT_BASE;
					String USER_DEMO_NAME;
					String USER_DEMO_PASSWORD;
					
					ArtifactGovernanceService service;
					
					 GovernanceStatus govProObjs;
					
				//	StageCategory initialStage = new StageCategory("Candidate","uddi:systinet.com:soa:model:taxonomies:lifecycleStages:candidate");
					
						ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
								soa_urlIP);
				USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
						"admin");
				USER_DEMO_PASSWORD = DemoProperties.getProperty(
						"platform.demos.user.password", "adminadmin");

				service= GovernanceServiceFactory.createArtifactGovernanceService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
			       try{
			    	   
			    	   if (artifactUuid != null && lifecyleId!=null) { 
			    		   	    		   
			    		    govProObjs=service.govern(artifactUuid, lifecyleId);    
			    		    
			    		    System.out.println(govProObjs.getStatus()); 
			    		    
			    		    startGov=true;    
			    	   }
			    	 	    	          	    	   
			       }catch(Exception e){
			          System.out.println("Exception while calling setvotingDetails():" + e.getMessage());  
			       }  
			       return startGov;
			 }	
		  public boolean endGovern (String artifactUuid) throws RemoteException, MalformedURLException{
				 System.out.println("systinetStatelessBean endGovern");
			    
			       boolean endGov = false;
			       
			       String ENDPOINT_BASE;
					String USER_DEMO_NAME;
					String USER_DEMO_PASSWORD;
					
					ArtifactGovernanceService service;
					
					 GovernanceStatus govProObjs;
					
				//	StageCategory initialStage = new StageCategory("Candidate","uddi:systinet.com:soa:model:taxonomies:lifecycleStages:candidate");
					
						ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
								soa_urlIP);
				USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
						"admin");
				USER_DEMO_PASSWORD = DemoProperties.getProperty(
						"platform.demos.user.password", "adminadmin");

				service= GovernanceServiceFactory.createArtifactGovernanceService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
			       try{
			    	   
			    	   if (artifactUuid != null) { 
			    		   	    		   
			    		    service.ungovern(artifactUuid);
			    		    
			    		  		    		    
			    		    endGov=true;    
			    	   }
			    	 	    	          	    	   
			       }catch(Exception e){
			          System.out.println("Exception while calling endGovern():" + e.getMessage());  
			       }  
			       return endGov;
			 }  
		  public boolean updateArtifactMapDetails(String artifactUuid, String lifecyleId) throws RemoteException{
			   System.out.println("SystinetStatelessBean updateArtifactMapDetails()");
			     
			    boolean result = false;
			        try{
			        	if(artifactUuid!= null && lifecyleId!=null){
			        		
			         SystinetDao da0=new SystinetDao();
			         			     			         
			         result=da0.updateArtifactMapDetails(artifactUuid,lifecyleId);
			    
			        	}
			         
			        }catch(Exception e){
			           System.out.println("Exception while calling updateArtifactMapDetails():" + e.getMessage());  
			        }  
			        return result;
			  }	 
		 
		  
		  public boolean requestApproval (String artifactUuid, String message) throws RemoteException, MalformedURLException{
				 System.out.println("systinetStatelessBean requestApproval");
			    
				 System.out.println("artifactUuid inside request"+artifactUuid);
				 System.out.println("message inside request"+message);
			       boolean reqRaise = false;
			       
			       String ENDPOINT_BASE;
					String USER_DEMO_NAME;
					String USER_DEMO_PASSWORD;
					
					ArtifactGovernanceService service;					
					 GovernanceStatus govProObjs;
								
						ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
								soa_urlIP);
				USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
						"admin");
				USER_DEMO_PASSWORD = DemoProperties.getProperty(
						"platform.demos.user.password", "adminadmin");

				service= GovernanceServiceFactory.createArtifactGovernanceService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
			       try{
			    	   System.out.println("artifactUuid inside requestApproval:: "+artifactUuid);
			    	   
			    	   SystinetDao da0=new SystinetDao();
			    	   
			    	  
			    	  
			    	   if (artifactUuid!= null) { 
			    		  // service.changeTaskStatus(taskId, true);
			    		   	    		   			    		   
			    		   service.requestApproval(artifactUuid, message);
			    		  // govProObjs.
			    		   reqRaise=true;    
			    	   }	    	 	    	          	    	   
			       }catch(Exception e){
			          System.out.println("Exception while calling requestApproval():" + e.getMessage()); 
			          e.printStackTrace();
			       }  
			       return reqRaise;
			 }		
		  
		  
		  public ArrayList getStageDetails(String lifecyleId) throws RemoteException{
			   System.out.println("SystinetStatelessBean getStageDetails()");
			     
			    ArrayList lifecycleList=null;
			        try{
			        	if(lifecyleId!= null){
			        		
			         SystinetDao da0=new SystinetDao();
			         			     			         
			         lifecycleList=da0.getStageDetails(lifecyleId);
			    
			        	}
			         
			        }catch(Exception e){
			           System.out.println("Exception while calling getStageDetails():" + e.getMessage());  
			        }  
			        return lifecycleList;
			  }	  
	
	
		  public boolean insertRequestDetails(String artifactUuid, String lifecyleId, String stageName, String stageNameValue, String status, String reqName, String stageNo, String reqId, String artifactName, String description) throws RemoteException{
			   System.out.println("SystinetStatelessBean insertRequestDetails()");
			     
			    boolean result = false;
			        try{
			        	if(artifactUuid!= null && lifecyleId!=null){
			        		
			         SystinetDao da0=new SystinetDao();
			         System.out.println("Insidesession bean artifactUUID ="+artifactUuid+"  stageName="+stageName+"  lifecycleId="+lifecyleId+"  stageNameValue="+stageNameValue+"  status="+status+"  reqName="+reqName+"  stageNo="+stageNo);			     			         
			         result=da0.insertRequestDetails(artifactUuid,lifecyleId,stageName,stageNameValue,status,reqName,stageNo,reqId,artifactName,description);
			    
			        	}
			         
			        }catch(Exception e){
			           System.out.println("Exception while calling insertRequestDetails():" + e.getMessage());  
			        }  
			        return result;
			  }	 
		 	  
		  			  
		  public boolean voting (String artifactUuid, String approMsg, boolean status, String StageName) throws RemoteException, MalformedURLException{
				 System.out.println("systinetStatelessBean voting status"+status);
				 System.out.println("systinetStatelessBean voting artifactUuid"+artifactUuid);
				 System.out.println("systinetStatelessBean voting approMsg"+approMsg);
				 
				// String approvMsg="Approve";
			    
			       boolean votingStatus = false;
			       
			       String ENDPOINT_BASE;
					String USER_DEMO_NAME;
					String USER_DEMO_PASSWORD;
					
					ArtifactGovernanceService service;					
					 GovernanceStatus govProObjs;
								
						ENDPOINT_BASE = DemoProperties.getProperty("shared.http.urlbase",
								soa_urlIP);
				USER_DEMO_NAME = DemoProperties.getProperty("platform.demos.user.name",
						"admin");
				USER_DEMO_PASSWORD = DemoProperties.getProperty(
						"platform.demos.user.password", "adminadmin");

				service= GovernanceServiceFactory.createArtifactGovernanceService(ENDPOINT_BASE,USER_DEMO_NAME,USER_DEMO_PASSWORD,true);
			       try{
			    	   
			    	   if (artifactUuid != null) { 
			    		   	    		   			    		   
			    		   service.vote(artifactUuid, approMsg, status);System.out.println(" Inside voting method --> called service.vote(....) ");
			    		   
			    		  // System.out.println("service.getGovernanceStatus(artifactUuid);"+  service.getGovernanceStatus(artifactUuid));
			    		   if(status==true){
			    		   StageCategory nextStage = new StageCategory(StageName,"uddi:systinet.com:soa:model:taxonomies:lifecycleStages:"+StageName.toLowerCase());
			    		 			    		   
			    		  service.nextStage(artifactUuid, nextStage);
			    		  
			    		   }
			    		  // govProObjs.
			    		   votingStatus=true;    
			    	   }
			    	 	    	          	    	   
			       }catch(Exception e){
			          System.out.println("Exception while calling voting():" + e.getMessage());  
			       }  
			       return votingStatus;
			 }
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		 
		 
	//=====Ends Here==========================	 
		 
// Method for creating a business document artifact
		 public String createDocArtifact (ArrayList docList) throws RemoteException,Exception
		 {
			 String endpointBase = DemoProperties.getProperty("shared.http.urlbase",soa_urlIP);
		        String user = DemoProperties.getProperty("platform.demos.user.name","admin");
		        String password = DemoProperties.getProperty("platform.demos.user.password", "adminadmin");

				//String endpointBase=  "http://192.168.1.107:8380/soa/systinet/platform/restBasic/repository/";
				Credentials credentials = SecurityHelper.createCredentials("admin", "adminadmin",SecurityHelper.HttpBasic);
				String docArtifactUri =null;
			//	String BpelProcessUri = HpsoaBpelProcessArtifact.create(endpointBase, "BpelSample", getBpelProcessArtifact(), null, new Source(getResourceAsStream("helloworld.bpel"),Client.CT_XMLDOC_UTF_8),credentials);
				try{
					
				
				if (docList != null || docList.size() != 0) { 
			    	 Iterator iterDoc = docList.iterator();
			    	
			    	 while (iterDoc.hasNext()) {
			             String strType[] = (String[]) iterDoc.next();			             
					String fileName=strType[0];
			    	String path=strType[1];
			    	String description=strType[2];
			    	String criticality=strType[3];
			    	String version=strType[4];
			    	String trname=strType[4];
			    	String location=strType[4];
			    	 
				  docArtifactUri = DocumentationArtifact.create( endpointBase,fileName,getDocumentationArtifact(fileName,description,location,version),null, new Source(getResourceAsStream(path,fileName),Client.MT_TEXTDOC),credentials);
			    
			    	 }  	 
				}
				}catch (Exception e){
					System.out.println("Exception while calling SystinetStatelessBean.createDocArtifact():" + e.getMessage());
				}
			return docArtifactUri;
	 }
		 
		 public static DocumentationArtifact getDocumentationArtifact(String fileName, String description, String location, String version) {
		        DocumentationArtifact docartifact
		                = new DocumentationArtifact();

		        // common properties
		        NameGroup names = new NameGroup(new Name[]{
		                new Name("en", fileName)
		        });
		        docartifact.setNameGroup(names);

		        DescriptionGroup descriptions = new DescriptionGroup(
		                new Description[]{
		                        new Description("en", description)
		                });
		        docartifact.setDescriptionGroup(descriptions);
		        docartifact.setLocation(new Location(location));
		        docartifact.setVersion(new Version(version));
		        
		        //Location location = Upload_Location ;
		     //   Location location = new Location();
				//location.setValue(Upload_Location );
				//Documentation doc= new Documentation();
				//doc.getHref(documentationUri);
		      
		        return docartifact;
		    }
			
			
// Method for reading a file from local disk	
	
			public static InputStream getResourceAsStream(String path,String resourceName) throws IOException {
				InputStream resourceAsStream = new FileInputStream(path+resourceName);			
				//InputStream resourceAsStream = DocumentationArtifactCreation.class.getResourceAsStream("files/" + resourceName);	
				//System.out.println("resorse contains" +resourceAsStream);
			        if (resourceAsStream == null) {
			            throw new RuntimeException("Resource " + resourceName + " cannot be loaded");
			        }
			        else
			        {
			        	System.out.println("inside else loop");
			        }
			        return resourceAsStream;
			    }
			
//Method for creating a WSDL artifact
			public String createWsdlArtifact (ArrayList wsdlWSprocess) throws RemoteException,Exception		
			{
				
				String fileNamewsdl = null	;
				String pathwsdl  = null;
				//String descripwsdl  = null;
				//String criticwsdl  = null;
				//String versionwsdl  = null;
				String tarwsdl  = null;
				String locwsdl  = null;
				
				if (wsdlWSprocess != null && wsdlWSprocess.size() != 0) { 
				    	 Iterator iterwsdl = wsdlWSprocess.iterator();
				    	 
				    	 while (iterwsdl.hasNext()) {
				        String strType[] = (String[]) iterwsdl.next();			             
				     //   fileNamewsdl=strType[0];
				        pathwsdl=strType[1];				        
				        tarwsdl=strType[5];	
				        locwsdl=strType[6];	
				        
				   // System.out.println("Name" +fileNameBs+"consump"+consumpBs+ "desc" +descriptionBs+" critic "+criticalityBs+"ver"+ versionBs);	 
				    	 }
				}
				
				int slashIndex = pathwsdl.lastIndexOf('/');								
				fileNamewsdl = pathwsdl.substring(slashIndex + 1);
				
				String endpoint=  "http://198.71.58.51:8080/soa/systinet/platform/restBasic/";
				Credentials credentials = SecurityHelper.createCredentials("admin", "adminadmin", SecurityHelper.HttpBasic);
				String endpointBase = endpoint+"repository/";
				
			String wsdlUri = WsdlArtifact.create(endpointBase, fileNamewsdl,   getWsdlArtifact(fileNamewsdl,tarwsdl,locwsdl), null,
		            new Source(getResourceAsStream(pathwsdl,fileNamewsdl),Client.CT_XMLDOC_UTF_8),  credentials);
			
			return wsdlUri;
			}

// Support method for creating a WSDL artifact
			
			public static WsdlArtifact getWsdlArtifact(String fileNamewsdl,String tarwsdl,String locwsdl) {
		        WsdlArtifact artifact
		                = new WsdlArtifact();

		        // common properties
		        NameGroup names = new NameGroup(new Name[]{ new Name("en",  fileNamewsdl) });
		        artifact.setNameGroup(names);
		        
		 
		      //  DescriptionGroup descriptions = new DescriptionGroup(new Description[]{ new Description("en", descripwsdl)  });
		        //artifact.setDescriptionGroup(descriptions);


		       // artifact.setDesignTimePolicyGroup(new DesignTimePolicyGroup(new DesignTimePolicy[]{ new DesignTimePolicy("wsPolicies/autoSyncPolicy"), new DesignTimePolicy(designTimePolicyUri) }));

		        // # special properties ###############################################################################################

		       // artifact.setVersion(new Version(versionwsdl));

		        artifact.setTargetNamespace(new TargetNamespace(tarwsdl));

		        artifact.setLocation(new Location(locwsdl));
		        
		        artifact.setIsCached(Boolean.TRUE);
		    //    artifact.setOriginUrl(new OriginUrl(REST_UNSEC_ENDPOINT + MOCK_FTP_WSDL));

		        return artifact;
		    }
			
			
// Method for creating a web Service artifact
			
		public String createWSArtifact( ArrayList wsdlWSprocess) throws RemoteException,Exception
		{
			String fileNamewsdl = null	;
			String pathwsdl  = null;
			String descripwsdl  = null;
			String criticalityws  = null;
			String versionwsdl  = null;
			String tarwsdl  = null;
			String locwsdl  = null;
			
			if (wsdlWSprocess != null && wsdlWSprocess.size() != 0) { 
			    	 Iterator iterwsdl = wsdlWSprocess.iterator();
			    	 
			    	 while (iterwsdl.hasNext()) {
			        String strType[] = (String[]) iterwsdl.next();			             
			        fileNamewsdl=strType[0];
			        pathwsdl=strType[1];
			        descripwsdl=strType[2];
			        criticalityws=strType[3];
			        versionwsdl=strType[4];	
			        tarwsdl=strType[5];	
			        locwsdl=strType[6];	
			        
			   // System.out.println("Name" +fileNameBs+"consump"+consumpBs+ "desc" +descriptionBs+" critic "+criticalityBs+"ver"+ versionBs);	 
			    	 }
			}
				 final String TAXONOMY_LEVEL ;
				 if (criticalityws.equals("High") && !criticalityws.equals(null) && !criticalityws.equals("")){
					 TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:high";
					}else if (criticalityws.equals("Medium")&& !criticalityws.equals(null) && !criticalityws.equals("")){
						TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:medium";
					}else if (criticalityws.equals("Low")&& !criticalityws.equals(null) && !criticalityws.equals("")){
						TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:low";
				   }
			String endpointBase=  "http://198.71.58.51:8080/soa/systinet/platform/restBasic/repository/";
			Credentials credentials = SecurityHelper.createCredentials("admin", "adminadmin", SecurityHelper.HttpBasic);
			
			String wsdlDocumentUri=null;
			if(wsdlWSprocess != null && wsdlWSprocess.size() != 0)
			{
				wsdlDocumentUri = createWsdlArtifact(wsdlWSprocess);
	
			}
			
			String webServiceUrl = WebServiceArtifact.create(endpointBase, fileNamewsdl, getWsArtifact(fileNamewsdl,descripwsdl,criticalityws,wsdlDocumentUri), null, null, credentials);
			
			return webServiceUrl;
		}
	
		public static WebServiceArtifact getWsArtifact(String fileNamews,String descriptionws,String criticalityws, String wsdlDocumentUri) {

			
			WebServiceArtifact artifact = new WebServiceArtifact();

	        // common properties
	        NameGroup names = new NameGroup(new Name[]{
	                new Name("en", fileNamews)
	        });
	        artifact.setNameGroup(names);

	        DescriptionGroup descriptions = new DescriptionGroup(
	                new Description[]{
	                        new Description("en",descriptionws)
	                });
	        artifact.setDescriptionGroup(descriptions);

	   //     artifact.setDesignTimePolicyGroup(new DesignTimePolicyGroup(new DesignTimePolicy[]{	                new DesignTimePolicy(designTimePolicyUri)	        }));
	        
	    //    artifact.setRuntimePolicyGroup(new RuntimePolicyGroup(new RuntimePolicy[]{	                new RuntimePolicy(runtimePolicyUri)	        }));
	        
	  //      artifact.setDocumentationGroup(new DocumentationGroup(new Documentation[]{	                new Documentation(documentationUri)	        }));

	        // # special properties ###############################################################################################

	        // production stage
	        //artifact.setProductionStage(new ProductionStage("Production", TAXONOMY_LIFECYCLE_STAGES, "uddi:systinet.com:soa:model:taxonomies:lifecycleStages:production"));

	        // criticality
	        artifact.setCriticality(new Criticality(criticalityws, SdmClientConstants.TAXONOMY_IMPACT_LEVEL, "uddi:systinet.com:soa:model:taxonomies:impactLevel:medium"));

	        // reference to WSDL document
	        artifact.setDefinitionGroup(new DefinitionGroup(new Definition[]{new Definition(wsdlDocumentUri)}));

	    //    artifact.setEndpointGroup(new EndpointGroup(new Endpoint[]{new Endpoint(endpointUri)}));

	     //   artifact.setServiceName(new ServiceName(serviceName));

	    //    artifact.setServiceNamespace(new ServiceNamespace(serviceNamespace));

	        return artifact;		

	}
		
				
		
// Method for creating a Business Service artifact		 
			
		 public boolean createBSArtifact (ArrayList wsprocess, ArrayList wsdlWSprocess,ArrayList docWSprocess) throws RemoteException,Exception
		 {
			 String fileNameBs = null	;
				String consumpBs  = null;
				String descriptionBs  = null;
				String criticalityBs  = null;
				String versionBs  = null;
				String criticality = null;
				 boolean consumptionBs = false;
				 String documentUri=null;
					String wsdlUri=null;
			
					try{
						
					
				if (wsprocess != null && wsprocess.size() != 0) { 
				    	 Iterator iterws = wsprocess.iterator();
				    	 
				    	 while (iterws.hasNext()) {
				        String strType[] = (String[]) iterws.next();			             
				        fileNameBs=strType[0];
				        consumpBs=strType[1];
				        descriptionBs=strType[2];
				        criticalityBs=strType[3];
				        versionBs=strType[4];	    	
				    System.out.println("Name" +fileNameBs+"consump"+consumpBs+ "desc" +descriptionBs+" critic "+criticalityBs+"ver"+ versionBs);	 
				    	 }
				}
			 
			 
			 final String TAXONOMY_SERVICE_TYPE = "uddi:hp.com:soa:model:service:type";
			 String TAXONOMY_LEVEL = null ;
				if (criticalityBs.equals("High") && !criticalityBs.equals(null) && !criticalityBs.equals("")){
				 TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:high";
				}else if (criticalityBs.equals("Medium")&& !criticalityBs.equals(null) && !criticalityBs.equals("")){
					TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:medium";
				}else if (criticalityBs.equals("Low")&& !criticalityBs.equals(null) && !criticalityBs.equals("")){
					TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:low";
			   }
				if (consumpBs != null)
					consumptionBs=true;
				else
					consumptionBs=false; 
				
				
				
				
						if(docWSprocess != null && docWSprocess.size() != 0)
						{
							 documentUri = createDocArtifact(docWSprocess);
				
						}
						
						if(wsdlWSprocess != null && wsdlWSprocess.size() != 0)
						{
							wsdlUri = createWSArtifact(wsdlWSprocess);
				
						}
					String endpointBase=  "http://198.71.58.51:8080/soa/systinet/platform/restBasic/repository/";
					Credentials credentials = SecurityHelper.createCredentials("admin", "adminadmin", SecurityHelper.HttpBasic);					
					 String businessProcessUri = BusinessServiceArtifact.create( endpointBase,fileNameBs,  getBusinessServiceArtifact(fileNameBs,descriptionBs,criticalityBs,versionBs,consumptionBs,TAXONOMY_SERVICE_TYPE,TAXONOMY_LEVEL,documentUri,wsdlUri),null, null,credentials);
					}catch(Exception e)
					{
						System.out.println("Exception while calling SystinetStatelessBean.createBSArtifact():" + e.getMessage());
					}
					
				return true;
		 }
		 
	//Support method for creating a Business Service	 
		 
				public static BusinessServiceArtifact getBusinessServiceArtifact(String fileName, String description, String criticality, String version, boolean consumption, String TAXONOMY_SERVICE_TYPE,String TAXONOMY_LEVEL,String documentUri,String wsdlUri) 
				 { 
					System.out.println("++++++++++++Inside getBusinessServiceArtifact+++++++++++");
					BusinessServiceArtifact artifact = new BusinessServiceArtifact();
					 
			        NameGroup names = new NameGroup(new Name[]{ new Name("en", fileName) });
			        artifact.setNameGroup(names);

			        DescriptionGroup descriptions = new DescriptionGroup( new Description[]{  new Description("en", description) });
			        artifact.setDescriptionGroup(descriptions);	        
			        
			        ServiceGroup wsService = new ServiceGroup (new Service[] { new Service (wsdlUri)});
			        artifact.setServiceGroup(wsService); 
			       // R_implementsProcessGroup implProcess = new R_implementsProcessGroup(new R_implementsProcess[] {new R_implementsProcess(webServiceUri) }); 
			      //  artifact.setR_implementsProcess(new R_implementsProcess(webServiceUri));
			     
			        artifact.setCriticality(new Criticality("criticality", SdmClientConstants.TAXONOMY_IMPACT_LEVEL, TAXONOMY_LEVEL));
			        artifact.setVersion(new Version(version));
			        artifact.setReadyForConsumption(consumption);	
			        if(documentUri!= null  && documentUri != "")
					  {
					   DocumentationGroup docGroup = new DocumentationGroup(new Documentation[] {new Documentation (documentUri)} );
			        artifact.setDocumentationGroup(docGroup );
					  }
			      //
			        artifact.setR_serviceType(new R_serviceType("Business service", TAXONOMY_SERVICE_TYPE, "businessService"));
			        
				        return artifact;
				  }
				
// Method for creating a Business Process artifact
		 
	public boolean createBPArtifact ( ArrayList bpList, ArrayList bpPopList, ArrayList docList) throws RemoteException, Exception
	{
		System.out.println(" Inside createBPArtifact");
		
	String endpointBase;		
	String endpoint =	"http://198.71.58.51:8080/soa/systinet/platform/restBasic/";
	System.out.println(" Inside createBPArtifact before login credentials");
	endpointBase = endpoint + "repository/";
	 Wasp.init();
    // Credentials credentials = WaspSecurity.acquireClientCredentials
   //          ("demo_john", "demo_john", SecurityHelper.HttpBasic);
    // WaspSecurity.setInitiatingProvider(serviceClient, SecurityHelper.HttpBasic);
    // WaspSecurity.setCredentials(serviceClient, new Credentials[]{credentials});
	Credentials credentials = SecurityHelper.createCredentials("admin", "adminadmin", SecurityHelper.HttpBasic);	
	System.out.println(" Inside createBPArtifact after login credentials");
	
	 SystinetDao dao=new SystinetDao();
	String fileName = null	;
	String consump  = null;
	String description  = null;
	String criticality  = null;
	String version  = null;	
	
	try{
		
	if (bpList != null || bpList.size() != 0) { 
	    	 Iterator iterBp = bpList.iterator();
	    	 
	    	 while (iterBp.hasNext()) {
	        String strType[] = (String[]) iterBp.next();			             
			 fileName=strType[0];
	    	 consump=strType[1];
	    	 description=strType[2];
	    	 criticality=strType[3];
	    	 version=strType[4];	    	
	    System.out.println("Name" +fileName+"consump"+consump+ "desc" +description+" critic "+criticality+"ver"+ version);	 
	    	 }
	}

		String TAXONOMY_LEVEL = null ;
		if (criticality.equals("High") && !criticality.equals(null) && !criticality.equals("")){
		 TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:high";
		}else if (criticality.equals("Medium")&& !criticality.equals(null) && !criticality.equals("")){
			TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:medium";
		}else if (criticality.equals("Low")&& !criticality.equals(null) && !criticality.equals("")){
			TAXONOMY_LEVEL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:low";
	   }
		System.out.println("Value of TAXONOMY_LEVEL" +TAXONOMY_LEVEL);
		
		boolean consumption;		
		
		if (consump != null)
			consumption=true;
		else
			consumption=false; 
		
		String documentUri=null;
		System.out.println("Before doclist if loop docList.size() is" +docList.size()+"and "+docList);
				if(docList != null && docList.size() != 0)
				{
					System.out.println("Inside doclist if loop");
					 documentUri = createDocArtifact(docList);
		
				}
				
				String bpelName = null	;
				String bpelPath  = null;
				String bpelDesc  = null;
				String bpelCrit  = null;
				String bpelver  = null;
				String bpelTrname = null;
				String bplocation = null;
				String BpelProcessUri = null;
				System.out.println("Before bpPopList if loop bpPopList.size()" +bpPopList.size());
				if (bpPopList != null && bpPopList.size() != 0) { 
			    	 Iterator iterBpel = bpPopList.iterator();
			    	 
			    	 while (iterBpel.hasNext()) {
			        String strType[] = (String[]) iterBpel.next();			             
			          bpelName=strType[0];
			          bpelPath=strType[1];
			          bpelDesc=strType[2];
			          bpelCrit=strType[3];
			          bpelver=strType[4];	    	
			          bpelTrname=strType[5];
			          bplocation=strType[6];
			    	 }			
					 BpelProcessUri = HpsoaBpelProcessArtifact.create(endpointBase, bpelName, getBpelProcessArtifact(bpelName,bpelDesc,bpelCrit,TAXONOMY_LEVEL,bpelver,bpelTrname,bplocation), null, new Source(getResourceAsStream(bpelPath,fileName),Client.CT_XMLDOC_UTF_8),credentials);
		
				}
				System.out.println("Before create endpointBase=" +endpointBase+ "fileName=" +fileName+ "description=" +description+ "criticality="+criticality+ "version=" +version+ "consumption=" +consumption+ "TAXONOMY_LEVEL=" +TAXONOMY_LEVEL+ "BpelProcessUri=" +BpelProcessUri+ "documentUri=" +documentUri);
				 String businessProcessUri = HpsoaProcessArtifact.create( endpointBase, fileName,  getBusinessProcessArtifact(fileName,description,criticality,version,consumption,TAXONOMY_LEVEL,BpelProcessUri,documentUri),null, null,credentials);
				 System.out.println("+++++++++++++Value of businessProcessUri+++++++++" +businessProcessUri);
				 
	}catch(Exception e){
        System.out.println("Exception while calling SystinetStatelessBean.createBPArtifact():" + e.getMessage());
	}
				 return true;		
	}
	
	//Support method for creating a Business Process
	
			public static HpsoaProcessArtifact getBusinessProcessArtifact(String fileName, String description, String criticality, String version, boolean consumption,String TAXONOMYLEVEL,String BpelProcessUri,String documentUri) 
			 { 
				
				System.out.println("+++++++++++++Inside getBusinessProcessArtifact+++++++++");
				HpsoaProcessArtifact artifact = new HpsoaProcessArtifact();
				
				try{
					
			        NameGroup names = new NameGroup( new Name[]{ new Name ("en", fileName) });
			        artifact.setNameGroup(names);

			        DescriptionGroup descriptions = new DescriptionGroup( new Description[]{  new Description("en",description ) });
			        artifact.setDescriptionGroup(descriptions);

			       
			        artifact.setCriticality(new Criticality(criticality, SdmClientConstants.TAXONOMY_IMPACT_LEVEL, TAXONOMYLEVEL));
			        artifact.setVersion(new Version(version));
			      
			  
			        artifact.setReadyForConsumption(consumption);
					
					if(BpelProcessUri != null || BpelProcessUri !="") {
			       
						R_hpsoaProcessImplGroup implGroup = new R_hpsoaProcessImplGroup(new R_hpsoaProcessImpl[]{ new R_hpsoaProcessImpl(BpelProcessUri) });
			            artifact.setR_hpsoaProcessImplGroup(implGroup);
			        }
					
				  if(documentUri!= null  || documentUri != "")
				  {
				   DocumentationGroup docGroup = new DocumentationGroup(new Documentation[] {new Documentation (documentUri)} );
		        artifact.setDocumentationGroup(docGroup );
				  }
				  
				}catch(Exception e){
			        System.out.println("Exception while calling SystinetStatelessBean.getBusinessProcessArtifact():" + e.getMessage());
				}
			       
				return artifact;
			  }
			
		public static HpsoaBpelProcessArtifact getBpelProcessArtifact(String bpelName,String bpelDesc,String bpelCrit,String TAXONOMY_LEVEL,String bpelver,String bpelTrname,String bpelLocation) 
		 { 
			 HpsoaBpelProcessArtifact bpelArtft = new HpsoaBpelProcessArtifact();
			
			 try{
				 
		        NameGroup names1 = new NameGroup(new Name[]{ new Name("en", bpelName) });
		        bpelArtft.setNameGroup(names1);

		        DescriptionGroup descriptions1 = new DescriptionGroup( new Description[]{  new Description("en", bpelDesc) });
		        bpelArtft.setDescriptionGroup(descriptions1);
		        
		        bpelArtft.setCriticality(new Criticality(bpelCrit, SdmClientConstants.TAXONOMY_IMPACT_LEVEL, TAXONOMY_LEVEL));
		        bpelArtft.setVersion(new Version(bpelver));
		        bpelArtft.setTargetNamespace(new TargetNamespace(bpelTrname));
		        bpelArtft.setLocation(new Location(bpelLocation));
		        bpelArtft.setIsCached(Boolean.TRUE);	
		        
			 }catch(Exception e){
			        System.out.println("Exception while calling SystinetStatelessBean.getBpelProcessArtifact():" + e.getMessage());
			}			 
		     
		        return bpelArtft;
		    }	
		 
		//start systinet digiblitz technoglogies
		 public  ArrayList listProcessView(String arttype)  throws RemoteException{
			 System.out.println("kaverystateless Bean listProcessView");
			 ArrayList lst = new ArrayList();			
			
			 try{		    	   
		    	   SystinetDao da0=new SystinetDao();
		    	   
		    	   
		    	   lst=da0.listProcessView(arttype);	    	 	    	   
		    	   
		       }catch(Exception e){
		          System.out.println("Exception while calling listProcessView():" + e.getMessage());  
		       }  	
		       
			 return lst;			
		 }
		 
		 public void govWSBPMValue(String id,String name,String value) throws RemoteException
		 {
			 
			 System.out.println("value==="+value+"==id=="+id+"==name=="+name);
		
			 
		 }
		 public String wsproceesSubmit(ArrayList wsprocess, ArrayList wsdlWSprocess,ArrayList docWSprocess, String artifactSysUUID) throws RemoteException{
			   System.out.println("kaverystateless Bean chgStatusWSBPM");
			    boolean bsCreated; 
			    String artifactbsUuid=null;
			    boolean insert = false;
			    SystinetDao da0=new SystinetDao();
			    
			        try{
			        	
			        	if(wsprocess!= null && wsprocess.size()!= 0 && artifactSysUUID==null){
				        		System.out.println("SystinetStatelessBean wsproceesSubmit calling createBPArtifact");
				        		//bsCreated= createBSArtifact(wsprocess,wsdlWSprocess,docWSprocess);
				        	artifactbsUuid= createBusSerArtifact(wsprocess,wsdlWSprocess,docWSprocess);
				        		/*if(bsCreated == true){			        			 
			        		    	 Iterator iterws = wsprocess.iterator();			        		    	 
			        		    	 while (iterws.hasNext()) {
			        		        String strType[] = (String[]) iterws.next();			             
			        				 String fileName=strType[0];
			        				 artifactSysUUID = da0.getArtifactUUid(fileName);
			        		 }
			        	 }*/	
                          ArrayList newList = new ArrayList();
				        	
				        	String name="";
				        	String cons="";
				        	String desc="";
				        	String criticality="";
				        	String version="";
				        	String wsdlname="";
				        	String wsdlpath="";
				        	String wsdldesc="";
				        	String wsdlcriticality="";
				        	String wsdlversion="";
				        	String wsdltrname="";
				        	String wsdllocation="";
				        	String docname="";
				        	String docdesc="";
				        	String docpath="";
				        	String doclocat="";

				        		
				        	
				        	Iterator itr = wsprocess.iterator();

				        	while (itr.hasNext()){
				        		
				        		String [] array = (String []) itr.next();

				        		  name=array[0];
				        	         cons=array[1];
				        	         desc=array[2];
				        	         criticality=array[3];
				        	         version=array[4];
				        	         
				        	}

				        	itr = wsdlWSprocess.iterator();

				        	while (itr.hasNext()){
				        		
				        		String [] strType = (String []) itr.next();

				        		wsdlname=strType[0];
					        	wsdlpath=strType[1];
					        	wsdldesc=strType[2];
					        	wsdlcriticality=strType[3];
					        	wsdlversion=strType[4];	
					        	wsdltrname=strType[5];	
					        	wsdllocation=strType[6];
				        	}

				        	itr = docWSprocess.iterator();

				        	while (itr.hasNext()){
				        		
				        		String [] array = (String []) itr.next();

				        		 docname=array[0];
				        	         docdesc=array[1];
				        	         docpath=array[2];
				        	         doclocat=array[3];
				        	}
				        	System.out.println("the name of the bp is "+name);

				        	String arr[] = {name, cons, desc, criticality,version, wsdlname, wsdlpath, wsdldesc, wsdlcriticality,wsdlversion, wsdltrname, wsdllocation, docname, docdesc, docpath, doclocat};
				        	
				        	newList.add(arr);
				        	
				        //==========================END======================
				        
				   if(artifactbsUuid!=null ){ 		         
				        insert=da0.wsproceesSubmit(newList,artifactbsUuid);			    
				       }    
			        }
			         
			           // insert=da0.wsproceesSubmit(wsprocess,wsdlWSprocess,docWSprocess,artifactSysUUID);			                
			         
			        }catch(Exception e){
			           System.out.println("Exception while calling chgStatusWSBPM():" + e.getMessage());  
			        }  
		 
			        return artifactbsUuid;
			  }
			  
			  

			 public boolean changeapprovedstatus() throws RemoteException{
				
			    
				  boolean app = false;
			       try{
			    	   
			    	  
			    	 	    	   System.out.println("Approved Button Pressed");
			    	   
			       }catch(Exception e){
			          System.out.println("Exception while calling insertRequestValues():" + e.getMessage());  
			       }  
			       return app;
			 }
			 
			 public boolean changedeniedstatus() throws RemoteException{
					
				    
				  boolean app = false;
			       try{
			    	   		    	  
			    	 	    	   System.out.println("Denied Button Pressed");
			    	   
			       }catch(Exception e){
			          System.out.println("Exception while calling insertRequestValues():" + e.getMessage());  
			       }  
			       return app;
			 }
			  
			  
			  public String bpproceesSubmit(ArrayList bplist, ArrayList bppoplist,ArrayList docList,String artifactSysUUID) throws RemoteException{
			   System.out.println("SystinetStatelessBean bpproceesSubmit");
			     
			    boolean insert = false;
			    boolean bpCreated = false;
			    String artifactSysUuid=null;
			    SystinetDao da0=new SystinetDao();
			        try{
			        	if(bplist!= null && bplist.size()!= 0 && artifactSysUUID==null){
			        		System.out.println("SystinetStatelessBean bpproceesSubmit calling createBPArtifact");
			        		// bpCreated=createBPArtifact(bplist,bppoplist,docList);
			        		String fileName="";
			        		artifactSysUuid=createBusProcArtifact(bplist,bppoplist,docList);
			        		
			        		
			        		/* if(bpCreated == true){			        			 
			        		    	 Iterator iterBp = bplist.iterator();			        		    	 
			        		    	 while (iterBp.hasNext()) {
			        		        String strType[] = (String[]) iterBp.next();			             
			        				  fileName=strType[0];
			        				
			        		 }		        		    	 
			        		    artifactSysUUID = da0.getArtifactUUid(fileName);	    	 
			        	 }	*/		        		
			        		
			        }
			         
			        	 /**
				         *  Iterating the three list and puting in a single list Start=======================
				         * 
				         */
				        	ArrayList newList = new ArrayList();
				        	
				        	String name="";
				        	String cons="";
				        	String desc="";
				        	String criticality="";
				        	String version="";
				        	String bpname="";
				        	String bppath="";
				        	String bpdesc="";
				        	String bpcriticality="";
				        	String bpversion="";
				        	String bptrname="";
				        	String bplocation="";
				        	String docname="";
				        	String docdesc="";
				        	String docpath="";
				        	String doclocat="";

				        	
				        	
				        	Iterator itr = bplist.iterator();

				        	while (itr.hasNext()){
				        		
				        		String [] array = (String []) itr.next();

				        		  name=array[0];
				        	         cons=array[1];
				        	         desc=array[2];
				        	         criticality=array[3];
				        	         version=array[4];
				        	         
				        	}

				        	itr = bppoplist.iterator();

				        	while (itr.hasNext()){
				        		
				        		String [] array = (String []) itr.next();

				        		 bpname=array[0];
				        	         bppath=array[1];
				        	         bpdesc=array[2];
				        	         bpcriticality=array[3];
				        	         bpversion=array[4];
				        	         bptrname=array[5];
				        	         bplocation=array[6];
				        	}
				        	itr = docList.iterator();

				        	while (itr.hasNext()){
				        		
				        		String [] array = (String []) itr.next();

				        		 docname=array[0];
				        	         docdesc=array[1];
				        	         docpath=array[2];
				        	         doclocat=array[3];
				        	}
				        	System.out.println("the name of the bp is "+name);

				        	String arr[] = {name, cons, desc, criticality,version, bpname, bppath, bpdesc, bpcriticality, bpversion, bptrname, bplocation, docname, docdesc, docpath, doclocat};
				        	
				        	newList.add(arr);
				        	
				        //==========================END======================
				        
				   if(artifactSysUuid!=null ){ 		         
				        insert=da0.bpproceesSubmit(newList,artifactSysUuid);			    
				       }    
			        } catch(Exception e){
			           System.out.println("Exception while calling systinetstatelessbean bpproceesSubmit():" + e.getMessage());  
			        }  
			        return artifactSysUuid;
			  }
			  			  
			  //22-2-13
			  public  HashMap votingList()  throws RemoteException{
					 System.out.println("votingList");
					 HashMap <String,String[]> BundlesBPMData=new HashMap();      	 
			     	
			     	
			 		String [] buntName1 = {"1","Artifact Name1", "Artifact Description1","21/2/2013","Stage Name1","Comments1"};
			 		String [] buntName2 = {"2","Artifact Name2", "Artifact Description2","22/2/2013","Stage Name2","Comments2" };
			 		String [] buntName3 = {"3","Artifact Name3", "Artifact Description3","23/2/2013","Stage Name3","Comments3" };		 		
			 		
			 		

			 		BundlesBPMData.put("1",buntName1);
			 		BundlesBPMData.put("2",buntName2);
			 		BundlesBPMData.put("3",buntName3);			     	
					     
					
					 return BundlesBPMData;
				 } 
			  
			  
			  
			  public  HashMap BPvotingList()  throws RemoteException{
					 System.out.println("votingList");
					 HashMap <Integer,String[]> BundlesBPMData=new HashMap();
					 
					 SystinetDao da0=new SystinetDao();
			  	   
			     	
					 BundlesBPMData=da0.selectvoting();
					 
					 return BundlesBPMData;
				 }
				 public  HashMap WSvotingList()  throws RemoteException{
					 System.out.println("votingList");
					 HashMap <Integer,String[]> BundlesBPMData=new HashMap();
					 
					 SystinetDao da0=new SystinetDao();
			  	   
			     	
					 BundlesBPMData=da0.selectvotingws();
			     	
			 
					     
					
					 return BundlesBPMData;
				 }
			  
				 public boolean changeBPapprovedstatus(ArrayList approve) throws RemoteException{
						
					    
					  boolean app = false;
				       try{
				    	   System.out.println("this bp approved");
				    	   ArrayList approvelist=approve;
				    	 if(  approvelist.size()>0){
				    	   Iterator list=approvelist.iterator();
				    	   
				    	   while(list.hasNext()){
				    		   
				    		   String[] applist=(String[])list.next();
				    		   
				    		   String StageName=applist[0];
				    			String StageValue=applist[1];
				    			String ArtifactId=applist[2];
				    			String ProcessId=applist[3];
				    		   
				    		   System.out.println("StageName=="+StageName+"StageValue=="+StageValue+"ArtifactId=="+ArtifactId+"ProcessId=="+ProcessId);
				    
				    		   SystinetDao da0=new SystinetDao();
						         
						         
				    		   app=da0.changeBPapprovedstatus(ProcessId,StageName,ArtifactId);    		
				    		   
				    	   }
				    	   
				    	   
				    	 }    	   System.out.println("Approved Button Pressed");
				    	 	    	  System.out.println(approvelist.size());
				       }catch(Exception e){
				          System.out.println("Exception while calling changeBPapprovedstatus():" + e.getMessage());  
				       }  
				       return app;
				 }
				 
				 public String getProcUuid(String artifactUuid) throws RemoteException{
						
					    
					  String proceId = "";
				       try{
				    	   System.out.println("getProcUuid() in session");
				    	
				    		   SystinetDao da0=new SystinetDao();
						         
						         
				    		   proceId=da0.getProcUuid(artifactUuid);    		
				    		  
				       }catch(Exception e){
				          System.out.println("Exception while calling getProcUuid():" + e.getMessage());  
				       }  
				       return proceId;
				 }	
				 
				 
				 
				 public String getProcessId(String proceUuid) throws RemoteException{
						
					    
					  String processssId = "";
				       try{
				    	   System.out.println("getProcessId() in session");
				    	
				    		   SystinetDao da0=new SystinetDao();
						         
						         
				    		   processssId=da0.getProcessId(proceUuid);    		
				    		  
				       }catch(Exception e){
				          System.out.println("Exception while calling getProcessId():" + e.getMessage());  
				       }  
				       return processssId;
				 }	
				 
				 public String getStateId(String stageName, String procesId) throws RemoteException{
						
					    
					  String stateId = "";
				       try{
				    	   System.out.println("getStateId() in session");
				    	
				    		   SystinetDao da0=new SystinetDao();
						         
						         
				    		   stateId=da0.getStateId(stageName,procesId);    		
				    		  
				       }catch(Exception e){
				          System.out.println("Exception while calling getStateId():" + e.getMessage());  
				       }  
				       return stateId;
				 }	
				 
				 public String getTaskId(String stateId) throws RemoteException{
						
					    
					  String taskId = "";
				       try{
				    	   System.out.println("getTaskId() in session");
				    	
				    		   SystinetDao da0=new SystinetDao();
						         
						         
				    		   taskId=da0.getTaskId(stateId);    		
				    		  
				       }catch(Exception e){
				          System.out.println("Exception while calling getTaskId():" + e.getMessage());  
				       }  
				       return taskId;
				 }	
				 
				 
				 public String getNextStage(String lifecycleId, String stageNo) throws RemoteException{
						
					    
					  String stageName = "";
				       try{
				    	   System.out.println("getNextStage()");
				    	
				    		   SystinetDao da0=new SystinetDao();
						         
						         
				    		   stageName=da0.getNextStage(lifecycleId,stageNo);    		
				    		  
				       }catch(Exception e){
				          System.out.println("Exception while calling getNextStage():" + e.getMessage());  
				       }  
				       return stageName;
				 }		 
				 
				 public boolean changeBPdeniedstatus(ArrayList approve) throws RemoteException{
						
					    
					  boolean app = false;
				       try{
				    	   System.out.println("this bp diened");
				    	   ArrayList approvelist=approve;
					    	 if(  approvelist.size()>0){
					    	   Iterator list=approvelist.iterator();
					    	   
					    	   while(list.hasNext()){
					    		   
					    		   String[] applist=(String[])list.next();
					    		   
					    		   String StageName=applist[0];
					    			String StageValue=applist[1];
					    			String ArtifactId=applist[2];
					    			String ProcessId=applist[3];
					    			System.out.println("StageName=="+StageName+"StageValue=="+StageValue+"ArtifactId=="+ArtifactId+"ProcessId=="+ProcessId);
					    
					    			
					    			 SystinetDao da0=new SystinetDao();
							         
							         
						    		   app=da0.changeBPdeniedstatus(ProcessId,StageName,ArtifactId);   	    			
					    			
					    			
					    	   }
					    	   
					    	   
					    	 } 
				    	 	    	  System.out.println(approvelist.size());
				       }catch(Exception e){
				          System.out.println("Exception while calling changeBPdeniedstatus():" + e.getMessage());  
				       }  
				       return app;
				 }
				 
				 
				 public boolean changeWSapprovedstatus(ArrayList approve) throws RemoteException{
						
					    
					  boolean app = false;
				       try{
				    	   System.out.println("this ws approved");
				    	  
				    	   ArrayList approvelist=approve;
					    	 if(  approvelist.size()>0){
					    	   Iterator list=approvelist.iterator();
					    	   
					    	   while(list.hasNext()){
					    		   
					    		   String[] applist=(String[])list.next();
					    		   
					    		   String StageName=applist[0];
					    			String StageValue=applist[1];
					    			String ArtifactId=applist[2];
					    			String ProcessId=applist[3];
					    		   
					    			
					    			System.out.println("StageName=="+StageName+"StageValue=="+StageValue+"ArtifactId=="+ArtifactId+"ProcessId=="+ProcessId);
					    	  
					    	   

						    		   SystinetDao da0=new SystinetDao();
								         
								         
						    		   app=da0.changeWSapprovedstatus(ProcessId,StageName,ArtifactId);    	 	   
					    	   
					    	   
					    	   
					    	   
					    	   }
					    	 }
					    	   
				    	   System.out.println(approvelist.size());
				       }catch(Exception e){
				          System.out.println("Exception while calling insertRequestValues():" + e.getMessage());  
				       }  
				       return app;
				 }
				 
				 public boolean changeWSdeniedstatus(ArrayList approve) throws RemoteException{
						
					    
					  boolean app = false;
				       try{
				    	   System.out.println("this ws dineed");
				    	   
				    	   ArrayList approvelist=approve;
					    	 if(  approvelist.size()>0){
					    	   Iterator list=approvelist.iterator();
					    	   
					    	   while(list.hasNext()){
					    		   
					    		   String[] applist=(String[])list.next();
					    		   
					    		   String StageName=applist[0];
					    			String StageValue=applist[1];
					    			String ArtifactId=applist[2];
					    			String ProcessId=applist[3];
					    		   
					    			System.out.println("StageName=="+StageName+"StageValue=="+StageValue+"ArtifactId=="+ArtifactId+"ProcessId=="+ProcessId);
					    	   
					    			 SystinetDao da0=new SystinetDao();
							         
							         
						    		   app=da0.changeWSdeniedstatus(ProcessId,StageName,ArtifactId);   	    			
					    				   
					    	   }
					    	   
					    	   
				    	  
				    	   System.out.println(approvelist.size());
					    	 } 
				       }catch(Exception e){
				          System.out.println("Exception while calling insertRequestValues():" + e.getMessage());  
				       }  
				       return app;
				 }
			  
			  
			  //end
		 //end systinet digiblitz technoglogies
		 
			  public boolean addlifeCycleprocess(ArrayList lifecyclelist, String processId) throws RemoteException{
				   System.out.println("SystinetStateless Bean addlifeCycleprocess");
				     
				    boolean insert = false;
				        try{
				         
				         SystinetDao da0=new SystinetDao();
				         
				         
				         insert=da0.lifeCycleInsert(lifecyclelist,processId);
				    
				                
				         
				        }catch(Exception e){
				           System.out.println("Exception while calling chgStatusWSBPM():" + e.getMessage());  
				        }  
				        return insert;
				  }
			  public void callEndGov() throws RemoteException{
				  System.out.println("End Gov");  
				  }
	
			  
			  public boolean updateStatus(String reqId, String artifactUuid, String status, String StageName) throws RemoteException{
					 System.out.println("SystinetStatelessBean updateStatus");
				    
					  boolean update = false;
				       try{
				    	   
				    	   SystinetDao da0=new SystinetDao();
				    	   
				    	   
				    	   update=da0.updateStatus(reqId,artifactUuid,status,StageName);
				   
				    	 	    	   
				    	   
				       }catch(Exception e){
				          System.out.println("Exception while calling updateStatus():" + e.getMessage());  
				       }  
				       return update;
				 }
				 
			  
			    public boolean updateArtifactStatus(String lifecycleId, String artifactId,String status) throws RemoteException{
					 System.out.println("SystinetStatelessBean updateArtifactStatus");
				    
					  boolean update = false;
				       try{
				    	   
				    	   SystinetDao da0=new SystinetDao();
				    	   
				    	   
				    	   update=da0.updateArtifactStatus(lifecycleId,artifactId,status);
				   
				    	 	    	   
				    	   
				       }catch(Exception e){
				          System.out.println("Exception while calling updateArtifactStatus():" + e.getMessage());  
				       }  
				       return update;
				 }
				 
			  
			  
			  
//Alternate methods for creating the artifacts using Repository Client	
			  
			  public String createBusProcArtifact ( ArrayList bpList, ArrayList bpPopList, ArrayList docList) throws RemoteException, Exception	
			  {
				  System.out.println("Inside createBusProcArtifact ======>>>> ");
				  String fileName = null	;
					String consump  = null;
					String description  = null;
					String criticality  = null;
					String version  = null;
					
					if (bpList != null && bpList.size() != 0) { 
					    	 Iterator iterBp = bpList.iterator();
					    	 
					    	 while (iterBp.hasNext()) {
					        String strType[] = (String[]) iterBp.next();			             
							 fileName=strType[0];
					    	 consump=strType[1];
					    	 description=strType[2];
					    	 criticality=strType[3];
					    	 version=strType[4];	    	
					    System.out.println("Name" +fileName+"consump"+consump+ "desc" +description+" critic "+criticality+"ver"+ version);	 
					    	 }
					}
				  final String CRITICALITY_TAX_URI = "uddi:systinet.com:soa:model:taxonomies:impactLevel";
				   // final String CRITICALITY_VAL = "uddi:systinet.com:soa:model:taxonomies:impactLevel:high";
					String CRITICALITY_VAL = null ;
					if (criticality.equals("High") && !criticality.equals(null) && !criticality.equals("")){
						CRITICALITY_VAL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:high";
					}else if (criticality.equals("Medium")&& !criticality.equals(null) && !criticality.equals("")){
						CRITICALITY_VAL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:medium";
					}else if (criticality.equals("Low")&& !criticality.equals(null) && !criticality.equals("")){
						CRITICALITY_VAL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:low";
				   }
				    /** repository client is a Java interface of the ATOM REST API */
				    RepositoryClient repositoryClient;
				//  System.setProperty("uddi.local.properties", "C:/EclipseEuropaworkspace/systinet-newworkbench/sample_soa/src/common/DemoProperties.java");
			        // read demo properties
			        String endpointBase = DemoProperties.getProperty("shared.http.urlbase","http://198.71.58.51:8080/soa");
			        String user = DemoProperties.getProperty("platform.demos.user.name","admin");
			        String password = DemoProperties.getProperty("platform.demos.user.password", "adminadmin");

			        // initialize repository client
			        repositoryClient = RepositoryClientFactory.createRepositoryClient(endpointBase, user, password, false, null, 0);
			        String bpArtUUID=createBusinessProcess(repositoryClient,CRITICALITY_TAX_URI,CRITICALITY_VAL,version,fileName,description,criticality,bpPopList,consump);
			        System.out.println("\n=== createBusinessProcess bpArtUUID===" +bpArtUUID);
			        return bpArtUUID;
				  
			  }
			  public String createBusinessProcess(RepositoryClient repositoryClient,String CRITICALITY_TAX_URI,String CRITICALITY_VAL,String version,String fileName,String description,String criticality,ArrayList bpPopList,String consump) {
			    	System.out.println("\n=== createBusinessProcess() ===");
			        // create a new service artifact	        
			        
			     //String version1 =version;
			        
			      //  hpsoaProcess = repositoryClient.createArtifact(hpsoaProcess);
			      //  System.out.println("Organizational Unit artifact created, its UUID is: "+hpsoaProcess.get_uuid().toString());
			    	
			    	 ArtifactBase businessProcess = repositoryClient.getArtifactFactory().newArtifact("hpsoaBpelProcessArtifact");
			    	 
			    	 String bpelName = null	;
						String bpelPath  = null;
						String bpelDesc  = null;
						String bpelCrit  = null;
						String bpelver  = null;
						String bpelTrname = null;
						String bplocation = null;
						String BpelProcessUri = null;
						if (bpPopList.size() != 0) { 
					    	 Iterator iterBpel = bpPopList.iterator();
					    	 
					    	 while (iterBpel.hasNext()) {
					        String strType[] = (String[]) iterBpel.next();			             
					          bpelName=strType[0];
					          bpelPath=strType[1];
					          bpelDesc=strType[2];
					          bpelCrit=strType[3];
					          bpelver=strType[4];	    	
					          bpelTrname=strType[5];
					          bplocation=strType[6];
					    	 }
						
			       
			        businessProcess.setName(bpelName); 
			        businessProcess.setDescription(bpelDesc);
			        businessProcess.setStringProperty("version",bpelver);
			        
			    	}
			      
			      //  businessProcess = repositoryClient.createArtifact(businessProcess);
			        //String businessServiceUrl	= endpointBase + SdmClientConstants.COLLECTION_NAME_BUSINESSSERVICE + "/"
			        ArtifactBase hpsoaProcess = repositoryClient.getArtifactFactory().newArtifact("hpsoaProcessArtifact");
			        hpsoaProcess.setName(fileName);     
			        hpsoaProcess.setDescription(description);
			        hpsoaProcess.setStringProperty("version",version);
			        if (consump != null)
			        	hpsoaProcess.setBooleanProperty("readyForConsumption", Boolean.TRUE);
			        else
			        	hpsoaProcess.setBooleanProperty("readyForConsumption", Boolean.FALSE);
			        
			        Category propertyCriticality = new Category(
			                CRITICALITY_TAX_URI, criticality,
			                CRITICALITY_VAL);
			        hpsoaProcess.setProperty("criticality", propertyCriticality);
			        
			        // add relation from business process to bpel
			        if (bpPopList.size()!= 0 || bpPopList != null){
			        List<Uuid> services = new ArrayList<Uuid>();
			        services.add(businessProcess.get_uuid());
			      
			        hpsoaProcess.setMultiRelationProperty("r_hpsoaProcessImpl", services);
			        }
			    
			        // store service on server
			        hpsoaProcess = repositoryClient.createArtifact(hpsoaProcess);
			        String bpArtifactUUID=hpsoaProcess.get_uuid().toString();
			        System.out.println("Service artifact created, its UUID is: "+hpsoaProcess.get_uuid().toString());
			       // List<Relation> multiRelationProperty = hpsoaProcess.getMultiRelationProperty("service"); 			        

			        return bpArtifactUUID;
			    }
			   
			
			  
			  
			  public String createBusSerArtifact (ArrayList wsprocess, ArrayList wsdlWSprocess,ArrayList docWSprocess) throws RemoteException, Exception	
			  {
				  System.out.println("Inside createBusProcArtifact ======>>>> ");
				  String fileNameBs = null	;
					String consumpBs  = null;
					String descriptionBs  = null;
					String criticalityBs  = null;
					String versionBs  = null;
					
					if (wsprocess != null && wsprocess.size() != 0) { 
					    	 Iterator iterws = wsprocess.iterator();
					    	 
					    	 while (iterws.hasNext()) {
					        String strType[] = (String[]) iterws.next();			             
					        fileNameBs=strType[0];
					        consumpBs=strType[1];
					        descriptionBs=strType[2];
					        criticalityBs=strType[3];
					        versionBs=strType[4];	    	
					    System.out.println("Name" +fileNameBs+"consump"+consumpBs+ "desc" +descriptionBs+" critic "+criticalityBs+"ver"+ versionBs);	 
					    	 }
					}
				  final String CRITICALITY_TAX_URI = "uddi:systinet.com:soa:model:taxonomies:impactLevel";
				   // final String CRITICALITY_VAL = "uddi:systinet.com:soa:model:taxonomies:impactLevel:high";
					String CRITICALITY_VAL = null ;
					if (criticalityBs.equals("High") && !criticalityBs.equals(null) && !criticalityBs.equals("")){
						CRITICALITY_VAL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:high";
					}else if (criticalityBs.equals("Medium")&& !criticalityBs.equals(null) && !criticalityBs.equals("")){
						CRITICALITY_VAL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:medium";
					}else if (criticalityBs.equals("Low")&& !criticalityBs.equals(null) && !criticalityBs.equals("")){
						CRITICALITY_VAL ="uddi:systinet.com:soa:model:taxonomies:impactLevel:low";
				   }
				    /** repository client is a Java interface of the ATOM REST API */
				    RepositoryClient repositoryClient;
				//  System.setProperty("uddi.local.properties", "C:/EclipseEuropaworkspace/systinet-newworkbench/sample_soa/src/common/DemoProperties.java");
			        // read demo properties
			        String endpointBase = DemoProperties.getProperty("shared.http.urlbase","http://198.71.58.51:8080/soa");
			        String user = DemoProperties.getProperty("platform.demos.user.name","admin");
			        String password = DemoProperties.getProperty("platform.demos.user.password", "adminadmin");

			        // initialize repository client
			        repositoryClient = RepositoryClientFactory.createRepositoryClient(endpointBase, user, password, false, null, 0);
			        String bsArtUUID=createBusinessService(repositoryClient,CRITICALITY_TAX_URI,CRITICALITY_VAL,versionBs,fileNameBs,descriptionBs,criticalityBs,wsdlWSprocess,consumpBs);
			        System.out.println("\n=== createBusinessService bsArtUUID===" +bsArtUUID);
			        return bsArtUUID;
				  
			  }
			  public String createBusinessService(RepositoryClient repositoryClient,String CRITICALITY_TAX_URI,String CRITICALITY_VAL,String versionBs,String fileNameBs,String descriptionBs,String criticality,ArrayList wsdlWSprocess,String consumpBs) {
			    	System.out.println("\n=== createBusinessService() ===");
			        // create a new service artifact	        
			        
			   
			    	ArtifactBase webService = repositoryClient.getArtifactFactory().newArtifact("webServiceArtifact");
			    	 String wsName = null	;
						String wsPath  = null;
						String wsDesc  = null;
						String wsCrit  = null;
						String wsVer  = null;
						String wsTrname = null;
						String wslocation = null;
						String wsProcessUri = null;
						if (wsdlWSprocess.size() != 0) { 
					    	 Iterator iterWS = wsdlWSprocess.iterator();
					    	 
					    	 while (iterWS.hasNext()) {
					        String strType[] = (String[]) iterWS.next();			             
					        wsName=strType[0];
					        wsPath=strType[1];
					        wsDesc=strType[2];
					        wsCrit=strType[3];
					        wsVer=strType[4];	    	
					        wsTrname=strType[5];
					        wslocation=strType[6];
					    	 }
						
			       
					    	 webService.setName(wsName); 
					    	 webService.setDescription(wsDesc);
					    	 webService.setStringProperty("version",wsVer);
			        
			    	}
			      
			      
			    	ArtifactBase businessService = repositoryClient.getArtifactFactory().newArtifact("businessServiceArtifact");
			    	businessService.setName(fileNameBs);     
			    	businessService.setDescription(descriptionBs);
			    	businessService.setStringProperty("version",versionBs);
			        if (consumpBs != null)
			        	businessService.setBooleanProperty("readyForConsumption", Boolean.TRUE);
			        else
			        	businessService.setBooleanProperty("readyForConsumption", Boolean.FALSE);
			        
			        Category propertyCriticality = new Category(
			                CRITICALITY_TAX_URI, criticality,
			                CRITICALITY_VAL);
			        businessService.setProperty("criticality", propertyCriticality);
			        
			        // add relation from business process to bpel
			        if (wsdlWSprocess.size()!= 0 ){
			        List<Uuid> services = new ArrayList<Uuid>();
			        services.add(webService.get_uuid());
			      
			        businessService.setMultiRelationProperty("service", services);
			        }
			    
			        // store service on server
			        businessService = repositoryClient.createArtifact(businessService);
			        String bsArtifactUUID=businessService.get_uuid().toString();
			        System.out.println("Service artifact created, its UUID is: "+businessService.get_uuid().toString());
			        List<Relation> multiRelationProperty = businessService.getMultiRelationProperty("service");         

			        
			        return bsArtifactUUID;
			    }	
			  
			  
			  
			  public boolean moveFileForVersioning (String fromPath,String toPath) throws RemoteException, Exception	  
			  {

			    	InputStream inStream = null;
				OutputStream outStream = null;

			    	try{
			    		
			      //   Scanner scanner = new Scanner(System.in);

			  
			 // System.out.println("Enter the path of current location of file : ");

			  
			  String currentPath = fromPath.trim();

			  
			//  System.out.println("Enter the path of target location of file : ");

			  String targetLocation = toPath.trim();

			  System.out.println();
			    	    File afile =new File(currentPath);
			    	  
			    	    File bfile =new File(targetLocation+ afile.getName());
			    	    

			    	    boolean fileExists = bfile.exists();
			    	    
			    	   
			    	    inStream = new FileInputStream(afile);
			    	    outStream = new FileOutputStream(bfile);

			    	    byte[] buffer = new byte[1024];

			    	    int length;
			    	  
			    	    while ((length = inStream.read(buffer)) > 0){

			    	    	outStream.write(buffer, 0, length);

			    	    }

			    	    inStream.close();
			    	    outStream.close();

			    	  
			    	   
			    	    System.out.println("File moved successfully : ");
			    	   
			    	   
			    	}catch(IOException e){
			    	    e.printStackTrace();
			    	}
				  return true;
			  }
			  
			  
			  public boolean updateWSBPM(String artifactUuid, String govStatus, String artifactName) throws RemoteException{
				   System.out.println("SystinetStatelessBean updateWSBPM()");
				     
				    boolean result = false;
				        try{
				        	if(artifactUuid!= null){
				        		
				         SystinetDao da0=new SystinetDao();
				         			     			         
				         result=da0.updateWSBPM(artifactUuid,govStatus,artifactName);
				    
				        	}
				         
				        }catch(Exception e){
				           System.out.println("Exception while calling updateWSBPM():" + e.getMessage());  
				        }  
				        return result;
				  }	 
			 
			  
			  
			  public boolean updateWSBPMgovStatus(String artifactUuid, String govStatus) throws RemoteException{
				   System.out.println("SystinetStatelessBean updateWSBPMgovStatus()");
				     
				    boolean result = false;
				        try{
				        	if(artifactUuid!= null){
				        		
				         SystinetDao da0=new SystinetDao();
				         			     			         
				         result=da0.updateWSBPMgovStatus(artifactUuid,govStatus);
				    
				        	}
				         
				        }catch(Exception e){
				           System.out.println("Exception while calling updateWSBPMgovStatus():" + e.getMessage());  
				        }  
				        return result;
				  }	 
			 
			  
			 

}
