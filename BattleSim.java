
/**
 * This program is designed to determine battle outcomes for Twilight Imperium
 *
 * Caleb Backer-Corthell
 * version 1.0
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class BattleSim
{
    
    public static void main(String[] args){
        Random rand = new Random();
        
        Scanner tell = new Scanner(System.in);
        ArrayList<Integer> me = new ArrayList<Integer>();
        ArrayList<Integer> opp = new ArrayList<Integer>();
    
        System.out.println("How many rolls do you have?");
        int meRolls = tell.nextInt();
        System.out.println ("How many rolls does your opponent have?");
        int oppRolls = tell.nextInt();
        System.out.println("Input your rolls, from most to least valuable");
        
        for( int x=0; x<meRolls; x++){
            System.out.println("Input roll");
            me.add(tell.nextInt());
        }
        System.out.println("Input opponent rolls, most to least valuable");
        
        for( int x=0; x<oppRolls; x++){
            System.out.println("Input roll");
            opp.add(tell.nextInt());
        }
        System.out.println("How many lives do you have?");
        int meLives = tell.nextInt();
        
        System.out.println("How may lives does your opponent have?");
        int oppLives = tell.nextInt();
        
        System.out.println("How many times would you like to run the sim?");
        double run = tell.nextInt();
        
        int Tcount = 0;
        int Wcount = 0;
        int Lcount = 0;
        int Dcount = 0;
        int meHits=0;
        int oppHits=0;
        int meSimLives;
        int oppSimLives;
        for (int a = 0; a < run; a++){
            ArrayList<Integer> meSim = new ArrayList();
            ArrayList<Integer> oppSim = new ArrayList() ;
            meSimLives = meLives;
            oppSimLives = oppLives;
            meSim.clear();
            oppSim.clear();
            meSim.addAll(me);
            oppSim.addAll(opp);
            System.out.println("reset");
            while( meSimLives > 0 && oppSimLives > 0){
                System.out.println(meSim);
                System.out.println(oppSim);
                meHits = getHits(meSim);
                oppHits = getHits(oppSim);
                meSimLives -= oppHits;
                oppSimLives -= meHits;
                takeHits(meSim,oppHits);
                takeHits(oppSim,meHits);
                
               // System.out.println();
               System.out.println ("Your lives: " +meSimLives);
               System.out.println("Opp lives: " + oppSimLives);
               System.out.println();
            }
            
            if( meSimLives <= 0 && oppSimLives <= 0){
                Tcount++;
                Dcount++;
            }
            else if(meSimLives>0){
                Wcount++;
                Tcount++;
            }
            else{
                Lcount++;
                Tcount++;
            }
            
           
        }
        double wPerc = 100 *Wcount/Tcount;
        double dPerc = 100*Dcount/Tcount;
        double lPerc = 100*Lcount/Tcount;
        
        System.out.println("Win : "+ wPerc);
        System.out.println("Loss : " + lPerc);
        System.out.println("Draw %: " + dPerc);
        
        System.out.println(Wcount);
        System.out.println(Lcount);
        System.out.println(Dcount);
    }
    
    public static int getHits(ArrayList<Integer> folk){
        Random rand = new Random();
        int dice;
        int hits =0;
        int goal;
        for ( int b = 0; b < folk.size();b++){
                    dice = rand.nextInt(10)+1;
                    System.out.println("You rolled: " + dice);
                    goal = folk.get(b);
                    System.out.println("Your goal is: " + goal);
                    if(dice >=goal ){
                        hits++;
                        System.out.println("hit");
                        
                    }
                    System.out.println();
                }
        return hits;
    }
    
    public static void takeHits(ArrayList folk, int loss){
        int index;
        for(int x = 0; x < loss; x++){
            if (folk.size() == 0){
                break;
            }
            index = folk.size()-1;
            folk.remove(index);
        }
    }
    
}
