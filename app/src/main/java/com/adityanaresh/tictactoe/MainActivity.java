package com.adityanaresh.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    int active=0;  //0:Red 1:Yellow
    int [] board = {2,2,2,2,2,2,2,2,2};  //board status
    int [][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};  // all possible combinations
    boolean state = false; //prevent game after wining
    String winner = "";
    int counter = 0;

    public void insert(View view){
        ImageView image = (ImageView) view;

        int position = Integer.parseInt(image.getTag().toString());

        if (board[position] == 2 && !state) {

            image.setTranslationY(-1500);

            if (active == 0) {
                image.setImageResource(R.drawable.red);
                active = 1;
                board[position] = 0;
            } else {
                image.setImageResource(R.drawable.yellow);
                active = 0;
                board[position] = 1;
            }
            counter ++;
            image.animate().translationYBy(1500).rotationBy(36000).setDuration(500);

            for(int[] ifWon : win){
                if(board[ifWon[0]] == board[ifWon[1]] && board[ifWon[0]] == board[ifWon[2]] && board[ifWon[0]] !=2){
                    state = true;
                    if (active == 0){
                        winner = "Yellow";
                        break;
                    }
                    else {
                        winner = "Red";
                    }
                    Button play = findViewById(R.id.palyAgain);
                    TextView wintext = findViewById(R.id.winner);

                    play.setVisibility(View.VISIBLE);

                    Toast.makeText(this,"Winner Winner Chicken Dinner!", Toast.LENGTH_LONG).show();

                    wintext.setText(winner+" won!");
                    wintext.setVisibility(View.VISIBLE);

                    break;
                }
            }
            if (counter == 9 && !state){
                state = true;
                Button play = findViewById(R.id.palyAgain);
                TextView wintext = findViewById(R.id.winner);

                play.setVisibility(View.VISIBLE);

                Toast.makeText(this,"To Infinity and Beyond!", Toast.LENGTH_LONG).show();

                wintext.setText("It's a Tie!");
                wintext.setVisibility(View.VISIBLE);
            }

        }

    }

    public void playAgain(View view){
        Button play = findViewById(R.id.palyAgain);
        TextView wintext = findViewById(R.id.winner);

        play.setVisibility(View.VISIBLE);
        wintext.setVisibility(View.VISIBLE);
        GridLayout grid = findViewById(R.id.gridLayout);
        for (int i = 0; i<grid.getChildCount(); i++){
            ImageView image = (ImageView) grid.getChildAt(i);
            image.setImageDrawable(null);
        }

        active=0;  //0:Red 1:Yellow
        counter = 0;
        for (int i=0; i < board.length; i++)
        board[i] = 2;  //board status
        state = false; //prevent game after wining
        winner = "";

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
