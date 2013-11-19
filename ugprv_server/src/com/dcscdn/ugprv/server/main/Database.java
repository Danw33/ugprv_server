/**
 * 
 */
package com.dcscdn.ugprv.server.main;

import com.dcscdn.ugprv.lib.engine.DatabaseEngine;

/**
 * @author Daniel Wilson
 *
 */
public class Database {

	/**
	 * 
	 */
	public Database() {
		super();
	}
	
	/**
	 * Database Connection Object
	 * @author Daniel Wilson
	 * @see DatabaseEngine
	 */
	public static DatabaseEngine Engine = new DatabaseEngine();
}
