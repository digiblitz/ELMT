/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msg.action;

import com.hlccommon.util.Debug;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author vidhya
 */
public class DownLoad extends Action {

    public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
        Debug.print("Inside the Download");
        MessageResources mr = getResources(request);
        try {
            String file = request.getParameter("fileName");
            // set the http content type to "APPLICATION/OCTET-STREAM
            response.setContentType("APPLICATION/OCTET-STREAM");
            // initialize the http content-disposition header to
            // indicate a file attachment with the  filename
             // transfer the file byte-by-byte to the response object
            response.setHeader("Content-Disposition", "attachment;filename=" + file);
            File fileToDownload = new File(mr.getMessage("file.downloadPath") + file);
            FileInputStream fileInputStream = new FileInputStream(fileToDownload);
            int i;
            PrintWriter out = response.getWriter();
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
            out.close();
        } catch (Exception e) // file IO errors
        {
            e.printStackTrace();
        }
        return null;

    }
}
