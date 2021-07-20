package com.example.vijay.simplegame;

import android.app.Dialog;
import android.content.Intent;
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

import static com.example.vijay.simplegame.R.drawable.*;

public class Level1GamePlay extends AppCompatActivity implements View.OnClickListener {
    ImageView imgcard1, imgcard2, imgcard3, imgcard4;
    Button btn1, btn2, btn3, btn4;
    Button retryBtn;
    TextView levelcounting, coinslabel;
    //First answer checking variables
    Boolean answerChecking1 = false;
    Boolean answerChecking2 = false;
    Boolean answerChecking3 = false;
    Boolean answerChecking4 = false;
    //Second answer checking variables
    Boolean answerCheckingBtnChanged1 = false;
    Boolean answerCheckingBtnChanged2 = false;
    Boolean answerCheckingBtnChanged3 = false;
    Boolean answerCheckingBtnChanged4 = false;
    //Level 2 variables
    Boolean answerChecking11 = false;
    Boolean answerChecking22 = false;
    Boolean answerChecking33 = false;
    Boolean answerChecking44 = false;

    Dialog myDialog;
    public Play_Screen play_screen;
    int levelCount; //customize for testing
    int coins;
    int[][] imageArray = {
            {ic_001_squirrel, ic_002_gorilla, ic_003_tortoise, ic_004_rat},
            {ic_005_bear, ic_006_cow, ic_007_horse, ic_008_monkey},
            {ic_009_elephant, ic_010_fish, ic_011_cat, ic_012_tiger},
            {ic_013_seahorse, ic_014_snake, ic_015_camel, ic_016_pig},
            {ic_017_frog, ic_018_rhinoceros, ic_019_lion, ic_020_kangaroo},
            {ic_021_dog, ic_022_crocodile, ic_023_rabbit, ic_024_whale},
            {ic_026_scorpion, ic_027_bat, ic_028_octopus, ic_029_dinosaur},
            {ic_030_crab, ic_031_snail, ic_032_swan, ic_033_chameleon},
            {ic_034_crow, ic_035_pigeon, ic_036_deer, ic_037_cock},
            {ic_038_cockroach, ic_039_penguin, ic_040_sheep, ic_041_graffle},
            {ic_011_cat_color, ic_012_tiger_color, ic_013_seahorse_color, ic_014_snake_color},
            {ic_015_camel_color, ic_016_pig_color, ic_017_frog_color, ic_018_rhinoceros_color},
            {ic_019_lion_color, ic_020_kangaroo_color, ic_011_cat_color_1, ic_012_tiger_color_1},
            {ic_013_seahorse_color_1, ic_014_snake_color_1, ic_015_camel_color_1, ic_016_pig_color_1},
            {ic_017_frog_color_1, ic_018_rhinoceros_color_1, ic_019_lion_color_1, ic_020_kangaroo_color_1},
            {ic_032_swan_color, ic_033_chameleon_color, ic_034_crow_color, ic_035_pigeon_color},
            {ic_036_deer_color, ic_037_cock_color, ic_038_cockroach_color, ic_039_penguin_color},
            {ic_040_sheep_color, ic_041_graffle_color, ic_032_swan_color_1, ic_033_chameleon_color_1},
            {ic_034_crow_color_1, ic_035_pigeon_color_1, ic_036_deer_color_1, ic_037_cock_color_1},
            {ic_038_cockroach_color_1, ic_039_penguin_color_1, ic_040_sheep_color_1, ic_041_graffle_color_1},
    };
    String[][] answerArray = {
            {"Squirrel", "Gorilla", "Tortoise", "Rat"},
            {"Bear", "Cow", "Horse", "Monkey"},
            {"Elephant", "Fish", "Cat", "Tiger"},
            {"Seahorse", "Snake", "Camel", "Pig"},
            {"Frog", "Rhinoceros", "Lion", "Kangaroo"},
            {"Dog", "Crocodile", "Rabbit", "Whale"},
            {"Scorpion", "Bat", "Octopus", "Dinosaur"},
            {"Crab", "Snail", "Swan", "Chameleon"},
            {"Crow", "Pigeon", "Deer", "Cock"},
            {"Cockroach", "Penguin", "Sheep", "Graffle"},
            //Second Level
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
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            //Second level
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
    private boolean secondLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_game_play);
        levelcounting = (TextView) findViewById(R.id.levelLabel);
        coinslabel = (TextView) findViewById(R.id.coinsLabel);

