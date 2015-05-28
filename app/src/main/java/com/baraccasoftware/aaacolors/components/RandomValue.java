package com.baraccasoftware.aaacolors.components;

import java.util.Random;

/**
 * Created by angelo on 25/05/15.
 */
public class RandomValue {

    public static int getIntColor(){
        Random r = new Random();
        return r.nextInt(256);
    }

    public static int getIntOffset(int offset){
        Random r = new Random();
        return r.nextInt(offset);
    }

    public static int getColorToModify(){
        Random r = new Random();
        return r.nextInt(3);
    }
}
