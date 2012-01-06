import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ProxyGrailsPlugin {
    def version = "0.1.1"
    def grailsVersion = "1.3.7 > *"

    def author = "Sean Gilligan"
    def authorEmail = "sean at msgilligan dot com"
    def title = "Ajax Proxy Plugin"
    def description = 'Ajax Proxy Plugin (for cross-domain requests)'

    def documentation = "http://grails.org/plugin/proxy"

    def doWithWebDescriptor = { xml ->
      def config = ConfigurationHolder.config.plugins.proxy

      def proxySchemeConfig = config.proxyScheme
      def proxyScheme = proxySchemeConfig instanceof ConfigObject?'https://':proxySchemeConfig

      def proxyHostConfig = config.proxyHost
      def proxyHost = proxyHostConfig instanceof ConfigObject?'www.msgilligan.com':proxyHostConfig

      def proxyPortConfig = config.proxyPort
      def proxyPort = proxyPortConfig instanceof ConfigObject?'80':proxyPortConfig

      def proxyPathConfig = config.proxyPath
      def proxyPath = proxyPathConfig instanceof ConfigObject?'':proxyPathConfig
      
      println "${proxyHost}:${proxyPort}//${proxyPath}"

      def servlets = xml.'servlet'
      servlets[servlets.size()-1] + {
        servlet{
          'servlet-name'('ProxyServlet')
          'servlet-class'('net.edwardstx.ProxyServlet')
          'init-param' {
            'param-name'('proxyScheme')
            'param-value'("${proxyScheme}")
          }
          'init-param' {
            'param-name'('proxyHost')
            'param-value'("${proxyHost}")
          }
          'init-param' {
            'param-name'('proxyPort')
            'param-value'("${proxyPort}")
          }
          'init-param' {
            'param-name'('proxyPath')
            'param-value'("${proxyPath}")
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
