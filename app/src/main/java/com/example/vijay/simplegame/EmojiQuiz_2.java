package com.example.vijay.simplegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vijay.simplegame.Adapter.GridViewAnswerAdapter;
import com.example.vijay.simplegame.Adapter.GridViewSuggestAdapter;
import com.example.vijay.simplegame.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.vijay.simplegame.R.drawable.*;


public class EmojiQuiz_2 extends AppCompatActivity  {
    ImageView imgcard1, imgcard2;
    public List<String> suggestSource = new ArrayList<>();

    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;
    public int btnIncrement = 0;
    public Button btnSubmit, refreshBtn;
    public TextView levellabel, coinslabel;

    public GridView gridViewAnswer,gridViewSuggest;

    public char[] answer;

    String correct_answer;
    int levelCount;
    int coins;
    public Play_Screen play_screen;
    int[][] emojiimg = {
            {ic_emoji_eiffel, ic_emoji_france},
            {ic_emoji_family, ic_emoji_nature},
            {ic_emoji_house, ic_emoji_woman},
            {ic_emoji_woman, ic_emoji_ladybug},
            {ic_emoji_crying, ic_emoji_baby},
            {ic_emoji_car, ic_emoji_bathroom},
            {ic_emoji_corn, ic_emoji_dog},
            {ic_emoji_toilet, ic_emoji_doc},
            {ic_emoji_airplane, ic_emoji_explosion},
            {ic_emoji_animals, ic_emoji_fish},
            {ic_emoji_doc, ic_emoji_cut},
            {ic_emoji_heart, ic_emoji_sick},
            {ic_emoji_open_book, ic_emoji_backpack},
            {ic_emoji_feeding_bottle, ic_emoji_man},
            {ic_emoji_heart, ic_emoji_triangle},
            {ic_emoji_dancer, ic_emoji_princess},
            {ic_emoji_office, ic_emoji_open_book},
            {ic_emoji_cat, ic_emoji_fist},
            {ic_emoji_eyes, ic_emoji_boat},
            {ic_emoji_fist, ic_emoji_backpack},
            {ic_emoji_usa, ic_emoji_startup},
            {ic_emoji_startup, ic_emoji_gas_station},
            {ic_emoji_happy, ic_emoji_footprints},
            {ic_emoji_explosion, ic_emoji_corn},
            {ic_emoji_snake, ic_emoji_eyes},
            {ic_emoji_newspaper, ic_emoji_lightning},
            {ic_emoji_syringe, ic_emoji_strong},
            {ic_emoji_horse, ic_emoji_airplane},
            {ic_emoji_demon, ic_emoji_forbidden},
            {ic_emoji_angel, ic_emoji_thinking}
    };
    String [] answerArray = {
            "EIFFELTOWER", "FAMILYTREE", "HOUSEWIFE", "LADYBIRD", "CRYBABY", "CARWASH", "CORNDOG", "TOILETPAPER", "PLANECRASH", "SHELLFISH", "PAPERCUT",
            "LOVESICK", "BOOKBAG", "MILKMAN", "DANCINGQUEEN", "FAIRYTALE", "CATFIGHT", "EYELINER", "PUNCHBAG", "NASA", "ROCKETFUEL", "HAPPYFEET", "POPCORN",
            "SNAKEEYES", "NEWSFLASH", "STEROIDS", "PEGASUS", "HELLNO", "GOD"
    };
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_quiz_2);
        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        imgcard1 =(ImageView)findViewById(R.id.imgCard21);
        imgcard2 =(ImageView)findViewById(R.id.imgCard22);
        levellabel = (TextView)findViewById(R.id.levelLabel);
        coinslabel = (TextView) findViewById(R.id.coinsLabel);
        Intent intent = getIntent();
        if(intent != null) {
            levelCount = intent.getIntExtra("levelCount",0);
            Log.w("levelEmoji", String.valueOf(levelCount));
            coins = intent.getIntExtra("coins",0);
        }
        imageShowing(levelCount);
        initView(levelCount);
    }
    public void imageShowing(int levels) {
        levellabel.setText("Level: "+levels);
        coinslabel.setText(String.valueOf(coins));
        imgcard1.setImageResource(emojiimg[levels][0]);
        imgcard2.setImageResource(emojiimg[levels][1]);
    }
    private void initView(int levels) {
        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView)findViewById(R.id.gridViewSuggest);

        //Add SetupList Here
        setupList(levels);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        refreshBtn = (Button)findViewById(R.id.refreshBtn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for(int i=0;i< Common.user_submit_answer.length;i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                if(result.equals(correct_answer))
                {
                    Toast.makeText(getApplicationContext(),"Finish ! This is "+result,Toast.LENGTH_SHORT).show();

                    //Reset
                    Common.count = 0;
                    btnIncrement = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];
                    Log.w("common answer_2", String.valueOf(Common.user_submit_answer));
                    //Set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource,getApplicationContext(),EmojiQuiz_2.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    //Level count is increase and stored.
                    levelCount++;
                    coins = coins + 20;
                    imageShowing(levelCount);
                    setupList(levelCount);
                }
                else
                {
                    if (coins >=10) {
                        Toast.makeText(EmojiQuiz_2.this, "Incorrect!!!", Toast.LENGTH_SHORT).show();
                        coins = coins - 10;
                        //Set Adapter
                        GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                        gridViewAnswer.setAdapter(answerAdapter);
                        answerAdapter.notifyDataSetChanged();

                        GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource,getApplicationContext(),EmojiQuiz_2.this);
                        gridViewSuggest.setAdapter(suggestAdapter);
                        suggestAdapter.notifyDataSetChanged();
                        btnIncrement = 0;
                        setupList(levelCount);
                    }else{
                        Toast.makeText(EmojiQuiz_2.this,"You dont have enough coins", Toast.LENGTH_LONG).show();
                        gameOver(levelCount, coins);
                    }
                }
            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set Adapter
                GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                gridViewAnswer.setAdapter(answerAdapter);
                answerAdapter.notifyDataSetChanged();

                GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestSource,getApplicationContext(),EmojiQuiz_2.this);
                gridViewSuggest.setAdapter(suggestAdapter);
                suggestAdapter.notifyDataSetChanged();
                btnIncrement = 0;
                setupList(levelCount);
            }
        });
    }

    private void setupList(int level) {
        //Random logo
        Random random = new Random();
//        int imageSelected = image_list[random.nextInt(image_list.length)];
//        imgViewQuestion.setImageResource(imageSelected);
//        int imageSelected = answerArray[0];
        correct_answer = answerArray[level]; //getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/")+1);
        Log.w("correct answer_22", String.valueOf(correct_answer));
        answer = correct_answer.toCharArray();
        Log.w("answer_22", String.valueOf(answer));
        Common.user_submit_answer = new char[answer.length];
        Log.w("common answer_22", String.valueOf(Common.user_submit_answer));
        //Add Answer character to List
        suggestSource.clear();
        for(char item:answer)
        {
            //Add logo name to list
            suggestSource.add(String.valueOf(item));
        }
        int suggestShuffle = 21-answer.length;
        //Random add some character to list
        for(int i = answer.length;i<answer.length+suggestShuffle;i++)
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

        //Sort random
        Collections.shuffle(suggestSource);

        //Set for GridView
        answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        suggestAdapter = new GridViewSuggestAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);


    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0;i<answer.length;i++)
            result[i]=' ';
        return result;
    }
    public void gameOver(int level, int coins){
        Log.w("levelEmoji", String.valueOf(level));
        play_screen.firstTime = true;
        Intent menu = new Intent(EmojiQuiz_2.this, Play_Screen.class);
        //Create the bundle
        Bundle bundle = new Bundle();

        //Add your data to bundle
        bundle.putInt("EmojiLevel", level);
        bundle.putInt("coins_emo", coins);
        bundle.putString("Unique_id", "Emoji_Activity");
        //Add the bundle to the intent
        menu.putExtras(bundle);

        //Fire that second activity
        startActivity(menu);
    }
    @Override
    public void onBackPressed() {
        gameOver(levelCount, coins);
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
