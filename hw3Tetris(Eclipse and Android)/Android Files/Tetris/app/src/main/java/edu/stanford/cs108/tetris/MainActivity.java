package edu.stanford.cs108.tetris;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TetrisUIInterface {

    BoardView boardView;
    TetrisBrainLogic tetrisLogic;

    private CheckBox testCheckBox;
    private CheckBox brainCheckBox;
    private SeekBar speedBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardView = (BoardView) findViewById(R.id.board_view);
        tetrisLogic = new TetrisBrainLogic(this);
        boardView.setLogic(tetrisLogic);

        testCheckBox = (CheckBox) findViewById(R.id.test_check);
        brainCheckBox = (CheckBox) findViewById(R.id.brain_check);
        speedBar = (SeekBar) findViewById(R.id.speed_check);


    }

    boolean gameRunning = false;

    @Override
    public void boardUpdated() {
        boardView.invalidate();
    }

    @Override
    public void dataUpdated() {
        int score = tetrisLogic.getScore();
        TextView scoreText = (TextView) findViewById(R.id.score_text);
        scoreText.setText("Score: " + score);
    }

    @Override
    public void rigGameOver() {
        gameRunning = false;
        handler.removeCallbacks(tetrisTick);
        Button startButton = (Button) findViewById(R.id.start_btn);
        startButton.setEnabled(true);
        Button stopButton = (Button) findViewById(R.id.stop_btn);
        stopButton.setEnabled(false);

        testCheckBox.setEnabled(true);
        brainCheckBox.setEnabled(true);
    }

    @Override
    public void rigGameInProgress() {
        Button startButton = (Button) findViewById(R.id.start_btn);
        startButton.setEnabled(false);
        Button stopButton = (Button) findViewById(R.id.stop_btn);
        stopButton.setEnabled(true);

        testCheckBox.setEnabled(false);
        brainCheckBox.setEnabled(false);
    }

    private class TetrisTick implements Runnable {

        @Override
        public void run() {
            if (gameRunning) {
                tetrisLogic.onTick();
                handler.postDelayed(this, getTickDelay());
            }
        }
    }

    Handler handler = new Handler();
    Runnable tetrisTick = new TetrisTick();
    int tickDelay = 1000;

    int getTickDelay() {

        tickDelay = speedBar.getProgress();
        return tickDelay;
    }

    public void onStart(View view) {
        if (gameRunning) return;


        tetrisLogic.setTestMode(testCheckBox.isChecked());
        tetrisLogic.setBrainMode(brainCheckBox.isChecked());

        tetrisLogic.onStartGame();
        gameRunning = true;
        handler.postDelayed(tetrisTick, getTickDelay());
    }

    public void onStop(View view) {
        tetrisLogic.onStopGame();
        gameRunning = false;
        handler.removeCallbacks(tetrisTick);
    }

    public void handleLeft(View view) {
        if (!gameRunning) return;

        tetrisLogic.onLeft();
    }
    public void handleRight(View view) {
        if (!gameRunning) return;

        tetrisLogic.onRight();
    }
    public void handleRotate(View view) {
        if (!gameRunning) return;

        tetrisLogic.onRotate();
    }
    public void handleDrop(View view) {
        if (!gameRunning) return;

        tetrisLogic.onDrop();
    }

}
