/** Created by Stasa on 16.2.2018  */

import java.util.*;

public class Main {

    public String function(HashSet<String> Dictionary, String inputString) {

        // step[i] = j This means that in the inputString the word begins with index j and goes til the index i
        // inputString.substring(j, i)
        // step[i] = -1 means there is no word which exactly ending before the index i
        // example for inputString = todayisfriday step[] = [0,-1,0,-1,-1,0,-1,5,-1,-1,-1,-1,-1,7]
        // example for inputString = tomorrowissaturday imamo step[] = [0,-1,0,-1,-1,-1,-1,-1,0,-1,8,-1,-1,-1,-1,-1,-1,-1,10]

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

        // if text can not be made from the whole string, return an empty string
        if (step[inputString.length()] == -1)
            return "";

        // Moving backwards from the end, the words contain words in the reverse order
        ArrayList<String> words = new ArrayList<String>();

        for (int x = inputString.length(); x > 0; x = step[x])
            words.add(inputString.substring(step[x], x));

        // Create a string using a StringBuilder that contains words in the normal order
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
