package org.vstech.test.webservice.axis2;

public class AddNumberBody extends AddNumbersServiceSkeleton implements AddNumbersServiceSkeletonInterface {
	
	 public int addNumbers(int arg0, int arg1) throws AddNumbersFault {
		 return arg0+arg1;
	 }
	 
}
