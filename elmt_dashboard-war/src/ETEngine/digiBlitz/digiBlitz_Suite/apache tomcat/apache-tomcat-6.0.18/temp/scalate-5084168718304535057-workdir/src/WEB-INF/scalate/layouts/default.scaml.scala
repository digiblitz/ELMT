/* NOTE this file is autogenerated by Scalate : see http://scalate.fusesource.org/ */
package scalate.layouts

import _root_.scala.collection.JavaConversions._
import _root_.org.fusesource.scalate.support.TemplateConversions._

object $_scalate_$default_scaml {
  def $_scalate_$render($_scalate_$_context: _root_.org.fusesource.scalate.RenderContext): Unit = {
    import _root_.org.fusesource.scalate.support.RenderHelper.{sanitize=>$_scalate_$_sanitize, preserve=>$_scalate_$_preserve, indent=>$_scalate_$_indent, smart_sanitize=>$_scalate_$_smart_sanitize, attributes=>$_scalate_$_attributes}
    ;{
      implicit val context: _root_.org.fusesource.scalate.servlet.ServletRenderContext = $_scalate_$_context.attribute("context")
      import context._
      
      
      //
      //       Copyright (C) 2009-2010 the original author or authors.

      //       See the notice.md file distributed with this work for additional

      //       information regarding copyright ownership.

      //
      //       Licensed under the Apache License, Version 2.0 (the "License");

      //       you may not use this file except in compliance with the License.

      //       You may obtain a copy of the License at

      //
      //           http://www.apache.org/licenses/LICENSE-2.0

      //
      //       Unless required by applicable law or agreed to in writing, software

      //       distributed under the License is distributed on an "AS IS" BASIS,

      //       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

      //       See the License for the specific language governing permissions and

      //       limitations under the License.

      //
      ;{
        val body: String = $_scalate_$_context.attribute("body")
        ;{
          val title: String = $_scalate_$_context.attributeOrElse("title", "Apache Karaf ${project.version} Guide")
                    response.setContentType("text/html")

          //           Only include the console if it's available and the engine is in dev mode.

                    val include_console = engine.isDevelopmentMode && engine.resourceLoader.exists("/org/fusesource/scalate/console/console_head.scaml")

          $_scalate_$_context << ( "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML Basic 1.1//EN\" \"http://www.w3.org/TR/xhtml-basic/xhtml-basic11.dtd\"> \n<html lang=\"en\">\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n    <meta name=\"description\" content=\"description goes here\"/>\n    <meta name=\"keywords\" content=\"keywords,goes,here\"/>\n    <meta name=\"author\" content=\"The Apache Karaf Team\"/>\n" );
                    if (include_console)
{
            $_scalate_$_context << ( "    " );
            $_scalate_$_context << ( $_scalate_$_indent ( "    ", $_scalate_$_context.value(
               include("/org/fusesource/scalate/console/console_head.scaml")
            ) ) );
            $_scalate_$_context << ( "\n" );
          }
          $_scalate_$_context << ( "    <link" );
          $_scalate_$_context << $_scalate_$_attributes( $_scalate_$_context, List( (
              "href"
            ,
                            uri("/css/style.css")

            ), (
              "rel"
            ,
              "stylesheet"
            ), (
              "type"
            ,
              "text/css"
          ) ) )
          $_scalate_$_context << ( "/>\n    <link" );
          $_scalate_$_context << $_scalate_$_attributes( $_scalate_$_context, List( (
              "href"
            ,
                            uri("/css/pygmentize.css")

            ), (
              "rel"
            ,
              "stylesheet"
            ), (
              "type"
            ,
              "text/css"
          ) ) )
          $_scalate_$_context << ( "/>\n" );
                    if (include_console)
{
            $_scalate_$_context << ( "    <link" );
            $_scalate_$_context << $_scalate_$_attributes( $_scalate_$_context, List( (
                "href"
              ,
                                uri("/css/scalate/console.css")

              ), (
                "rel"
              ,
                "stylesheet"
              ), (
                "type"
              ,
                "text/css"
            ) ) )
            $_scalate_$_context << ( "/>\n" );
          }
          $_scalate_$_context << ( "    <title>\n      " );
          $_scalate_$_context << ( $_scalate_$_indent ( "      ", $_scalate_$_context.value(
             title
          ) ) );
          $_scalate_$_context << ( "\n    </title>\n  </head>\n  <body>\n    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n      <tr width=\"100%\">\n        <td id=\"cell-0-0\" colspan=\"2\">\n          &nbsp;\n        </td>\n        <td id=\"cell-0-1\">\n          &nbsp;\n        </td>\n        <td id=\"cell-0-2\" colspan=\"2\">\n          &nbsp;\n        </td>\n      </tr>\n      <tr width=\"100%\">\n        <td id=\"cell-1-0\">\n          &nbsp;\n        </td>\n        <td id=\"cell-1-1\">\n          &nbsp;\n        </td>\n        <td id=\"cell-1-2\">\n          <div style=\"padding: 5px;\">\n            <div id=\"banner\">\n              " );
          $_scalate_$_context << ( $_scalate_$_indent ( "              ", $_scalate_$_context.value(
             include("/_banner.ssp")
          ) ) );
          $_scalate_$_context << ( "\n            </div>\n            <div id=\"top-menu\">\n              <table border=\"0\" cellpadding=\"1\" cellspacing=\"0\" width=\"100%\">\n                <tr>\n                  <td>\n                    <div align=\"left\"></div>\n                  </td>\n                  <td>\n                    <div align=\"right\">\n                      " );
          $_scalate_$_context << ( $_scalate_$_indent ( "                      ", $_scalate_$_context.value(
             include("/_quicklinks.ssp")
          ) ) );
          $_scalate_$_context << ( "\n                    </div>\n                  </td>\n                </tr>\n              </table>\n            </div>\n          </div>\n        </td>\n        <td id=\"cell-1-3\">\n          &nbsp;\n        </td>\n        <td id=\"cell-1-4\">\n          &nbsp;\n        </td>\n      </tr>\n      <tr width=\"100%\">\n        <td id=\"cell-2-0\" colspan=\"2\">\n          &nbsp;\n        </td>\n        <td id=\"cell-2-1\">\n          <table>\n            <tr height=\"100%\" valign=\"top\">\n              <td height=\"100%\">\n                <div id=\"wrapper-menu-page-right\">\n                  <div id=\"wrapper-menu-page-top\">\n                    <div id=\"wrapper-menu-page-bottom\">\n                      <div id=\"menu-page\">\n                        " );
          $_scalate_$_context << ( $_scalate_$_indent ( "                        ", $_scalate_$_context.value(
             include("/_navigation.conf")
          ) ) );
          $_scalate_$_context << ( "\n                      </div>\n                    </div>\n                  </div>\n                </div>\n              </td>\n              <td height=\"100%\" width=\"100%\">\n                <div class=\"wiki-content\">\n" );
          $_scalate_$_context << ( $_scalate_$_context.valueUnescaped(
             body
          ) );
          $_scalate_$_context << ( "\n                </div>\n              </td>\n            </tr>\n          </table>\n        </td>\n        <td id=\"cell-2-2\" colspan=\"2\">\n          &nbsp;\n        </td>\n      </tr>\n      <tr width=\"100%\">\n        <td id=\"cell-3-0\">\n          &nbsp;\n        </td>\n        <td id=\"cell-3-1\">\n          &nbsp;\n        </td>\n        <td id=\"cell-3-2\">\n          <div id=\"footer\">\n            <div id=\"site-footer\">\n              &copy; 2008-2011 The Apache Software Foundation\n              <br/>\n              Apache Karaf, Karaf, Apache, the Apache feather logo, and the Apache Karaf project logo are trademarks of The Apache Software Foundation.\n            </div>\n          </div>\n        </td>\n        <td id=\"cell-3-3\">\n          &nbsp;\n        </td>\n        <td id=\"cell-3-4\">\n          &nbsp;\n        </td>\n      </tr>\n      <tr width=\"100%\">\n        <td id=\"cell-4-0\" colspan=\"2\">\n          &nbsp;\n        </td>\n        <td id=\"cell-4-1\">\n          &nbsp;\n        </td>\n        <td id=\"cell-4-2\" colspan=\"2\">\n          &nbsp;\n        </td>\n      </tr>\n    </table>\n" );
                    if (include_console)
{
            $_scalate_$_context << ( "    " );
            $_scalate_$_context << ( $_scalate_$_indent ( "    ", $_scalate_$_context.value(
               include("/org/fusesource/scalate/console/console.scaml")
            ) ) );
            $_scalate_$_context << ( "\n" );
          }
          $_scalate_$_context << ( "  </body>\n</html>\n" );
        }
      }
    }
  }
}


class $_scalate_$default_scaml extends _root_.org.fusesource.scalate.Template {
  def render(context: _root_.org.fusesource.scalate.RenderContext): Unit = $_scalate_$default_scaml.$_scalate_$render(context)
}