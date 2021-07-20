package com.example.vijay.simplegame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vijay.simplegame.Adapter.GridViewAnswerAdapter_1;
import com.example.vijay.simplegame.Adapter.GridViewSuggestAdapter_1;
import com.example.vijay.simplegame.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.vijay.simplegame.R.drawable.*;

public class EmojiQuiz_1 extends AppCompatActivity {
    ImageView imgcard1;
    public List<String> suggestSource = new ArrayList<>();
    public List<String> answerSource = new ArrayList<>();
    public List<String> suggested_position = new ArrayList<>();
    public List<String> answered_position = new ArrayList<>();
    public GridViewAnswerAdapter_1 answerAdapter;
    public GridViewSuggestAdapter_1 suggestAdapter;
    public int btnIncrement = 0;
    public Button btnSubmit, hintsBottom;
    public TextView levellabel, hintslabel;

    public GridView gridViewSuggest;
    public GridView gridViewAnswer;
    public LinearLayout linearLayout3;
    public char[] answer;
    Animation imagemove,  imagemove1;
    String correct_answer, answerwhitespcae;
    int levelCount;
    int hints;
    String corr_answer;
    public Play_Screen play_screen;
    int[] emojiimg = {
            ic_alien, ic_camping, ic_carousel,
            ic_crosswalk, ic_crown, ic_imp, ic_drop,
            ic_fire, ic_guard, ic_hair_cut, ic_hospital,
            ic_kimono, ic_house, ic_negative, ic_hotel,
            ic_poo, ic_school, ic_shining, ic_skull, ic_sunset, ic_tent,
            //Two words emoji start hereafter
            ic_anger, ic_angry, ic_angry_1, ic_broken_heart,
            ic_chat, ic_diamond, ic_sad_10, ic_heart_7,
            ic_email, ic_explosion, ic_fart, ic_footprint,
            ic_gas_station, ic_hand, ic_hat,
            ic_heart_9, ic_kiss, ic_kiss_2, ic_massage,
            ic_monster, ic_nervous_1, ic_policeman, ic_shooting_star,
            ic_sleep, ic_star, ic_straight,
            ic_thinking, ic_wink, ic_zzz
    };
    String[] answerArray = {
            "ALIEN", "CAMPING", "CAROUSEL",
            "CROSSWALK", "CROWN", "IMP", "DROPLET",
            "FIRE", "GUARDSMAN", "HAIRCUT", "HOSPITAL",
            "KIMONO", "HOUSE", "NEGATIVE", "HOTEL",
            "POO", "SCHOOL", "SPARKLES", "SKULL", "SUNSET", "TENT",
            //Two words emoji start hereafter
            "ANGER SYMBOL", "ANGRY FACE", "POUTING FACE", "BROKEN HEART",
            "SPEECH BALLOON", "GEM STONE", "WORRIED FACE", "TWO HEARTS",
            "LOVE LETTER", "COLLISION SYMBOL", "DASH SYMBOL", "FOOT PRINTS",
            "FUEL PUMP", "RAISED HAND", "WOMANS HAT",
            "REVOLVING HEARTS", "KISSING FACE", "KISS MARK", "FACE MASSAGE",
            "JAPANESE OGRO", "CONFOUNDED FACE", "POLICE OFFICER", "DIZZY SYMBOL",
            "SLEEPY FACE", "GLOWING STAR", "NEUTRAL FACE",
            "THOUGHT BALLOON", "WINKING FACE", "SLEEPING SYMBOL"
    };
    SharedPreferences sharedPref;
    public int whiteSpace = 30;
    public int ans_Btn_pos = 30;
    public Boolean answer_btn_clicked = false;
    Dialog epicDialogPositive, hintPopup;
    TextView title_positive_txt, text_positive_txt, levelHeading, levelContent, hintCost;
    ImageView home_positive_img;
    Button next_positive_btn, noBtn, yesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_quiz_1);
        imgcard1 = (ImageView) findViewById(R.id.imgCard21);
        levellabel = (TextView) findViewById(R.id.levelLabel);
        hintslabel = (TextView) findViewById(R.id.hintsLabel);

        Intent intent = getIntent();
        if (intent != null) {
            levelCount = intent.getIntExtra("levelCount", 0);
//            Log.w("levelEmoji", String.valueOf(levelCount));
            hints = intent.getIntExtra("hints", 0);
        }
        imageShowing(levelCount);
        initView(levelCount);
        epicDialogPositive = new Dialog(this);
        hintsBottom = (Button) findViewById(R.id.hintsBtn);
        hintsBottom.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"InflateParams", "ResourceType"})
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(EmojiQuiz_1.this);
                View dialogLayout = inflater.inflate(R.layout.dialog_hint, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(EmojiQuiz_1.this, AlertDialog.THEME_HOLO_LIGHT);
                builder.setView(dialogLayout);
                noBtn = (Button) dialogLayout.findViewById(R.id.noBtn);
                yesBtn = (Button) dialogLayout.findViewById(R.id.yesBtn);
                final Dialog dialog = builder.create();
                //Popup make it center of the screen
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
//                wmlp.x = 100;   //x position
//                wmlp.y = 400;   //y position
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        hints = hints - 1;
                        SharedPreferences  sharedPreferences= getSharedPreferences("com.example.vijay.simplegame", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("hintsAni", hints);
                        editor.apply();
                    }
                });

                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
    }

    public void imageShowing(int levels) {
        levellabel.setText("Level: " + levels);
        hintslabel.setText(String.valueOf(hints));
        imgcard1.setImageResource(emojiimg[levels]);
    }

    private void initView(int levels) {
        gridViewAnswer = (GridView) findViewById(R.id.gridViewAnswer);
        gridViewSuggest = (GridView) findViewById(R.id.gridViewSuggest);

        //Add SetupList Here
        setupList(levels);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//        refreshBtn = (Button)findViewById(R.id.refreshBtn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void answerChecking() {
        String result = "";
        for (int i = 0; i < Common.user_submit_answer_1.size(); i++)
            result += String.valueOf(Common.user_submit_answer_1.get(i));
//                Log.w("result", result);
        if (result.length() == corr_answer.length()) {
            if (result.equals(corr_answer)) {
                showPositivePopup();
            } else {
                    //Animation for imageView
                    TranslateAnimation animation = new TranslateAnimation(-50.0f, 50.0f,
                            0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
                    animation.setDuration(800);  // animation duration
                    animation.setRepeatCount(2);  // animation repeat count
                    animation.setRepeatMode(2);
                    imgcard1.startAnimation(animation);
/*
                if (hints >= 10) {
                    hints = hints - 10;
                    //Set Adapter
                    GridViewAnswerAdapter_1 answerAdapter = new GridViewAnswerAdapter_1(setupNullList(), getApplicationContext(), EmojiQuiz_1.this);
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewSuggestAdapter_1 suggestAdapter = new GridViewSuggestAdapter_1(suggestSource, getApplicationContext(), EmojiQuiz_1.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();
                    btnIncrement = 0;
                    setupList(levelCount);
                } else {
                    Toast.makeText(EmojiQuiz_1.this, "You dont have enough hints", Toast.LENGTH_LONG).show();
                    gameOver(levelCount, hints);
                }
*/
            }
        }
    }

    public void showPositivePopup() {
        epicDialogPositive.setContentView(R.layout.epic_layout_positive);
        home_positive_img = (ImageView) epicDialogPositive.findViewById(R.id.home_popup_positive_img);
        next_positive_btn = (Button) epicDialogPositive.findViewById(R.id.next_positive_btn);
        title_positive_txt = (TextView) epicDialogPositive.findViewById(R.id.title_positive_txt);
        text_positive_txt = (TextView) epicDialogPositive.findViewById(R.id.text_positive_txt);

        next_positive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicDialogPositive.dismiss();
                answerSource.clear();
                //Reset
                Common.count = 0;
                btnIncrement = 0;
                Common.user_submit_answer_1.add(String.valueOf(corr_answer.length()));

                GridViewAnswerAdapter_1 answerAdapter = new GridViewAnswerAdapter_1(setupNullList(), getApplicationContext(), EmojiQuiz_1.this);
                gridViewAnswer.setAdapter(answerAdapter);
                answerAdapter.notifyDataSetChanged();

                GridViewSuggestAdapter_1 suggestAdapter = new GridViewSuggestAdapter_1(suggestSource, getApplicationContext(), EmojiQuiz_1.this);
                gridViewSuggest.setAdapter(suggestAdapter);
                suggestAdapter.notifyDataSetChanged();

                //Level count is increase and stored.
                levelCount = levelCount+1;
                hints = hints + 1;
                imageShowing(levelCount);
                setupList(levelCount);
                SharedPreferences  sharedPreferences= getSharedPreferences("com.example.vijay.simplegame", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("levelsAni", levelCount);
                editor.putInt("hintsAni", hints);
                editor.apply();
            }
        });

        home_positive_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOver(levelCount, hints);
                epicDialogPositive.dismiss();
            }
        });
        epicDialogPositive.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialogPositive.show();
    }

    private void setupList(int level) {
        //Random logo
        Random random = new Random();
        correct_answer = answerArray[level];
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/") + 1);
        corr_answer = correct_answer.replace(" ", "");
        answerwhitespcae = corr_answer.replaceAll(".", "$0 ");
//        Log.w("common answer_11", String.valueOf(answerwhitespcae));
        for (int i = 0, length = answerwhitespcae.length(); i < length; i++) {
            if (answerwhitespcae.charAt(i) != ' ') {
                answerSource.add(String.valueOf(answerwhitespcae.charAt(i)));
//                Log.w("common answer_11", String.valueOf(answerSource));
            }
        }
        if (Common.user_submit_answer_1 != null) {
            Common.user_submit_answer_1.clear();
        }
        Common.user_submit_answer_1 = new ArrayList<String>(Collections.<String>nCopies(answerSource.size(), String.valueOf(new char[1])));
//        Log.w("common answer_11", String.valueOf(Common.java.user_submit_answer_1));
        //Add Answer character to List
        suggestSource.clear();
        for (String item : answerSource) {
            suggestSource.add(String.valueOf(item));
        }
        int suggestShuffle = 21 - answerSource.size();
        //Random add some character to list
        for (int i = answerSource.size(); i < answerSource.size() + suggestShuffle; i++)
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);

        //Sort random
        Collections.shuffle(suggestSource);

        //Set for GridView
        answerAdapter = new GridViewAnswerAdapter_1(setupNullList(), this, this);
        suggestAdapter = new GridViewSuggestAdapter_1(suggestSource, this, this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);


    }

    private List<String> setupNullList() {
        List<String> results = new ArrayList<>(answerSource.size());
        for (int i = 0; i < answerSource.size(); i++)
            results.add(String.valueOf(' '));
        return results;
    }

    public void gameOver(int level, int hints) {
        Log.w("levelEmoji", String.valueOf(level));
        play_screen.firstTime = true;
        Intent menu = new Intent(EmojiQuiz_1.this, Play_Screen.class);
        //Create the bundle
        Bundle bundle = new Bundle();

        //Add your data to bundle
        bundle.putInt("EmojiLevel_1", level);
        bundle.putInt("hints_emo_1", hints);
        bundle.putString("Unique_id", "emoji_level_1");
        //Add the bundle to the intent
        menu.putExtras(bundle);

        //Fire that second activity
        startActivity(menu);
        finish();
    }

    @Override
    public void onBackPressed() {
        gameOver(levelCount, hints);
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    public int getWhiteSpaceValue() {
        for (int i = 0, length = correct_answer.length(); i < length; i++) {
            if (correct_answer.charAt(i) == ' ') {
                whiteSpace = i;
//                Log.w("whitespace", String.valueOf(whiteSpace));
            }
        }
        return whiteSpace;
    }

    // Method for converting DP value to pixels
    public static int getPixelsFromDPs(Activity activity, int dps) {
        Resources r = activity.getResources();
        int px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }
}
