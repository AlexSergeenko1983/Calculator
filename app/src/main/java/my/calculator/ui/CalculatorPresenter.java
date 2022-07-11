package my.calculator.ui;

import java.io.Serializable;
import java.text.DecimalFormat;

import my.calculator.model.Calculator;
import my.calculator.model.Operator;

public class CalculatorPresenter implements Serializable {

    private final DecimalFormat formater = new DecimalFormat("#.##");

    private CalcView view;
    private Calculator calculator;

    private double argOne;

    private Double argTwo;

    private Operator selectedOperator;

    private double lastResult;
    private int dotCount = 1;
    private boolean point = false;

    public CalculatorPresenter(CalcView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void setLastResult(Double lastResult) {
        this.lastResult = lastResult;
    }

    public Double getLastResult() {
        return lastResult;
    }


//    Нажатие кнопки числа
    public void onDigitPressed(double digit) {

        if (!point) {
            if (argTwo == null) {

                argOne = argOne * 10 + digit;

                showFormatted(argOne);
                lastResult = argOne;
            } else {

                argTwo = argTwo * 10 + digit;
                showFormatted(argTwo);
                lastResult = argTwo;
            }
        } else {
            if (argTwo == null) {
                argOne = argOne + digit / (10 * dotCount);
                dotCount *= 10;
                showFormatted(argOne);
                lastResult = argOne;
            } else {
                argTwo = argTwo + digit / (10 * dotCount);
                dotCount *= 10;
                showFormatted(argTwo);
                lastResult = argTwo;
            }
        }
    }

    public void onOperatorPressed(Operator operator) {
        if (selectedOperator != null) {

            argOne = calculator.action(argOne, argTwo, selectedOperator);
            lastResult = argOne;
            showFormatted(argOne);

        }

        argTwo = 0.0;
        dotCount = 1;
        point = false;
        lastResult = argOne;
        selectedOperator = operator;
    }

    public void onDotPressed() {
        if (!point) {
            point = true;
        } else {
            point = false;
        }
    }

    public void onEqualsPressed() {

        if (selectedOperator != null) {

            argOne = calculator.action(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
            argTwo = 0.0;
            dotCount = 1;
            point = false;
            lastResult = argOne;
            selectedOperator = null;
        }
    }

    public void showFormatted(double value) {
        view.showResult(formater.format(value));
    }
}


