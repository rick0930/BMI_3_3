package com.demo.android.bmi_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Bmi extends AppCompatActivity {

    private Button calc_button;
    private EditText field_height;
    private EditText field_weight;
    private TextView view_result;
    private TextView view_suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        findViews();
        setListeners();
    }

    private void findViews() {
        calc_button = (Button) findViewById(R.id.submit);
        field_height = (EditText) findViewById(R.id.height);
        field_weight = (EditText) findViewById(R.id.weight);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
    }

    private void setListeners() {
        calc_button.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            try {
                double BMI = calcBMI();
                showResult(BMI);
                openOptionsDialog();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(Bmi.this, R.string.input_error,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    private double calcBMI() {
        double height = Double.parseDouble(field_height.getText() + "") / 100;
        double weight = Double.parseDouble(field_weight.getText() + "");
        return weight / (height * height);
    }

    private void showResult(double BMI) {
        DecimalFormat nf = new DecimalFormat("0.00");
        view_result.setText("Your BMI is " + nf.format(BMI));
        if (BMI > 25) {
            view_suggest.setText(R.string.advice_heavy);
        } else if (BMI < 20) {
            view_suggest.setText(R.string.advice_light);
        } else {
            view_suggest.setText(R.string.advice_average);
        }
    }

    private void openOptionsDialog() {
        Toast.makeText(Bmi.this, R.string.toast_calculator, Toast.LENGTH_SHORT).show();
    }
}
