package com.example.javastudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메시지를 잠시 보여준다.
        Toast.makeText(getApplicationContext(), "프로그래밍을 시작해보자!", Toast.LENGTH_LONG).show();

        // 레이아웃에 선언된 뷰에 클릭 이벤트 리스너를 등록한다.
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                clickCount = clickCount + 1;

                Toast.makeText(getApplicationContext(), "프로그래밍을 시작해보자! " + clickCount, Toast.LENGTH_LONG).show();
            }
        });
    }
}
