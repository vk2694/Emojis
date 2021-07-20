package com.example.vijay.simplegame;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.vijay.simplegame.R.drawable.yes_button_style;

public class Level3GamePlay extends AppCompatActivity implements View.OnClickListener {
    ImageView imgcard1, imgcard2, imgcard3, imgcard4;
    Button btn1, btn2, btn3, btn4;
    TextView levelcounting;
    //First answer checking variables
    Boolean answerChecking1 = false;
    Boolean answerChecking2 = false;
    Boolean answerChecking3 = false;
    Boolean answerChecking4 = false;

    int getRandomImg1;
    int getRandomImg2;
    int getRandomImg3;
    int getRandomImg4;

    Dialog myDialog;

    int levelCount = 1; //customize for testing
    int[] imgArraySession1 = {
            R.drawable.ic_001_squirrel, R.drawable.ic_002_gorilla, R.drawable.ic_003_tortoise, R.drawable.ic_004_rat, R.drawable.ic_005_bear,
            R.drawable.ic_006_cow, R.drawable.ic_007_horse, R.drawable.ic_008_monkey, R.drawable.ic_009_elephant, R.drawable.ic_010_fish
    };
    int[] imgArraySession2 = {
            R.drawable.ic_011_cat, R.drawable.ic_012_tiger, R.drawable.ic_013_seahorse, R.drawable.ic_014_snake, R.drawable.ic_015_camel,
            R.drawable.ic_016_pig, R.drawable.ic_017_frog, R.drawable.ic_018_rhinoceros, R.drawable.ic_019_lion, R.drawable.ic_020_kangaroo
    };
    int[] imgArraySession3 = {
            R.drawable.ic_021_dog, R.drawable.ic_022_crocodile, R.drawable.ic_023_rabbit, R.drawable.ic_024_whale, R.drawable.ic_026_scorpion,
            R.drawable.ic_027_bat, R.drawable.ic_028_octopus, R.drawable.ic_029_dinosaur, R.drawable.ic_030_crab, R.drawable.ic_031_snail
    };
    int[] imgArraySession4 = {
            R.drawable.ic_032_swan, R.drawable.ic_033_chameleon, R.drawable.ic_034_crow, R.drawable.ic_035_pigeon, R.drawable.ic_036_deer,
            R.drawable.ic_037_cock, R.drawable.ic_038_cockroach, R.drawable.ic_039_penguin, R.drawable.ic_040_sheep, R.drawable.ic_041_graffle
    };
    String[] inputArraySession1 = {
            "Squirrel", "Gorilla", "Tortoise", "Rat", "Bear", "Cow", "Horse", "Monkey", "Elephant", "Fish"
    };
    String[] inputArraySession2 = {
            "Cat", "Tiger", "Seahorse", "Snake", "Camel", "Pig", "Frog", "Rhinoceros", "Lion", "Kangaroo"
    };
    String[] inputArraySession3 = {
            "Dog", "Crocodile", "Rabbit", "Whale", "Scorpion", "Bat", "Octopus", "Dinosaur", "Crab", "Snail"
    };
    String[] inputArraySession4 = {
            "Swan", "Chameleon", "Crow", "Pigeon", "Deer", "Cock", "Cockroach", "Penguin", "Sheep", "Graffle"
    };
    String[] wrongAnswers = {
            "Artichoke", "Asparagus", "Beetroot", "BellPepper", "Broccoli", "BrusselsSprout", "Cabbage", "Carrot", "Cauliflower", "Celery", "Corn",
            "Cucumber", "Eggplant", "GreenBean", "Lettuce", "Mushroom", "Onion", "Pea", "Potato", "Pumpkin", "radish", "SweetPotato", "Tomato", "Zucchini"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_game_play);
        levelcounting = (TextView) findViewById(R.id.levelCount);

        imgcard1 = (ImageView) findViewById(R.id.imgCard1);
        imgcard2 = (ImageView) findViewById(R.id.imgCard2);
        imgcard3 = (ImageView) findViewById(R.id.imgCard3);
        imgcard4 = (ImageView) findViewById(R.id.imgCard4);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(Level3GamePlay.this);
        btn2.setOnClickListener(Level3GamePlay.this);
        btn3.setOnClickListener(Level3GamePlay.this);
        btn4.setOnClickListener(Level3GamePlay.this);

        imgcard1.setImageResource(R.drawable.ic_025_idea);
        imgcard2.setImageResource(R.drawable.ic_025_idea);
        imgcard3.setImageResource(R.drawable.ic_025_idea);
        imgcard4.setImageResource(R.drawable.ic_025_idea);

        myDialog = new Dialog(this);

