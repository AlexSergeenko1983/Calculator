package my.calculator.ui;

import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import my.calculator.R;
import my.calculator.model.Calc;
import my.calculator.model.Operator;

public class CalculatorActivity extends AppCompatActivity implements CalcView {


    private static final String KEY_LASTRESULT = "key_lastResult";
    private TextView textView;
    private CalculatorPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.key_edittext);

        presenter = new CalculatorPresenter(this, new Calc());

        Map<Integer, Integer> digits = new HashMap<>();

        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));

            }
        };
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        Map<Integer, Operator> operators = new HashMap<>();

        operators.put(R.id.key_add, Operator.ADD);
        operators.put(R.id.key_minus, Operator.MINUS);
        operators.put(R.id.key_multiply, Operator.MYLTIPLY);
        operators.put(R.id.key_diverse, Operator.DIVERSE);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.key_add).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_multiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_diverse).setOnClickListener(operatorClickListener);

        findViewById(R.id.key_point).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });

        findViewById(R.id.key_equally).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onEqualsPressed();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_LASTRESULT, presenter.getLastResult());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        presenter.setLastResult((double) savedInstanceState.getSerializable(KEY_LASTRESULT));

        showResult(String.valueOf(presenter.getLastResult()));

        super.onRestoreInstanceState(savedInstanceState);
    }

    public void showResult(String result) {
        textView.setText(result);

    }
}