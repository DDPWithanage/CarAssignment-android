package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AdvancedLevel extends AppCompatActivity {
    //Define all the variables
    ImageView imageView1, imageView2, imageView3, home;
    Button btnSubmit;
    EditText editText1, editText2, editText3;
    TextView Wrong, Correct1, Correct2, Correct3, countText, CorrectAns, points;
    int randCar;
    Integer[] carRandomList = new Integer[3];
    List<String> carImageArray;
    int totalAttempts = 0;
    boolean isTimerChecked = false;
    CountDownTimer timer;
    long timeInMilliSeconds = 21000; //20 minutes in milliseconds
    Random random;
    int point = 0;

    //Create an integer array to store all the cars
    Integer[] image = {
            R.drawable.audi, R.drawable.baic, R.drawable.benz, R.drawable.bmw,
            R.drawable.byd, R.drawable.daimler, R.drawable.datsun, R.drawable.dongfeng,
            R.drawable.faw, R.drawable.ferrari, R.drawable.ford, R.drawable.gac,
            R.drawable.geely, R.drawable.generalmotors, R.drawable.gmc, R.drawable.honda,
            R.drawable.hyundai, R.drawable.infiniti, R.drawable.isuzu, R.drawable.jaguar,
            R.drawable.kia, R.drawable.lamborghini, R.drawable.lexus, R.drawable.mazda,
            R.drawable.micro, R.drawable.mitsubishi, R.drawable.nio, R.drawable.nissan,
            R.drawable.rangerover, R.drawable.renault, R.drawable.saic, R.drawable.stellantis,
            R.drawable.subaru, R.drawable.suzuki, R.drawable.tata, R.drawable.tesla,
            R.drawable.toyota, R.drawable.vessel, R.drawable.volkswagen, R.drawable.volvo,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);
        random = new Random();

        //use id for get image views, buttons, edit Text
        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.img2);
        imageView3 = findViewById(R.id.img3);
        countText = findViewById(R.id.textCount3);
        btnSubmit = findViewById(R.id.submitButton);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        home = findViewById(R.id.homeButton1);
        Wrong = findViewById(R.id.txtWrong);
        Correct1 = findViewById(R.id.txtCorrect1);
        Correct2 = findViewById(R.id.txtCorrect2);
        Correct3 = findViewById(R.id.txtCorrect3);
        points = findViewById(R.id.txtPoint);
        CorrectAns = findViewById(R.id.CorrectTxt);

        carImageArray = Arrays.asList(getResources().getStringArray(R.array.CarNames));

        carRandomList[0] = random.nextInt(image.length);
        //create three random images
        carRandomList[1] = random.nextInt(image.length);
        while (carRandomList[0] == carRandomList[1]) {
            carRandomList[1] = random.nextInt(image.length);
        }

        carRandomList[2] = random.nextInt(image.length);
        while (carRandomList[0] == carRandomList[2]) {
            carRandomList[2] = random.nextInt(image.length);
        }

        while (carRandomList[1] == carRandomList[2]) {
            carRandomList[2] = random.nextInt(image.length);
        }

        final List list = Arrays.asList(carRandomList);
        Collections.shuffle(list);
        list.toArray(carRandomList);

        imageView1.setImageResource(image[carRandomList[0]]);
        imageView2.setImageResource(image[carRandomList[1]]);
        imageView3.setImageResource(image[carRandomList[2]]);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in);
            }
        });
    }

    public void Submit(View view) {
        totalAttempts++;

        String answer1 = carImageArray.get(carRandomList[0]);
        String answer2 = carImageArray.get(carRandomList[1]);
        String answer3 = carImageArray.get(carRandomList[2]);

        //Find whether above text field is empty
        if (editText1 != null) {
            //Find user input is equal to correct answer from the array list and if it is correct make it green.
            if (answer1.equals(editText1.getText().toString().toLowerCase())) {
                editText1.setBackgroundColor(0xff00ff00);
                //Add points
                point++;
                if (points != null) {
                    points.setText(Integer.toString(point));
                }
                point = 0;
                //if answer correct - Disable the edit Text field
                editText1.setEnabled(false);
            } else {
                //If answer is wrong makes the text field to red colour
                editText1.setBackgroundColor(0xFFFF0000);
            }
        }
        //Text - 02
        //Find whether above text field is empty
        if (editText2 != null) {
            if (answer2.equals(editText2.getText().toString().toLowerCase())) {
                editText2.setBackgroundColor(0xff00ff00);
                //Add points
                point++;
                if (points != null) {
                    points.setText(Integer.toString(point));
                }
                point = 0;
                //if answer correct - Disable the edit Text field
                editText2.setEnabled(false);
            } else {
                editText2.setBackgroundColor(0xFFFF0000);
            }
        }
        //Text - 03
        //Find whether above text field is empty
        if (editText3 != null) {
            if (answer3.equals(editText3.getText().toString().toLowerCase())) {
                editText3.setBackgroundColor(0xff00ff00);
                //Add points
                point++;
                if (points != null) {
                    points.setText(Integer.toString(point));
                }
                point = 0;
                //if answer correct - Disable the edit Text field
                editText3.setEnabled(false);

            } else {
                editText3.setBackgroundColor(0xFFFF0000);
            }
        }

        if (editText1 != null && editText2 != null && editText3 != null) {
            if (answer1.equals(editText1.getText().toString()) && answer2.equals(editText2.getText().toString()) && answer3.equals(editText3.getText().toString())) {
                CorrectAns.setText("CORRECT!");
                CorrectAns.setTextColor(Color.parseColor("0xff00ff00"));

            }
        }

        // point 2 answers
        if (answer1.equals(editText1.getText().toString()) && (answer2.equals(editText2.getText().toString()))) {
            points.setText(Integer.toString(2));
        }
        if (answer1.equals(editText1.getText().toString()) && (answer3.equals(editText3.getText().toString()))) {
            points.setText(Integer.toString(2));
        }
        if (answer2.equals(editText2.getText().toString()) && (answer3.equals(editText3.getText().toString()))) {
            points.setText(Integer.toString(2));
        }
        //point 3 for correct all answers
        if (answer1.equals(editText1.getText().toString()) && (answer3.equals(editText3.getText().toString()) && (answer2.equals(editText2.getText().toString())))) {
            points.setText(Integer.toString(3));
        }

        if (totalAttempts == 3) {
            if (editText1 != null && editText2 != null && editText3 != null) {
                if (!answer1.equals(editText1.getText().toString()) || !answer2.equals(editText2.getText().toString()) || !answer3.equals(editText3.getText().toString())) {
                    Wrong.setText("WRONG!");
                    Wrong.setTextColor(Color.parseColor("#FF0000"));

                    if (!answer1.equals (editText1.getText().toString())) {
                        Correct1.setText(answer1);
                        Correct1.setTextColor(Color.parseColor("#FFD300"));

                    }
                    if (!answer2.equals(editText2.getText().toString())) {
                        Correct2.setText(answer2);
                        Correct2.setTextColor(Color.parseColor("#FFD300"));
                    }

                    if (!answer3.equals(editText3.getText().toString())) {
                        Correct3.setText(answer3);
                        Correct3.setTextColor(Color.parseColor("#FFD300"));

                    }
                }
            }

            nextBtn();
        }


    }

    public void  nextBtn(){
        btnSubmit.setText("Next");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdvancedLevel.class);
                /*intent.putExtra("valueOfSwitch", isTimerChecked);*/
                startActivity(intent);
            }
        });
    }


}

