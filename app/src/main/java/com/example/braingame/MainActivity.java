package com.example.braingame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    GridLayout grid;
    Button option,playagainbutton,gobutton,opt;
    TextView questiontext,scoretext,timetext,wrongtext;
    CountDownTimer count;
    Random a;
    int x,y,b,d,q,z,findtag,counterans=0,counter=0;
    boolean gameactive=true;

    public void go(View view){
        gobutton.setVisibility(View.INVISIBLE);
        grid.setVisibility(View.VISIBLE);
        questiontext.setVisibility(View.VISIBLE);
        scoretext.setVisibility(View.VISIBLE);
        timetext.setVisibility(View.VISIBLE);
        mcq();
        count=new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timetext.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                gameactive=false;
                playagainbutton.setVisibility(View.VISIBLE);
                wrongtext.setText("Done");
            }
        }.start();
    }

    public void playagain(View view){
        playagainbutton.setVisibility(View.INVISIBLE);
        gameactive=true;
        counter=0;
        counterans=0;
        wrongtext.setVisibility(View.INVISIBLE);
        go(view);

    }

    public void mcq(){

        scoretext.setText(Integer.toString(counterans)+"/"+Integer.toString(counter));
        randomnumbergeneratoranswer();
        questiontext.setText(Integer.toString(x)+" + "+Integer.toString(y));
        findtaganswer();
        for(int i=0;i<grid.getChildCount();i++){
            if (findtag==i){
                b=x+y;
                option=(Button) grid.getChildAt(i);
                option.setText(Integer.toString(b));
            }
            else{
                option=(Button) grid.getChildAt(i);
                randomnumbergenerator();
                option.setText(Integer.toString(d));
            }
        }
    }

    public void randomnumbergeneratoranswer(){
        a=new Random();
        x=a.nextInt(50-0+1)+0;
        y=a.nextInt(50-0+1)+0;
    }

    public void findtaganswer(){
        a=new Random();
        findtag=a.nextInt(3-0+1)+0;
    }

    public void randomnumbergenerator(){
        a=new Random();
        q=a.nextInt(50-0+1)+0;
        z=a.nextInt(50-0+1)+0;
        d=q+z;
    }


    public void options(View view){
        if(gameactive) {
            opt = (Button) view;
            // Log.i("Tag",option.getTag().toString());
            int ans = Integer.parseInt(opt.getTag().toString());
            if (ans == findtag) {
                wrongtext.setText("Correct :)");
                wrongtext.setVisibility(View.VISIBLE);
                counterans++;
                counter++;
                mcq();
            } else {
                wrongtext.setText("Wrong :(");
                wrongtext.setVisibility(View.VISIBLE);
                counter++;
                mcq();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid=(GridLayout) findViewById(R.id.gridlayout);
        playagainbutton=(Button) findViewById(R.id.playagainbutton);
        gobutton=(Button) findViewById(R.id.gobutton);
        questiontext=(TextView) findViewById(R.id.questiontext);
        scoretext=(TextView) findViewById(R.id.scoretext);
        timetext=(TextView) findViewById(R.id.timetext);
        wrongtext=(TextView) findViewById(R.id.wrongtext);
        grid.setVisibility(View.INVISIBLE);
        questiontext.setVisibility(View.INVISIBLE);
        scoretext.setVisibility(View.INVISIBLE);
        timetext.setVisibility(View.INVISIBLE);
        playagainbutton.setVisibility(View.INVISIBLE);
        wrongtext.setVisibility(View.INVISIBLE);
    }
}


