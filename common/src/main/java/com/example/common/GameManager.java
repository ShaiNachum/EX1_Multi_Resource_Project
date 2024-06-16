package com.example.common;

import java.util.ArrayList;

public class GameManager {
    private static final int VISIBLE = 1;
    private static final int INVISIBLE = 0;
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private Card[][] images;
    private int [][] covers;
    private int attempts;
    private int matches;
    private ArrayList<Card> allCountries;
    private int card_1_i;
    private int card_1_j;
    private int card_2_i;
    private int card_2_j;


    public GameManager(DataManager_Base dataManager){
        this.allCountries = dataManager.getCards();

        InitCovers();

        initImages();

        this.attempts = 0;
        this.matches = 0;
    }

    private void InitCovers() {
        this.covers = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                covers[i][j] = VISIBLE;
            }
        }
    }

    private void initImages() {
        this.images = new Card[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                images[i][j] = allCountries.get(i * ROWS + j);
            }
        }
    }

    public String getImage(int i, int j) {
        return images[i][j].getFlag();
    }

    public void changeCoverStatus(int i, int j) {
        covers[i][j] = INVISIBLE;
    }

    public void setCard_1_i(int card_1_i) {
        this.card_1_i = card_1_i;
    }

    public void setCard_1_j(int card_1_j) {
        this.card_1_j = card_1_j;
    }

    public void setCard_2_i(int card_2_i) {
        this.card_2_i = card_2_i;
    }

    public void setCard_2_j(int card_2_j) {
        this.card_2_j = card_2_j;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean checkMatch() {
        if(images[card_1_i][card_1_j].getId() == images[card_2_i][card_2_j].getId()){
            images[card_1_i][card_1_j].setFlipped(true);
            images[card_2_i][card_2_j].setFlipped(true);
            attempts++;
            matches++;
            return true;
        }
        else{
            attempts++;
            return false;
        }
    }

    public void updateCovers(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(images[i][j].isFlipped())
                    covers[i][j] = INVISIBLE;
                else
                    covers[i][j] = VISIBLE;
            }
        }
    }

    public boolean isGameOver(){
        return matches == 8;
    }

    public int getCoverStatus(int i, int j) {
        return covers[i][j];
    }

}
