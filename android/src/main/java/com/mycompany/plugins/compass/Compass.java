package com.mycompany.plugins.compass;

import android.util.Log;

public class Compass {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public boolean check() {
        return true;
    }

    public void start() {
        Log.i("hola", "que tal");
        return;
    }
}
