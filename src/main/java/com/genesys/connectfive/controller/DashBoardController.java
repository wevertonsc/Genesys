/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 * Genesys Assessment 
 * App: Connect Five
 * Candidate: Weverton de Souza Castanho
 * Date: March 29th, 2020
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 */

package com.genesys.connectfive.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.genesys.connectfive.engine.ConnectFiveEngine;
import com.genesys.connectfive.model.Dashboard;
import com.genesys.connectfive.model.Message;

@Controller
public class DashBoardController {

	// creates boolean to determine status of game
	boolean flag = true;

	@MessageMapping("/hello")
	@SendTo("/topic/connectfive")
	public Dashboard greeting(Message message) throws Exception {
		String msg = "";

		if (message.getName().isEmpty())
			return new Dashboard("Name invalid!");

		if (!ConnectFiveEngine.boardCreated) {
			msg = message.getName() + " is the player 1";
			ConnectFiveEngine.CreateBoard();
			ConnectFiveEngine.player1 = message.getName();
		} else {
			msg = message.getName() + " is the player 2";
			ConnectFiveEngine.player2 = message.getName();
		}
		return new Dashboard(
				"Hello " + HtmlUtils.htmlEscape(message.getName()) + "<br>" + ConnectFiveEngine.PrintBoard() + msg);

	}

	@MessageMapping("/play")
	@SendTo("/topic/connectfive")
	public Dashboard play(Message message) throws Exception {

		String board = "";

		if (ConnectFiveEngine.player1.equals(message.getName())) {
			if (ConnectFiveEngine.semaphoro == 1) {
				if (!ConnectFiveEngine.CheckX()) {
					flag = false; // sets flag to false so loop is not repeated if player 1 won
					board = "Player " + ConnectFiveEngine.player2 + "  wins!" + "<br>" + ConnectFiveEngine.PrintBoard();
					return new Dashboard(board);
				}
				ConnectFiveEngine.DropO(message.getPosition());
				ConnectFiveEngine.semaphoro = 0;
				// determines if player 1 has won

			} else {
				board = "Wrong player " + "<br>" + ConnectFiveEngine.PrintBoard() + "<br>" + "Turn player 2!";
				return new Dashboard(board);
			}

		} else {
			if (ConnectFiveEngine.semaphoro == 0) {
				if (!ConnectFiveEngine.CheckO()) {
					flag = false; // sets flag to false so loop is not repeated if player 1 won
					board = "Player " + ConnectFiveEngine.player1 + "  wins!" + "<br>" + ConnectFiveEngine.PrintBoard();
					return new Dashboard(board);
				}
				ConnectFiveEngine.DropX(message.getPosition());
				ConnectFiveEngine.semaphoro = 1;

			} else {
				board = "Wrong player " + "<br>" + ConnectFiveEngine.PrintBoard() + "<br>" + "Turn player 1!";
				return new Dashboard(board);
			}
		}

		board = ConnectFiveEngine.PrintBoard() + "<br>" + ConnectFiveEngine.message;

		return new Dashboard(board);

	}

	@MessageMapping("/disconnect")
	@SendTo("/topic/connectfive")
	public Dashboard disconnect(Message message) throws Exception {
		String board = "";
		if (ConnectFiveEngine.player1.equals(message.getName())) {
			if (ConnectFiveEngine.semaphoro == 1) {
				board = "Player " + ConnectFiveEngine.player2 + "  wins!" + "<br>" + ConnectFiveEngine.PrintBoard();
				ConnectFiveEngine.end();
				return new Dashboard(board);
			}

		} else {
			//ConnectFiveEngine.end();
			board = "Player " + ConnectFiveEngine.player1 + "  wins!" + "<br>" + ConnectFiveEngine.PrintBoard();
			ConnectFiveEngine.end();
			ConnectFiveEngine.boardCreated = false;
			return new Dashboard(board);
		}
		return null;
	}
	
}
