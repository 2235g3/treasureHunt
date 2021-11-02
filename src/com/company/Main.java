package com.company;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("How many columns do you want the board to have?");
        int columnsAmt = intInput();
        System.out.println("How many rows do you want the board to have?");
        int rowsAmt = intInput();

        String [][] board = boardInit(columnsAmt,rowsAmt);

        int movements = ((columnsAmt * rowsAmt) / 2) + 1;
        int total = 0;
        for (int i = 0; i < movements + 1; i++) {
            int[] coords = coordGuess(columnsAmt, rowsAmt);
            int treasureFound = 0;
            for (int j = 0; j < columnsAmt; j++) {
                for (int k = 0; k < rowsAmt; k++) {
                    if (Integer.parseInt(board[coords[0] - 1][coords[1] - 1]) > 0 && treasureFound  == 0) {
                        total += Integer.parseInt(board[coords[0] - 1][coords[1] - 1]) * 100;
                        System.out.println("You have found treasure!!\nThere is " + Integer.parseInt(board[coords[0] - 1][coords[1] - 1]) * 100 + " gold coins in the chest.\nYour total is now " + total + " gold coins\nYou have " + (movements - i) + " turns left.\n");
                        board[coords[0] - 1][coords[1] - 1] = "0";
                        treasureFound = 1;
                    }
                }
            }
            if (treasureFound == 0) {
                System.out.println("There was no treasure at those coordinates :(\nYou have " + (movements - i) + " turns left.\n");
            }
        }
        System.out.println("\nThe hunt is over!\nYour total gold was " + total + " coins and you had " + movements + " turns left.");
    }

    static int intInput() {
        Scanner input = new Scanner(System.in);
        int userIn = input.nextInt();
        return userIn;
    }

    static String [][] boardInit(int c,int r) {
        String [][] board = boardSetup(c, r);
        board = coinPlacement(c, r, board);
        return board;
    }
    static String[][] boardSetup(int c, int r) {
        String [][] board = new String[c][r];

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                board[i][j] = "0";
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
        return board;
    }
    static String [][] coinPlacement(int c, int r, String [][] b) {
        Random random = new Random();
        ArrayList coordList = new ArrayList();
        int coordMatch = 0;
        int amtCoinDeposits = 0;

        for (int i = 0; i < 2; i++) {
            amtCoinDeposits = random.nextInt(r*c-(r+4));
            if (amtCoinDeposits == 0) {
                i -= 1;
            }
        }
        for (int i = 0; i < amtCoinDeposits; i++) {
            int randXCoord = random.nextInt(c);
            int randYCoord = random.nextInt(r);
            if (coordList.size() == 0) {
                coordList.add(randXCoord);
                coordList.add(randYCoord);
            }
            else {
                for (int j = 0; j < coordList.size() - 1; j++) {
                    if (randXCoord == Integer.parseInt(coordList.get(j).toString()) && randYCoord == Integer.parseInt(coordList.get(j+1).toString())) {
                        i -= 1;
                        j += 1;
                        coordMatch = 1;
                        break;
                    }
                    else {
                        coordMatch = 0;
                    }
                }
                if (coordMatch == 0) {
                    coordList.add(randXCoord);
                    coordList.add(randYCoord);
                }
            }
        }
        for (int i = 0; i < (coordList.size()/2); i++) {
            int randCoins = random.nextInt(10);
            if (randCoins == 0) {
                i -= 1;
            }
            else {
                b[(int) coordList.get(i)][(int) coordList.get(i + 1)] = Integer.toString(randCoins);
            }
        }
        System.out.println("");
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(b[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        return b;
    }
    static int[] coordGuess(int c, int r) {
        int[] coords = new int[2];
        int coordXGuess = 0;
        int coordYGuess = 0;
        do {
            System.out.println("Enter the X coordinate (column):");
            coordXGuess = intInput();
            if (coordXGuess > c || coordXGuess <= 0) {
                System.out.println("Not a valid input\nPlease enter another coordinate\n");
            }
        } while (coordXGuess > c || coordXGuess <= 0);
        do {
            System.out.println("Enter the Y coordinate (row)");
            coordYGuess = intInput();
            if (coordYGuess > r || coordYGuess <= 0) {
                System.out.println("Not a valid input\nPlease enter another coordinate\n");
            }
        } while (coordYGuess > r || coordYGuess <= 0);
        coords[0] = coordYGuess;
        coords[1] = coordXGuess;
        return coords;
    }
}