        pageRefresh();
    }

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        Random random = new Random();
        int wrongRandom = random.nextInt(23);
        if (Id == R.id.btn1) {
            if (answerChecking4 == true) {
                answerChecking4 = false;
                levelCount++;
                pageRefresh();
            } else {
                Toast.makeText(Level3GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
            }
        } else if (Id == R.id.btn2) {
            if (answerChecking1 == true) {
                answerChecking1 = false;
                answerChecking2 = true;
                btn4.setText(inputArraySession2[getRandomImg2]);
//                if (wrongRandom < 18){
//                    btn1.setText(wrongAnswers[wrongRandom+1]);
//                    btn2.setText(wrongAnswers[wrongRandom+2]);
//                    btn3.setText(wrongAnswers[wrongRandom+3]);
//                } else
                    {
                    btn1.setText(wrongAnswers[wrongRandom]);
                    btn2.setText(wrongAnswers[wrongRandom]);
                    btn3.setText(wrongAnswers[wrongRandom]);
                }
            } else {
                Toast.makeText(Level3GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
            }
        } else if (Id == R.id.btn3) {
            if (answerChecking3 == true) {
                answerChecking3 = false;
                answerChecking4 = true;
                btn1.setText(inputArraySession4[getRandomImg4]);
//                if (wrongRandom < 18){
//                    btn3.setText(wrongAnswers[wrongRandom+0]);
//                    btn2.setText(wrongAnswers[wrongRandom+1]);
//                    btn4.setText(wrongAnswers[wrongRandom+3]);
//                } else
                    {
                    btn3.setText(wrongAnswers[wrongRandom]);
                    btn2.setText(wrongAnswers[wrongRandom]);
                    btn4.setText(wrongAnswers[wrongRandom]);
                }
            } else {
                Toast.makeText(Level3GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
            }
        } else if (Id == R.id.btn4) {
            if (answerChecking2 == true) {
                answerChecking2 = false;
                answerChecking3 = true;
                btn3.setText(inputArraySession3[getRandomImg3]);
//                if (wrongRandom < 18){
//                    btn1.setText(wrongAnswers[wrongRandom+2]);
//                    btn2.setText(wrongAnswers[wrongRandom+3]);
//                    btn4.setText(wrongAnswers[wrongRandom+4]);
//                } else
                    {
                    btn1.setText(wrongAnswers[wrongRandom]);
                    btn2.setText(wrongAnswers[wrongRandom]);
                    btn4.setText(wrongAnswers[wrongRandom]);
                }
            } else {
                Toast.makeText(Level3GamePlay.this, "Wrong Answer Clicked", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void pageRefresh() {
        levelcounting.setText("Level:" + levelCount);
        if (levelCount <= 5) {

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
            Random random = new Random();
            final int wrongRandom = random.nextInt(23);

            new CountDownTimer(1000, 1000) { // 5000 = 5 sec

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    Random random = new Random();
                    getRandomImg1 = random.nextInt(9);
                    imgcard1.setImageResource(imgArraySession1[getRandomImg1]);
                    btn2.setText(inputArraySession1[getRandomImg1]);
                    btn2.setBackground(getResources().getDrawable(yes_button_style));
//                    btn2.setText(wrongAnswers[wrongRandom]);
                    answerChecking1 = true;
                }
            }.start();
            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    Random random = new Random();
                    getRandomImg2 = random.nextInt(9);
                    imgcard2.setImageResource(imgArraySession2[getRandomImg2]);
                    imgcard1.setImageResource(R.drawable.ic_025_idea);
//                    btn4.setText(inputArraySession2[getRandomImg2]);
                    btn4.setBackground(getResources().getDrawable(yes_button_style));
                    btn4.setText(wrongAnswers[wrongRandom]);
                }
            }.start();
            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    Random random = new Random();
                    getRandomImg3 = random.nextInt(9);
                    imgcard3.setImageResource(imgArraySession3[getRandomImg3]);
                    imgcard2.setImageResource(R.drawable.ic_025_idea);
//                    btn3.setText(inputArraySession3[getRandomImg3]);
                    btn3.setBackground(getResources().getDrawable(yes_button_style));
                    btn3.setText(wrongAnswers[wrongRandom]);
                }
            }.start();
            new CountDownTimer(7000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    Random random = new Random();
                    getRandomImg4 = random.nextInt(9);
                    imgcard4.setImageResource(imgArraySession4[getRandomImg4]);
                    imgcard3.setImageResource(R.drawable.ic_025_idea);
//                  btn1.setText(inputArraySession4[getRandomImg4]);
                    btn1.setText(wrongAnswers[wrongRandom]);
                    btn1.setBackground(getResources().getDrawable(yes_button_style));
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
                    Intent nextLevel = new Intent(Level3GamePlay.this, Play_Screen.class);
                    startActivity(nextLevel);
                    finish();
                }
            });
            myDialog.show();
        }
    }

}
