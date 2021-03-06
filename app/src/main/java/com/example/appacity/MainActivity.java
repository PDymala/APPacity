package com.example.appacity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    /**
     * APPacity is an application made in order to experiment with screen's touch parameters.
     *
     * @author Piotr Dymala p.dymala@gmail.com
     * @version 1.0
     * @since 2020-02-20
     */

    Button resetButton;
    Switch switchColorsButton;
    Switch switchParamButton;
    Switch switchStyleButton;
    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetButton = (Button) findViewById(R.id.buttonReset);
        switchColorsButton = (Switch) findViewById(R.id.switchColors);
        switchParamButton = (Switch) findViewById(R.id.switchParam);
        switchStyleButton = (Switch) findViewById(R.id.switchStyle);
        myView = findViewById(R.id.viewTouch);

    }


    public void resetScreen(View v) {
        myView.resetScreen();
    }

    public void switchColor(View v) {
        myView.switchColor();
    }

    public void switchParam(View v) {
        myView.switchParam();
    }

    public void switchStyle(View v) {
        myView.switchStyle();
    }

}