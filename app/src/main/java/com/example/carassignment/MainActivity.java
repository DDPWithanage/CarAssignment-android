package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Switch switchButton;
    boolean isTimerChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate");

        //Identify the switch
        switchButton = findViewById(R.id.switchButton);

        //change the boolean value when button is clicked
       switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           //set On action for switch
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isTimerChecked = true;
                }
                else {
                    isTimerChecked = false;
                }

            }
        });

    }

    //Create Identify car make activity and set the switch button
    public void identifyCarMake(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(getApplicationContext(), IdentifyCarMake.class);
        intent.putExtra("valueOfSwitch", isTimerChecked);
        startActivity(intent);

    }

    //Create hints activity and set the switch button
    public void hints(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(getApplicationContext(), Hints.class);
        intent.putExtra("valueOfSwitch", isTimerChecked);
        startActivity(intent);
    }

    //Create Identify car Image activity and set the switch button
    public void identifyCarImage(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(getApplicationContext(), IdentifyCarImage.class);
        intent.putExtra("valueOfSwitch", isTimerChecked);
        startActivity(intent);
    }

    //Create advanced level activity and set the switch button
    public void advancedLevel(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(getApplicationContext(), AdvancedLevel.class);
        intent.putExtra("valueOfSwitch", isTimerChecked);
        startActivity(intent);
    }

}