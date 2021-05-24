package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IdentifyCarMake extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Define all the variables
    ImageView imgView, home;
    Random random;
    int loadedImage;
    Button btnIdentify;
    TextView tv, tvCorrect, countText;
    List<String> carImageArray;
    boolean isTimerChecked = false;
    CountDownTimer timer;
    long timeInMilliSeconds  = 21000; //20 minutes in milliseconds

    //Create an integer array to store all the cars
    Integer[] image = {
            R.drawable.audi,
            R.drawable.baic,
            R.drawable.benz,
            R.drawable.bmw,
            R.drawable.byd,
            R.drawable.daimler,
            R.drawable.datsun,
            R.drawable.dongfeng,
            R.drawable.faw,
            R.drawable.ferrari,
            R.drawable.ford,
            R.drawable.gac,
            R.drawable.geely,
            R.drawable.generalmotors,
            R.drawable.gmc,
            R.drawable.honda,
            R.drawable.hyundai,
            R.drawable.infiniti,
            R.drawable.isuzu,
            R.drawable.jaguar,
            R.drawable.kia,
            R.drawable.lamborghini,
            R.drawable.lexus,
            R.drawable.mazda,
            R.drawable.micro,
            R.drawable.mitsubishi,
            R.drawable.nio,
            R.drawable.nissan,
            R.drawable.rangerover,
            R.drawable.renault,
            R.drawable.saic,
            R.drawable.stellantis,
            R.drawable.subaru,
            R.drawable.suzuki,
            R.drawable.tata,
            R.drawable.tesla,
            R.drawable.toyota,
            R.drawable.vessel,
            R.drawable.volkswagen,
            R.drawable.volvo,
    };
    private String spinnerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_make);
        //Create a array called CarBrandsArray in String.xml file to get the string names of the above cars
        carImageArray = Arrays.asList(getResources().getStringArray(R.array.CarBrandsArray));

        //use id for get image views, buttons, edit Text
        imgView = findViewById(R.id.imageView);
        btnIdentify = findViewById(R.id.buttonIdentify);
        tv = findViewById(R.id.tVAnswer);
        tvCorrect = findViewById(R.id.tVCorrectAnswer);
        home = findViewById(R.id.homebutton);
        countText = findViewById(R.id.countText);

        Intent in = getIntent();
        isTimerChecked = in.getExtras().getBoolean("valueOfSwitch");

        if (isTimerChecked) {
            //Create the timer
            timer = new CountDownTimer(timeInMilliSeconds, 1000) {
                @Override
                public void onTick(long l) {
                    timeInMilliSeconds = l;
                    String seconds = timeInMilliSeconds / 1000 + "";
                    countText.setText(seconds);
                }

                @Override
                //Create onFinish method for make the rest of the application for switch button
                public void onFinish() {
                    Spinner spinner = findViewById(R.id.label_spinner);
                    int selectedItem = spinner.getSelectedItemPosition();

                    if (selectedItem == loadedImage) {
                        tv.setText("CORRECT!");
                        tv.setTextColor(Color.parseColor("#1BBEA1"));

                    } else {
                        tv.setText("WRONG!");
                        tvCorrect.setText(carImageArray.get(loadedImage));
                        tv.setTextColor(Color.parseColor("#FF0000"));
                        tvCorrect.setTextColor(Color.parseColor("#FFD300"));
                    }
                    btnIdentify.setText("Next");
                    btnIdentify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), IdentifyCarMake.class);
                            intent.putExtra("valueOfSwitch", isTimerChecked);
                            startActivity(intent);
                        }
                    });

                }
            };timer.start();
            rest();
        }
        else{
            //Call the rest method in switch off mode
            rest();
        }
    }
        public void rest () {
            Spinner spinner = findViewById(R.id.label_spinner);
            if (spinner != null) {
                spinner.setOnItemSelectedListener(this);
            }

            ArrayAdapter<CharSequence> carList = ArrayAdapter.createFromResource(this,
                    R.array.CarBrandsArray, android.R.layout.simple_spinner_item);
            carList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            if (spinner != null) {
                spinner.setAdapter(carList);
            }

            //Create random list to get random images
            random = new Random();
            loadedImage = random.nextInt(image.length);
            imgView.setImageResource(image[loadedImage]);

            btnIdentify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assert spinner != null;
                    int selectedItem = spinner.getSelectedItemPosition();

                    if (selectedItem == loadedImage) {
                        tv.setText("CORRECT!");
                        tv.setTextColor(Color.parseColor("#1BBEA1"));

                    } else {
                        tv.setText("WRONG!");
                        tv.setTextColor(Color.parseColor("#FF0000"));
                        tvCorrect.setText(carImageArray.get(loadedImage));
                        tvCorrect.setTextColor(Color.parseColor("#FFD300"));

                    }
                    if(isTimerChecked){
                        timer.cancel();
                    }

                    btnIdentify = findViewById(R.id.buttonIdentify);
                    btnIdentify.setText("Next");
                    btnIdentify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), IdentifyCarMake.class);
                            intent.putExtra("valueOfSwitch", isTimerChecked);
                            startActivity(intent);
                        }
                    });

                }
            });
            //Create a home button and add functions to get back to the home screen from identify car image
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(in);
                }
            });
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerLabel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}