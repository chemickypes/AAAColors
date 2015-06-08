package com.baraccasoftware.aaacolors.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by angelo on 25/05/15.
 */
public class LevelPreferences {

    private static final String PREFERENCES_TAG = "LEVEL_PREFENCES";
    private static final String RECORD_TAG = "RECORD_TAG";
    private static final String MATCHES_N_TAG = "MATCHES_N_TAG";

    public static final String YOUNG_L_TAG = "young_l_tag";
    public static final String EXPERT_L_TAG="expert_l_tag";
    public static final String SENIOR_L_TAG = "senior_l_tag";
    public static final String LEADER_L_TAG  = "leader_l_tag";
    public static final String BOSS_L_TAG  = "boss_l_tag";
    public static final String KING_L_TAG = "king_l_tag";
    public static final String[] LEVELS_TAG = {YOUNG_L_TAG,EXPERT_L_TAG,SENIOR_L_TAG,LEADER_L_TAG,BOSS_L_TAG,KING_L_TAG};
    private static LevelPreferences instance;
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor; //lo prendo quando serve

    public LevelPreferences(Context co) {
        this.context = co;
        preferences = context.getSharedPreferences(PREFERENCES_TAG,Context.MODE_PRIVATE);
    }

    public static LevelPreferences getInstance(Context co) {
        if(instance == null) instance = new LevelPreferences(co);

        return instance;
    }

    public int getRecord(){
        return preferences.getInt(RECORD_TAG,0);
    }

    public void setRecord(int record){
        editor = preferences.edit();
        editor.putInt(RECORD_TAG,record);
        editor.apply();
    }

    public int getNumberOfColor(){
        int n = preferences.getInt(MATCHES_N_TAG,0);

        return n;

    }

    public void incrementNumberOfColor(int number){

        editor = preferences.edit();
        editor.putInt(MATCHES_N_TAG,number);
        editor.apply();
    }

    public void logGoals(String goals){
        editor = preferences.edit();
        editor.putBoolean(goals, true);
        editor.apply();
    }

    public boolean getGoals(String goals){
        return preferences.getBoolean(goals,false);
    }

    public boolean[] getGoals(){
        boolean[] goals = new boolean[6];
        /*goals[0] = getGoals(YOUNG_L_TAG);
        goals[1] = getGoals(EXPERT_L_TAG);
        goals[2] = getGoals(SENIOR_L_TAG);
        goals[3] = getGoals(LEADER_L_TAG);
        goals[4] = getGoals(BOSS_L_TAG);
        goals[5] = getGoals(KING_L_TAG);*/
        for(int i = 0;i<6;i++){
            goals[i] = getGoals(LEVELS_TAG[i]);
        }
        return goals;
    }


    public void logGoals(int levelGot) {
        editor = preferences.edit();
        for(int i = 0; i<levelGot+1;i++){
           editor.putBoolean(LEVELS_TAG[i],true);
        }
        editor.apply();
    }
}
