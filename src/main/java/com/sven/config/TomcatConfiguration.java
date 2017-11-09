package com.sven.config;

import java.io.IOException;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class TomcatConfiguration
{

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TomcatConfiguration.class);

    @Autowired
    private TomcatConnectorSettings tomcatConnectorSettings;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() throws IOException
    {
        LOGGER.debug("Entering servletContainer()");
        TomcatEmbeddedServletContainerFactory tomcat =
                new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createHttpsConnector());
        tomcat.addContextCustomizers(
                new TomcatContextCustomizer()
                {
                    @Override
                    public void customize(final Context context)
                    {
                        //allowed the domain to start with "."
                        context.setCookieProcessor(new LegacyCookieProcessor());
                    }
                });
        return tomcat;

    }
    
    private Connector createHttpsConnector() throws IOException
    {
        Connector connector = new Connector("org.apache.coyote.http11.Http11Nio2Protocol");
        Http11Nio2Protocol protocol = (Http11Nio2Protocol) connector.getProtocolHandler();
        protocol.setSSLEnabled(true);
        connector.setScheme("https");
        connector.setRedirectPort(8080);
        connector.setPort(8009);
        connector.setSecure(true);
        
        String path = new ClassPathResource("keystore.p12").getURI().getPath();
        protocol.setKeystoreFile(path);
        protocol.setKeystorePass("123456");
        protocol.setKeystoreType("PKCS12");
        protocol.setKeyAlias("tomcat");

        return connector;
    }

    private Connector createAjpConnector()
    {
        LOGGER.debug("Entering createAjpConnector()");

        Connector connector = new Connector("org.apache.coyote.ajp.AjpNio2Protocol");
        AjpNio2Protocol protocol = (AjpNio2Protocol) connector.getProtocolHandler();

        protocol.setConnectionTimeout(20000);

        connector.setScheme(tomcatConnectorSettings.getScheme());
        connector.setProtocol("AJP/1.3");
        connector.setRedirectPort(tomcatConnectorSettings.getAjpredirectport());
        connector.setPort(tomcatConnectorSettings.getAjpport());
        // enable DNS lookups
        connector.setEnableLookups(tomcatConnectorSettings.isEnablelookups());
        // allow tracing
        connector.setAllowTrace(tomcatConnectorSettings.isAllowtrace());
        // set proxy name
        connector.setProxyName(tomcatConnectorSettings.getProxyname());
        // set proxy port
        connector.setProxyPort(tomcatConnectorSettings.getProxyport());
        // set whether the connection is secure or not
        connector.setSecure(tomcatConnectorSettings.isSecure());

        LOGGER.info("TomcatConnectorSettings: " + tomcatConnectorSettings);

        return connector;
    }
}