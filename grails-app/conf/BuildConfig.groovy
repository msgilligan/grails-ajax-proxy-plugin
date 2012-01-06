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
		runtime('commons-httpclient:commons-httpclient:3.1') {
			transitive = false
		}
		runtime('commons-logging:commons-logging:1.1.1') {
			transitive = false
		}
	}

	plugins {
		build(':release:1.0.0') {
			export = false
		}
	}
}
