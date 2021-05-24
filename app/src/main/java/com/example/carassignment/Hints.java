
package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Hints extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Define all the variables
    ImageView carImage, home;
    Button btnSubmit;
    TextView viewHints, Ans;
    EditText getHints;
    int Image;
    String Array[];
    String input = "";
    List<String> hintCars;
    int count = 0;
    boolean isTimerChecked = false;
    CountDownTimer timer;
    long timeInMilliSeconds  = 21000; //20 minutes in milliseconds
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
        setContentView(R.layout.activity_hints);

            //use id for get image views, buttons, edit Text
            carImage = findViewById(R.id.hintImage);
            btnSubmit = findViewById(R.id.buttonSubmit);
            viewHints = findViewById(R.id.viewHints);
            home = findViewById(R.id.homeButton);
            getHints = findViewById(R.id.getHints);
            hintCars = Arrays.asList(getResources().getStringArray(R.array.CarNames));
            Array = getResources().getStringArray(R.array.CarNames);
            Ans = findViewById(R.id.TxtViewAns);

            //Create random list to get random images
            Random random = new Random();
            Image = random.nextInt(image.length);
            carImage.setImageResource((image[Image]));

            String FinalWord = hintCars.get(Image);
            int word = Array[Image].length();
            String[] marks = new String[word];

            viewHints.setText("");
            for (int i = 0; i < word; i++) {
                marks[i] = " - ";
                viewHints.append(marks[i] + " ");
            }
           input = getHints.getText().toString();

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashSet<Character> guessLetter = new HashSet<>();
                    String CurrentWord = "";
                    input = getHints.getText().toString().toLowerCase();
                    if (input.length() != 1) {
                        Ans.setText("Invalid");
                    } else if (!FinalWord.contains(input)) {
                        Ans.setText("WRONG!");
                        Ans.setTextColor(Color.parseColor("#FFD300"));
                        count++;
                    } else if (FinalWord.contains(input)) {
                        for (int i = 0; i < FinalWord.length(); i++) {
                            if (FinalWord.charAt(i) == input.charAt(0)) {
                                marks[i] = "" + input.charAt(0);
                            }
                            CurrentWord += marks[i];
                        }
                    }
                    viewHints.setText("");
                    for (int i = 0; i < marks.length; i++) {
                        viewHints.append(marks[i] + " ");

                    }

                    if(CurrentWord.equals(FinalWord)){
                        Ans.setText("CORRECT!");
                        Ans.setTextColor(Color.parseColor("#1BBEA1"));

                        nextBtn();
                    }

                    if(count == 3){
                        Ans.setText("WRONG!");
                        Ans.setTextColor(Color.parseColor("#FF0000"));
                        nextBtn();
                    }
                }
            });

            //Create a home button and add functions to get back to the home screen from hints screen
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void  nextBtn(){
        btnSubmit.setText("Next");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hints.class);
                /*intent.putExtra("valueOfSwitch", isTimerChecked);*/
                startActivity(intent);
            }
        });
    }

}