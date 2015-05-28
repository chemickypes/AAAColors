package com.baraccasoftware.aaacolors.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by angelo on 25/05/15.
 */
public class LevelPreferences {

    private static final String PREFERENCES_TAG = "LEVEL_PREFENCES";
    private static final String RECORD_TAG = "RECORD_TAG";
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


}
