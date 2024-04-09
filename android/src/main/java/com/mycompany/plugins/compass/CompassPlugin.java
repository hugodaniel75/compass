package com.mycompany.plugins.compass;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

@CapacitorPlugin(name = "Compass")
public class CompassPlugin extends Plugin implements SensorEventListener {
    private Context mContext;
    private SensorManager mSensorManager;
    private Sensor mOrientationSensor;

    private float mCurrentDegree = 0f;

    private Compass implementation = new Compass();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void check(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", "true");
        call.resolve(ret);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float degree = Math.round(event.values[0]);
            // Aquí tienes el ángulo de la brújula en grados

            // Puedes hacer lo que quieras con el ángulo, por ejemplo, actualizar la interfaz de usuario
            // Para mantener el ángulo en el rango de 0 a 360 grados
            degree = (degree + 360) % 360;

            // Actualizar la interfaz de usuario con el nuevo ángulo
            // Por ejemplo, puedes enviar el ángulo a tu actividad principal o a una vista en particular
            // mediante un método de actualización de la interfaz de usuario
            updateUI(degree);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No necesitas implementar esto para el propósito de obtener la orientación de la brújula
    }

    private void updateUI(float degree) {
        // Aquí actualizas la interfaz de usuario con el nuevo ángulo
        // Por ejemplo, puedes enviar el ángulo a tu actividad principal o a una vista en particular
        // mediante un método de actualización de la interfaz de usuario
    }
}
