package my.calculator.model;

public class Calc implements Calculator {

    @Override
    public double action(double a1, double a2, Operator operator) {
        switch (operator) {
            case ADD:
                return a1 + a2;
            case MINUS:
                return a1 - a2;
            case MYLTIPLY:
                return a1 * a2;
            case DIVERSE:
                return a1 / a2;
        }
        return 0.0;
    }
}
