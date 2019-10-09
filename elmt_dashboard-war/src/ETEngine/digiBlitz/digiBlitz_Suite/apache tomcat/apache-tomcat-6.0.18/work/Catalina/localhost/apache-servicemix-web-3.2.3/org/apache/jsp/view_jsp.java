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
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/jspf/headertags.jspf");
    _jspx_dependants.add("/WEB-INF/sitemesh-decorator.tld");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>View</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");

Throwable dotException = null;
try {
    org.apache.servicemix.web.view.Dot.initialize();
} catch (Throwable t) {
    dotException = t;
}
if (dotException != null) {

      out.write("\r\n");
      out.write("<br></br>\r\n");
      out.write("Unable to run dot: ");
      out.print(dotException.getMessage());
      out.write("\r\n");
      out.write("<br></br>\r\n");
      out.write("Check that the dot executable is available in the path or install it from <a href=\"http://www.graphviz.org/\">http://www.graphviz.org/</a>.\r\n");

} else {

      out.write("\r\n");
      out.write("\r\n");
      out.write("<h2>View</h2>\r\n");
      out.write("\r\n");
      out.write("<fieldset style=\"height:25em\">\r\n");
      out.write("  <legend>Endpoints &nbsp; <small>(<a href=\"dot-endpoints.svg\">full page</a>)</small></legend>\r\n");
      out.write("  <object width=\"100%\" height=\"90%\" type=\"image/svg+xml\" codetype=\"image/svg+xml\" data=\"dot-endpoints.svg\">\r\n");
      out.write("    <span style=\"background-color:#FFFF20;padding-top:1pt;padding-bottom:1pt;\">\r\n");
      out.write("      This browser can't display the SVG file <a href=\"dot-endpoints.svg\">dot-endpoints.svg</a>.\r\n");
      out.write("      <br/>\r\n");
      out.write("      Please use newer versions of <a href=\"http://www.mozilla.com/firefox/\">Firefox</a> or install \r\n");
      out.write("      the <a href=\"http://www.adobe.com/svg/viewer/install/main.html\">Adobe SVG Viewer</a> for your\r\n");
      out.write("      browser.\r\n");
      out.write("    </span>\r\n");
      out.write("  </object>\r\n");
      out.write("</fieldset>\r\n");
      out.write("\r\n");
      out.write("<fieldset style=\"height:25em\">\r\n");
      out.write("  <legend>Flow &nbsp; <small>(<a href=\"dot-flow.svg\">full page</a>)</small></legend>\r\n");
      out.write("  <object width=\"100%\" height=\"90%\" type=\"image/svg+xml\" codetype=\"image/svg+xml\" data=\"dot-flow.svg\">\r\n");
      out.write("    <span style=\"background-color:#FFFF20;padding-top:1pt;padding-bottom:1pt;\">\r\n");
      out.write("      This browser can't display the SVG file <a href=\"dot-flow.svg\">dot-flow.svg</a>.\r\n");
      out.write("      <br/>\r\n");
      out.write("      Please use newer versions of <a href=\"http://www.mozilla.com/firefox/\">Firefox</a> or install \r\n");
      out.write("      the <a href=\"http://www.adobe.com/svg/viewer/install/main.html\">Adobe SVG Viewer</a> for your\r\n");
      out.write("      browser.\r\n");
      out.write("    </span>\r\n");
      out.write("  </object>\r\n");
      out.write("</fieldset>\r\n");
      out.write("\r\n");

}

      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\t\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}