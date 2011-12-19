import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ProxyGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Sean Gilligan"
    def authorEmail = "sean at msgilligan dot com"
    def title = "Ajax Proxy Plugin (for cross-domain requests)"
    def description = '''\\
Brief description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/proxy"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional)
      def config = ConfigurationHolder.config.plugins.grailsProxyPlugin
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

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
