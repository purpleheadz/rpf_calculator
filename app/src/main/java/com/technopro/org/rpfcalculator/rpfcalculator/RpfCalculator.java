package com.technopro.org.rpfcalculator.rpfcalculator;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by Makoto Mine on 15/09/08.
 */

interface IRpfCalculatorOperator {
    double execute(double num1, double num2);
}

public class RpfCalculator {
    static class OperatorAdd implements IRpfCalculatorOperator {
        @Override
        public double execute(double num1, double num2) {
            return num2 + num1;
        }
    }

    static class OperatorSub implements IRpfCalculatorOperator {
        @Override
        public double execute(double num1, double num2) {
            return num2 - num1;
        }
    }

    static class OperatorMul implements IRpfCalculatorOperator {
        @Override
        public double execute(double num1, double num2) {
            return num2 * num1;
        }
    }

    static class OperatorDev implements IRpfCalculatorOperator {
        @Override
        public double execute(double num1, double num2) {
            return num2 / num1;
        }
    }

    private Stack<Double> mOperandStack;
    private IRpfCalculatorOperator mOperator;

    public RpfCalculator() {
        mOperandStack = new Stack<Double>();
        mOperator = null;
    }

    protected boolean isOperand(String token) throws ArithmeticException{
        return Pattern.compile("^[0-9.]*$").matcher(token).find();
    }

    public double calculateRfp(String rpf) {
        if (rpf == null) return 0.0f;

        for (String token : rpf.split(" ")) {
            if (token.trim().length() <= 0) continue;
            if (isOperand(token)) {
                mOperandStack.push(Double.valueOf(token));
            } else {
                switch (token) {
                    case "+":
                        mOperator = new OperatorAdd();
                        break;
                    case "-":
                        mOperator = new OperatorSub();
                        break;
                    case "*":
                        mOperator = new OperatorMul();
                        break;
                    case "/":
                        mOperator = new OperatorDev();
                        break;
                    default:
                        continue;
                }
                if (mOperandStack.size() >= 2) {
                    mOperandStack.push(mOperator.execute(mOperandStack.pop(), mOperandStack.pop()));
                }
            }
        }
        if (mOperandStack.size() == 1)  {
            return mOperandStack.pop();
        } else {
            return 0.0f;
        }
    }
}
