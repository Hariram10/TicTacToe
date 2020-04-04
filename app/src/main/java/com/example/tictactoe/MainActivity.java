package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    boolean game = true;
    String winner = " ";

    int playerAtive = 1; //0 -> black 1-> red

    int states[] = {0,0,0,0,0,0,0,0,0};

    int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void clickMe(View view){

        ImageView clickOn = (ImageView) view;
        TextView result = (TextView)findViewById(R.id.textView);
        Button playAgain = (Button)findViewById(R.id.button);


        // Log.i("Tag", clickOn.getTag().toString());

        int tappedImage = Integer.parseInt(clickOn.getTag().toString());

        if(states[tappedImage]==0 && game){

            states[tappedImage] = playerAtive;

            if(playerAtive == 1){
                clickOn.setImageResource(R.drawable.grungex4);
                playerAtive = 2;

            }else{

                clickOn.setImageResource(R.drawable.stroke);
                playerAtive = 1;
            }

            for(int[] winPos : winPos){

                if((states[winPos[0]] == states[winPos[1]]) && (states[winPos[1]] == states[winPos[2]])&&(states[winPos[0]]!= 0)) {




                    if(playerAtive == 2){
                        winner = "Black";
                    }else if(playerAtive == 1){
                        winner = "Red";
                    }
                    game = false;


                    result.setText(winner + "Won!");

                    //Toast.makeText(this,winner+ " Won",Toast.LENGTH_LONG).show();

                    playAgain.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                }

            }

            checkDraw();

            clickOn.animate().alpha(1).setDuration(1000);

        }


    }

    public void checkDraw(){
        TextView result = (TextView)findViewById(R.id.textView);

        boolean empty = false;

        for(int square : states){
            if(square == 0){
                empty = true;
            }
        }

        if(!empty && game){
            winner = "Draw";
            game = false;
             result.setText("Match " + winner);
        }
    }

    public void playAgain(View view){

        TextView result = (TextView)findViewById(R.id.textView);
        Button playAgain = (Button)findViewById(R.id.button);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        playAgain.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        game = true;
        playerAtive = 1;

        for (int i=0;i<gridLayout.getChildCount();i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

            for(int i=0;i<states.length;i++){
                states[i] = 0;
            }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