        imgcard1 = (ImageView) findViewById(R.id.imgCard1);
        imgcard2 = (ImageView) findViewById(R.id.imgCard2);
        imgcard3 = (ImageView) findViewById(R.id.imgCard3);
        imgcard4 = (ImageView) findViewById(R.id.imgCard4);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        retryBtn = (Button) findViewById(R.id.retryBtn);
        btn1.setOnClickListener(Level1GamePlay.this);
        btn2.setOnClickListener(Level1GamePlay.this);
        btn3.setOnClickListener(Level1GamePlay.this);
        btn4.setOnClickListener(Level1GamePlay.this);
        imgcard1.setImageResource(ic_025_idea);
        imgcard2.setImageResource(ic_025_idea);
        imgcard3.setImageResource(ic_025_idea);
        imgcard4.setImageResource(ic_025_idea);

        myDialog = new Dialog(this);
        Intent intent = getIntent();
        if(intent != null) {
            levelCount = intent.getIntExtra("levelCount",0);
            Log.w("levelCount", String.valueOf(levelCount));
            coins = intent.getIntExtra("coins",0);
        }
        pageRefresh(levelCount);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coins >= 30) {
                    pageRefresh(levelCount);
                    coins = coins - 30;
                    updateLevelandCoins(levelCount, coins);
                } else {
                    Toast.makeText(Level1GamePlay.this, "You don't have enough coins", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        if(secondLevel){
            if (Id == R.id.btn1) {
                if (answerChecking33 == true) {
                    answerChecking33 = false;
                    answerChecking44 = true;
                    btn1.setVisibility(view.INVISIBLE);
                } else {
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
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
                    pageRefresh(levelCount);
                    btn2.setVisibility(view.INVISIBLE);
                } else {
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                    if (coins >= 20){
                        coins = coins - 20;
                    } else {
                        gameOver(levelCount,coins);
                    }
                }
            }
        }else{
            if (Id == R.id.btn1) {
                if (answerChecking4 == true) {
                    btn1.setVisibility(view.INVISIBLE);
                    answerChecking4 = false;
                    levelCount++;
                    coins = coins + 40;
                    pageRefresh(levelCount);
                } else if (answerCheckingBtnChanged3 == true) {
                    btn1.setVisibility(view.INVISIBLE);
                    answerCheckingBtnChanged3 = false;
                    answerCheckingBtnChanged4 = true;
                } else {
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                    if (coins >= 20){
                        coins = coins - 20;
                        updateLevelandCoins(levelCount, coins);
                    } else {
                        gameOver(levelCount, coins);
                    }
                }
            } else if (Id == R.id.btn2) {
                if (answerChecking1 == true) {
                    btn2.setVisibility(view.INVISIBLE);
                    answerChecking1 = false;
                    answerChecking2 = true;
                } else if (answerCheckingBtnChanged2 == true) {
                    btn2.setVisibility(view.INVISIBLE);
                    answerCheckingBtnChanged2 = false;
                    answerCheckingBtnChanged3 = true;
                } else {
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                    if (coins >= 20){
                        coins = coins - 20;
                        updateLevelandCoins(levelCount, coins);
                    } else {
                        gameOver(levelCount, coins);
                    }
                }
            } else if (Id == R.id.btn3) {
                if (answerChecking3 == true) {
                    btn3.setVisibility(view.INVISIBLE);
                    answerChecking3 = false;
                    answerChecking4 = true;
                } else if (answerCheckingBtnChanged1 == true) {
                    btn3.setVisibility(view.INVISIBLE);
                    answerCheckingBtnChanged1 = false;
                    answerCheckingBtnChanged2 = true;
                } else {
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                    if (coins >= 20){
                        coins = coins - 20;
                        updateLevelandCoins(levelCount, coins);
                    } else {
                        gameOver(levelCount, coins);
                    }
                }
            } else if (Id == R.id.btn4) {
                if (answerChecking2 == true) {
                    btn4.setVisibility(view.INVISIBLE);
                    answerChecking2 = false;
                    answerChecking3 = true;
                } else if (answerCheckingBtnChanged4 == true) {
                    btn4.setVisibility(view.INVISIBLE);
                    answerCheckingBtnChanged4 = false;
                    levelCount++;
                    coins = coins + 40;
                    pageRefresh(levelCount);
                } else {
                    Toast.makeText(Level1GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
                    if (coins >= 20){
                        coins = coins - 20;
                        updateLevelandCoins(levelCount, coins);
                    } else {
                        gameOver(levelCount, coins);
                    }
                }
            }
        }
    }

    public void pageRefresh(final int level) {
        updateLevelandCoins(level, coins);
        if (levelCount <= 9) {

            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
            btn4.setVisibility(View.INVISIBLE);

            btn1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            btn2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            btn3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            btn4.setBackgroundColor(getResources().getColor(R.color.colorGrey));

            btn1.setClickable(true);
            btn2.setClickable(true);
            btn3.setClickable(true);
            btn4.setClickable(true);

            new CountDownTimer(1000, 1000) { // 5000 = 5 sec

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard1.setImageResource(imageArray[level][0]);
                    if (levelCount <= 3) {
                        btn3.setText(answerArray[level][0]);
                        answerCheckingBtnChanged1 = true;
                        btn3.setBackground(getResources().getDrawable(yes_button_style));
                    } else {
                        btn2.setText(answerArray[level][0]);
                        btn2.setBackground(getResources().getDrawable(yes_button_style));
                        answerChecking1 = true;
                    }
                }
            }.start();
            new CountDownTimer(2500, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard1.setImageResource(ic_025_idea);
                    imgcard2.setImageResource(imageArray[level][1]);
                    if (levelCount <= 3) {
                        btn2.setText(answerArray[level][1]);
                        btn2.setBackground(getResources().getDrawable(yes_button_style));
                    } else {
                        btn4.setText(answerArray[level][1]);
                        btn4.setBackground(getResources().getDrawable(yes_button_style));
                    }
                }
            }.start();
            new CountDownTimer(4500, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard2.setImageResource(ic_025_idea);
                    imgcard3.setImageResource(imageArray[level][2]);
                    if (levelCount <= 3) {
                        btn1.setText(answerArray[level][2]);
                        btn1.setBackground(getResources().getDrawable(yes_button_style));
                    } else {
                        btn3.setText(answerArray[level][2]);
                        btn3.setBackground(getResources().getDrawable(yes_button_style));
                    }
                }
            }.start();
            new CountDownTimer(6500, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard4.setImageResource(imageArray[level][3]);
                    imgcard3.setImageResource(ic_025_idea);
                    if (levelCount <= 3) {
                        btn4.setText(answerArray[level][3]);
                        btn4.setBackground(getResources().getDrawable(yes_button_style));
                    } else {
                        btn1.setText(answerArray[level][3]);
                        btn1.setBackground(getResources().getDrawable(yes_button_style));
                    }
                }
            }.start();
            new CountDownTimer(8500, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard4.setImageResource(ic_025_idea);
                    btn1.setVisibility(View.VISIBLE);
                    btn2.setVisibility(View.VISIBLE);
                    btn3.setVisibility(View.VISIBLE);
                    btn4.setVisibility(View.VISIBLE);
                }
            }.start();

        } else {
            pageRefreshLevel2(levelCount);
            secondLevel = true;
        }
    }
    public void pageRefreshLevel2(final int level) {
        updateLevelandCoins(level, coins);
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
                    imgcard1.setImageResource(imageArray[level][0]);
                    btn3.setText(answerArray[level][0]);
                    btn3.setBackgroundColor(Color.parseColor(answerButtonColor[level][0]));
                    btn3.setBackground(getResources().getDrawable(yes_button_style));
                }
            }.start();
            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    imgcard2.setImageResource(imageArray[level][1]);
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
                    imgcard3.setImageResource(imageArray[level][2]);
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
                    imgcard4.setImageResource(imageArray[level][3]);
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
                    secondLevel = false;
                }
            });
            myDialog.show();
        }
    }
    public void gameOver(int level, int coins){
        play_screen.firstTime = true;
        Intent menu = new Intent(Level1GamePlay.this, Play_Screen.class);
        menu.putExtra("AnimalLevel",level);
        menu.putExtra("coins_ani", coins);
        menu.putExtra("Unique_id","Animal_Activity");
        startActivity(menu);
        finish();
    }
    public void updateLevelandCoins(int level, int coins){
        levelcounting.setText("Level: " + level);
        coinslabel.setText(String.valueOf(coins));
    }
    @Override
    public void onBackPressed() {
        gameOver(levelCount, coins);
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
