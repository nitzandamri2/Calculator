package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private double result = 0;
    private String lastOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setRawInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    public void onOperatorClick(View view) {
        String fullText = display.getText().toString();

        if (fullText.contains("=")) {
            fullText = String.valueOf(result);
        }

        if (!fullText.isEmpty()) {
            String lastNumStr = lastOperator.isEmpty() ? fullText : fullText.substring(fullText.lastIndexOf(lastOperator) + 1);
            try {
                if (!lastNumStr.isEmpty()) {
                    calculate(Double.parseDouble(lastNumStr));
                }
            } catch (Exception e) {
            }
        }

        Button b = (Button) view;
        lastOperator = b.getText().toString();
        display.setText(fullText + lastOperator);

        display.setSelection(display.getText().length());
    }

    private void calculate(double n) {
        if (lastOperator.isEmpty()) {
            result = n;
        } else {
            if (lastOperator.equals("+")) result += n;
            else if (lastOperator.equals("-")) result -= n;
            else if (lastOperator.equals("×")) result *= n;
            else if (lastOperator.equals("÷")) {
                if (n != 0) result /= n;
            }
        }
    }

    public void onEqualClick(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        String fullText = display.getText().toString();
        if (!fullText.isEmpty() && !lastOperator.isEmpty()) {
            String lastNumStr = fullText.substring(fullText.lastIndexOf(lastOperator) + 1);
            try {
                if (!lastNumStr.isEmpty()) {
                    calculate(Double.parseDouble(lastNumStr));
                    display.setText(fullText + "=" + result);
                    lastOperator = "";
                }
            } catch (Exception e) {
                display.setText("Error");
            }
        }
    }

    public void onClearClick(View view) {
        display.setText("");
        result = 0;
        lastOperator = "";
    }
}