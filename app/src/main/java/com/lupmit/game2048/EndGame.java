package com.lupmit.game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {
    private TextView TvScore;
    private Button BtnTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        TvScore = (TextView) findViewById(R.id.tv_score);
        BtnTry = findViewById(R.id.btn_try);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("package");
        int diem = (int) bundle.getSerializable("diem");
        TvScore.setText("SCORE\n" + diem);

        BtnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });
    }
}
