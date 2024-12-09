package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText inputField;
    private TextView resultText;
    private String currentInput = "";
    private Button backToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        inputField = findViewById(R.id.editText_input);
        resultText = findViewById(R.id.textView_result);
        backToMainButton = findViewById(R.id.backToMainButton);

        // 숫자 버튼 클릭 리스너
        findViewById(R.id.button_0).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_1).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_2).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_3).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_4).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_5).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_6).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_7).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_8).setOnClickListener(onDigitClickListener);
        findViewById(R.id.button_9).setOnClickListener(onDigitClickListener);

        // 연산자 버튼 클릭 리스너
        findViewById(R.id.button_add).setOnClickListener(onOperatorClickListener);
        findViewById(R.id.button_subtract).setOnClickListener(onOperatorClickListener);
        findViewById(R.id.button_multiply).setOnClickListener(onOperatorClickListener);
        findViewById(R.id.button_divide).setOnClickListener(onOperatorClickListener);

        // = 버튼 클릭 리스너
        findViewById(R.id.button_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        // C 버튼 클릭 리스너 (초기화)
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });

        // '목록으로 가기' 버튼 클릭 시 MainActivity로 이동
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this, MainActivity.class); // MainActivity로 변경
                startActivity(intent);
            }
        });
    }

    // 숫자 버튼 클릭 리스너
    private View.OnClickListener onDigitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            currentInput += button.getText().toString();
            inputField.setText(currentInput);
        }
    };

    // 연산자 버튼 클릭 리스너
    private View.OnClickListener onOperatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            currentInput += " " + button.getText().toString() + " ";
            inputField.setText(currentInput);
        }
    };

    // 계산 결과 계산 메서드
    private void calculateResult() {
        try {
            String[] tokens = currentInput.split(" ");
            if (tokens.length == 3) {
                double operand1 = Double.parseDouble(tokens[0]);
                double operand2 = Double.parseDouble(tokens[2]);
                double result = 0;

                switch (tokens[1]) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            resultText.setText("오류: 0으로 나눌 수 없습니다.");
                            return;
                        }
                        break;
                }

                resultText.setText(String.format("결과: %.2f", result));
                currentInput = String.valueOf(result);  // 결과값을 다시 입력으로 설정
            }
        } catch (Exception e) {
            resultText.setText("잘못된 입력");
        }
    }

    // 입력 초기화
    private void clearInput() {
        currentInput = "";
        inputField.setText("");
        resultText.setText("");
    }
}
