package com.example.simplecalculatorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CalculatorLogic calculator;

    private TextView resultField;
    private TextView actionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale locale = new Locale("en");
        Locale.setDefault(locale);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int[] buttonNumbersId = new int[] {
                R.id.button_zero,
                R.id.button_one,
                R.id.button_two,
                R.id.button_three,
                R.id.button_four,
                R.id.button_five,
                R.id.button_six,
                R.id.button_seven,
                R.id.button_eight,
                R.id.button_nine
        };

        int[] buttonActionsId = new int[] {
                R.id.button_plus,
                R.id.button_minus,
                R.id.button_multiply,
                R.id.button_divide,
                R.id.button_dot,
                R.id.button_equals,
                R.id.button_clear
        };

        resultField = findViewById(R.id.resultField);
        actionField = findViewById(R.id.actionField);

        calculator = new CalculatorLogic();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumButtonPressed(view.getId());
                resultField.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onActionButtonPressed(view.getId());
                resultField.setText(calculator.getText());
                actionField.setText(calculator.getAction());
            }
        };

        for(int i = 0; i < buttonNumbersId.length; i++) {
            findViewById(buttonNumbersId[i]).setOnClickListener(numberButtonClickListener);
        }

        for(int i = 0; i < buttonActionsId.length; i++) {
            findViewById(buttonActionsId[i]).setOnClickListener(actionButtonClickListener);
        }

    }
}
