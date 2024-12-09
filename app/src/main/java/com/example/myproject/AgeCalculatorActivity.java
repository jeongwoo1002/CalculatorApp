package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AgeCalculatorActivity extends AppCompatActivity {

    private EditText birthYearInput, birthMonthInput, birthDayInput;
    private Button calculateButton, backToMainButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);

        birthYearInput = findViewById(R.id.editText_birth_year);
        birthMonthInput = findViewById(R.id.editText_birth_month);
        birthDayInput = findViewById(R.id.editText_birth_day);
        calculateButton = findViewById(R.id.button_calculate_age);
        resultText = findViewById(R.id.textView_age_result);
        backToMainButton = findViewById(R.id.backToMainButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });

        // '목록으로 가기' 버튼 클릭 시 MainActivity로 이동
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgeCalculatorActivity.this, MainActivity.class); // MainActivity로 변경
                startActivity(intent);
            }
        });
    }

    private void calculateAge() {
        String birthYearStr = birthYearInput.getText().toString();
        String birthMonthStr = birthMonthInput.getText().toString();
        String birthDayStr = birthDayInput.getText().toString();

        if (birthYearStr.isEmpty() || birthMonthStr.isEmpty() || birthDayStr.isEmpty()) {
            resultText.setText("생년월일을 모두 입력하세요.");
            return;
        }

        int birthYear = Integer.parseInt(birthYearStr);
        int birthMonth = Integer.parseInt(birthMonthStr);
        int birthDay = Integer.parseInt(birthDayStr);

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(birthYear, birthMonth - 1, birthDay); // 월은 0부터 시작하므로 1을 빼줘야 함.

        Calendar currentDate = Calendar.getInstance();

        int age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // 생일이 지나지 않았으면 만나이를 하나 빼줌
        if (currentDate.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
                (currentDate.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && currentDate.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        resultText.setText(String.format("현재 만나이: %d", age));
    }
}
