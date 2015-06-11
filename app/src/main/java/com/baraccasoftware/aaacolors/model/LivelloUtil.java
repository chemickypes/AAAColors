package com.baraccasoftware.aaacolors.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;

import com.baraccasoftware.aaacolors.R;

import java.util.ArrayList;

/**
 * Created by angelo on 25/05/15.
 */
public class LivelloUtil {
    public static final int YOUNG = 10;
    public static final int EXPERT = 50;
    public static final int SENIOR = 70;
    public static final int LEADER = 100;
    public static final int BOSS = 150;
    public static final int KING = 200;
    public static final int[] LEVELS = {10,50,70,100,150,200};
    private static Livello next;

    private static ArrayList<Livello> livellos;

    static public int currentLevel = 0;

    public static Livello getNext() {
        if(livellos == null || livellos.size()==0){
            livellos = new ArrayList<>();
            livellos.add(Livello.getLivello(currentLevel));
        }
        next = livellos.get(0);
        livellos.remove(0);
        livellos.add(Livello.getLivello(currentLevel));
        return next;
    }

    static public Drawable getTargetDrawable (Context context,Livello l){
        ShapeDrawable pacMan = new ShapeDrawable(new ArcShape(0, 360));
        int px = context.getResources().getDimensionPixelSize(R.dimen.central_color);
        pacMan.setIntrinsicHeight(px);
        pacMan.setIntrinsicWidth(px);
        pacMan.getPaint().setColor(l.getTarget());

        return pacMan;
    }

    static public Drawable getFirstDrawable (Context context,Livello l){
        ShapeDrawable pacMan = new ShapeDrawable(new ArcShape(0, 360));
        int px = context.getResources().getDimensionPixelSize(R.dimen.second_color);
        pacMan.setIntrinsicHeight(px);
        pacMan.setIntrinsicWidth(px);
        pacMan.getPaint().setColor(l.getFirst());

        return pacMan;
    }
    static public Drawable getSecondDrawable (Context context,Livello l){
        ShapeDrawable pacMan = new ShapeDrawable(new ArcShape(0, 360));
        int px = context.getResources().getDimensionPixelSize(R.dimen.second_color);
        pacMan.setIntrinsicHeight(px);
        pacMan.setIntrinsicWidth(px);
        pacMan.getPaint().setColor(l.getSecond());

        return pacMan;
    }

    static public Drawable getThirdDrawable (Context context,Livello l){
        ShapeDrawable pacMan = new ShapeDrawable(new ArcShape(0, 360));
        int px = context.getResources().getDimensionPixelSize(R.dimen.second_color);
        pacMan.setIntrinsicHeight(px);
        pacMan.setIntrinsicWidth(px);
        pacMan.getPaint().setColor(l.getThird());

        return pacMan;
    }

    static public  boolean[] getGoals(int record){
        boolean[] goals = new boolean[6];
        /*goals[0] = record >= YOUNG;
        goals[1] = record >= EXPERT;
        goals[2] = record >= SENIOR;
        goals[3] = record >= LEADER;
        goals[4] = record >= BOSS;
        goals[5] = record >= KING;*/
        for(int i = 0; i<6;i++){
            goals[i] = record >= LEVELS[i];
        }
        return goals;
    }


}
