package com.nguyenthanhluan6251071059.multi_thread_ex2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.nguyenthanhluan6251071059.multi_thread_ex2.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int percent, randNumb;
    Random random = new Random();

    Handler handler = new Handler();

    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            binding.txtPercent.setText(percent + "%");
            binding.pbPercent.setProgress(percent);

            //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200,200);
            LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(200,200);
            params.setMargins(15,20,15,20);

            TextView textView = new TextView(MainActivity.this);

            if(randNumb % 2 == 0){
                params.gravity = Gravity.LEFT;
                textView.setBackgroundColor(Color.rgb(0,255,255));
            }else {
                textView.setBackgroundColor(Color.rgb(0,0,255));
                params.gravity = Gravity.RIGHT;
            }
            textView.setLayoutParams(params);
            textView.setText(String.valueOf(randNumb));
            textView.setTextSize(26);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);

            binding.containerLayout.addView(textView);



            if(percent == 100){
                binding.txtPercent.setText("DONE!");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addEvents();
    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execLongRunningTask();
            }
        });
    }

    private void execLongRunningTask() {
        // Worker/Background Thread
        binding.containerLayout.removeAllViews();
        int numbOfViews =Integer.parseInt(binding.edtNumbOfViews.getText().toString()) ;
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= numbOfViews ; i++){
                    percent = i * 100 / numbOfViews;
                    randNumb = random.nextInt(100);
                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }
}