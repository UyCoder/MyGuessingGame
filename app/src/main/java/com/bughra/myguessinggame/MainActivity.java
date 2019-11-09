package com.bughra.myguessinggame;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;

    private int theNumber;

    public void checkGuess() {

        // get the number the user entered
        String theirNumber = txtGuess.getText().toString();
        String message = "";

        try{

           int guess = Integer.parseInt(theirNumber);

           if (guess > theNumber){ // too high
               message = guess + " چوڭ بولۇپ قالدى، كىچىكرەك ساننى پەرەز قىلىڭ.";
               lblOutput.setText(message);

           } else if(guess < theNumber){ //too low
               message = guess + "  كىچىك بولۇپ، قالدى، چوڭراق ساننى پەرەز قىلىڭ.";
               lblOutput.setText(message);

           } else { //correct
               message = guess + " توغرا تاپتىڭىز!!!";
               lblOutput.setText(message);
               newGame();
           }

        }
        catch (Exception ex){
            message = " ھېچقانداق سان كىرگۈزمىدىڭىز. بىرەر سان كىرگۈزۈڭ. ";
            lblOutput.setText(message);
        }
        finally { // highlight the txtGuess txt field
            txtGuess.requestFocus();
            txtGuess.selectAll();

        }
    }

    private void newGame(){

        theNumber = (int)(Math.random() * 100 + 1);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGuess = (EditText) findViewById(R.id.txtGuess);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        lblOutput = (TextView) findViewById(R.id.lblOutput);

        newGame();

        // set up the event listener for our guess button
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        // set up the event listener for our input field
        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkGuess();
                return true;
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
