package com.abhinav.mavenexample.app.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.log4j.Logger;


public class BeanLocator {
    private static final String APP_CONTEXT_FILE = "applicationContext.xml";
    
    private static final String CLASS_NAME = BeanLocator.class.getName();
	private static final Logger logger = Logger.getLogger(CLASS_NAME);

    private static class ApplicationcontextHolder {
        // Leverage Java Language Specification guarantee (static initializers
        // are run only once) to create a unique instance.
        public static ApplicationContext applicationContext = createApplicationcontext();

        private static ApplicationContext createApplicationcontext() {
            try {
                final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{APP_CONTEXT_FILE});
                return applicationContext;
            } catch (final Exception e) {
                throw new RuntimeException(serializeStackTrace(e));
            }
        }

        /**
         * Utility method for easier debugging
         * 
         * @param e
         * @return
         */
        private static String serializeStackTrace(final Exception e) {
            final StringBuffer buffer = new StringBuffer(e.getMessage() + "\n");
            final StackTraceElement[] trace = e.getStackTrace();
            for (int i = 0; trace != null && i != trace.length; i++) {
                final StackTraceElement current = trace[i];
                buffer.append(current.getClassName() + ":" + current.getMethodName() + ":" + current.getLineNumber()
                        + "\n");
            }
            return buffer.toString();
        }
    }

    // private constructor
    private BeanLocator() {

    }

    public static Object getBean(final String name) {
    	logger.info("******** Loading Bean from applicationContext : "+name);
        return ApplicationcontextHolder.applicationContext.getBean(name);
    }
}
