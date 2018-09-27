package org.vstech.test.axis2.client;

import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.description.MessageContextListener;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.wsdl.WSDLConstants;

public class WebServiceClient {

	
	public void run ()  {
		WebserviceTest1Stub stub;
		try {
			stub = getStub();
			
			int result = stub.addNumbers(2, 3);
			
			System.out.println("Result of webservice is"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("SUDDENLY EXCEPTION???!!!");
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		WebServiceClient ws = new WebServiceClient();
		ws.run();
	}
	
	public WebserviceTest1Stub getStub () throws Exception {
		WebserviceTest1Stub stub = new WebserviceTest1Stub("http://localhost/test-axis2/services/WebserviceTest1");
		
		ServiceClient serviceClient = stub._getServiceClient();
		
		Options options = serviceClient.getOptions();
		
		//Setting the chuked request
		options.setProperty(HTTPConstants.CHUNKED, true);
		
		//Adding logging information 
		serviceClient.getAxisService().addMessageContextListener(new MessageContextListener() {
			
			@Override
			public void attachServiceContextEvent(ServiceContext sc, MessageContext mc) {
			}
			
			@Override
			public void attachEnvelopeEvent(MessageContext mc) {
				try {
					MessageContext out = mc.getOperationContext().getMessageContext(WSDLConstants.MESSAGE_LABEL_OUT_VALUE);
					
					if (out != null && out.getEnvelope() != null) {
						System.out.println(out.getCurrentPhaseIndex() + ":" + out.getFLOW() + ":" + out.outputWritten);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					MessageContext in = mc.getOperationContext().getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
					
					if(in != null && in.getEnvelope() != null) {
						System.out.println(in.getCurrentPhaseIndex() + ":" + in.getFLOW() + ":" + in.outputWritten);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		return stub;
	}
	
}
