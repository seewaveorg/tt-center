package com.ngs.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringServiceLocator implements ApplicationContextAware {

	private static SpringServiceLocator svcLoc = null;
   
	private ApplicationContext applicationContext;

	/**
	 * i am expecting to use this class to access objects currently running in spring bean factory
	 * 
	 * but most of time it gives null due to objects are already gone or not initiated.
	 * 
	 * But i hope i will be find some solutions by chanign the bean scope
	 * 
	 */
	public SpringServiceLocator() {
		super();
		//setupAppContext();
		// TODO Auto-generated constructor stub
	}
	
   public Object getBean(String beanRefName, Class beanClass) {

	BeanFactory factory = (BeanFactory) applicationContext ;
	Object object = factory.getBean(beanRefName, beanClass);
	return object;
    }


	@Override
	public void setApplicationContext(ApplicationContext context)	throws BeansException {
		  applicationContext = context;
		
	}

}
