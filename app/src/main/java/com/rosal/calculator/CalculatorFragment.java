package com.rosal.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment {

    private EditText number1EditText, number2EditText;
    private TextView resultTextView;
    private Button addButton, subtractButton, multiplyButton, divideButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        number1EditText = view.findViewById(R.id.et_number1);
        number2EditText = view.findViewById(R.id.et_number2);
        resultTextView = view.findViewById(R.id.tv_result);
        addButton = view.findViewById(R.id.btn_add);
        subtractButton = view.findViewById(R.id.btn_subtract);
        multiplyButton = view.findViewById(R.id.btn_multiply);
        divideButton = view.findViewById(R.id.btn_divide);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });

        return view;
    }

    private void calculate(char operator) {
        String num1Str = number1EditText.getText().toString();
        String num2Str = number2EditText.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }

            DecimalFormat df = new DecimalFormat("0.00");
            resultTextView.setText(df.format(result));
        } else {
            resultTextView.setText("0.00");
        }
    }
}