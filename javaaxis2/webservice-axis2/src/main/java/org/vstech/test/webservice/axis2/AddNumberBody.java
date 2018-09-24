package org.vstech.test.webservice.axis2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class AddNumberBody extends WebserviceTest1Skeleton implements WebserviceTest1SkeletonInterface, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(AddNumberBody.class);
	
	 public int addNumbers(int arg0, int arg1) throws AddNumbersFault {
		 return arg0+arg1;
	 }

	@Override
	public void afterPropertiesSet() throws Exception {
	}
	 
}
