package labs.lab5;

import java.util.ArrayList;
import java.util.Scanner;



public class Question1 {
    //private static int dayCount = 0;
    private static int min = Integer.MAX_VALUE;
    // private static final int VISITED = 2;
    // private static final int PORTAL1 = 3;
    // private static final int PORTAL2 = 4;
    // private static final int PORTAL3 = 5;
    private static int size; // Size of superset.
    private static int storage = 0;
    private static int maxScore = Integer.MIN_VALUE;
    private static boolean first = true;
    private static ArrayList<GameL5> subset = new ArrayList<> (); 
    private static ArrayList<GameL5> gamesDownloaded = new ArrayList<>();

    public static boolean canEat(int apples, int appleLeft, int day, int finalDay){
       if(day == 0 && !(apples == appleLeft)){return false;}
       else if(day == 0 && (apples == appleLeft)){return true;}
       if(apples == appleLeft && finalDay == day){return true;}
       if(day > finalDay){return false;}
       return canEat(apples - 3, appleLeft, day + 1, finalDay) || canEat(apples - 2, appleLeft, day + 1, finalDay);
    }
    
    public static void wazowski(int index, GameL5[] gameArray) {
        if(storage == 0){maxScore = 0; return;}
        size = gameArray.length; 
        if ( index > size-1 ) { 
            for ( int i = 0; i < subset.size(); i++ ) { 
                if( sum(subset)<= storage ){  
                    if(score(subset) >= maxScore){
                        maxScore=score(subset);
                        gamesDownloaded= new ArrayList<>(subset);
                    }
                }
            }
        } else {
            subset.add (gameArray[index]);
            wazowski(index + 1,gameArray);

            subset.remove ( subset.size() - 1 );
            wazowski(index + 1,gameArray);
        }
    }    

    public static int score(ArrayList<GameL5> g){
        int sum = 0;
        for(GameL5 t : g){
            sum += t.score;
        }
        return sum;
    }

    public static int sum(ArrayList<GameL5> g){
        int sum = 0;
        for(GameL5 t : g){
            sum += t.memory;
        }
        return sum;
    } 
    public static int getMax(){
        return maxScore;
    }
    public static String getNamesOfGamesDownloaded(){
        String s = "";
        for(GameL5 temp : gamesDownloaded){
            s += temp.toString() + " ";
        }
        return s;
    }

    public static int leastInteract(String a, String b, int index, int indexberOfManip){
        if(a == null || b == null){return 0;}
        else if(a.length() < b.length() && first){String temp = a; a = b; b = temp;}
        if(a.equals(b)){
            if(min > indexberOfManip){
                min = indexberOfManip;
            }
            return indexberOfManip;
        }
        while(index < Math.min(a.length(), b.length()) - 1 && a.charAt(index) == b.charAt(index)){
            index++;
        }
        if(index + 1 > Math.max(a.length(),b.length()) && !a.equals(b)){
            return Integer.MAX_VALUE;
        }
        if(a.length() < b.length()){return Integer.MAX_VALUE;}
        if(indexberOfManip > min){return Integer.MAX_VALUE;}
        first = false;
        return Math.min(leastInteract(a.substring(0, index) + a.substring(index + 1) , b, index, indexberOfManip + 1), Math.min(leastInteract( a.substring(0, index) + String.valueOf(b.charAt(index)) + a.substring(index + 1), b, index, indexberOfManip + 1),     leastInteract(a.substring(0, index) + b.charAt(index) + a.substring(index), b, index, indexberOfManip + 1)));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /** question 1*/
         /*
        System.out.println("enter the apple count");
        int apple = sc.nextInt();
        System.out.println("enter the apple left count");
        int appleLeft = sc.nextInt();
        System.out.println("enter the day count");
        int day = sc.nextInt();
        System.out.println(canEat(apple, appleLeft, 1, day));;
        /*
        /** question 2*/
        /*
        System.out.println("----------------------------QUESTION 2 -----------------------------");
        GameL5 g1 = new GameL5(45, 50, "1");
        GameL5 g2 = new GameL5(10,85, "2");
        GameL5 g3 = new GameL5(15, 45, "3");
        GameL5 g4 = new GameL5(20, 100, "4");
        GameL5 g5 = new GameL5(25, 6, "5");
        GameL5 g6 = new GameL5(100, 100, "6");

        
        GameL5[] games = {g1,g2,g3, g4, g5, g6};
        wazowski(0,games);
        System.out.println(getMax());
        System.out.println(getNamesOfGamesDownloaded());
        
        */
        System.out.println("------------------------QUESTION 3--------------");
        //System.out.println(leastInteract("Bruh", "Bruuh", 0, 0, 0));
        System.out.println(leastInteract("exclamation","excavation" , 0, 0));
        
        sc.close();
    }
}