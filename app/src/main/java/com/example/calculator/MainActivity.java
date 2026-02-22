package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFirstNumber;
    private EditText editTextSecondNumber;
    private TextView textViewResultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextFirstNumber = findViewById(R.id.edit_text_first_number);
        editTextSecondNumber = findViewById(R.id.edit_text_second_number);
        textViewResultDisplay = findViewById(R.id.text_view_result);
    }

    public void onOperatorClick(View view) {
        String firstInput = editTextFirstNumber.getText().toString();
        String secondInput = editTextSecondNumber.getText().toString();

        if (firstInput.isEmpty() || secondInput.isEmpty()) {
            Toast.makeText(this, "נא להזין שני מספרים", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(firstInput);
        double num2 = Double.parseDouble(secondInput);
        double result = 0;

        int viewId = view.getId();

        if (viewId == R.id.button_add) {
            result = num1 + num2;
        } else if (viewId == R.id.button_subtract) {
            result = num1 - num2;
        } else if (viewId == R.id.button_multiply) {
            result = num1 * num2;
        } else if (viewId == R.id.button_divide) {
            if (num2 != 0) {
                result = num1 / num2;
            } else {
                Toast.makeText(this, "שגיאה: חלוקה ב-0!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        textViewResultDisplay.setText("תוצאה: " + result);
    }
    public void onClearClick(View view) {
        editTextFirstNumber.setText("");
        editTextSecondNumber.setText("");
        textViewResultDisplay.setText("תוצאה: 0");
    }
}