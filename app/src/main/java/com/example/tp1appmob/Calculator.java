package com.example.tp1appmob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;


public class Calculator extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv,solutionTv;
    boolean isEqualPressedLast = false;


    MaterialButton button_div,button_mins,button_plus,button_mul,button_equal;
    MaterialButton button_0,button_1,button_2, button_3,button_4,button_5, button_6,button_7,button_8, button_9;
    MaterialButton button_openBracket,button_closeBracket,button_coma,button_AC,button_C;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        resultTv = findViewById(R.id.result);
        solutionTv = findViewById(R.id.solution);
        assignId(button_C,R.id.button_c);
        assignId(button_div,R.id.button_div);
        assignId(button_mins,R.id.button_mins);
        assignId(button_plus,R.id.button_plus);
        assignId(button_mul,R.id.button_mul);
        assignId(button_equal,R.id.button_equal);
        assignId(button_0,R.id.button_0);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_openBracket,R.id.button_openBracket);
        assignId(button_closeBracket,R.id.closeBracket);
        assignId(button_coma,R.id.button_coma);
        assignId(button_AC,R.id.button_AC);


    }

    void assignId(MaterialButton btn ,int id){
        btn= findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonTxt = button.getText().toString();

        if (buttonTxt.equals("AC")) {
            solutionTv.setText("0");
            resultTv.setText("0");
            isEqualPressedLast = false;
            return;
        } else if (buttonTxt.equals("C")) {
            String currentText = solutionTv.getText().toString();
            if (currentText.length() > 1) {
                //backspace
                solutionTv.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                // if once set "0"
                solutionTv.setText("0");
            }
            isEqualPressedLast = false;
            return;
        } else if (buttonTxt.equals("=")) {
            String evaluatedResult = getResult(solutionTv.getText().toString());
            if (!evaluatedResult.equals("Err")) {
                //if not have an error display rsult
                resultTv.setText(evaluatedResult);
            } else {
                resultTv.setText("Error");
            }
            isEqualPressedLast = true;
            return;
        } else {
            if (isEqualPressedLast) {
                if (buttonTxt.matches("[0-9]") || buttonTxt.equals("(") || buttonTxt.matches("[a-zA-Z]+")) {
                    solutionTv.setText("");
                }
                isEqualPressedLast = false;
            }
        }

        String data = solutionTv.getText().toString();
        if (data.equals("0")) {
            data = "";
        }
//get expression from buttons 9--9.text in scrreen
        data += buttonTxt;
        solutionTv.setText(data);
    }

    public String getResult(String expression) {
        try {
            //add * between number and (
            String processedExpression = problemMul(expression);

            // close parenthese if not closing
            processedExpression = closingParentais(processedExpression);

            double result = eval(processedExpression);

            if (result == (long) result) {
                //if long display without zero
                return String.format("%d", (long) result);
            } else {
                //display 2 digit after point
                return String.format("%.2f", result);
            }
        } catch (Exception e) {
            return "Error";
        }
    }

    private String problemMul(String expression) {
        return expression.replaceAll("(\\d)\\s*\\(", "$1*(");
    }

    private String closingParentais(String expression) {
        int openCount = 0;
        int closeCount = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                openCount++;
            } else if (expression.charAt(i) == ')') {
                closeCount++;
            }
        }

        while (openCount > closeCount) {
            expression += ")";
            closeCount++;
        }

        return expression;
    }



    //Eval Script Engine JavaScript
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }
            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }


    private boolean isContainPoint(String expression) {
        int i = expression.length() - 1;
        while (i >= 0) {
            char c = expression.charAt(i);
            if (c == '.') {
                return true;
            }
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                break;
            }
            i--;
        }
        return false;
    }



}