package com.hexahedron.ibdemo;

import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;

public class ConnectionManager {
	
	private EReaderSignal ers;
	private EClientSocket ecs;
	protected int currentOrderId = -1;
	
	protected void connect()
	{
		initializeClientSocket();
		initializeEReader();
	}
	
	private void initializeClientSocket()
	{
		ers = new EJavaSignal();
		ecs = new EClientSocket(new TwsEWrapper(), ers); 
		ecs.eConnect("127.0.0.1", 7497, 0);
	}
	private void initializeEReader()
	{
		final EReader reader = new EReader(ecs, ers);   
        
        reader.start();
        //An additional thread is created in this program design to empty the messaging queue
        new Thread(() -> {
            while (ecs.isConnected()) {
                ers.waitForSignal();
                System.out.println("Successfully connected to the tws api.");
                try {
                    reader.processMsgs();
                } catch (Exception e) {
                    System.out.println("Exception: "+e.getMessage());
                }
            }
        }).start();
	}

}
