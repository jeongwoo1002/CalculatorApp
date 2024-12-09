package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText weightInput;
    private EditText heightInput;
    private Button calculateButton;
    private TextView resultText;
    private Button backToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        // '목록으로 가기' 버튼 초기화 및 클릭 리스너 설정
        backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity로 돌아가기
                Intent intent = new Intent(BMICalculatorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // BMI 계산기 입력 필드 및 버튼 초기화
        weightInput = findViewById(R.id.editText_weight);
        heightInput = findViewById(R.id.editText_height);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.textView_result);

        // BMI 계산 버튼 클릭 리스너 설정
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    // BMI 계산 메서드
    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        // 입력 값이 비어 있는지 체크
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            resultText.setText("키와 몸무게를 입력하세요.");
            return;
        }

        // 입력 값을 double로 변환
        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr) / 100.0; // height를 cm에서 m로 변환

        // 유효한 값인지 체크
        if (height <= 0 || weight <= 0) {
            resultText.setText("올바른 값을 입력하세요.");
            return;
        }

        // BMI 계산
        double bmi = weight / (height * height);
        String bmiCategory;

        // BMI 카테고리 계산
        if (bmi < 18.5) {
            bmiCategory = "저체중";
        } else if (bmi < 24.9) {
            bmiCategory = "정상";
        } else if (bmi < 29.9) {
            bmiCategory = "과체중";
        } else {
            bmiCategory = "비만";
        }

        // 결과 표시
        resultText.setText(String.format("BMI: %.1f (%s)", bmi, bmiCategory));
    }
}
