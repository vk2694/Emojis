package com.example.vijay.simplegame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.vijay.simplegame.R.drawable.yes_button_style;

public class Level2GamePlay extends AppCompatActivity implements View.OnClickListener {
    ImageView imgcard1, imgcard2, imgcard3, imgcard4;
    Button btn1, btn2, btn3, btn4, retryBtn;
    TextView levelcounting, coinslabel;
    Boolean answerChecking11 = false;
    Boolean answerChecking22 = false;
    Boolean answerChecking33 = false;
    Boolean answerChecking44 = false;
    Dialog myDialog;
    int levelCount;
    int coins;
    public Play_Screen play_screen;
    SharedPreferences sharedPref;
    int[][] imgArray = {
            {R.drawable.ic_011_cat_color, R.drawable.ic_012_tiger_color, R.drawable.ic_013_seahorse_color, R.drawable.ic_014_snake_color},
            {R.drawable.ic_015_camel_color, R.drawable.ic_016_pig_color, R.drawable.ic_017_frog_color, R.drawable.ic_018_rhinoceros_color},
            {R.drawable.ic_019_lion_color, R.drawable.ic_020_kangaroo_color, R.drawable.ic_011_cat_color_1, R.drawable.ic_012_tiger_color_1},
            {R.drawable.ic_013_seahorse_color_1, R.drawable.ic_014_snake_color_1, R.drawable.ic_015_camel_color_1, R.drawable.ic_016_pig_color_1},
            {R.drawable.ic_017_frog_color_1, R.drawable.ic_018_rhinoceros_color_1, R.drawable.ic_019_lion_color_1, R.drawable.ic_020_kangaroo_color_1},
            {R.drawable.ic_032_swan_color, R.drawable.ic_033_chameleon_color, R.drawable.ic_034_crow_color, R.drawable.ic_035_pigeon_color},
            {R.drawable.ic_036_deer_color, R.drawable.ic_037_cock_color, R.drawable.ic_038_cockroach_color, R.drawable.ic_039_penguin_color},
            {R.drawable.ic_040_sheep_color, R.drawable.ic_041_graffle_color, R.drawable.ic_032_swan_color_1, R.drawable.ic_033_chameleon_color_1},
            {R.drawable.ic_034_crow_color_1, R.drawable.ic_035_pigeon_color_1, R.drawable.ic_036_deer_color_1, R.drawable.ic_037_cock_color_1},
            {R.drawable.ic_038_cockroach_color_1, R.drawable.ic_039_penguin_color_1, R.drawable.ic_040_sheep_color_1, R.drawable.ic_041_graffle_color_1},
    };
    String[][] answerArray = {
            {"Cat", "Tiger", "Seahorse", "Snake"},
            {"Camel", "Pig", "Frog", "Rhinoceros"},
            {"Lion", "Kangaroo", "Cat", "Tiger"},
            {"Seahorse", "Snake", "Camel", "Pig"},
            {"Frog", "Rhinoceros", "Lion", "Kangaroo"},
            {"Swan", "Chameleon", "Crow", "Pigeon"},
            {"Deer", "Cock", "Cockroach", "Penguin"},
            {"Sheep", "Graffle", "Swan", "Chameleon"},
            {"Crow", "Pigeon", "Deer", "Cock"},
            {"Cockroach", "Penguin", "Sheep", "Graffle"},
    };
    String[][] answerButtonColor = {
            {"#DA7E6A", "#42C3EC", "#EC7042", "#EC42D0"},
            {"#E25836", "#E26236", "#3753C6", "#AD5705"},
            {"#D13512", "#13DC0D", "#63D327", "#4539C3"},
            {"#ECD542", "#DC2626", "#36C8E2", "#EE1218"},
            {"#22E64F", "#0C0776", "#1235D1", "#DC550D"},
            {"#0DDCD3", "#07AB07", "#99956A", "#53E9C0"},
            {"#E97C53", "#E98A53", "#52BE80", "#B2BABB"},
            {"#E67E22", "#BA4A00", "#0D23DC", "#DCCC0A"},
            {"#53E9C0", "#5367E9", "#5EE953", "#E13943"},
            {"#EC7063", "#DC7633", "#A569BD", "#99A3A4"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_game_play);

        levelcounting = (TextView) findViewById(R.id.levelCount);

        retryBtn = (Button) findViewById(R.id.retryBtn);
        coinslabel = (TextView) findViewById(R.id.coinsLabel);
        imgcard1 = (ImageView) findViewById(R.id.imgCard1);
        imgcard2 = (ImageView) findViewById(R.id.imgCard2);
        imgcard3 = (ImageView) findViewById(R.id.imgCard3);
        imgcard4 = (ImageView) findViewById(R.id.imgCard4);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(Level2GamePlay.this);
        btn2.setOnClickListener(Level2GamePlay.this);
        btn3.setOnClickListener(Level2GamePlay.this);
        btn4.setOnClickListener(Level2GamePlay.this);

        imgcard1.setImageResource(R.drawable.ic_025_idea);
        imgcard2.setImageResource(R.drawable.ic_025_idea);
        imgcard3.setImageResource(R.drawable.ic_025_idea);
        imgcard4.setImageResource(R.drawable.ic_025_idea);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras == null) {
            levelCount = intent.getIntExtra("levelCount",0);
            coins = intent.getIntExtra("coins",0);
        }
        myDialog = new Dialog(this);

