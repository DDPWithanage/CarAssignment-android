package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IdentifyCarImage extends AppCompatActivity {
    //Define all the variables
    ImageView image1,image2,image3, home;
    Button buttonNext;
    TextView textAnswer, countText;
    TextView textQuestion;
    Random random;
    int randCar;
    Integer[] carRandomList= new Integer[3];
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_image);

        Intent in = getIntent();
//        isTimerChecked = in.getExtras().getBoolean("valueOfSwitch");

        countText = findViewById(R.id.countText1);

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
                public void onFinish() {

                            if (randCar == carRandomList[0]) {
                                textAnswer.setText("CORRECT!");
                                textAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                            } else {

                                textAnswer.setText("WRONG!");
                                textAnswer.setTextColor(Color.parseColor("#FF0000"));
                            }



                            if (randCar == carRandomList[1]) {
                                textAnswer.setText("CORRECT!");
                                textAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                            } else {

                                textAnswer.setText("WRONG!");
                                textAnswer.setTextColor(Color.parseColor("#FF0000"));
                            }


                            if (randCar == carRandomList[2]) {
                                textAnswer.setText("CORRECT!");
                                textAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                            } else {

                                textAnswer.setText("WRONG!");
                                textAnswer.setTextColor(Color.parseColor("#FF0000"));
                            }

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
            //Create a array called CarBrandsArray in String.xml file to get the string names of the above cars
            String[] carImageArray = getResources().getStringArray(R.array.CarBrandsArray);

            //Create random list to get random images
            random = new Random();

            //use id for get image views, buttons, edit Text views
            textQuestion = findViewById(R.id.txtViewQuestion);
            randCar = random.nextInt(carImageArray.length);
            textQuestion.setText(carImageArray[randCar]);

            //int ImageArray = random.nextInt(image.length);

            carRandomList[0] = randCar;
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

            textAnswer = findViewById(R.id.txtViewAnswer);
            image1 = findViewById(R.id.imageV1);
            image2 = findViewById(R.id.imageV2);
            image3 = findViewById(R.id.imageV3);
            buttonNext = findViewById(R.id.ButtonNext);
            home = findViewById(R.id.homeButton2);

            final List list = Arrays.asList(carRandomList);
            Collections.shuffle(list);
            list.toArray(carRandomList);

            if ((image1 != image2) || (image1 != image3) || (image2 != image3)) {
                image1.setImageResource(image[carRandomList[0]]);
                image2.setImageResource(image[carRandomList[1]]);
                image3.setImageResource(image[carRandomList[2]]);
            }

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (randCar == carRandomList[0]) {
                        //Define correct
                        textAnswer.setText("CORRECT!");
                        textAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                    } else {
                        //Define wrong
                        textAnswer.setText("WRONG!");
                        textAnswer.setTextColor(Color.parseColor("#FF0000"));
                    }
                    terminateButton();
                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (randCar == carRandomList[1]) {
                        textAnswer.setText("CORRECT!");
                        textAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                    } else {

                        textAnswer.setText("WRONG!");
                        textAnswer.setTextColor(Color.parseColor("#FF0000"));
                    }
                    terminateButton();
                }
            });

            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (randCar == carRandomList[2]) {
                        textAnswer.setText("CORRECT!");
                        textAnswer.setTextColor(Color.parseColor("#1BBEA1"));

                    } else {

                        textAnswer.setText("WRONG!");
                        textAnswer.setTextColor(Color.parseColor("#FF0000"));
                    }
                    terminateButton();
                }
            });

            if(isTimerChecked){
                timer.cancel();
            }

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getApplicationContext(), IdentifyCarImage.class);
                    in.putExtra("valueOfSwitch", isTimerChecked);
                    startActivity(in);
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
//enabled buttons
    public void terminateButton() {
        image1.setEnabled(false);
        image2.setEnabled(false);
        image3.setEnabled(false);

    }


}
