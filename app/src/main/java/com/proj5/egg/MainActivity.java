package com.proj5.egg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Tyler Tracey and Patrick Hennis
 */
public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> ids = new ArrayList<>();

    public static SharedPreferences preferences;

    Button addOne;
    Button addTwo;
    Button subOne;
    Button makeBreakfast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);

        //Initializes buttons
        addOne = (Button) findViewById(R.id.add_one);
        addTwo = (Button) findViewById(R.id.add_two);
        subOne = (Button) findViewById(R.id.subtract_one);
        makeBreakfast = (Button) findViewById(R.id.make_bfast);


        addOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intentCaller(CONSTANTS.CONSTANT_ONE_EGG);
            }
        });

        addTwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intentCaller(CONSTANTS.CONSTANT_TWO_EGGS);
            }
        });

        subOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intentCaller(CONSTANTS.CONSTANT_MINUS_EGG);
            }
        });

        makeBreakfast.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intentCaller(CONSTANTS.CONSTANT_MAKE_BREAKFAST);
            }
        });
    }

    private void intentCaller(int num) {
        Intent intent = new Intent(getApplicationContext(), EggBR.class);
        intent.putExtra("eggCount", num);
        sendBroadcast(intent);
    }

}
