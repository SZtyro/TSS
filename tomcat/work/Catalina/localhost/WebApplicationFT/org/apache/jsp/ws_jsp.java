/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.9
 * Generated at: 2020-06-22 07:16:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ws_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <script src=\"js/canvasjs.min.js\"></script>\n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\">\n");
      out.write("\n");
      out.write("        var wsUri = getRootUri() + \"/WebApplicationFT/endpoint\";\n");
      out.write("        function getRootUri() {\n");
      out.write("            var wsUri = (location.protocol === \"http:\" ? \"ws://\" : \"wss://\");\n");
      out.write("            return wsUri + (document.location.hostname === \"\" ?\n");
      out.write("                    \"localhost\" :\n");
      out.write("                    document.location.hostname) + \":\" +\n");
      out.write("                    (document.location.port === \"\" ? \"8080\" :\n");
      out.write("                            document.location.port);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function init() {\n");
      out.write("            //output = document.getElementById(\"output\");\n");
      out.write("            initWebSocket();\n");
      out.write("        }\n");
      out.write("        function initWebSocket() {\n");
      out.write("            websocket = new WebSocket(wsUri);\n");
      out.write("            websocket.onopen = function (evt) {\n");
      out.write("                onOpen(evt);\n");
      out.write("            };\n");
      out.write("            websocket.onmessage = function (evt) {\n");
      out.write("                onMessage(evt);\n");
      out.write("            };\n");
      out.write("            websocket.onerror = function (evt) {\n");
      out.write("                onError(evt);\n");
      out.write("            };\n");
      out.write("\n");
      out.write("        }\n");
      out.write("        ;\n");
      out.write("\n");
      out.write("        function onOpen(evt) {\n");
      out.write("            writeToScreen(\"connected\");\n");
      out.write("        }\n");
      out.write("        function onMessage(evt) {\n");
      out.write("            writeToScreen(evt.data);\n");
      out.write("            var json = JSON.parse(evt.data);\n");
      out.write("            var d = [];\n");
      out.write("            for (i = 0; i < json.length; i++) {\n");
      out.write("                d.push({y: json[i]});\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            var chart = new CanvasJS.Chart(\"chartContainer\", {\n");
      out.write("                title: {\n");
      out.write("                    text: \"Losowe liczby\"\n");
      out.write("                },\n");
      out.write("                data: [\n");
      out.write("                    {\n");
      out.write("                        // Change type to \"doughnut\", \"line\", \"splineArea\", etc.\n");
      out.write("                        type: \"column\",\n");
      out.write("                        dataPoints: d\n");
      out.write("                    }\n");
      out.write("                ]\n");
      out.write("            });\n");
      out.write("            chart.render();\n");
      out.write("        }\n");
      out.write("        function onError(evt) {\n");
      out.write("            writeToScreen(\"error\" + evt.data);\n");
      out.write("        }\n");
      out.write("        function doSend(msg) {\n");
      out.write("            writeToScreen(\"sent\" + msg);\n");
      out.write("            websocket.send(msg);\n");
      out.write("\n");
      out.write("        }\n");
      out.write("        function writeToScreen(msg) {\n");
      out.write("            var pre = document.getElementById(\"messageID\");\n");
      out.write("            pre.innerHTML = msg;\n");
      out.write("            //pre.value = msg;\n");
      out.write("\n");
      out.write("        }\n");
      out.write("        window.addEventListener(\"load\", init, false);\n");
      out.write("\n");
      out.write("    </script>\n");
      out.write("    <body>\n");
      out.write("        <h1>Generowanie wykresu przez WS</h1>\n");
      out.write("        <br>\n");
      out.write("        <h5>Wiadomosc z ws:</h5>\n");
      out.write("        <p id=\"messageID\"></p>\n");
      out.write("   \n");
      out.write("        \n");
      out.write("        <div id=\"chartContainer\" style=\"width: 70%;margin: 0 auto\">\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
