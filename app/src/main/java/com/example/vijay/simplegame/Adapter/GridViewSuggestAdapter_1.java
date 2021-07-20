package com.example.vijay.simplegame.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.vijay.simplegame.Common.Common;
import com.example.vijay.simplegame.EmojiQuiz_1;
import com.example.vijay.simplegame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class GridViewSuggestAdapter_1 extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    private EmojiQuiz_1 emojiQuiz;
    public GridViewSuggestAdapter_1(List<String> suggestSource, Context context, EmojiQuiz_1 emojiQuiz) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.emojiQuiz = emojiQuiz;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        Display display = emojiQuiz.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        if (convertView == null) {
            if (suggestSource.get(position).equals("null")) {
                button = new Button(context);
                if (width <=720) {
                    button.setLayoutParams(new GridView.LayoutParams(60, 60));
                    button.setPadding(8, 8, 8, 8);
                } else {
                    button.setLayoutParams(new GridView.LayoutParams(85, 85));
                    button.setPadding(8, 8, 8, 8);
                }
                button.setBackgroundColor(button.getContext().getResources().getColor(R.color.colorSuggestAnswerBtn));
            } else {
                button = new Button(context);
                if (width <=720) {
                    button.setLayoutParams(new GridView.LayoutParams(60, 60));
                    button.setPadding(8, 8, 8, 8);
                } else {
                    button.setLayoutParams(new GridView.LayoutParams(85, 85));
                    button.setPadding(8, 8, 8, 8);
                }
                button.setBackgroundColor(button.getContext().getResources().getColor(R.color.colorSuggestAnswerBtn));
                button.setTextColor(Color.WHITE);
                button.setText(suggestSource.get(position));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ///New Logic I wrote it//
                        if (emojiQuiz.btnIncrement < emojiQuiz.answerSource.size()){
                            emojiQuiz.suggested_position.add(String.valueOf(position));
                            char compare = suggestSource.get(position).charAt(0);
                            if (!emojiQuiz.answer_btn_clicked) {
                                Common.user_submit_answer_1.set(emojiQuiz.btnIncrement, String.valueOf(compare));
                            } else {
                                emojiQuiz.answer_btn_clicked = false;
                                int minIndex = emojiQuiz.answered_position.indexOf(Collections.min(emojiQuiz.answered_position));
                                Log.w("mixindex", String.valueOf(minIndex));
                                Common.user_submit_answer_1.set(Integer.parseInt(emojiQuiz.answered_position.get(minIndex)), String.valueOf(compare));
                                emojiQuiz.answered_position.remove(minIndex);
                            }
                            emojiQuiz.btnIncrement++;
                            notifyDataSetChanged();
                            //Update UI
                            GridViewAnswerAdapter_1 answerAdapter = new GridViewAnswerAdapter_1(Common.user_submit_answer_1, context, emojiQuiz);
                            emojiQuiz.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //Remove from suggest source
                            emojiQuiz.suggestSource.set(position,"null");
                            emojiQuiz.suggestAdapter = new GridViewSuggestAdapter_1(emojiQuiz.suggestSource,context,emojiQuiz);
                            emojiQuiz.gridViewSuggest.setAdapter(emojiQuiz.suggestAdapter);
                            emojiQuiz.suggestAdapter.notifyDataSetChanged();
                        }
                    }
                });
                if (emojiQuiz.btnIncrement == Common.user_submit_answer_1.size()){
                    emojiQuiz.answerChecking();
                }
            }
        } else
            button = (Button) convertView;
        return button;

    }
}
