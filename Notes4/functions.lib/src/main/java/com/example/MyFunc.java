package com.example;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MyFunc {
    public int getListFromInput(String input, ArrayList<String> listItems)
    {
        // create StringTokenizer with delimiter ";"
        StringTokenizer strtok = new StringTokenizer(input, ";");

        if(!strtok.hasMoreElements())
        {
            return 1;
        }

        // get all the tokens out of input
        while (strtok.hasMoreTokens()) {

            // add next token to list
            listItems.add(strtok.nextToken());
        }

        return 0;
    }
}
