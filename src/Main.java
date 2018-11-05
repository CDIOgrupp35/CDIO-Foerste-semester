import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.FileReader;
import java.util.Scanner;

public class Main{
    static boolean play = true;
    public static void main(String[] args) {

        /*

        Welcoming players to the game.
       Defining the players and dice.
       */

        try{JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("./test.json"));
            JSONObject jason = (JSONObject) obj;


        System.out.println((String) jason.get("intro"));
        Scanner scan = new Scanner(System.in);
        System.out.println("Player 1 please enter your name");
        Player player1 = new Player(scan.next());
        System.out.println("Player 2 please enter your name");
        Player player2 = new Player(scan.next());
        Game game = new Game(player1, player2);

        System.out.println("To play, press 1 and enter");
        System.out.println("\n " + player1.toString() + " starts!");

        player1.setTurn(true);

        /** Defining that both players can't play at once. **/

        while (play) {
            int input = scan.nextInt();
            if (input == 1) {
                if(player1.getIsTurn())
                game.playTurn(player1, player2);
                else
                    game.playTurn(player2,player1);
                /** Stating each player's points and when one player has reached the highest count. **/

                showScore(player1, player2);
                showWinner(player1, player2);
                showTurn(player1, player2);

            if(input == 1)
                System.out.println(player1.getIsTurn()+ " " +player2.getIsTurn() );

            }
        }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException f){
            f.printStackTrace();
        }
    }

    public static void showScore(Player player1, Player player2){
            System.out.println("Player 1: " + player1.toString()+ " has "+ player1.getScore() +" points");
            System.out.println("Player 2: " + player2.toString()+ " has "+ player2.getScore() +" points");
    }
    public static void showWinner(Player player1, Player player2){
        if(player1.winGame()) {
            System.out.print(player1.toString() + " won the game");
            play = false;
        }
        else if(player2.winGame()) {
            System.out.print(player2.toString() + " won the game");
            play = false;
        }
    }

        /** Stating which player is next. **/

    public static void showTurn(Player player1, Player player2){
        if (play) {
            if (player1.getIsTurn()) {
                System.out.println("\n " + player1.toString() + ", you're next!");
            } else if (player2.getIsTurn()) {
                System.out.println("\n " + player2.toString() + ", you're next!");
            }
        }
    }
}