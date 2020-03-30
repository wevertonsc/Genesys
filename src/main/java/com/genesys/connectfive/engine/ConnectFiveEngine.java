/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 * Genesys Assessment 
 * App: Connect Five
 * Candidate: Weverton de Souza Castanho
 * Date: March 29th, 2020
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 */

package com.genesys.connectfive.engine;

/**
*
* @author weverton.castanho
*/
public class ConnectFiveEngine {
     // global variables
   final static int WIDTH = 6;
   final static int HEIGHT = 9;
   final static int BOTTOM_ROW = WIDTH - 1;

   // game board
   static char[][] board = new char[WIDTH][HEIGHT];
   public static String message;
   public static boolean boardCreated = false;
   public static String player1 = null;
   public static String player2 = null;
   public static int semaphoro = 1;
   
   public static void CreateBoard() {
       // fills board with ' ' for the width and height
       for (int w = 0; WIDTH > w; w += 1) {
           for (int h = 0; HEIGHT > h; h += 1) {
               board[w][h] = ' ';
           }
       }
       boardCreated = true;
   }

   public static String PrintBoard() {
       String result = "";
       // prints the board
       for (int w = 0; WIDTH > w; w += 1) {
           for (int h = 0; HEIGHT > h; h += 1) {
               result += "[" + board[w][h] + "]";
           }
           result += "<br>";
       }
       result += "<br>";
       return result;
   }

   public static void DropX(int column) {
       // creates a counter
       int counter = 1;

       // shows whos turn
       message = "Player 1 turn";

       while (true) {
           if (column > WIDTH) {
               message = "That's not a valid column";
               break;
           }

           if (board[BOTTOM_ROW][column] == ' ') { // checks to see if space is blank, puts X there if it is
               board[BOTTOM_ROW][column] = 'X';
               break; // breaks loop after placing
           } else if (board[BOTTOM_ROW][column] == 'X' || board[BOTTOM_ROW][column] == 'O') { // if space isn't blank,
               // checks to see if one
               // above is
               if (board[BOTTOM_ROW - counter][column] == ' ') { // puts X if blank
                   board[BOTTOM_ROW - counter][column] = 'X';
                   break; // breaks loop after placing
               }
           }
           counter += 1; // adds one to counter if the space wasn't blank, then loops again
           if (counter == WIDTH) { // checks to see if at end of column
               System.out.println("That column is full");
               break;
           }
       }
   }

   public static void DropO(int column) {
       // creates a counter
       int counter = 1;
       
       message = "";

       // shows whos turn
       message = "Player 2 turn";

       while (true) {
           if (column > WIDTH) {
               message = "That's not a valid column";
               break;
           }

           if (board[BOTTOM_ROW][column] == ' ') { // checks to see if space is blank, puts O there if it is
               board[BOTTOM_ROW][column] = 'O';
               break; // breaks loop after placing
           } else if (board[BOTTOM_ROW][column] == 'X' || board[BOTTOM_ROW][column] == 'O') { // if space isn't blank,
               // checks to see if one
               // above is
               if (board[BOTTOM_ROW - counter][column] == ' ') { // puts O if blank
                   board[BOTTOM_ROW - counter][column] = 'O';
                   break; // breaks loop after placing
               }
           }
           counter += 1; // adds one to counter if the space wasn't blank, then loops again
           if (counter == WIDTH) { // checks to see if at end of column
               message = "That column is full"; 
               break;
           }
       }
   }

