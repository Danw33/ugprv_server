/**
 * 
 */
package com.dcscdn.ugprv.server.protocols;

import com.dcscdn.ugprv.lib.engine.SocketEngine.SEProtocol;

/**
 * Mode 3 Protocol (Client) for use with the UGPRV Socket Engine.
 * @author Daniel Wilson
 */
public class SEClientProtocol extends SEProtocol {

	public SEClientProtocol()
	{
		super();
	}
	
	/**
	 * Receives Input Data and Processes, Returning Output Data to send to the server or relay.
	 * @param InputData
	 * @return Output Data to send back to the server.
	 * @author Daniel Wilson
	 */
	public String ProcessInput(String InputData)
	{
		//Here, the dummy function simply returns the same data it recieved!
		//Great for testing, although this should be overriden by an extending SEProtocol Class!
		return InputData;
	}
}
