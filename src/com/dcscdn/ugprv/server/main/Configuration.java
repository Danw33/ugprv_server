/**
 * 
 */
package com.dcscdn.ugprv.server.main;

import java.util.HashMap;

import com.dcscdn.ugprv.lib.engine.ConfigurationEngine;
import com.dcscdn.ugprv.lib.system.Trace;

/**
 * @author Daniel Wilson
 *
 */
public final class Configuration extends ConfigurationEngine {

	/**
	 * 
	 */
	public Configuration() {
		// TODO Auto-generated constructor stub
		super();
	}

	

	/**
	 * Enumerated Section Headers
	 * @author Daniel Wilson
	 */
	protected enum Section
	{
		SERVER		("server"),
		DATABASE	("database"),
		LOG			("log"),
		;
		
		Section( String Identifier ) { }
	}
	
	/**
	 * Enumerated Parameters and the related methods for getting and setting parameters.
	 * @author Daniel Wilson
	 */
	protected enum Parameter
	{
		SERVER_MODE				(Section.SERVER, "server-mode"),	//Server Mode (0/1/2)
		SERVER_ID				(Section.SERVER, "server-id"),		//Server ID (For mode 0 and 1 servers)
		
		DATABASE_USERNAME		(Section.DATABASE, "db-username"),	//Database Username
		DATABASE_PASSWORD		(Section.DATABASE, "db-password"),	//Database Password
		DATABASE_DRIVER			(Section.DATABASE, "db-driver"),		//Database Driver
		DATABASE_JDBCURI		(Section.DATABASE, "db-jdbcuri"),		//Database JDBC Connection URI
		
		PRETTY_LOGGING			(Section.LOG, "log-pretty"),		//Pretty Logging Support (True/False)
		;
		 
		Parameter( Section Section, String Identifier ){}
		
		//=========
		// Getters
		//=========
		
		/**
		 * Get the value of of a configuration parameter.
		 * @param Parameter The configuration parameter from which to fetch the value
		 * @return The value of the specified parameter as a string
		 */
		public static String getValueAsString(Parameter Parameter)
		{
			return getValue( Parameter ).toString();
		}
		
		/**
		 * Get the value of of a configuration parameter.
		 * @param Parameter 
		 * @return The value of the specified parameter as a integer
		 */
		public static int getValueAsInt(Parameter Parameter)
		{
			return Integer.parseInt( getValue( Parameter ) );
		}
		
		/**
		 * Get the value of of a configuration parameter.
		 * @param Parameter 
		 * @return The value of the specified parameter as a long integer
		 */
		public static long getValueAsLong(Parameter Parameter)
		{
			return Long.parseLong( getValue( Parameter ) );
		}
		
		/**
		 * Get the value of of a configuration parameter.
		 * @param Parameter 
		 * @return The value of the specified parameter as a boolean
		 */
		public static boolean getValueAsBoolean(Parameter Parameter)
		{
			return false;
		}

		/**
		 * Get a value for a given parameter from the loaded configuration set.
		 * @param Parameter
		 * @return Value
		 */
		private static String getValue(Parameter Parameter)
		{
			try
			{
				return Loaded.get( Parameter ).toString();
			}
			catch ( Exception e )
			{
				Trace.Warn( e , "A call to Configuration.Parameter->getValue(" + Parameter.toString() + ") resulted in the following error: " + e.getMessage() );
				return "";
			}
		}
	}

	//private static HashMap<Parameter, String> Loaded = new HashMap<Parameter, String>();

}
