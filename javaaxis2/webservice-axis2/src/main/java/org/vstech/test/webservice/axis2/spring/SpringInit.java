package org.vstech.test.webservice.axis2.spring;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInit implements ServiceLifeCycle {

	@Override
	public void startUp(ConfigurationContext configctx, AxisService service) {
		try {
			System.out.println("inside webservice-axis2 spinginit");
			// GET THE SERVICE CLASSLOADER
			ClassLoader classLoader = service.getClassLoader();

			// NOT SURE WHY THIS NEEDS TO HAPPEN BUT IT DOES
			Thread.currentThread().setContextClassLoader(classLoader);

			// LOAD THE SPRING CONFIG AND UPDATE THE CLASSLOADER

			System.out.println("Starting spring init");
			ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(
					new String[] { "spring-config-main.xml" }, false);
			appCtx.setClassLoader(classLoader);
			appCtx.refresh();
			System.out.println("spring loaded");

			// String location = "spring-config-main.xml";
			//
			// GenericApplicationContext applicationContext = new
			// GenericApplicationContext();
			// applicationContext.setClassLoader(classLoader);
			//
			//
			// XmlBeanDefinitionReader xmlReader = new
			// XmlBeanDefinitionReader(applicationContext);
			// xmlReader.loadBeanDefinitions(new ClassPathResource(location));
			//
			// applicationContext.refresh();
			//
			// service.addParameter(ApplicationContextUtil.SPRING_APPLICATION_CONTEXT,
			// ApplicationContextHolder.getContext());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutDown(ConfigurationContext configctx, AxisService service) {

	}

}
