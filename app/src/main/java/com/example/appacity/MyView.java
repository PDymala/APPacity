package com.example.appacity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View {

    Boolean switchColor = false;
    Boolean switchParam = false;
    Boolean switchStyle = false;
    private ArrayList<xy> drawList;
    private boolean touching = false;

    public void switchColor() {
        if (!switchColor) {
            switchColor = true;
        } else {
            switchColor = false;
        }
        invalidate();
    }

    public void switchParam() {
        if (!switchParam) {
            switchParam = true;
        } else {
            switchParam = false;
        }
        invalidate();
    }

    public void switchStyle() {
        if (!switchStyle) {
            switchStyle = true;
        } else {
            switchStyle = false;
        }
        invalidate();
    }


    Paint paintTouchPointer;

    public void resetScreen() {

        touchList.clear();
        invalidate();
    }

    public MyView(Context context) {
        super(context);


        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }


    private void init() {


        paintTouchPointer = new Paint();
        paintTouchPointer.setStyle(Paint.Style.FILL);


    }

    public int getIntFromColor(float Red, float Green, float Blue) {
        int R = Math.round(255 * Red);
        int G = Math.round(255 * Green);
        int B = Math.round(255 * Blue);

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        if (touching) {
            for (xy pt : touchList) {


                float radius = 50;


                if (!switchParam) {

                    if (!switchColor) {
                        paintTouchPointer.setColor(getIntFromColor(pt.getPressure(), pt.getPressure(), pt.getPressure()));
                    } else {
                        paintTouchPointer.setColor(getIntFromColor(1 - pt.getPressure(), 1 - pt.getPressure(), 1 - pt.getPressure()));

                    }

                    if (!switchStyle) {
                        radius = 7;
                    } else {
                        radius = radius * pt.getPressure();


                    }

                } else {

                    if (!switchColor) {
                        paintTouchPointer.setColor(getIntFromColor(pt.getSize(), pt.getSize(), pt.getSize()));
                    } else {
                        paintTouchPointer.setColor(getIntFromColor(1 - pt.getSize(), 1 - pt.getSize(), 1 - pt.getSize()));

                    }

                    if (!switchStyle) {
                        radius = 7;
                    } else {
                        radius = radius * pt.getSize();


                    }
                }


                canvas.drawCircle(
                        pt.getX(),
                        pt.getY(),
                        radius,
                        paintTouchPointer);

//                canvas.drawCircle(
//                        pt.getX(),
//                        pt.getY(),
//                        PRESSURE_ONE_RADIUS,
//                        paintTouchPointer_ONE);
            }
        }
    }

    ArrayList<xy> touchList = new ArrayList<xy>();

//
//for (xy s : touchList){
//        Log.i("full group", Float.toString(s.getPressure()));
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        Log.i("Pressure", String.valueOf(event.getPressure()));
        //     Log.i("Pressure", String.valueOf(event.getSize() ) + " "+ String.valueOf(event.getPressure() ));

        int action = event.getAction() & MotionEvent.ACTION_MASK;
//        switch(action){
//            case MotionEvent.ACTION_UP:
//
//
//                for (xy s : touchList){
//                    Log.i("full group", Float.toString(s.getPressure()));
//                }

//            case MotionEvent.ACTION_DOWN:

        int pointerCount = event.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            touchList.add(
                    new xy(
                            event.getX(i),
                            event.getY(i),
                            event.getPressure(i),
                            event.getSize(i)));
        }
        drawList = touchList;
        touching = true;
//                break;
//            default:
//               // touching = false;
//        }

        invalidate();
        return true;
    }

    class xy {
        float x;
        float y;
        float pressure;
        float size;

        public xy(float x, float y, float pressure, float size) {
            this.x = x;
            this.y = y;
            this.pressure = pressure;
            this.size = size;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public float getPressure() {
            return pressure;
        }

        public float getSize() {
            return size;
        }
    }

}