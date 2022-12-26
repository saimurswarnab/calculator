package com.example.calculatomine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackopen,buttonbrackclose;
    MaterialButton buttondivide,buttonmultiply,buttonplus,buttonminus,buttonequal;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackopen,R.id.button_open_braket);
        assignId(buttonbrackclose,R.id.button_close_braket);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonmultiply,R.id.multiplication);
        assignId(buttonplus,R.id.button_plus);
        assignId(buttonminus,R.id.button_minus);
        assignId(buttonequal,R.id.button_qual);
        assignId(button0,R.id.button_zero);
        assignId(button1,R.id.button_one);
        assignId(button2,R.id.button_two);
        assignId(button3,R.id.button_three);
        assignId(button4,R.id.button_four);
        assignId(button5,R.id.button_five);
        assignId(button6,R.id.button_six);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.eight);
        assignId(button9,R.id.button_nine);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttondot,R.id.button_dot);







    }
    void  assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton)  view;
        String buttonText = button.getText().toString();
        String dataTocCalculate = solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataTocCalculate = dataTocCalculate.substring(0,dataTocCalculate.length()-1);
        }else{

            dataTocCalculate = dataTocCalculate+buttonText;

        }
        solutionTv.setText(dataTocCalculate);
        String finalResult = getResult(dataTocCalculate);

        if (!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0"," ");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }


    }
}