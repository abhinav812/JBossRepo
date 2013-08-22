package com.abhinav.mavenexample.app.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.abhinav.mavenexample.app.ejb.Hello;
import com.abhinav.mavenexample.app.ejb.HelloRemote;

public class HelloClient {
	private static final String CLASS_NAME = HelloClient.class.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);
	
	// Lookup Format will be
	// <app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
	
	private static final String HELLO_BEAN_REMOTE_EJB_JNDI = "ejb:JavaMavenExampleEnterpriseApp/JavaMavenExampleEnterpriseApp-ejb-1.0.0-SNAPSHOT/HelloEJB!com.abhinav.mavenexample.app.ejb.HelloRemote";
	private static final String HELLO_BEAN_REMOTE_PROVIDER_URL = "remote://localhost:4447";
	private static final String HELLO_BEAN_REMOTE_JNDI_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	
//	private static final String HELLO_BEAN_REMOTE_EJB_JNDI = "com.abhinav.mavenexample.app.ejb.HelloRemote";
//	private static final String HELLO_BEAN_REMOTE_PROVIDER_URL = "jnp://localhost:4447";
//	private static final String HELLO_BEAN_REMOTE_JNDI_FACTORY = "org.jnp.interfaces.NamingContextFactory";

	
	private static final String HELLO_BEAN_LOCAL_EJB_JNDI = "com.abhinav.mavenexample.app.ejb.HelloLocal";
	private static final String HELLO_BEAN_LOCAL_PROVIDER_URL = "jnp://localhost:4447";
	private static final String HELLO_BEAN_LOCAL_JNDI_FACTORY = "org.jnp.interfaces.NamingContextFactory";

	public static HelloRemote getHelloEJB() throws Exception {
		return (HelloRemote) getEJB(HELLO_BEAN_REMOTE_EJB_JNDI,
				HELLO_BEAN_REMOTE_PROVIDER_URL, HELLO_BEAN_REMOTE_JNDI_FACTORY);
	}

	public static HelloRemote getHelloEJBLocal() throws Exception {
		return (HelloRemote) getEJB(HELLO_BEAN_LOCAL_EJB_JNDI,
				HELLO_BEAN_LOCAL_PROVIDER_URL, HELLO_BEAN_LOCAL_JNDI_FACTORY);
	}

	private static Hello getEJB(final String jndiNamePath,
			final String providerURLPath, final String connectionFactoryPath)
			throws Exception {
		Hello hello = null;

		try {
//			final String helloEJBname = jndiNamePath;
//			final String providerURL = providerURLPath;
//			final String jndiConFactory = connectionFactoryPath;
			final Properties environment = new Properties();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, connectionFactoryPath);
			environment.put(Context.PROVIDER_URL, providerURLPath);
			environment.put(Context.SECURITY_PRINCIPAL, "abhinavApp");
			environment.put(Context.SECURITY_CREDENTIALS, "P@ssw0rd");
			environment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming"); 
			Context ctx = new InitialContext(environment);
			
			logger.info("\n\tGot initial Context: "+ctx);
			// ejb:JavaMavenExampleEnterpriseApp/JavaMavenExampleEnterpriseApp-ejb-1.0.0.jar/HelloEJB!com.abhinav.mavenexample.app.ejb.HelloRemote
			Object obj = ctx.lookup(jndiNamePath);
			hello = (Hello) obj;
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return hello;
	}

}
