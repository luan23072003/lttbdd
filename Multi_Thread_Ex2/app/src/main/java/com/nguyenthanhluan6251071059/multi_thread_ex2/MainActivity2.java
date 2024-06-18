package com.nguyenthanhluan6251071059.multi_thread_ex2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenthanhluan6251071059.multi_thread_ex2.databinding.ActivityMain2Binding;
import com.nguyenthanhluan6251071059.multi_thread_ex2.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    ExecutorService executorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        executorService = Executors.newSingleThreadExecutor();

        addEvents();

    }

    private void addEvents() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerLayout.removeAllViews();
                executeLongRunningTask();
            }
        });
    }

//    private void executeLongRunningTask() {
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                int numbOfViews = Integer.parseInt(binding.edtNumbOfViews.getText().toString());
//
//                Random random = new Random();
//                for (int i = 1; i <= numbOfViews ; i++){
//                    final int percent, randNumb;
//                    percent = i * 100/ numbOfViews;
//                    randNumb = random.nextInt(100);
//
//                    //Update UI
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            binding.txtPercent.setText(percent +"%");
//                            binding.pbPercent.setProgress(percent);
//
//                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);
//                            TextView textView = new TextView(MainActivity2.this);
//                            DisplayMetrics displayMetrics = new DisplayMetrics();
//                            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//                            int width = displayMetrics.widthPixels;
//
//                            if(randNumb % 2 == 0){
//                                params.gravity = Gravity.START;
//                                textView.setBackgroundColor(Color.rgb(255,255,0));
//                                params.width = width/2;
//                            }else {params.gravity = Gravity.END;
//                                textView.setBackgroundColor(Color.rgb(255,0,0));
//                                params.width = width/2;
//                            }
//
//                            textView.setText(String.valueOf(randNumb));
//                            textView.setTextColor(Color.WHITE);
//                            textView.setGravity(Gravity.CENTER);
//
//                            textView.setLayoutParams(params);
//
//                            binding.containerLayout.addView(textView);
//
//                            if(percent == 100){
//                                binding.txtPercent.setText("DONE!");
//                            }
//                        }
//                    });
//
//                    try {
//                        Thread.sleep(1000);
//                    }catch (InterruptedException e){
//                        Thread.currentThread().interrupt();
//                    }
//
//                }
//
//
//
//            }
//        });
//    }
//private void executeLongRunningTask() {
//    executorService.execute(new Runnable() {
//        @Override
//        public void run() {
//            int numbOfViews = Integer.parseInt(binding.edtNumbOfViews.getText().toString());
//            Random random = new Random();
//            for (int i = 1; i <= numbOfViews; i++) {
//                final int percent = i * 100 / numbOfViews;
//                final int randNumb1 = random.nextInt(100);
//                final int randNumb2 = random.nextInt(100);
//                final int currentIndex = i; // Biến tạm thời để sử dụng trong lớp ẩn danh
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.txtPercent.setText(percent + "%");
//                        binding.pbPercent.setProgress(percent);
//
//                        // Tạo hàng mới
//                        LinearLayout rowLayout = new LinearLayout(MainActivity2.this);
//                        rowLayout.setOrientation(LinearLayout.HORIZONTAL);
//                        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
//                                LinearLayout.LayoutParams.MATCH_PARENT,
//                                LinearLayout.LayoutParams.WRAP_CONTENT);
//                        rowLayout.setLayoutParams(rowParams);
//
//                        // Tạo TextView đầu tiên
//                        TextView textView1 = new TextView(MainActivity2.this);
//                        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(0, 200, 2); // Tỷ lệ 2
//                        textView1.setLayoutParams(params1);
//                        textView1.setBackgroundColor(Color.rgb(255, 255, 0));
//                        textView1.setText(String.valueOf(randNumb1));
//                        textView1.setTextColor(Color.WHITE);
//                        textView1.setGravity(Gravity.CENTER);
//
//                        // Tạo TextView thứ hai
//                        TextView textView2 = new TextView(MainActivity2.this);
//                        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, 200, 1); // Tỷ lệ 1
//                        textView2.setLayoutParams(params2);
//                        textView2.setBackgroundColor(Color.rgb(255, 0, 0));
//                        textView2.setText(String.valueOf(randNumb2));
//                        textView2.setTextColor(Color.WHITE);
//                        textView2.setGravity(Gravity.CENTER);
//
//                        // Thêm TextView vào hàng so le nhau
//                        if (currentIndex % 2 == 0) {
//                            rowLayout.addView(textView1);
//                            rowLayout.addView(textView2);
//                        } else {
//                            rowLayout.addView(textView2);
//                            rowLayout.addView(textView1);
//                        }
//
//                        // Thêm hàng vào container
//                        binding.containerLayout.addView(rowLayout);
//
//                        if (percent == 100) {
//                            binding.txtPercent.setText("DONE!");
//                        }
//                    }
//                });
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    });
//}
//private void executeLongRunningTask() {
//    executorService.execute(new Runnable() {
//        @Override
//        public void run() {
//            int numbOfViews = Integer.parseInt(binding.edtNumbOfViews.getText().toString());
//            Random random = new Random();
//            for (int i = 1; i <= numbOfViews; i++) {
//                final int percent = i * 100 / numbOfViews;
//                final int randNumb = random.nextInt(100) + 1;
//                final int currentIndex = i;
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.txtPercent.setText(percent + "%");
//                        binding.pbPercent.setProgress(percent);
//
//                        if (currentIndex % 2 == 0) {
//                            // Tạo Button
//                            Button button = new Button(MainActivity2.this);
//                            button.setText(String.valueOf(randNumb));
//                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                    ViewGroup.LayoutParams.MATCH_PARENT,
//                                    ViewGroup.LayoutParams.WRAP_CONTENT);
//                            button.setLayoutParams(params);
//                            binding.containerLayout.addView(button);
//                        } else {
//                            // Tạo EditText
//                            EditText editText = new EditText(MainActivity2.this);
//                            editText.setText(String.valueOf(randNumb));
//                            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                    ViewGroup.LayoutParams.MATCH_PARENT,
//                                    ViewGroup.LayoutParams.WRAP_CONTENT);
//                            editText.setLayoutParams(params);
//                            binding.containerLayout.addView(editText);
//                        }
//
//                        if (percent == 100) {
//                            binding.txtPercent.setText("DONE!");
//                        }
//                    }
//                });
//
//                try {
//                    Thread.sleep(500); // Giảm thời gian ngủ xuống 500ms để tốc độ tạo view nhanh hơn
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    });
//}
private void executeLongRunningTask() {
    executorService.execute(new Runnable() {
        @Override
        public void run() {
            String[] buttons = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};
            Handler mainHandler = new Handler(Looper.getMainLooper());

            final int totalButtons = buttons.length;
            final int[] currentProgress = {0}; // Array with a single element

            for (int i = 0; i < totalButtons; i++) {
                final String buttonText = buttons[i];
                final int index = i;

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Button button = new Button(MainActivity2.this);
                        button.setText(buttonText);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                0,
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                1.0f
                        );
                        button.setLayoutParams(params);

                        // Thêm sự kiện nhấn nút
                        // Trong hàm executeLongRunningTask()

                        // Trong hàm executeLongRunningTask()

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String buttonText = ((Button) v).getText().toString();

                                if (buttonText.equals("*") || buttonText.equals("#")) {
                                    binding.edtNumbOfViews.append(buttonText);
                                } else {
                                    binding.edtNumbOfViews.append(buttonText);
                                }
                            }
                        });
                        // Tạo hàng mới sau mỗi 3 button
                        if (index % 3 == 0) {
                            LinearLayout rowLayout = new LinearLayout(MainActivity2.this);
                            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            ));
                            binding.containerLayout.addView(rowLayout);
                        }

                        // Thêm button vào hàng hiện tại
                        LinearLayout currentRow = (LinearLayout) binding.containerLayout.getChildAt(binding.containerLayout.getChildCount() - 1);
                        currentRow.addView(button);

                        // Cập nhật thanh tiến trình
                        currentProgress[0]++;
                        int percent = currentProgress[0] * 100 / totalButtons;
                        updateProgressBar(percent);
                    }
                });

                try {
                    // Đợi một lúc để quan sát quá trình
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    });
}

    private void updateProgressBar(int percent) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.txtPercent.setText(percent + "%");
                binding.pbPercent.setProgress(percent);

                if (percent == 100) {
                    binding.txtPercent.setText("DONE!");
                }
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(executorService!=null){
            executorService.shutdown();
        }
    }
}