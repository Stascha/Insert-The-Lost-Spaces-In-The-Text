/** Created by Stasa on 16.2.2018  */

import java.util.*;

public class Main {

    public String function(HashSet<String> Dictionary, String inputString) {

        // niz koji popunjavamo na sledeci nacin
        // step[i] = ukoliko ne postoji nacin da se prefiks od prvih i slova
        // stringa inputString isece na reci, onda je vrednost -1

        // step[0] = definisemo na 0 (prazan string moze da se podeli)

        // inace je vrednost j >= 0, i to znaci da sva slova unutar [j, i) cine
        // jednu validnu rec i da step[j] moze da se isece moze i j = 0

        // step[i] = j to znači da u inputStringu reč počinje od indexa j i ide do indexa i
        // inputString.substring(j, i)
        // indexi do kojih se ne zvršava ni jedna reč niz step u tim indexima ima vrednost -1
        // na primer za inputString = todayisfriday step[] = [0,-1,0,-1,-1,0,-1,5,-1,-1,-1,-1,-1,7]
        // za inputString = tomorrowissaturday imamo step[] = [0,-1,0,-1,-1,-1,-1,-1,0,-1,8,-1,-1,-1,-1,-1,-1,-1,10]

        int[] step = new int[inputString.length() + 1];

        step[0] = 0;

        for (int i = 1; i <= inputString.length(); i++)
        {
            step[i] = -1;

            for ( int j = 0 ; j < i ; j++ )
            {
                if ( Dictionary.contains( inputString.substring(j, i) ) && step[j] != -1 )
                {
                    step[i] = j;
                    break;
                }
            }
        }

        // ako ne moze da se napravi od celog stringa, vrati prazan string
        if (step[inputString.length()] == -1)
            return "";

        // Kreci se unazad od kraja, rekonstruisi, words sadrzi reci u obrnutom redosledu
        ArrayList<String> words = new ArrayList<String>();

        for (int x = inputString.length(); x > 0; x = step[x])
            words.add(inputString.substring(step[x], x));

        // Napravi string pomocu string builder-a koji sadrzi reci u normalnom redosledu
        StringBuilder sb = new StringBuilder();

        for (int i = words.size()-1; i >= 0; i--)
           sb.append( i > 0 ? words.get(i) + " " : words.get(i) );

        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "todayisfriday";
        String s2 = "tomorrowissaturday";
        String s3 = "abcdefg";
        HashSet<String> hs = new HashSet<String>();

        hs.add("today");
        hs.add("is");
        hs.add("friday");
        hs.add("to");
        hs.add("day");
        hs.add("tomorrow");
        hs.add("saturday");
        hs.add("ab");
        hs.add("abc");
        hs.add("cde");
        hs.add("cdef");
        hs.add("g");


        System.out.println(s1 + " --> " + (new Main()).function(hs, s1));
        System.out.println(s2 + " --> " +(new Main()).function(hs, s2));
        System.out.println(s3 + " --> " +(new Main()).function(hs, s3));
    }

}
