package com.example.k23411teapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.ExpressionBuilder;

public class CalculatorActivity extends AppCompatActivity {

    EditText edtFormula;
    Button btnDel, btnCal;

    TextView txtMC, txtMR, txtMPlus, txtMSub, txtMS, txtM;

    View.OnClickListener m_click_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);

        addViews();
        addEvents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtFormula = findViewById(R.id.edtFormula);

        btnDel = findViewById(R.id.btnDel);
        btnCal = findViewById(R.id.btnCal);

        txtMC = findViewById(R.id.txtMC);
        txtMR = findViewById(R.id.txtMR);
        txtMPlus = findViewById(R.id.txtMPlus);
        txtMSub = findViewById(R.id.txtMSub);
        txtMS = findViewById(R.id.txtMS);
        txtM = findViewById(R.id.txtM);
    }

    private void addEvents() {

        // Nút Del: xóa 1 ký tự cuối
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String formula = edtFormula.getText().toString();

                if (formula.length() > 0) {
                    String new_formula = formula.substring(0, formula.length() - 1);
                    edtFormula.setText(new_formula);
                }
            }
        });

        // Nút = : tính kết quả
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String formula = edtFormula.getText().toString();

                if (formula.isEmpty()) {
                    Toast.makeText(CalculatorActivity.this, "Vui lòng nhập công thức", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double result = new ExpressionBuilder(formula).build().evaluate();

                    // Nếu kết quả là số nguyên thì không hiện .0
                    if (result == Math.rint(result)) {
                        edtFormula.setText(String.valueOf((long) result));
                    } else {
                        edtFormula.setText(String.valueOf(result));
                    }

                } catch (Exception e) {
                    Toast.makeText(CalculatorActivity.this, "Công thức không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Các nút Memory
        m_click_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.equals(txtM)) {
                    Toast.makeText(CalculatorActivity.this, "You clicked M", Toast.LENGTH_SHORT).show();
                } else if (v.equals(txtMR)) {
                    Toast.makeText(CalculatorActivity.this, "You clicked MR", Toast.LENGTH_SHORT).show();
                } else if (v.equals(txtMC)) {
                    Toast.makeText(CalculatorActivity.this, "You clicked MC", Toast.LENGTH_SHORT).show();
                } else if (v.equals(txtMPlus)) {
                    Toast.makeText(CalculatorActivity.this, "You clicked M+", Toast.LENGTH_SHORT).show();
                } else if (v.equals(txtMSub)) {
                    Toast.makeText(CalculatorActivity.this, "You clicked M-", Toast.LENGTH_SHORT).show();
                } else if (v.equals(txtMS)) {
                    Toast.makeText(CalculatorActivity.this, "You clicked MS", Toast.LENGTH_SHORT).show();
                }
            }
        };

        txtMC.setOnClickListener(m_click_listener);
        txtMR.setOnClickListener(m_click_listener);
        txtMPlus.setOnClickListener(m_click_listener);
        txtMSub.setOnClickListener(m_click_listener);
        txtMS.setOnClickListener(m_click_listener);
        txtM.setOnClickListener(m_click_listener);
    }

    // Hàm này dùng cho các nút nhập bình thường: 0-9, +, -, *, /, .
    public void processInputData(View view) {

        Button btn = (Button) view;

        String new_value = btn.getText().toString();
        String current_value = edtFormula.getText().toString();

        String lasted_value = current_value + new_value;

        edtFormula.setText(lasted_value);
    }

    // Hàm này dùng cho các nút chức năng: C, CE, %, 1/X, x^2, SQR(), +/-
    public void processFunction(View view) {

        Button btn = (Button) view;

        String function = btn.getText().toString();
        String formula = edtFormula.getText().toString();

        if (function.equals("C")) {
            edtFormula.setText("");
        }

        else if (function.equals("CE")) {
            edtFormula.setText("");
        }

        else if (function.equals("%")) {
            if (!formula.isEmpty()) {
                edtFormula.setText("(" + formula + ")/100");
            }
        }

        else if (function.equals("1/X")) {
            if (!formula.isEmpty()) {
                edtFormula.setText("1/(" + formula + ")");
            }
        }

        else if (function.equals("x^2")) {
            if (!formula.isEmpty()) {
                edtFormula.setText("(" + formula + ")^2");
            }
        }

        else if (function.equals("SQR()")) {
            if (!formula.isEmpty()) {
                edtFormula.setText("sqrt(" + formula + ")");
            }
        }

        else if (function.equals("+/-")) {
            if (formula.isEmpty()) {
                edtFormula.setText("-");
            } else if (formula.startsWith("-")) {
                edtFormula.setText(formula.substring(1));
            } else {
                edtFormula.setText("-(" + formula + ")");
            }
        }
    }
}