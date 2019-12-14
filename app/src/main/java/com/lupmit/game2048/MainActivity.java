package com.lupmit.game2048;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView gdv_game;
    public customAdapter customAdapter;
    private View.OnTouchListener listener;
    private float X,Y;
    public TextView diem, best;
    public ImageButton IbtnBack;
    static int x, y;
    static boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gdv_game = (GridView)findViewById(R.id.gdv_game);
        diem = (TextView)findViewById(R.id.diem);
        best = (TextView)findViewById(R.id.best);
        IbtnBack = (ImageButton) findViewById(R.id.ibtn_back);
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
                                int di = maingame.getDatagame().vuottrai();
                                update(di);

                            } else {
                                int di = maingame.getDatagame().vuotphai();
                                update(di);
                            }
                        }else
                            if(motionEvent.getY() > Y) {
                                //do it
                                int di = maingame.getDatagame().vuotxuong();
                                update(di);

                            }else {
                                //go
                                int di = maingame.getDatagame().vuotlen();
                                update(di);

                            }
                }


                return true;
            }
        };

        IbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check == false) {
                    Toast.makeText(MainActivity.this, "Bạn không thể thực hiện chức năng này 2 lần liên tiếp", Toast.LENGTH_LONG).show();
                    return;
                }
                int x = Integer.parseInt(diem.getText().toString());
                if(x < 10){
                    Toast.makeText(MainActivity.this, "Số điểm của bạn không đủ 10 để thực hiện chức năng này!", Toast.LENGTH_LONG).show();
                    return;
                }
                update(-10);
                maingame.getDatagame().back_to_bf();
                check = false;
                customAdapter.notifyDataSetChanged();
            }
        });

        gdv_game.setAdapter(customAdapter);
        gdv_game.setOnTouchListener(listener);
    }
    public void update(int di) {
        check = true;
        x = x+di;
        diem.setText(""+x);
        if(x> y)
            y = x;
        best.setText(""+ y);
        customAdapter.notifyDataSetChanged();
        if(maingame.getDatagame().endgame() == true) {
            Intent intent = new Intent(MainActivity.this, EndGame.class);
            Bundle bundle= new Bundle();
            bundle.putSerializable("diem", Integer.parseInt(diem.getText().toString()));
            intent.putExtra("package", bundle);
            startActivity(intent);
            maingame.getDatagame().Init(MainActivity.this);
            customAdapter.notifyDataSetChanged();
        }
    }
}
