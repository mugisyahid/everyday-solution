package myapp

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.grails.io.support.FileSystemResource
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment
import org.springframework.core.env.PropertiesPropertySource

import javax.annotation.Resource


class Application extends GrailsAutoConfiguration implements EnvironmentAware {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    void setEnvironment(Environment environment) {
        String configPath = System.properties["myapp.config.location"]
        if (configPath) {
            Resource resourceConfig = new FileSystemResource(configPath)
            YamlPropertiesFactoryBean propertyFactoryBean = new YamlPropertiesFactoryBean()
            propertyFactoryBean.setResources(resourceConfig)
            propertyFactoryBean.afterPropertiesSet()
            Properties properties = propertyFactoryBean.getObject()
            environment.propertySources.addFirst(new PropertiesPropertySource("myapp.config.location", properties))
        }
    }
}
	
	
	// java -jar -Dgrails.env=development myapp-0.1.war --spring.config.location=application.yml --server.port=9080
    // java -jar -Dmyapp.config.location=application.yml build/libs/myapp-0.1.war -Dgrails.env=development --server.port=9080