package com.example.vijay.simplegame.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.vijay.simplegame.EmojiQuiz_2;

import java.util.List;

import com.example.vijay.simplegame.Common.Common;
import com.example.vijay.simplegame.R;


public class GridViewSuggestAdapter extends BaseAdapter {

    private List<String> suggestSource;
    private Context context;
    private EmojiQuiz_2 emojiQuiz;
    public GridViewSuggestAdapter(List<String> suggestSource, Context context, EmojiQuiz_2 emojiQuiz) {
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
        if (convertView == null) {
            if (suggestSource.get(position).equals("null")) {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(button.getContext().getResources().getColor(R.color.colorSuggestAnswerBtn));
            } else {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(button.getContext().getResources().getColor(R.color.colorSuggestAnswerBtn));
                button.setTextColor(Color.WHITE);
                button.setText(suggestSource.get(position));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ///OLD LOGIC FOR BUTTON CLICK
                        //If correct answer contains character user selected
                        /*if(String.valueOf(emojiQuiz.answer).contains(suggestSource.get(position)))
                        {
                            char compare = suggestSource.get(position).charAt(0); // Get char
                            for(int i =0;i<emojiQuiz.answer.length;i++)
                            {
                                if(compare == emojiQuiz.answer[i]) {
                                    Common.java.user_submit_answer[i] = compare;
                                }
                            }

//                        Update UI
                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.java.user_submit_answer,context);
                            emojiQuiz.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //Remove from suggest source
                            emojiQuiz.suggestSource.set(position,"null");
                            emojiQuiz.suggestAdapter = new GridViewSuggestAdapter(emojiQuiz.suggestSource,context,emojiQuiz);
                            emojiQuiz.gridViewSuggest.setAdapter(emojiQuiz.suggestAdapter);
                            emojiQuiz.suggestAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            //Remove from suggest source
                            emojiQuiz.suggestSource.set(position,"null");
                            emojiQuiz.suggestAdapter = new GridViewSuggestAdapter(emojiQuiz.suggestSource,context,emojiQuiz);
                            emojiQuiz.gridViewSuggest.setAdapter(emojiQuiz.suggestAdapter);
                            emojiQuiz.suggestAdapter.notifyDataSetChanged();
                        }   */
                        ///New Logic I wrote it//
                        if (emojiQuiz.btnIncrement < emojiQuiz.answer.length){
                            char compare = suggestSource.get(position).charAt(0);
                            Common.user_submit_answer[emojiQuiz.btnIncrement] = compare;
                            emojiQuiz.btnIncrement++;
                            notifyDataSetChanged();
                            //Update UI
                            Log.w("Commonuser_subm", String.valueOf(Common.user_submit_answer));
                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Common.user_submit_answer,context);
                            emojiQuiz.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            //Remove from suggest source
                            emojiQuiz.suggestSource.set(position,"null");
                            emojiQuiz.suggestAdapter = new GridViewSuggestAdapter(emojiQuiz.suggestSource,context,emojiQuiz);
                            emojiQuiz.gridViewSuggest.setAdapter(emojiQuiz.suggestAdapter);
                            emojiQuiz.suggestAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        } else
            button = (Button) convertView;
        return button;

    }
}
