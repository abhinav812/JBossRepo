package com.abhinav.mavenexample.app.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;
//import org.jboss.ejb3.annotation.LocalBinding;
//import org.jboss.ejb3.annotation.RemoteBinding;

import com.abhinav.mavenexample.app.beans.PrototypeBeanWithClassProxy;
import com.abhinav.mavenexample.app.beans.PrototypeBeanWithoutProxy;
import com.abhinav.mavenexample.app.interceptor.HelloInterceptor;
import com.abhinav.mavenexample.app.util.BeanLocator;

@Stateless(name = "HelloEJB")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(value = HelloInterceptor.class)
// @RemoteBinding(jndiBinding = "com.abhinav.mavenexample.app.ejb.HelloRemote")
// @LocalBinding(jndiBinding = "com.abhinav.mavenexample.app.ejb.HelloLocal")
@Remote(HelloRemote.class)
@Local(HelloLocal.class)
public class HelloBean implements Hello, HelloRemote, HelloLocal {
	private static final String CLASS_NAME = HelloBean.class.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);

	private PrototypeBeanWithClassProxy proxyBean;
	private PrototypeBeanWithoutProxy noProxyBean;

	@PostConstruct
	public void initialize() {
		// Initialize the beans
		logger.debug("*** Initializing ValidationHandlerBean");
		proxyBean = (PrototypeBeanWithClassProxy) BeanLocator
				.getBean("prototypeBeanWithClassProxy");
		noProxyBean = (PrototypeBeanWithoutProxy) BeanLocator
				.getBean("prototypeBeanWithoutProxy");
	}

	public String sayHello() {

		StringBuffer msg = new StringBuffer();

		msg.append("Proxy :: ");
		msg.append(proxyBean.getRandomNum() +"\n");
		msg.append("NoProxy :: ");
		msg.append(noProxyBean.getRandomNum() +"\n");

		return msg.toString();
	}

}
