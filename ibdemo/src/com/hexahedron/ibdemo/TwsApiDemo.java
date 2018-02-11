package com.hexahedron.ibdemo;

import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;

public class TwsApiDemo {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionManager twsConnect = new ConnectionManager();
		twsConnect.connect();

	}

}