        pageRefreshLevel2(levelCount);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coins >= 30) {
                    pageRefreshLevel2(levelCount);
                    coins = coins - 30;
                } else {
                    Toast.makeText(Level2GamePlay.this, "You don't have enough coins", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        if (Id == R.id.btn1) {
            if (answerChecking33 == true) {
                answerChecking33 = false;
                answerChecking44 = true;
                btn1.setVisibility(view.INVISIBLE);
            } else {
                Toast.makeText(Level2GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                if (coins >= 20){
                    coins = coins - 20;
                } else {
                    gameOver(levelCount,coins);
                }
            }
        } else if (Id == R.id.btn2) {
            if (answerChecking44 == true) {
                answerChecking44 = false;
                levelCount++;
                coins = coins + 20;
                pageRefreshLevel2(levelCount);
                btn2.setVisibility(view.INVISIBLE);
            } else {
                Toast.makeText(Level2GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                if (coins >= 20){
                    coins = coins - 20;
                } else {
                    gameOver(levelCount,coins);
                }
            }
        } else if (Id == R.id.btn3) {
            if (answerChecking11 == true) {
                btn3.setClickable(false);
                answerChecking11 = false;
                answerChecking22 = true;
                btn3.setVisibility(view.INVISIBLE);
            } else {
                Toast.makeText(Level2GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                if (coins >= 20){
                    coins = coins - 20;
                } else {
                    gameOver(levelCount,coins);
                }
            }
        } else if (Id == R.id.btn4) {
            if (answerChecking22 == true) {
                answerChecking22 = false;
                answerChecking33 = true;
                btn4.setVisibility(view.INVISIBLE);
            } else {
                Toast.makeText(Level2GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                if (coins >= 20){
                    coins = coins - 20;
                } else {
                    gameOver(levelCount,coins);
                }
            }
        }
    }

    public void pageRefreshLevel2(final int level) {
        levelcounting.setText("Level: " + levelCount);
        coinslabel.setText(String.valueOf(coins));
        if (levelCount <= 18) {

            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
            btn4.setVisibility(View.INVISIBLE);

            btn1.setClickable(true);
            btn2.setClickable(true);
            btn3.setClickable(true);
            btn4.setClickable(true);

            new CountDownTimer(1000, 1000) { // 5000
                // = 5 sec

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard1.setImageResource(imgArray[level][0]);
                    btn3.setText(answerArray[level][0]);
                    btn3.setBackgroundColor(Color.parseColor(answerButtonColor[level][0]));
                    btn3.setBackground(getResources().getDrawable(yes_button_style));
                }
            }.start();
            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard2.setImageResource(imgArray[level][1]);
                    imgcard1.setImageResource(R.drawable.ic_025_idea);
                    btn4.setText(answerArray[level][1]);
                    btn4.setBackgroundColor(Color.parseColor(answerButtonColor[level][1]));
                    btn4.setBackground(getResources().getDrawable(yes_button_style));
                }
            }.start();
            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard3.setImageResource(imgArray[level][2]);
                    imgcard2.setImageResource(R.drawable.ic_025_idea);
                    answerChecking11 = true;
                    btn1.setText(answerArray[level][2]);
                    btn1.setBackgroundColor(Color.parseColor(answerButtonColor[level][2]));
                    btn1.setBackground(getResources().getDrawable(yes_button_style));
                }
            }.start();
            new CountDownTimer(7000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard4.setImageResource(imgArray[level][3]);
                    imgcard3.setImageResource(R.drawable.ic_025_idea);
                    btn2.setText(answerArray[level][3]);
                    btn2.setBackgroundColor(Color.parseColor(answerButtonColor[level][3]));
                    btn2.setBackground(getResources().getDrawable(yes_button_style));
                }
            }.start();
            new CountDownTimer(9000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard4.setImageResource(R.drawable.ic_025_idea);
                    btn1.setVisibility(View.VISIBLE);
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                }
            }.start();

        } else {
            Button nextLevelBtn;
            myDialog.setContentView(R.layout.level1complete);
            nextLevelBtn = (Button) myDialog.findViewById(R.id.nextLevelBtn);
            nextLevelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameOver(levelCount,coins);
                }
            });
            myDialog.show();
        }
    }
    public void gameOver(int level, int coins){
        Log.w("Level", String.valueOf(level));
        play_screen.firstTime = true;
        Intent menu = new Intent(Level2GamePlay.this, Play_Screen.class);
        menu.putExtra("AnimalLevel",level);
        menu.putExtra("coins", coins);
        startActivity(menu);
        finish();
    }
    @Override
    public void onBackPressed() {
        gameOver(levelCount, coins);
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
