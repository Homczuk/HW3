package com.example.hw2;

import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public Ball eball;
    public SensorManager sensor;
    long time_updated;
    private static int SHAKE_SPEED = 200;
    double axis_X, axis_Y, axis_Z, old_axis_X, old_axis_Y, old_axis_Z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eball = new Ball(getApplicationContext());

        sensor = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor.registerListener(this,sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),sensor.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
           long time_current = System.currentTimeMillis();
           if((time_current - time_updated) > 100){
               long time_delta = (time_current - time_updated);
               time_updated = time_current;

               axis_X = event.values[0];
               axis_Y = event.values[1];
               axis_Z = event.values[2];

               double speed = Math.abs(axis_X + axis_Y + axis_Z - old_axis_X - old_axis_Y - old_axis_Z)/time_delta * 500;
                if(speed > SHAKE_SPEED){
                    update(speed);
                }
                old_axis_X = event.values[0];
                old_axis_Y = event.values[1];
                old_axis_Z = event.values[2];
           }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void update(double speed){
        ImageView ball = findViewById(R.id.empty_ball);
        TextView value = findViewById(R.id.result);
        ball.setImageResource(R.drawable.hw3ball_empty);
        String result = eball.values[((int)speed)%12];
        value.setText(result);
        value.setVisibility(View.VISIBLE);
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        ball.startAnimation(shake);
    }
}
