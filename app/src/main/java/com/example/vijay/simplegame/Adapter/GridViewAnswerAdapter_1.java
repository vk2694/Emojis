package com.example.vijay.simplegame.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.vijay.simplegame.Common.Common;
import com.example.vijay.simplegame.EmojiQuiz_1;

import java.util.Collections;
import java.util.List;

public class GridViewAnswerAdapter_1 extends BaseAdapter {

    private List<String> answerCharacter;
    private Context context;
    private EmojiQuiz_1 emojiQuiz;
    int value;
    Boolean colorChange = false;

    public GridViewAnswerAdapter_1(List<String> answerCharacter, Context context, EmojiQuiz_1 emojiQuiz) {
        this.answerCharacter = answerCharacter;
        this.context = context;
        this.emojiQuiz = emojiQuiz;
        value = emojiQuiz.getWhiteSpaceValue();
        emojiQuiz.whiteSpace = 30;
        Log.w("answerCharacter", String.valueOf(value));
    }

    @Override
    public int getCount() {
        return answerCharacter.size();
    }

    @Override
    public Object getItem(int position) {
        return answerCharacter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
//        Log.w("position", String.valueOf(position));
        if (convertView == null) {
            button = new Button(context);
            if (position == value) {
                colorChange = true;
            }
            //GridView width dynamically changing method
            changeAnswerGridLayoutSize();
            //Create new button
            if (value == 30) {
                button.setLayoutParams(new GridView.LayoutParams(dpToPx(25), dpToPx(25)));
                button.setPadding(8, 8, 8, 8);
                //button.setX(widthdiv2 /2);
            } else {
                if (!colorChange) {
                    button.setLayoutParams(new GridView.LayoutParams(dpToPx(25), dpToPx(25)));
                    button.setPadding(8, 8, 8, 8);
                } else {
                    GridView.LayoutParams grid = new GridView.LayoutParams(dpToPx(25), dpToPx(25));
                    button.setPadding(8, 8, 8, 8);
                    button.setLayoutParams(grid);
                    button.setX(30);
                }
            }
            button.setBackgroundColor(Color.WHITE);
            button.setTextColor(Color.DKGRAY);
//            if (!colorChange) {
//            } else {
//                button.setBackgroundColor(Color.WHITE);
//                button.setTextColor(Color.DKGRAY);
//            }
            button.setText(String.valueOf(answerCharacter.get(position)));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    emojiQuiz.ans_Btn_pos = position;
                    if(emojiQuiz.suggested_position != null && (emojiQuiz.suggested_position).size() != 0){
                        Log.w("suggested", String.valueOf(emojiQuiz.suggested_position));
                        emojiQuiz.answer_btn_clicked = true;
                        emojiQuiz.answered_position.add(String.valueOf(position));
                        char compare = Common.user_submit_answer_1.get(position).charAt(0);
                        int minIndex = emojiQuiz.suggested_position.indexOf(Collections.min(emojiQuiz.suggested_position));
                        Log.w("mixindex", String.valueOf(minIndex));
                        emojiQuiz.suggestSource.set(Integer.parseInt(emojiQuiz.suggested_position.get(minIndex)), String.valueOf(compare));
                        emojiQuiz.suggested_position.remove(minIndex);
                        notifyDataSetChanged();

                        GridViewSuggestAdapter_1 suggestAdapter = new GridViewSuggestAdapter_1(emojiQuiz.suggestSource, context, emojiQuiz);
                        emojiQuiz.gridViewSuggest.setAdapter(emojiQuiz.suggestAdapter);
                        emojiQuiz.btnIncrement--;
                        suggestAdapter.notifyDataSetChanged();

                        Common.user_submit_answer_1.set(position, String.valueOf(new char[1]));
                        emojiQuiz.answerAdapter = new GridViewAnswerAdapter_1(Common.user_submit_answer_1, context, emojiQuiz);
                        emojiQuiz.gridViewAnswer.setAdapter(emojiQuiz.answerAdapter);
                        emojiQuiz.answerAdapter.notifyDataSetChanged();
                    }
                }
            });
        } else {
            button = (Button) convertView;
        }

        return button;
    }

    public int dpToPx(int dp) {
        float density = emojiQuiz.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }

    public void changeAnswerGridLayoutSize() {
        Display display = emojiQuiz.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Log.w("Width", String.valueOf(width));
        int widthdiv2 = 0;
        if (width >= 300) {
            widthdiv2 = width / 2;
        }
//        if (width <= 1080) // 720 && 1440 Okay //This condition below 5.0 inch devices
        {
            if (answerCharacter.size() <= 4) {
                ViewGroup.LayoutParams layoutParams = emojiQuiz.gridViewAnswer.getLayoutParams();
                layoutParams.width = dpToPx(120); //this is in pixels
                emojiQuiz.gridViewAnswer.setLayoutParams(layoutParams);
            } else if (answerCharacter.size() <= 6) {
                ViewGroup.LayoutParams layoutParams = emojiQuiz.gridViewAnswer.getLayoutParams();
                layoutParams.width = dpToPx(170); //this is in pixels
                emojiQuiz.gridViewAnswer.setLayoutParams(layoutParams);
            } else if (answerCharacter.size() <= 8) {
                ViewGroup.LayoutParams layoutParams = emojiQuiz.gridViewAnswer.getLayoutParams();
                layoutParams.width = dpToPx(230); //this is in pixels
                emojiQuiz.gridViewAnswer.setLayoutParams(layoutParams);
            } else if (answerCharacter.size() <= 10) {
                ViewGroup.LayoutParams layoutParams = emojiQuiz.gridViewAnswer.getLayoutParams();
                layoutParams.width = dpToPx(310); //this is in pixels
                emojiQuiz.gridViewAnswer.setLayoutParams(layoutParams);
            } else if (answerCharacter.size() <= 12) {
                ViewGroup.LayoutParams layoutParams = emojiQuiz.gridViewAnswer.getLayoutParams();
                layoutParams.width = dpToPx(350); //this is in pixels
                emojiQuiz.gridViewAnswer.setLayoutParams(layoutParams);
            }
        }
    }
}
