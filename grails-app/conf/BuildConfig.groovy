grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {
		compile('javax.servlet:servlet-api:2.5') {
			transitive = false
		}
		runtime('org.apache.httpcomponents:httpclient:4.1.3') {
			transitive = true
		}
	}

	plugins {
		build(':release:1.0.0') {
			export = false
		}
	}
}
