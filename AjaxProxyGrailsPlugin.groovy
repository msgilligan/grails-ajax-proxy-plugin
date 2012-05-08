class AjaxProxyGrailsPlugin {
    def version = "0.2"
    def grailsVersion = "2.0 > *"

    def author = "Sean Gilligan"
    def authorEmail = "sean at msgilligan dot com"
    def title = "Ajax Proxy Plugin"
    def description = 'Ajax Proxy Plugin (for cross-domain requests)'
    def documentation = "http://grails.org/plugin/ajax-proxy"

    def doWithWebDescriptor = { xml ->

      def config = application.config.plugins.proxy

      String proxyScheme = config.proxyScheme ?: 'https://'
      String proxyHost = config.proxyHost ?: 'www.msgilligan.com'
      String proxyPort = config.proxyPort ?: '80'
      String proxyPath = config.proxyPath ?: ''

      // println "${proxyHost}:${proxyPort}//${proxyPath}"

      def servlets = xml.'servlet'
      servlets[servlets.size()-1] + {
        servlet{
          'servlet-name'('ProxyServlet')
          'servlet-class'('net.edwardstx.ProxyServlet')
          'init-param' {
            'param-name'('proxyScheme')
            'param-value'(proxyScheme)
          }
          'init-param' {
            'param-name'('proxyHost')
            'param-value'(proxyHost)
          }
          'init-param' {
            'param-name'('proxyPort')
            'param-value'(proxyPort)
          }
          'init-param' {
            'param-name'('proxyPath')
            'param-value'(proxyPath)
          }
          'init-param' {
            'param-name'('maxFileUploadSize')
            'param-value'('')
          }
        }
      }

      def servletMappings = xml.'servlet-mapping'
      servletMappings[servletMappings.size()-1] + {
        'servlet-mapping'{
          'servlet-name'('ProxyServlet')
          'url-pattern'('/proxy/*')
        }
      }
    }
}