   public static boolean CheckXHorizontal() {
       // creates boolean to act as flag
       boolean flag = true;
       
       message= "";

       // creates counter
       int counter = 0;
       while (flag) {

           // goes through board horizontally
           for (int w = 0; WIDTH > w; w += 1) {
               for (int h = 0; HEIGHT > h; h += 1) {
                   if (board[w][h] == 'X') { // if it finds an X, add 1 to counter
                       counter += 1;
                   } else {
                       counter = 0; // if next piece is not an X, set counter to 0
                   }
                   if (counter >= 5) {
                       message = "Player 1 wins"; // if counter is greater or equal to 5, player wins
                       flag = false;
                   }
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckXVertical() {
       // creates boolean to act as flag
       boolean flag = true;
      
       message = "";
       
       // creates counter
       int counter = 0;
       while (flag) {

           // goes through board vertically
           for (int h = 0; HEIGHT > h; h += 1) {
               for (int w = 0; WIDTH > w; w += 1) {
                   if (board[w][h] == 'X') { // if it finds an X, add 1 to counter
                       counter += 1;
                   } else {
                       counter = 0; // if next piece is not an X, set counter to 0
                   }
                   if (counter >= 5) {
                       message = "Player 1 wins"; // if counter is greater or equal to 5, player wins
                       flag = false;
                   }
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckOVertical() {
       // creates boolean to act as flag
       boolean flag = true;

       message = "";
       
       // creates counter
       int counter = 0;
       while (flag) {

           // goes through board vertically
           for (int h = 0; HEIGHT > h; h += 1) {
               for (int w = 0; WIDTH > w; w += 1) {
                   if (board[w][h] == 'O') { // if it finds an O, add 1 to counter
                       counter += 1;
                   } else {
                       counter = 0; // if next piece is not an O, set counter to 0
                   }
                   if (counter >= 5) {
                       message = "Player 2 wins"; // if counter is greater or equal to 5, player wins
                       flag = false;
                   }
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckOHorizontal() {
       // creates boolean to act as flag
       boolean flag = true;
       
       message= ""; 
       
       // creates counter
       int counter = 0;
       while (flag) {

           // goes through board vertically
           for (int w = 0; WIDTH > w; w += 1) {
               for (int h = 0; HEIGHT > h; h += 1) {
                   if (board[w][h] == 'O') { // if it finds an O, add 1 to counter
                       counter += 1;
                   } else {
                       counter = 0; // if next piece is not an O, set counter to 0
                   }
                   if (counter >= 5) {
                       message = "Player 2 wins"; // if counter is greater or equal to 5, player wins
                       flag = false;
                   }
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckXDiagonalForward() {
       // flag
       boolean flag = true;

       message = "";
       
       // counter
       int counter = 0;

       // check boolean
       Boolean check = false;

       // checkers
       int checkColumn = 1;
       int checkRow = 1;

       while (flag) { // goes through until an X is found
           
           for (int w = 0; WIDTH > w; w += 1) {
               for (int h = 0; HEIGHT > h; h += 1) {
                   if (board[w][h] == 'X') { // if X is found, add one to counter and go into loop
                       counter += 1;
                       check = true;
                       while (check) { // goes through diagonally looking for Xs
                           if (checkColumn + w <= WIDTH - 1 && checkRow + h <= HEIGHT - 1) {
                               if (board[w + checkColumn][h + checkRow] == 'X') { // if X is found, add 1 to counter
                                   counter += 1;
                               }
                           }

                           // adds 1 to checkers
                           checkColumn += 1;
                           checkRow += 1;

                           if (checkColumn == WIDTH - 1 || checkRow == HEIGHT - 1) { // if outside of board, break
                               check = false;
                               break;
                           }

                           if (counter >= 5) {
                               message = "Player 1 wins"; // if counter is greater or equal to 5, player wins
                               check = false;
                               flag = false;
                               break;
                           }
                       }
                   }
                   if (counter >= 5) {
                       flag = false;
                       break;
                   }

                   // resets counter and checkers
                   counter = 0;
                   checkColumn = 1;
                   checkRow = 1;
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckODiagonalForward() {
       // flag
       boolean flag = true;
       
       message = "";
       
       // counter
       int counter = 0;

       // check boolean
       Boolean check = false;

       // checkers
       int checkColumn = 1;
       int checkRow = 1;

       while (flag) { // goes through until an O is found
           for (int w = 0; WIDTH > w; w += 1) {
               for (int h = 0; HEIGHT > h; h += 1) {
                   if (board[w][h] == 'O') { // if O is found, add one to counter and go into loop
                       counter += 1;
                       check = true;
                       while (check) { // goes through diagonally looking for Os
                           if (checkColumn + w <= WIDTH - 1 && checkRow + h <= HEIGHT - 1) {
                               if (board[w + checkColumn][h + checkRow] == 'O') { // if O is found, add 1 to counter
                                   counter += 1;
                               }
                           }

                           // adds 1 to checkers
                           checkColumn += 1;
                           checkRow += 1;

                           if (checkColumn == WIDTH - 1 || checkRow == HEIGHT - 1) { // if outside of board, break
                               check = false;
                               break;
                           }

                           if (counter >= 5) {
                               message = "Player 2 wins"; // if counter is greater or equal to 5, player wins
                               check = false;
                               flag = false;
                               break;
                           }
                       }
                   }
                   if (counter >= 5) {
                       flag = false;
                       break;
                   }

                   // resets counter and checkers
                   counter = 0;
                   checkColumn = 1;
                   checkRow = 1;
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckXDiagonalBack() {
       // flag
       boolean flag = true;
       
       message = "";

       // counter
       int counter = 0;

       // check boolean
       Boolean check = false;

       // checkers
       int checkColumn = 1;
       int checkRow = 1;

       while (flag) { // goes through until an X is found
           for (int w = 0; WIDTH > w; w += 1) {
               for (int h = 0; HEIGHT > h; h += 1) {
                   if (board[w][h] == 'X') { // if X is found, add one to counter and go into loop
                       counter += 1;
                       check = true;
                       while (check) { // goes through diagonally looking for Xs
                           if (w - checkColumn >= 0 && h - checkRow >= 0) {
                               if (board[w - checkColumn][h - checkRow] == 'X') {
                                   counter += 1; // if X is found, add 1 to counter
                               }
                           }

                           // adds 1 to checkers
                           checkColumn += 1;
                           checkRow += 1;

                           if (checkColumn == 0 || checkRow == HEIGHT - 1) { // if outside of board, break
                               check = false;
                               break;
                           }

                           if (counter >= 5) {
                               message = "Player 1 wins"; // if counter is greater or equal to 5, player wins
                               check = false;
                               flag = false;
                               break;
                           }
                       }
                   }
                   if (counter >= 5) {
                       flag = false;
                       break;
                   }

                   // resets counter and checkers
                   counter = 0;
                   checkColumn = 1;
                   checkRow = 1;
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckODiagonalBack() {
       // flag
       boolean flag = true;
       
       message = "";

       // counter
       int counter = 0;

       // check boolean
       Boolean check = false;

       // checkers
       int checkColumn = 1;
       int checkRow = 1;

       while (flag) {

           // goes through until an O is found
           for (int w = 0; WIDTH > w; w += 1) {
               for (int h = 0; HEIGHT > h; h += 1) {
                   if (board[w][h] == 'O') { // if O is found, add one to counter and go into loop
                       counter += 1;
                       check = true;
                       while (check) { // goes through diagonally looking for Os
                           if (w - checkColumn >= 0 && h - checkRow >= 0) {
                               if (board[w - checkColumn][h - checkRow] == 'O') {
                                   counter += 1; // if O is found, add 1 to counter
                               }
                           }

                           // adds 1 to checkers
                           checkColumn += 1;
                           checkRow += 1;

                           if (checkColumn == 0 || checkRow == HEIGHT - 1) { // if outside of board, break
                               check = false;
                               break;
                           }

                           if (counter >= 5) {
                               message = "Player 2 wins"; // if counter is greater or equal to 5, player wins
                               check = false;
                               flag = false;
                               break;
                           }
                       }
                   }
                   if (counter >= 5) {
                       flag = false;
                       break;
                   }

                   // resets counter and checkers
                   counter = 0;
                   checkColumn = 1;
                   checkRow = 1;
               }
           }
           break;
       }
       return flag;
   }

   public static boolean CheckX() {
       // creates flag
       boolean flag = true;

       // checks all Xs at once, for cleaner main loop
       if (!CheckXVertical() || !CheckXHorizontal() || !CheckXDiagonalBack() || !CheckXDiagonalForward()) {
           flag = false;
       }
       return flag;
   }

   public static boolean CheckO() {
       // creates flag
       boolean flag = true;

       // checks all Os at once, for cleaner main loop
       if (!CheckOVertical() || !CheckOHorizontal() || !CheckODiagonalBack() || !CheckODiagonalForward()) {
           flag = false;
       }
       return flag;
   }
   public static boolean end() {
	    board = new char[WIDTH][HEIGHT];
	    ConnectFiveEngine.boardCreated = false;
		ConnectFiveEngine.message = "";
		ConnectFiveEngine.player1 = null;
		ConnectFiveEngine.player2 = null;
		ConnectFiveEngine.semaphoro = 1;
		ConnectFiveEngine.CreateBoard();
		ConnectFiveEngine.PrintBoard();
		
		return true;
   }
   
}
