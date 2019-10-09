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
package com.systinet.action;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.hlcform.stateless.*;
import com.hp.systinet.lifecycle.remote.model.StageCategory;
import com.form.SystinetUserRegVo;
import com.systinet.info.SystinetStatelessRemote;
import com.systinet.info.SystinetStatelessRemoteHome;

import org.apache.struts.util.MessageResources;
import org.jasypt.util.text.BasicTextEncryptor;
import com.form.SystinetRequestVo;
import com.form.SystinetUserRegVo;



//public class SystinetManagementActions extends DispatchAction {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
	
  
	  
	    
	
	
	//==================================================BPM Start =====================================================================================

	/*public ActionForward callBPMList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			MessageResources mr = getResources(request);
			String namingfactory = mr.getMessage("ejbclient.namingfactory");
			String contextfactory = mr.getMessage("ejbclient.contextfactory");
			String urlprovider = mr.getMessage("ejbclient.urlprovider");
			String lookupip = mr.getMessage("ejbclient.ip");
			String jndiname = "ejb/SystinetStatelessBeanJNDI";

			System.setProperty(namingfactory, contextfactory);
			System.setProperty(urlprovider, lookupip);
			Context jndiContext = new InitialContext();
			Object objref = jndiContext.lookup(jndiname);

			SystinetStatelessRemoteHome home = (SystinetStatelessRemoteHome) javax.rmi.PortableRemoteObject
					.narrow(objref, SystinetStatelessRemoteHome.class);
			SystinetStatelessRemote remote = home.create();

			System.setProperty(namingfactory, contextfactory);
			System.setProperty(urlprovider, lookupip);
			HashMap bundles = remote.viewWsBPMList("BPM");

			request.setAttribute("bpmData", bundles);

		} catch (Exception ex) {
			System.err.println("Caught an exception.");
			ex.printStackTrace();
		}

		return mapping.findForward("callBPM");

	}
	
	public ActionForward callBPMEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("callBPMEdit");

	}
	
	 public ActionForward subBpRequest(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			MessageResources mr = getResources(request);
			String namingfactory = mr.getMessage("ejbclient.namingfactory");
			String contextfactory = mr.getMessage("ejbclient.contextfactory");
			String urlprovider = mr.getMessage("ejbclient.urlprovider");
			String lookupip = mr.getMessage("ejbclient.ip");
			String jndiname = "ejb/SystinetStatelessBeanJNDI";

			System.setProperty(namingfactory, contextfactory);
			System.setProperty(urlprovider, lookupip);
			Context jndiContext = new InitialContext();
			Object objref = jndiContext.lookup(jndiname);

			SystinetStatelessRemoteHome home = (SystinetStatelessRemoteHome) javax.rmi.PortableRemoteObject
					.narrow(objref, SystinetStatelessRemoteHome.class);
			SystinetStatelessRemote remote = home.create();
			SystinetRequestVo vo=new SystinetRequestVo();
			
			
			
			vo.setReqId(request.getParameter("reqId"));
			vo.setReqTitle(request.getParameter("reqTitle"));
			vo.setDate1(request.getParameter("date1"));
			vo.setDate2(request.getParameter("date2"));
			vo.setCurVersion(request.getParameter("curVersion"));
			vo.setModVersion(request.getParameter("modVersion"));
			vo.setReqPri(request.getParameter("reqPri"));
			vo.setReqDesc(request.getParameter("reqDesc"));
	      
			boolean insertReq = remote.insertRequestValues(vo,"BP");
	       request.setAttribute("success", "success");
	       HashMap bpmdata = remote.viewWsBPMList("BPM");

	       request.setAttribute("bpmData", bpmdata);

			return mapping.findForward("callBPM");

		}
		
	 
		//==================================================WebService Start =====================================================================================
	public ActionForward callBundles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			MessageResources mr = getResources(request);
			String namingfactory = mr.getMessage("ejbclient.namingfactory");
			String contextfactory = mr.getMessage("ejbclient.contextfactory");
			String urlprovider = mr.getMessage("ejbclient.urlprovider");
			String lookupip = mr.getMessage("ejbclient.ip");
			String jndiname = "ejb/SystinetStatelessBeanJNDI";

			System.setProperty(namingfactory, contextfactory);
			System.setProperty(urlprovider, lookupip);
			Context jndiContext = new InitialContext();
			Object objref = jndiContext.lookup(jndiname);

			SystinetStatelessRemoteHome home = (SystinetStatelessRemoteHome) javax.rmi.PortableRemoteObject
					.narrow(objref, SystinetStatelessRemoteHome.class);
			SystinetStatelessRemote remote = home.create();

			System.setProperty(namingfactory, contextfactory);
			System.setProperty(urlprovider, lookupip);
			HashMap bundles = remote.viewWsBPMList("WS");

			request.setAttribute("BundlesData", bundles);

		} catch (Exception ex) {
			System.err.println("Caught an exception.");
			ex.printStackTrace();
		}

		return mapping.findForward("callBundle");

	}

	public ActionForward callBundlesEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("callBundleEdit");

	}

	public ActionForward subRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MessageResources mr = getResources(request);
		String namingfactory = mr.getMessage("ejbclient.namingfactory");
		String contextfactory = mr.getMessage("ejbclient.contextfactory");
		String urlprovider = mr.getMessage("ejbclient.urlprovider");
		String lookupip = mr.getMessage("ejbclient.ip");
		String jndiname = "ejb/SystinetStatelessBeanJNDI";

		System.setProperty(namingfactory, contextfactory);
		System.setProperty(urlprovider, lookupip);
		Context jndiContext = new InitialContext();
		Object objref = jndiContext.lookup(jndiname);

		SystinetStatelessRemoteHome home = (SystinetStatelessRemoteHome) javax.rmi.PortableRemoteObject
				.narrow(objref, SystinetStatelessRemoteHome.class);
		SystinetStatelessRemote remote = home.create();
		SystinetRequestVo vo=new SystinetRequestVo();
		
		
		
		vo.setReqId(request.getParameter("reqId"));
		vo.setReqTitle(request.getParameter("reqTitle"));
		vo.setDate1(request.getParameter("date1"));
		vo.setDate2(request.getParameter("date2"));
		vo.setCurVersion(request.getParameter("curVersion"));
		vo.setModVersion(request.getParameter("modVersion"));
		vo.setReqPri(request.getParameter("reqPri"));
		vo.setReqDesc(request.getParameter("reqDesc"));
      
		boolean insertReq = remote.insertRequestValues(vo,"WS");
       request.setAttribute("success", "success");
       HashMap bundles = remote.viewWsBPMList("WS");

       request.setAttribute("BundlesData", bundles);

		return mapping.findForward("callBundle");

	}

	
	//==================================================WebService End =====================================================================================	    
	   
	
	
	
	
	public ActionForward callBPReq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		MessageResources mr = getResources(request);
		String namingfactory = mr.getMessage("ejbclient.namingfactory");
		String contextfactory = mr.getMessage("ejbclient.contextfactory");
		String urlprovider = mr.getMessage("ejbclient.urlprovider");
		String lookupip = mr.getMessage("ejbclient.ip");
		String jndiname = "ejb/SystinetStatelessBeanJNDI";

		System.setProperty(namingfactory, contextfactory);
		System.setProperty(urlprovider, lookupip);
		Context jndiContext = new InitialContext();
		Object objref = jndiContext.lookup(jndiname);

		SystinetStatelessRemoteHome home = (SystinetStatelessRemoteHome) javax.rmi.PortableRemoteObject
				.narrow(objref, SystinetStatelessRemoteHome.class);
		SystinetStatelessRemote remote = home.create();
		
		String data = request.getParameter("frmDate");
		String req = request.getParameter("req");
		String reqId = request.getParameter("reqId");
		String reqPri = request.getParameter("reqPri");
		
		  ArrayList list = remote.viewBPReqList(data,req,reqId,reqPri);

	       request.setAttribute("list", list);
		return mapping.findForward("callBPReq");

	}

	
	//===========================Lifecycle Process:Systinet Integration============
	
	 public ActionForward callArtadd(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    	
	    	//System.out.println("asfdddddddddddddddddddddd");
	    	HttpSession session=request.getSession(true); 
	    	
	    	ArrayList addArtifact=new ArrayList();
	    	
	    	
	    	String addArtifacttype=request.getParameter("SelArtifacttype");
	    	String lifecycleProName=request.getParameter("txtLifeProcees");
	    	String numofStage=request.getParameter("selNolifeStage");
	    	String dateofCreation=request.getParameter("frmDate");
	    	String createdBy=request.getParameter("selNolifeStage");
	    	String stage=request.getParameter("selStages");
	    	String input=request.getParameter("txtInput");
	    	String process=request.getParameter("txtprocess");
	    	String control=request.getParameter("selVotes");
	    	String output=request.getParameter("txtOutput");
	    	String descripition=request.getParameter("txtDesc");
	    
			  if(session.getAttribute("artifactData")!=null){
				  addArtifact = (ArrayList) session.getAttribute("artifactData");
			  }
			  
			  
			  if(lifecycleProName != null && lifecycleProName.length()> 0){
			    	String [] artifactName = {lifecycleProName,numofStage,dateofCreation,createdBy,stage,input,process,control,output,descripition,addArtifacttype };
			    	
			    	addArtifact.add(artifactName);
			  }
			  
		  
	    	System.out.println("artifactName"+addArtifact+"inputID");
	    	System.out.println("lifecycleProName"+lifecycleProName+"numofStage"+numofStage+"dateofCreation"+dateofCreation+"createdBy"+createdBy+"stage"+stage+"input"+input+"process"+process+"control"+control+"output"+output+"descripition"+descripition);
	    	
	    	
	    	Map<String,String> artifactType=new HashMap();
	        
	    	 artifactType.put("hpsoaProjectArtifact", "Project");  
	    	 artifactType.put("hpsoaApplicationArtifact", "Application");
	    	 artifactType.put("businessServiceArtifact", "Service");  
	    	 artifactType.put("implementationArtifact", "Implementaion");
	    	 artifactType.put("hpsoaProcessArtifact", "Business Process");  
	    	

	    	 
	    	
	    	
	    	ArrayList StageCycle=new ArrayList();
	    	StageCycle.add( "1");  
	    	StageCycle.add( "2");
	    	StageCycle.add( "3");  
	    	StageCycle.add( "4");  
	    	StageCycle.add( "5");
	    	
	    	ArrayList lifeCycle=new ArrayList();
	    	lifeCycle.add( "1");  
	    	lifeCycle.add( "2");
	    	lifeCycle.add( "3");  
	    	lifeCycle.add( "4");  
	    	lifeCycle.add( "5");
	    	
	    	ArrayList numberVoters=new ArrayList();
	    	numberVoters.add( "1");  
	    	numberVoters.add( "2");
	    	numberVoters.add( "3");  
	    	numberVoters.add( "4");  
	    	numberVoters.add( "5");
	    	
	    	Map<String,String> votesReq=new HashMap();
	    	votesReq.put(" Project_Managers "," Project Managers ");  
	    	votesReq.put( "BusinessAnalyst","BusinessAnalyst");
	    	votesReq.put(" Service_Providers ", "Service Providers");  
	    	
	    	
	    	
	       session.setAttribute("artifactType", artifactType);
	       session.setAttribute("lifeCycle", lifeCycle);
	       session.setAttribute("StageCycle", StageCycle);
	       session.setAttribute("numberVoters", numberVoters);
		   session.setAttribute("votesReq", votesReq);
		   session.setAttribute("artifactData", addArtifact);
		   
		   
		   request.setAttribute("lifecycleProName", lifecycleProName);
		   
		   request.setAttribute("numofStage",numofStage);
		  
		   request.setAttribute("dateofCreation",dateofCreation);
		 
		   request.setAttribute("addArtifacttype",addArtifacttype);
		   
	       return mapping.findForward("callArtifactadd");
	    }
	    
	
		public ActionForward systinetLifecycle(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			MessageResources mr = getResources(request);
			String namingfactory = mr.getMessage("ejbclient.namingfactory");
			String contextfactory = mr.getMessage("ejbclient.contextfactory");
			String urlprovider = mr.getMessage("ejbclient.urlprovider");
			String lookupip = mr.getMessage("ejbclient.ip");
			String jndiname = "ejb/SystinetStatelessBeanJNDI";

			System.setProperty(namingfactory, contextfactory);
			System.setProperty(urlprovider, lookupip);
			Context jndiContext = new InitialContext();
			Object objref = jndiContext.lookup(jndiname);

			SystinetStatelessRemoteHome home = (SystinetStatelessRemoteHome) javax.rmi.PortableRemoteObject
					.narrow(objref, SystinetStatelessRemoteHome.class);
			SystinetStatelessRemote remote = home.create();
			HttpSession session=request.getSession(true); 
			
			 ArrayList artifactName=(ArrayList)session.getAttribute("artifactData");
			 
			 ArrayList stageList=new ArrayList();
			 boolean output1=false;
			 String processId=null;
			 String stageName="";
			 if (artifactName != null && artifactName.size() != 0) { 
		    	 Iterator evotesartifactName = artifactName.iterator();
		    	
		    	 while (evotesartifactName.hasNext()) {
		             String strType[] = (String[]) evotesartifactName.next();
		         	
				String lifecycleProName=strType[0];
		    	String numofStage=strType[1];
		    	String dateofCreation=strType[2];
		    	String createdBy=strType[3];
		    	String stage=strType[4];
		    	 stageName=strType[5];
		    	String process=strType[6];
		    	String control=strType[7];
		    	String output=strType[8];
		    	String descripition=strType[9];
		        String rootArtifact=strType[10]; 
		        
		        String stageNameValue="uddi:systinet.com:soa:model:taxonomies:lifecycleStages:"+stageName.toLowerCase(); 
		        
		        String[] strArray = {""};
		    	Collection<String> subArti = Arrays.asList(strArray);
		    	
		    	if(stage!=null && stage.equalsIgnoreCase("1")){
		    		
		    		StageCategory initialStage = new StageCategory(stageName,stageNameValue);
		    			        
		         processId=remote.createLifecycleinSystinet(lifecycleProName,descripition,rootArtifact,subArti,initialStage);
		               	        
		    	}
		    	
		    	session.setAttribute("processId",processId);
		    			  		    	
		    	String stages[]={stageName};
		    	 stageList.add(stages);
	    				    				    	     	 
			    	}
		    	 
		    	 }
			 		
			 System.out.println("stageList.size()"+stageList.size());
			 
			 if (stageList != null && stageList.size()!= 0) { 
				 
				 Iterator evotesartifactName = stageList.iterator();
		    	
		    	int i=1;
		    	String tempStage="";
		    	
		    	 while (evotesartifactName.hasNext()) {
		             String strType[] = (String[]) evotesartifactName.next();	 
				 
		          String stage=strType[0];   
		    //System.out.println("Stage name in stageList()"+stage);
		   	    
		          if(i==1){
		        	tempStage=stage;		        	
		        	 System.out.println("Stage name inside if"+stage);
		          }else{
		        	  
		        	  String frmstageName=tempStage;
		        	  String toStageName=stage;
		        	  String frmStageNameValue="uddi:systinet.com:soa:model:taxonomies:lifecycleStages:"+frmstageName.toLowerCase(); 
		        	  String toStageNameValue="uddi:systinet.com:soa:model:taxonomies:lifecycleStages:"+toStageName.toLowerCase(); 
		        	  	        	  
		        	  StageCategory frmStage=new StageCategory(frmstageName,frmStageNameValue);	
			    	 	
				      StageCategory toStage = new StageCategory(toStageName,toStageNameValue);				    		
				    		    		
				    		Collection<StageCategory> toStageV = Arrays.asList(toStage);
				    			    		
				    		 output1=remote.addStageToProcess(processId, frmStage, toStageV);	  
		        			    		 
				    		 tempStage=stage;  
				    		 System.out.println("Stage name inside else "+stage);   		 
				 if(i==stageList.size()){   		 
				for(int j=i;j<=i;j++){
					//System.out.println("Inside else for "+stage);  	
					  String frmstageNametem=tempStage;
		        	  String toStageNametem="";
		        	  String frmStageNameValuetem="uddi:systinet.com:soa:model:taxonomies:lifecycleStages:"+frmstageNametem.toLowerCase(); 
		        	  String toStageNameValuetem=""; 	
					
		        	  StageCategory frmStagefinal=new StageCategory(frmstageNametem,frmStageNameValuetem);	
			    	 	
				      StageCategory toStagefinal = new StageCategory(toStageNametem,toStageNameValuetem);	
					
				      System.out.println("Inside else for stage"+frmstageNametem); 
				      System.out.println("Inside else for stage"+toStageNametem); 
				      Collection<StageCategory> toStageVal = Arrays.asList(toStagefinal);
					 output1=remote.addStageToProcess(processId, frmStagefinal, toStageVal);	
				}
		          }
		          }
		          		             		            			 
		          i++; }
				 
			 }
			
							 
			if(processId!=null && output1==true){
				boolean result=remote.publishProcess(processId);			
			 request.setAttribute("Success","Successfully Created the lifecycle");
			 session.setAttribute("artifactData", null); 	    
			 return mapping.findForward("callArtifactadd");
			 }else{
				 request.setAttribute("Success","Error");
				 session.setAttribute("artifactData", null); 
				 return mapping.findForward("callArtifactadd");	 
			 }
			 
			

		}
		
		   
}
*/

