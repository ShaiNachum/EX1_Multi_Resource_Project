package com.example.common;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Panel_Base extends AppCompatActivity {
    protected DataManager_Base dataManager;

    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final long GENERAL_TIMER_DELAY = 1000;
    private ShapeableImageView[][] main_IMG_images;
    private ShapeableImageView[][] main_IMG_covers;
    private MaterialTextView main_LBL_attempts;
    private MaterialTextView main_LBL_time;
    private GameManager gameManager;
    private int openCards = 0;
    private Timer openCardsTimer;
    private boolean openCardsTimerOn;
    private Timer generalTimer;
    private boolean generalTimerOn;
    private long startTime;
    private int delay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        openCardsTimerOn = false;

        findViews();

        gameManager = new GameManager(dataManager);

        initViews();

        generalStartTimer();
    }

    private void generalStartTimer() {
        if (!generalTimerOn) {
            generalTimerOn = true;

            startTime = System.currentTimeMillis();

            generalTimer = new Timer();
            generalTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> updateTimerUI());
                }
            }, 0, GENERAL_TIMER_DELAY);
        }
    }

    private void generalStopTimer() {
        generalTimerOn = false;
        generalTimer.cancel();
    }

    private void updateTimerUI() {
        long currentMillis = System.currentTimeMillis() - startTime;
        int seconds = (int) (currentMillis / 1000);
        int minutes = seconds / 60;
        seconds %= 60;
        int hours = minutes / 60;
        minutes %= 60;
        hours %= 24;

        main_LBL_time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }


    private void openCardsStartTimer() {
        this.openCardsTimerOn = true;
        openCardsTimer = new Timer();
        openCardsTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> updateUI());
                runOnUiThread(() -> openCardsTimerOn = false);
            }
        },delay);

    }

    private void setFirstCard(View view) {
        view.setVisibility(View.INVISIBLE);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (main_IMG_covers[i][j].getVisibility() == View.INVISIBLE && gameManager.getCoverStatus(i, j) == 1) {
                    gameManager.changeCoverStatus(i, j);
                    gameManager.setCard_1_i(i);
                    gameManager.setCard_1_j(j);
                }
            }
        }
        this.openCards = 1;
    }

    private void setSecondCard(View view) {
        view.setVisibility(View.INVISIBLE);
        for (int i = 0 ; i < ROWS ; i++) {
            for (int j = 0 ; j < COLS ; j++) {
                if (main_IMG_covers[i][j].getVisibility() == View.INVISIBLE && gameManager.getCoverStatus(i, j) == 1) {
                    gameManager.changeCoverStatus(i, j);
                    gameManager.setCard_2_i(i);
                    gameManager.setCard_2_j(j);
                }
            }
        }

        boolean isMatch = gameManager.checkMatch();
        boolean isGameOver = gameManager.isGameOver();

        if (isGameOver){
            gameOver();
        }
        else if(isMatch) {
            delay = 0;
        }
        else {
            delay = 1000;
        }

        gameManager.updateCovers();
        openCardsStartTimer();
        this.openCards = 0;
    }

    private void gameOver() {
        generalStopTimer();
    }

    private void coverClicked(View view) {
        if(!openCardsTimerOn) {
            if (openCards == 0) {
                setFirstCard(view);
            }
            else if (openCards == 1) {
                setSecondCard(view);
            }
        }
    }

    private void updateUI() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (gameManager.getCoverStatus(i,j) == 0) {
                    main_IMG_covers[i][j].setVisibility(View.INVISIBLE);
                }
                else {
                    main_IMG_covers[i][j].setVisibility(View.VISIBLE);
                }
            }
            main_LBL_attempts.setText("Attempts: " + gameManager.getAttempts());
        }
    }

    private void initViews() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Glide.with(this).load(gameManager.getImage(i,j)).into(main_IMG_images[i][j]);
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                main_IMG_covers[i][j].setOnClickListener(this :: coverClicked);
            }
        }
    }

    private void findViews() {
        main_IMG_images = new ShapeableImageView[ROWS][COLS];
        String baseNameIMG = "main_IMG_img";
        for (int i = 0; i < main_IMG_images.length; i++) {
            for (int j = 0; j < main_IMG_images[0].length; j++) {
                try {
                    Field idField = R.id.class.getDeclaredField(baseNameIMG + i + j);
                    int viewID = idField.getInt(idField);
                    main_IMG_images[i][j] = findViewById(viewID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        main_IMG_covers = new ShapeableImageView[ROWS][COLS];
        String baseNameCover = "main_IMG_cover";
        for (int i = 0; i < main_IMG_covers.length; i++) {
            for (int j = 0; j < main_IMG_covers[0].length; j++) {
                try {
                    Field idField = R.id.class.getDeclaredField(baseNameCover + i + j);
                    int viewID = idField.getInt(idField);
                    main_IMG_covers[i][j] = findViewById(viewID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        main_LBL_attempts = findViewById(R.id.main_LBL_attempts);
        main_LBL_time = findViewById(R.id.main_LBL_time);
    }
}