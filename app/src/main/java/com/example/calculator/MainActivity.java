package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution, resultv;
    MaterialButton buttonclear, buttonopen, buttonclose;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonallclear, buttondot, buttonequals;
    MaterialButton buttonadd, buttonminus, buttonmultiply, buttondivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultv = findViewById(R.id.result_tv);
        solution = findViewById(R.id.solution_tv);


        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonclear, R.id.button_c);
        assignId(buttonopen, R.id.button_open_bracket);
        assignId(buttonclose, R.id.button_close_bracket);
        assignId(buttonallclear, R.id.button_all_clear);
        assignId(buttondot, R.id.button_dot);
        assignId(buttonequals, R.id.button_equals);
        assignId(buttonadd, R.id.button_add);
        assignId(buttonminus, R.id.button_sub);
        assignId(buttonmultiply, R.id.button_multiply);
        assignId(buttondivide, R.id.button_divide);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        //solution.setText(buttonText);
        String dataCalculate = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            resultv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(resultv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataCalculate = dataCalculate.substring(0,dataCalculate.length()-1);
        }else{
            dataCalculate = dataCalculate+buttonText;
        }

        solution.setText(dataCalculate);
        String finalResult = getResult(dataCalculate);

        if(!finalResult.equals("Error occured")){
            resultv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data,"Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error occured";
        }
    }
}