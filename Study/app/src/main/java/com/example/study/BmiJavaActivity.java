package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BmiJavaActivity extends AppCompatActivity {

    EditText tallField;
    EditText weightField;
    TextView resultLabel;
    Button bmiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tallField = findViewById(R.id.tallText);
        weightField = findViewById(R.id.weightText);
        resultLabel = findViewById(R.id.resultText);
        bmiButton = findViewById(R.id.bmiButton);

        //bmiButton이 클릭된 경우의 이벤트 리스너를 등록한다.
        bmiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tall = tallField.getText().toString();

                String weight = weightField.getText().toString();

                double bmi = Double.parseDouble(weight) / Math.pow(Double.parseDouble(tall) / 100.0, 2);

                resultLabel.setText("키: " + tall + ", 체중: " + weight + ", BMI :" + bmi);
            }
        });
    }
}