package com.baraccasoftware.aaacolors.model;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;

import com.baraccasoftware.aaacolors.components.RandomValue;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by angelo on 25/05/15.
 */
public class Livello {

    int liv;
    int target,first,second,third;


    public static Livello getLivello(){
        Livello livello;
        int red = RandomValue.getIntColor();
        int green = RandomValue.getIntColor();
        int blue =RandomValue.getIntColor();
        int target = Color.rgb(red, green, blue);

        int c1 = target;

        int c2 = getModifiedColor(red,green,blue);
        int c3 = getModifiedColor(red,green,blue);

        livello = new Livello(target,c1,c2,c3);

        return livello;

    }

    private static int modifiedColor(int color, int offset){
        return RandomValue.getIntOffset(2) == 0?color+offset:color-offset;
    }

    private static int getModifiedColor(int red, int green, int blue){
        int c2;

        switch (RandomValue.getColorToModify()){
            case 0:
                c2 = Color.rgb(modifiedColor(red,50), green, blue);
                break;
            case 1:
                c2 = Color.rgb(red,modifiedColor(green,50),blue);
                break;
            default:
                c2 = Color.rgb(red,green,modifiedColor(blue,50));
                break;
        }

        return c2;
    }

    public Livello(int target, int first, int second,int third) {
        this.target = target;

        ArrayList<Integer> ss = new ArrayList<>();
        ss.add(first);
        ss.add(second);
        ss.add(third);

        Collections.shuffle(ss);

        this.first = ss.get(0);
        this.second = ss.get(1);
        this.third = ss.get(2);

    }

    public boolean checkFirstColor(){
        return target == first;
    }

    public boolean checkSecondColor(){
        return target == second;
    }

    public boolean checkThirdColor(){
        return target == third;
    }


    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public int getLiv() {
        return liv;
    }

    public void setLiv(int liv) {
        this.liv = liv;
    }


}
