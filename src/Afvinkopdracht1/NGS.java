/**
 * @author Ivar van den Akker
 * @version alpha version
 * @since 18 nov 2020
 *
 */
package Afvinkopdracht1;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class NGS {
    private static final int grote = 10000000;
    private static int[] getallijst = new int[grote];
    private static Random random = new Random();

    public static void main(String[] args) {
        NumberGenerator();
        Instant start = Instant.now();
        SortingFunction();
        Instant finish = Instant.now();
        System.out.println(Duration.between(start, finish).toMillis() + " Milliseconds");
    }

    private static void SortingFunction() {
        /**
         * Deze functie sorteerd de array getallijst en print hem uit
         *
         */
        int i,j,k=0,kleinste, grootste;
        // zoek kleinste en grootste
        kleinste = grootste = getallijst[0];
        for(i=1;i< getallijst.length;i++)
            if (getallijst[i]<kleinste)
                kleinste=getallijst[i];
            else if (getallijst[i]>grootste)
                grootste=getallijst[i];
        int[] turven = new int[grootste-kleinste+1]; // maak telarray<br>
        for(i=0;i<(grootste-kleinste+1);i++)
            turven[i]=0; // laat alle turven op nul beginnen
        for(i=0;i< getallijst.length;i++)
            turven[getallijst[i]-kleinste]++; // zet turven
        // sorteer data (zet turf "i" "j"x op plaats "k")
        for(i=kleinste;i<=grootste;i++)
            for(j=turven[i-kleinste];j>0;j--)
            {
                getallijst[k] = i;
                k++;
            }
        // print de gesorteerde getallen array

    }

    private static void NumberGenerator() {
        /**
         * Deze functie genereerd voor elke positie in de getallijst een
         * getal tussen 1 en 1 biljoen
         * @exception OutOfMemoryError als er niet genoeg geheugen in om een getal te genereren
         */
        try{
            for (int i=0;i<grote;i++){
                // genereer een random int
                getallijst[i]=random.nextInt(100000000);
            }
        }catch (OutOfMemoryError e){
            System.out.println("Er is niet genoeg geheugen om een cijfer te genereren tussen 1 en 1 biljoen");
        }
    }
}