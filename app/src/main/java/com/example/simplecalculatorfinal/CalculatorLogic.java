package com.example.simplecalculatorfinal;

import android.util.Log;

import java.math.BigDecimal;

public class CalculatorLogic {

    private static final String LOG_TAG = "CalcLogic";

    private StringBuilder input = new StringBuilder();

    private double firstArg;
    private double secondArg;

    private BigDecimal firstArgBIG;
    private BigDecimal secondArgBIG;

    private boolean dotPressed;
    private String scanVar;

    private int actualAction;

    private Status status;

    private enum Status {
        firstArgInput,
        selectAction,
        secondArgInput,
        showResult
    }

    public CalculatorLogic() {
        status = Status.firstArgInput;
    }

    public void onNumButtonPressed(int numButtonId) {

        if (status == Status.showResult) {
            firstArg = 0;
            secondArg = 0;
            status = Status.firstArgInput;
            input.setLength(0);
        }

        if (status == Status.selectAction) {
            status = Status.secondArgInput;
            input.setLength(0);
        }

        if (input.length() < 10) {

            switch (numButtonId) {
                case R.id.button_zero:
                    if((input.length() != 0 || firstArg != 0) && !input.toString().equals("0") && !(input.length() == 9 && input.charAt(8) == '0')) {
                        input.append("0");
                    }
                    break;
                case R.id.button_one:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '1');
                    } else {
                        input.append('1');
                    }
                    break;
                case R.id.button_two:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '2');
                    } else {
                        input.append('2');
                    }
                    break;
                case R.id.button_three:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '3');
                    } else {
                        input.append('3');
                    }
                    break;
                case R.id.button_four:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '4');
                    } else {
                        input.append('4');
                    }
                    break;
                case R.id.button_five:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '5');
                    } else {
                        input.append('5');
                    }
                    break;
                case R.id.button_six:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '6');
                    } else {
                        input.append('6');
                    }
                    break;
                case R.id.button_seven:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '7');
                    } else {
                        input.append('7');
                    }
                    break;
                case R.id.button_eight:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '8');
                    } else {
                        input.append('8');
                    }
                    break;
                case R.id.button_nine:
                    if(input.length() > 0 && input.length() < 2 && input.charAt(0) == '0') {
                        input.setCharAt(0, '9');
                    } else {
                        input.append('9');
                    }
                    break;
            }

        }

        Log.d(LOG_TAG, "Текущее значение в поле ввода: " + input.toString());
        Log.d(LOG_TAG, "Текущее состояние: " + status.toString());

    }

    public void onActionButtonPressed(int actionButtonId) {

        Log.d(LOG_TAG, "Текущее состояние: " + status.toString());
        dotPressed = false;
        scanInput(input);
        Log.d(LOG_TAG, "Есть ли в текущем числе в поле ввода точка: " + Boolean.toString(dotPressed));
        Log.d(LOG_TAG, "first: " + Double.toString(firstArg));
        Log.d(LOG_TAG, "second: " + Double.toString(secondArg));

        if (actionButtonId == R.id.button_equals && status == Status.secondArgInput && input.length() > 0) {

            secondArg = Double.parseDouble(input.toString());
            secondArgBIG = new BigDecimal(secondArg);
            status = Status.showResult;
            input.setLength(0);
            Log.d(LOG_TAG, "Второе число для счёта: " + secondArgBIG.toString());


            switch (actualAction) {
                case R.id.button_plus:
                    input.append(firstArgBIG.add(secondArgBIG).setScale(2, BigDecimal.ROUND_CEILING));
                    Log.d(LOG_TAG, input.toString());
                    scanInputToInt(input);
                    break;
                case R.id.button_minus:
                    input.append(firstArgBIG.subtract(secondArgBIG).setScale(2, BigDecimal.ROUND_CEILING));
                    Log.d(LOG_TAG, input.toString());
                    scanInputToInt(input);
                    break;
                case R.id.button_multiply:
                    input.append(firstArgBIG.multiply(secondArgBIG).setScale(2, BigDecimal.ROUND_CEILING));
                    Log.d(LOG_TAG, input.toString());
                    scanInputToInt(input);
                    break;
                case R.id.button_divide:
                    Log.d(LOG_TAG, Double.toString(firstArg / secondArg));
                    input.append(firstArgBIG.setScale(2, BigDecimal.ROUND_HALF_UP).divide(secondArgBIG, BigDecimal.ROUND_HALF_UP));
                    Log.d(LOG_TAG, input.toString());
                    scanInputToInt(input);
                    break;
            }

        }

        if (actionButtonId == R.id.button_dot && !dotPressed && input.length() != 0 && status != Status.selectAction) {
            input.append(".");
            dotPressed = true;
        }

        if (input.length() > 0 && status == Status.firstArgInput && actionButtonId != R.id.button_dot) {
            firstArg = Double.parseDouble(input.toString());
            firstArgBIG = new BigDecimal(firstArg);
            status = Status.selectAction;
            actualAction = actionButtonId;
            Log.d(LOG_TAG, "Первое число для счёта: " + firstArgBIG.toString());
        }

        if (input.length() > 0 && status == Status.showResult) {
            firstArg = Double.parseDouble(input.toString());
            firstArgBIG = new BigDecimal(firstArg);
            status = Status.firstArgInput;
            actualAction = actionButtonId;
            Log.d(LOG_TAG, "Результат и первое число для счёта: " + firstArgBIG.toString());
        }

        if (actionButtonId == R.id.button_clear && input.length() > 0) {
            input.setLength(0);
            actualAction = 0;
            firstArg = 0;
            secondArg = 0;
            status = Status.firstArgInput;
            dotPressed = false;
            Log.d(LOG_TAG, "Обнуление всех полей.");

        }

        Log.d(LOG_TAG, "Текущее состояние: " + status.toString());

    }

    public String getText() {
        return input.toString();
    }

    public String getAction() {
        if(actualAction == R.id.button_plus) return "+";
        else if(actualAction == R.id.button_minus) return "-";
        else if(actualAction == R.id.button_multiply) return "x";
        else if(actualAction == R.id.button_divide) return "÷";
        else if(actualAction == R.id.button_equals) return "=";
        else return " ";
    }

    private void scanInput(StringBuilder input) {

        scanVar = input.toString();

        for (int i = 0; i < scanVar.length(); i++) {
            if (scanVar.charAt(i) == '.') dotPressed = true;
        }

    }

    private void scanInputToInt(StringBuilder input) {

        scanVar = input.toString();

        for (int i = 0; i < scanVar.length(); i++) {
            if (scanVar.charAt(i) == '.') {
                if (scanVar.charAt(scanVar.length()-1) == '0') {
                    input.setLength(i+2);
                }
                if (scanVar.charAt(scanVar.length()-1) == '0' && scanVar.charAt(scanVar.length()-2) == '0' ) {
                    input.setLength(i);
                }
            }
        }

    }

    private void roundInput(StringBuilder input) {

        scanVar = input.toString();

        for (int i = 0; i < scanVar.length(); i++) {
            if (scanVar.charAt(i) == '.' && (actualAction == R.id.button_divide || actualAction == R.id.button_multiply)) {
                input.setLength(i + 1);
            } else {
                input.setLength(input.length());
            }
        }

    }

}
