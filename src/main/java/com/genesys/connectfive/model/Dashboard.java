/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 * Genesys Assessment 
 * App: Connect Five
 * Candidate: Weverton de Souza Castanho
 * Date: March 29th, 2020
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 */

package com.genesys.connectfive.model;

public class Dashboard {

	private  int id;

	private String content;

	public Dashboard() {
	}

	public Dashboard(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
