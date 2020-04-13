package com.example.highscore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private String textName = "";
    private String textScore = "";
    private LinearLayout nameLayout;
    private LinearLayout scoreLayout;
    private EditText name;
    private EditText score;
    private TextView added;
    private TextView adds;
    private String list[][] = new String[10][2];
    private int listLength = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameLayout  = (LinearLayout)findViewById(R.id.nameLayout);
        scoreLayout = (LinearLayout)findViewById(R.id.scoreLayout);
        setupList();
        setupText();


        Button list = findViewById(R.id.buttonAdd);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addScore();

            }
        });

    }

    //removes all textviews and calls setuptext
    private void refresh() {
        nameLayout.removeAllViewsInLayout();
        scoreLayout.removeAllViewsInLayout();
        setupText();
    }

    private void setupList() {
        list[0][0] = "Matti";
        list[0][1] = "300";
        list[1][0] = "Alex";
        list[1][1] = "200";
        list[2][0] = "Jenna";
        list[2][1] = "135";


    }
    //This gets the textviews showing in layout
    private void setupText() {
        int i = 0;
        while(i<10 && list[i][0] != null ){
            TextView tv = new TextView(this);
            tv.setText(list[i][0]);
            setStyle(tv, 1);
            nameLayout.addView(tv);

            TextView tvs = new TextView(getApplicationContext());
            tvs.setText(list[i][1]);
            setStyle(tvs, 0);
            scoreLayout.addView(tvs);

            i++;
        }

    }



    // Dialog where we put the name and score
    private void addScore() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup);
        Button add = (Button) dialog.findViewById(R.id.buttonOk);
        name = (EditText)dialog.findViewById(R.id.editTextName);
        score = (EditText)dialog.findViewById(R.id.editTextScore);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textName = name.getText().toString();
                textScore= score.getText().toString();
                compare(textScore);
                dialog.cancel();
                refresh();
            }
        });
        dialog.show();

    }

    //This finds suitable place for the score
    private void compare(String score) {
        int i = 0;
        int x = 0;

        while (i<=listLength){
            if(i<10 && list[i][1] == null ){
                reArrange(i, x);
                break;
            }
            if(Integer.parseInt(list[i][1]) < Integer.parseInt(score)){
                reArrange(i, x);
                break;
            }
            i++;
        }
    }

    //This arranges the list so the scores are in right order
    private void reArrange(int i, int x) {
        int y = 1+x;
        while(i<listLength-x) {
            list[listLength - x][1] = list[listLength - y][1];
            list[listLength - x][0] = list[listLength - y][0];
            y++;
            x++;
        }
        list[i][1] = textScore;
        list[i][0] = textName;
        if(listLength < 9){
            listLength++;
        }

    }

    //Sets styles to the textviews so they look similar
    public void setStyle(TextView tv, int i){
        tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(20);

        if(i==1) {
            tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }else{
            tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }

    }

}

