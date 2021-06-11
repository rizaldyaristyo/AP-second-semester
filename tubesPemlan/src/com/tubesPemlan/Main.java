package com.tubesPemlan;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Ananda Rizaldy Aristyo, Muhammad Lutfi Syukran, Nico Ardia Effendy
 * @since 2021
 * @version 1.0
 */

class Delay {
    /**
     * Method that provides delay in second
     * using an internal InterruptedException
     * so programmers don't need to catch
     * any exception on internal usage.
     * @param second determine the TimeUnit's sleep parameter
     */
    void inSec(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Exchange {
    /**
     * Adds the total held card value and returns assigned cards
     * @param randomize gets the local instance of Rand class
     * @param array reference to array that is going to be added
     * @param totalCard fetch and adds generated value to the total worth of cards value
     * @param incremental reference to the local incremental value
     * @return properly assigned card assignation
     */
    int theCardAndIncrement(Rand randomize, String[] array, int totalCard, int incremental) {
        int tempRand = randomize.theNumber();
        totalCard += tempRand;
        return cardAssigner(array, totalCard, incremental, tempRand);
    }

    /**
     *
     * @param randomize gets the local instance of Rand class
     * @param array reference to array that is going to be added
     * @param incremental reference to the local incremental value
     * @return properly assigned card assignation
     */
    int theCard(Rand randomize, String[] array, int incremental) {
        int tempRand;
        tempRand = randomize.theNumber();
        return cardAssigner(array, tempRand, incremental, tempRand);
    }

    /**
     * Filter the randomized number into card set value
     * for short, 1-10 would appear the same as a card number,
     * but in case of 11-13 appearance, they would be converted
     * to their corresponding card royal letters such as A,J,Q,and K.
     * @param array reference to array that is going to be added
     * @param totalCard fetch and adds generated value to the total worth of cards value
     * @param incremental reference to the local incremental value
     * @param tempRand reference to a temp variable holder
     * @return integer that resembles total worth of held card value
     */
    private int cardAssigner(String[] array, int totalCard, int incremental, int tempRand) {
        if (tempRand == 11) array[incremental] = "J";
        else if (tempRand == 12) array[incremental] = "Q";
        else if (tempRand == 13) array[incremental] = "K";
        else if (tempRand == 1) array[incremental] = "A";
        else array[incremental] = String.valueOf(tempRand);
        return totalCard;
    }
}

class Rand{
    /**
     * Simply randomize an integer that has been limited between 1-13 only
     * @return integer that has been randomized
     */
    int theNumber(){
        Random rand = new Random();
        return rand.nextInt(14-1)+1;
    }
}

class Start{
    int bjLimit;
    public int getBjLimit() {
        return bjLimit;
    }
    public void setBjLimit(int bjLimit) {
        this.bjLimit = bjLimit;
    }
    /**
     * Separates gameplay and the other menu
     * in order to get better control of looping
     * and main menu switch management
     */
    void theGame(){

        Exchange exchange = new Exchange();
        Rand randomize = new Rand();
        Delay delay = new Delay();
        Scanner imputan = new Scanner(System.in);
        //int randomizer =randomize.theCard();
        String[] b0 = new String[22];
        String[] p1 = new String[22];
        String[] p2 = new String[22];
        int btemp,p1temp,p2temp;
        int t0=0,t1=0,t2=0;
        for (int i=0; i<3; i++) {
            btemp = exchange.theCard(randomize, b0, i);
            p1temp = exchange.theCard(randomize, p1, i);
            p2temp = exchange.theCard(randomize, p2, i);
            t0+=btemp;
            t1+=p1temp;
            t2+=p2temp;
        }
        //game
        //p1turn
        int choice;
        System.out.println("===Giliran P1===\nBandar: █ █ █\nP1    : "+p1[0]+" "+p1[1]+" "+p1[2]+"	 Total: "+t1+"\nP2    : █ █ █");
        boolean loop = true;
        int a = 3;
        while (loop){
            if (t1<bjLimit) {
                System.out.println("\n[1.Hit] [2.Hold]");
                choice = imputan.nextInt();
                switch (choice) {
                    case 1 -> {
                        t1 = exchange.theCardAndIncrement(randomize, p1, t1, a);
                        System.out.print("Bandar: █ █ █\nP1    :");
                        for (String s : p1) if (s != null) System.out.print(" " + s);
                        System.out.print("	 Total: " + t1 + "\nP2    : █ █ █");
                        a++;
                    }
                    case 2 -> loop = false;
                }
            }else if (t1 == bjLimit){
                System.out.println("\nP1 BlackJack!\ntidak dapat menambah lagi!, tunggu 3 detik");
                loop = false;
                delay.inSec(3);
            }else {
                System.out.println("\nP1 Hangus\ntidak dapat menambah lagi!, tunggu 3 detik");
                t1=0;
                loop = false;
                delay.inSec(2);
            }
        }

        //p2turn
        System.out.print("\n\n\n\n\n===Giliran P2===\nBandar: █ █ █\nP1    :");
        for (String s : p1) if (s != null) System.out.print(" █");
        System.out.println("\nP2    : "+p2[0]+" "+p2[1]+" "+p2[2]);
        System.out.println("	 Total: "+t2);
        loop=true;
        a=3;
        while (loop){
            if (t2<bjLimit){
                System.out.println("\n[1.Hit] [2.Hold]");
                choice = imputan.nextInt();
                switch (choice) {
                    case 1 -> {
                        t2 = exchange.theCardAndIncrement(randomize, p2, t2, a);
                        System.out.print("Bandar: █ █ █\nP1    : █ █ █\nP2    :");
                        for (String s : p2) if (s != null) System.out.print(" " + s);
                        System.out.print("	 Total: " + t2);
                        a++;
                    }
                    case 2 -> loop = false;
                }
            }else if (t2 == bjLimit){
                System.out.println("\nP2 BlackJack!\ntidak dapat menambah lagi!, tunggu 3 detik");
                loop = false;
                delay.inSec(3);
            }else {
                System.out.println("\nP2 Hangus\ntidak dapat menambah lagi!, tunggu 3 detik");
                t2=0;
                loop = false;
                delay.inSec(2);
            }
        }

        //turnBot
        loop=true;
        a=3;
        System.out.println("\n\n\n\n\n===Giliran Bandar===\nBandar:");
        while (loop){
            if (t0<(bjLimit-3)){
                System.out.print("-Hit");
                btemp = randomize.theNumber();
                //System.out.print(" +"+btemp);
                t0 += btemp;
                b0[a] = String.valueOf(btemp);
                a++;
            }else if (t0 == bjLimit){
                System.out.println("-BlackJack!");
                loop = false;
                delay.inSec(3);
            }else if (t0 > bjLimit){
                System.out.println("-Hangus");
                t0=0;
                loop = false;
                delay.inSec(2);
            }else {
                System.out.println("-Hold");
                loop=false;
            }
        }
        System.out.print("\nBandar:");
        for (String s : b0) if (s != null) System.out.print(" █");
        System.out.print("\nP1    :");
        for (String s : p1) if (s != null) System.out.print(" █");
        System.out.print("\nP2    :");
        for (String s : p2) if (s != null) System.out.print(" █");
        System.out.println("\n3 detik menuju Hasil");
        delay.inSec(3);
        System.out.print("\n\n===Hasil===\nBandar:");
        for (String s : b0) if (s != null) System.out.print(" "+s);
        System.out.print("("+t0+")\nP1    :");
        for (String s : p1) if (s != null) System.out.print(" "+s);
        System.out.print("("+t1+")\nP2    :");
        for (String s : p2) if (s != null) System.out.print(" "+s);
        System.out.print("("+t2+")");

        if      (t0==t1&&t1==t2) System.out.println("\nBandar Menang");
        else if (t0>=t1&&t0>=t2)System.out.println("\nBandar Menang");
        else if (t0 < t2 && t1 == t2)System.out.println("\nSeri");
        else if (t0>=t1&&t0<=t2)System.out.println("\nP2 Menang");
        else if (t0<=t1&&t0>=t2)System.out.println("\nP1 Menang");
        else if (t0 <= t1 && t1 < t2) System.out.println("\nP2 Menang");
        else if (t0 < t2) System.out.println("\nP1 Menang");
    }

    void theSetting(){
        Scanner imputan = new Scanner(System.in);
        System.out.println("===BlackJack Limit===\n1.)Limits to 21\n2.)Limits to 30");
        int pilihan = imputan.nextInt();
        switch (pilihan){
            case 1  -> setBjLimit(21);
            case 2  -> setBjLimit(30);
            default -> setBjLimit(getBjLimit());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Start start = new Start();
        Scanner imputan = new Scanner(System.in);
        start.setBjLimit(30);
        while (true){
            System.out.println("\n\n===Main Menu===\n1.)Start\n2.)Setting\n3.)Exit");
            int pillihan = imputan.nextInt();
            switch (pillihan) {
                case 1 -> start.theGame();
                case 2 -> start.theSetting();
                case 3 -> System.exit(0);
                default -> System.out.println("Wrong Selection");
            }
        }
    }
}
