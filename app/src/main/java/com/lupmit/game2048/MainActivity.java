package com.lupmit.game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView gdv_game;
    public customAdapter customAdapter;
    private View.OnTouchListener listener;
    private float X,Y;
    public TextView diem, best;
    static int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gdv_game = (GridView)findViewById(R.id.gdv_game);
        diem = (TextView)findViewById(R.id.diem);
        best = (TextView)findViewById(R.id.best);
        diem.setText("0");

        maingame.getDatagame().Init(MainActivity.this);
        customAdapter = new customAdapter(MainActivity.this,0,maingame.getDatagame().getArrSo());

        x = Integer.parseInt(diem.getText().toString());
        y = Integer.parseInt(best.getText().toString());


        listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        X = motionEvent.getX();
                        Y = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        if(Math.abs(motionEvent.getX() - X) > Math.abs(motionEvent.getY() - Y)) {
                            if(motionEvent.getX() > X) {
                                int di = maingame.getDatagame().vuottrai(customAdapter);
                                update(di);

                            } else {
                                int di = maingame.getDatagame().vuotphai(customAdapter);
                                update(di);
                            }
                        }else
                            if(motionEvent.getY() > Y) {
                                //do it
                                int di = maingame.getDatagame().vuotxuong(customAdapter);
                                update(di);

                            }else {
                                //go
                                int di = maingame.getDatagame().vuotlen(customAdapter);
                                update(di);

                            }
                }


                return true;
            }
        };

        gdv_game.setAdapter(customAdapter);
        gdv_game.setOnTouchListener(listener);



    }
    public void update(int di) {
        x = x+di;
        diem.setText(""+x);
        if(x> y)
            y = x;
        best.setText(""+ y);
    }
}
