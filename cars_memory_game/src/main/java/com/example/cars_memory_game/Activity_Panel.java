package com.example.cars_memory_game;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.common.Activity_Panel_Base;

public class Activity_Panel extends Activity_Panel_Base {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataManager = new DataManagerCars();
        super.onCreate(savedInstanceState);

    }
}