package com.example.ex1_multi_resource_project;

import android.os.Bundle;

import com.example.common.Activity_Panel_Base;


public class Activity_Panel extends Activity_Panel_Base {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataManager = new DataManagerFlags();
        super.onCreate(savedInstanceState);

    }

}