/* Name : Ahmed Hanno
Project Battleships
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Battleship {
    public static final Scanner input = new Scanner(System.in);
    public static final Random rand = new Random();

    public static void printMap(String [][] map) {
        System.out.println("");
        System.out.println("   0123456789");
        for(int i = 0; i < map.length; i++) {
            System.out.print(i + " |");
            for(int j = 0; j < map[0].length; j++) {
                String temp = map[i][j];
                System.out.print(temp);
            }
            System.out.println("| " + i);
        }
        System.out.println("   0123456789");
        System.out.println("");
    }

    public static void main(String[] args) {
        String[][] playerMap = new String[10][10];
        String[][] computerMap = new String[10][10];
        for( int i = 0; i < playerMap.length; i++) {
            for (int j = 0; j < playerMap[0].length; j++) {
                playerMap[i][j] = " ";
                computerMap[i][j] = " ";
            }
        }
        System.out.println("Welcome to BATTLESHIPS");
        printMap(playerMap);
        System.out.println("Now you need to deploy your ships using the coordinates on the map");
        deployPlayer(playerMap);
        printMap(playerMap);
        deployComputer(computerMap,playerMap);
        doBattle(computerMap,playerMap);
    }

    public static void deployPlayer (String [][] map) {
        int x;  // x coordinate
        int y;  // y coordinate

        int i = 1;
        while(i <= 5) {
            System.out.print("Enter X coordinate of your " + i + " ship :");
            x = input.nextInt();
            while ( x < 0 || x > 9) {
                System.out.println("X coordinate not in the map!!!!");
                System.out.print("Enter X coordinate of your " + i + " ship :");
                x = input.nextInt();
            }
            System.out.print("Enter Y coordinate of your " + i + " ship :");
            y = input.nextInt();
            while ( y < 0 || y > 9) {
                System.out.println("y coordinate not in the map!!!!");
                System.out.print("Enter y coordinate of your " + i + " ship :");
                y = input.nextInt();
            }
            if(map[x][y] == " ") {
                map[x][y] = "@";
                i++;
            } else if(map[x][y] == "@"){
                System.out.println("A shipe is already deployed in this position. Please pick another location!");
                continue;
            }
        }

    }

    public static void deployComputer(String[][] computermap, String[][] playermap) {
        int x;  // x coordinate
        int y;  // y coordinate

        int i = 1;
        while(i <= 5) {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            if(computermap[x][y].equals("@") || playermap[x][y].equals("@")) {
                continue;
            }
            computermap[x][y] = "@";
            System.out.println(i + " ship deployed.");
            i++;
        }
    }

    public static void doBattle (String [][] comMap, String [][] pMap) {
        int px; // player x
        int py; // player y
        int cx; // computer x
        int cy; // computer y
        int pShips = 5;
        int comShips = 5;
        while(pShips > 0 && comShips > 0 ) {
            System.out.println("Your Turn!");
            System.out.println("Guess the position of the Ship");
            System.out.print("Enter the x coordinate of the ship :");
            px = input.nextInt();
            while(px < 0 || px > 9) {
                System.out.println("x coordinate out of range!!");
                System.out.println("Enter the x coordinate of the ship :");
                px = input.nextInt();
            }
            System.out.print("Enter the y coordinate of the ship :");
            py = input.nextInt();
            while(py < 0 || py > 9) {
                System.out.println("y coordinate out of range!!");
                System.out.println("Enter the y coordinate of the ship :");
                py = input.nextInt();
            }
            if (comMap[px][py].equals("@")) {
                System.out.println("BOOM! you sunk the ship :D .");
                pMap[px][py] = "!";
                comMap[px][py] = "x";
                comShips--;
            } else if (pMap[px][py].equals("@")) {
                System.out.println("Oh SHIT! you sunk your own ship -_- .");
                pMap[px][py] = "x";
                pShips--;
            } else if (pMap[px][py].equals(" ")){
                System.out.println("you missed !!!!");
                pMap[px][py] = "-";
            } else {
                System.out.println("you already tried this postion before.");
                continue;
            }
            System.out.println("Computer´s Turn.");
            cx = rand.nextInt(10);
            cy = rand.nextInt(10);
            while(pMap[cx][cy].equals("x") || pMap[cx][cy].equals("!") || pMap[cx][cy].equals("-")){
                cx = rand.nextInt(10);
                cy = rand.nextInt(10);
            }
            if (comMap[cx][cy].equals("@")) {
                System.out.println("BOOM! computer sunk one of its ships.");
                pMap[cx][cy] = "!";
                comMap[cx][cy] = "x";
                comShips--;
            } else if (pMap[cx][cy].equals("@")) {
                System.out.println("Computer sunk one of your Ships.");
                pMap[cx][cy] = "x";
                comMap[cx][cy] = "!";
                pShips--;
            } else if (comMap[cx][cy].equals(" ")){
                System.out.println("Computer missed.");
                comMap[cx][cy] = "-";
            }
            System.out.println("");
            printMap(pMap);
            System.out.println("Your ships :" + pShips + " | Computer ships : " + comShips);
            System.out.println("--------------------------");
        }
        if(comShips == 0) {
            System.out.println("----------VICTORY-----------");
        } else if (pShips == 0) {
            System.out.println("Defeat! better luck next time.");
        }
    }
}
