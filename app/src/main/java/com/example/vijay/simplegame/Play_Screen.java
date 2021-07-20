package com.example.vijay.simplegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Play_Screen extends AppCompatActivity {
    Button imagePlayBtn, emoji2QuizBtn, emoji3QuizBtn, emoji4QuizBtn;
    LinearLayout l1, l2;
    Animation uptoDown, downtoUp;
    SharedPreferences sharedPreferences;
    int levelCountAnimals, levelCountEmoji;
    int getlevelAni, getlevelEmoji, getCoinsAni, getCoinsEmo;
    TextView levelanimal, levelemoji2, levelemoji3, levelemoji4, coinsAnimalsTXT, coinsEmojisTXT;
    private int hintsAni, coinsEmo, coins;
    public static boolean firstTime;
    boolean doubleBackToExitPressedOnce = false;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__screen);
        imagePlayBtn = (Button)findViewById(R.id.playbtnAnimal);
        emoji2QuizBtn = (Button)findViewById(R.id.playbtn2Emoji);
        emoji3QuizBtn = (Button)findViewById(R.id.playbtn3Emoji);
        emoji4QuizBtn = (Button)findViewById(R.id.playbtn4Emoji);
        levelanimal = (TextView) findViewById(R.id.levelCountAnimals);
        levelemoji2 = (TextView) findViewById(R.id.levelCount2Emoji);
        levelemoji3 = (TextView) findViewById(R.id.levelCount3Emoji);
        levelemoji4 = (TextView) findViewById(R.id.levelCount4Emoji);
//        coinscount = (TextView) findViewById(R.id.coinsCount);
        coinsAnimalsTXT = (TextView) findViewById(R.id.coinsAnimals);
        coinsEmojisTXT = (TextView) findViewById(R.id.coinsEmoji);
        //Stored the values getting
        sharedPreferences = this.getSharedPreferences("com.example.vijay.simplegame",Context.MODE_PRIVATE);
        getlevelAni = sharedPreferences.getInt("levelsAni", levelCountAnimals);
        getlevelEmoji = sharedPreferences.getInt("levelsEmo", levelCountEmoji);
        getCoinsAni = sharedPreferences.getInt("hintsAni", hintsAni);
        getCoinsEmo = sharedPreferences.getInt("coinsEmo", coinsEmo);

        levelanimal.setText(String.valueOf(getlevelAni));
        levelemoji2.setText(String.valueOf(getlevelEmoji));
        coinsAnimalsTXT.setText(getCoinsAni+" H");
        coinsEmojisTXT.setText(getCoinsEmo+"C");
        imagePlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(Play_Screen.this, EmojiQuiz_1.class);
                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putInt("hints", getCoinsAni);
                bundle.putInt("levelCount", getlevelAni);
                //Add the bundle to the intent
                main.putExtras(bundle);

                //Fire that second activity
                startActivity(main);
                finish();
            }
        });
        emoji2QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.w("LevelEmoji", String.valueOf(getlevelEmoji));
                Intent main = new Intent(Play_Screen.this, EmojiQuiz_2.class);
                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putInt("coins", getCoinsEmo);
                bundle.putInt("levelCount", getlevelEmoji);
                //Add the bundle to the intent
                main.putExtras(bundle);

                //Fire that second activity
                startActivity(main);
                finish();
            }
        });
        l1 = (LinearLayout)findViewById(R.id.linearAni);
        l2 = (LinearLayout)findViewById(R.id.linearAni1);
        uptoDown = AnimationUtils.loadAnimation(this, R.anim.uptodownani);
        downtoUp = AnimationUtils.loadAnimation(this, R.anim.downupani);
        l1.setAnimation(uptoDown);
        l2.setAnimation(downtoUp);

        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            firstTime = false;
            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
        if (firstTime) {
            firstTime = false;
            //Get the bundle
            Bundle bundle = getIntent().getExtras();

            //Extract the data…
            String Unique_id = bundle.getString("Unique_id");
            if (bundle == null) {
//                Log.w("Intent", "Null");
            } else {
//                Log.w("Intent", "Not Null");
                if (Unique_id.equals("emoji_level_1")) {
                    levelCountAnimals = bundle.getInt("EmojiLevel_1");
                    hintsAni = bundle.getInt("hints_emo_1");
                    updateAni(levelCountAnimals, hintsAni);
                } else if (Unique_id.equals("Emoji_Activity")) {
                    levelCountEmoji = bundle.getInt("EmojiLevel");
                    coinsEmo = bundle.getInt("coins_emo");
                    updateEmo(levelCountEmoji, coinsEmo);
                }
            }
        }

    }

    private void updateAni(int levels, int hints) {
        levelanimal.setText(String.valueOf(levels));
        coinsAnimalsTXT.setText(hints+" H");
    }

    private void updateEmo(int levels, int coins) {
        levelemoji2.setText(String.valueOf(levels));
        coinsEmojisTXT.setText(coins+"C");
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
