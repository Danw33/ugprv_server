/**
 * 
 */
package com.dcscdn.ugprv.server.protocols;

import com.dcscdn.ugprv.lib.engine.SocketEngine.SEProtocol;

/**
 * Mode 1 Protocol (Server) for use with the UGPRV Socket Engine. 
 * Implements the SocketEngine Protocol from the UGPRV Library.
 * @see SEProtocol
 * @author Daniel Wilson
 */
public class SEServerProtocol implements SEProtocol {

	/**
	 * Protocol Constructor
	 * @author Daniel Wilson
	 */
	public SEServerProtocol()
	{
		super();
	}

	/**
	 * Receives Input Data and Processes, Returning Output Data to send to the connected client.
	 * @param InputData
	 * @return Output Data to send back to the connected client.
	 * @author Daniel Wilson
	 */
	public String ProcessInput(String InputData)
	{
		//Here, the dummy function simply returns the same data it recieved!
		//Great for testing, although this should be overriden by an extending SEProtocol Class!
		return InputData;
	}
}
