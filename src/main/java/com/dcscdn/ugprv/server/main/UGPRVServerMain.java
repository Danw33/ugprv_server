/**
 * 
 */
package com.dcscdn.ugprv.server.main;

import java.sql.SQLException;

import com.dcscdn.ugprv.lib.engine.DatabaseEngine;
import com.dcscdn.ugprv.lib.engine.DatabaseEngine.DriverClass;
import com.dcscdn.ugprv.lib.system.Trace;
import com.dcscdn.ugprv.lib.system.UncaughtExceptionHandler;
import com.dcscdn.ugprv.server.main.Configuration.Parameter;
import com.dcscdn.ugprv.server.main.Database;

/**
 * ATC Server Main Class
 * Invoked by running the Jar, at the heart of the ATC Server System!
 * 
 * @author Daniel Wilson
 */
public class UGPRVServerMain {
	/**
	 * Default Uncaught Exception Handler.
	 */
	public static final UncaughtExceptionHandler UAH = new UncaughtExceptionHandler();
	
	/**
	 * Constructor stub.
	 * @author Daniel Wilson
	 */
	public UGPRVServerMain() { super(); }

	/**
	 * Main Method for starting a new instance of the UGPRV Server Software.
	 * @author Daniel Wilson
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		//Add Support for Uncaught Exception Handling
		Thread.setDefaultUncaughtExceptionHandler(UAH);
		
		//Fire off an initial log message so we dont appear broken if startup processes take a while
		Trace.Info( "UGPRV Server Starting..." );
		
		//Parse any given launch parameters
		//TODO
		Trace.Debug( "  > Parsing Launch Arguments" );
		String ConfigurationFilePath = new String("server.conf");
		
		//Load up the server configuration from file
		Trace.Debug( "  > Loading Configuration" );
		Configuration.LoadConfiguration( ConfigurationFilePath );
		
		//Start the Database Engine and Prepare the Connection Pool
		Trace.Debug( "  > Starting Database Engine" );
		Database.Engine = new DatabaseEngine(DriverClass.MYSQL, 
				 Configuration.Parameter.getValueAsString( Parameter.DATABASE_USERNAME ), //Username from Config
				 Configuration.Parameter.getValueAsString( Parameter.DATABASE_PASSWORD ), //Password from Config
				 Configuration.Parameter.getValueAsString( Parameter.DATABASE_JDBCURI )); //JDBC URI from Config
		
		//Connect to the Database
		Trace.Debug( "  > Connecting to the Primary Database" );
		try {
			Database.Engine.DataSource.getConnection();
		} catch (SQLException e) {
			Trace.Error( e, "Failed to establish primary database connection! Error: " + e.getMessage() );
		}
		
		//Load up additional server configuration for the database
		//TODO
		Trace.Debug( "  > Loading Additional Configuration" );

		//Prepare the Socket Engine for Use
		//TODO
		Trace.Debug( "  > Starting Socket Engine" );
		
		int datagramPort = Configuration.Parameter.getValueAsInt( Parameter.SOCKET_DATAGRAM );
		Trace.Debug( "  > Attaching Datagram Listener to socket (UDP Port Number " + datagramPort  + ")" );
		
		//Prepare the Routing Engine for Use
		//TODO
		Trace.Debug( "  > Starting Routing Engine" );
		
		//And At last, the server is considered "started"
		//int ServerMode = Configuration.Parameter.getValueAsInt( Parameter.SERVER_MODE );
		Trace.Info( "UGPRV Server Started!" ); //TODO: Add a "Started in X Seconds" bit
				
		//Perform Role-Specific Loading Sequence (post-startup)
		Trace.Debug( "  > Firing Post-Startup Method (Role-Specific Init)" );
		InitServer();
		
		
		while( true ){}
	}

	/**
	 * Performs a Post-Startup Initialisation of the Server. 
	 * This is used to configure and start role-specific functionality, based on the desired server mode.
	 * @author Daniel Wilson
	 */
	private static void InitServer( )
	{
		int ServerMode = Configuration.Parameter.getValueAsInt( Parameter.SERVER_MODE );
		if ( ServerMode == 0 ) //0 = Flight Management Core (ATC Server)
		{
			Trace.Info( "Server is running as a Flight Management Core Server!" );
		}
		else if ( ServerMode == 1 ) //1 = Relay Node (Repeater Server)
		{
			Trace.Info( "Server is running as a Relay Node Server!" );
		}
		else if ( ServerMode == 2 ) //2 = Flight Control Mode (In-Air Computer)
		{
			Trace.Info( "Server is running as a Flight Control Server!" );
		}
		else 
		{
			String ServerModeString = Configuration.Parameter.getValueAsString( Parameter.SERVER_MODE );
			Trace.Fatal( "An invalid server mode was specified! The value recieved was '" + ServerModeString + "'." );
			Trace.Fatal( "The server will now shut down!" );
			StopServer( 1 );
		}
	}
	
	/**
	 * Gracefully Shuts down the server, releasing connections and calling System.Exit.
	 * </ br>
	 * As no error level is passed, the function will presume an Exit Level of 0.
	 * @author Daniel Wilson
	 */
	public static void StopServer( )
	{
		StopServer( 0 );
	}
	
	/**
	 * @param ExitLevel
	 * @author Daniel Wilson
	 */
	public static void StopServer( int ExitLevel )
	{
		Database.Engine.Destruct();
		System.exit( ExitLevel ); 
	}
}
