package com.technopro.org.rpfcalculator.rpfcalculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by Makoto Mine on 15/09/08.
 */
public class RpfGenerator {
    private Stack<String> mOperatorStack;

    public RpfGenerator() {
        mOperatorStack = new Stack<String>();
    }

    protected boolean isOperand(String token) {
        return Pattern.compile("^[0-9.]*$").matcher(token).find();
    }

    protected boolean isOperator(String token) {
        return Pattern.compile("^[+\\-*/()]*$").matcher(token).find();
    }

    protected ArrayList<String> tokenizeText(String text) {
        ArrayList<String> tokens = new ArrayList<String>();
        String buffer = "";

        if (text == null) return tokens;

        for (String s : text.split("")) {
            if (isOperand(s)) {
                buffer += s;
            } else {
                if (buffer.length() > 0) {
                    tokens.add(buffer);
                    buffer = "";
                }
                if (isOperator(s)) {
                    tokens.add(s);
                }
            }
        }
        if (buffer.length() > 0) {
            tokens.add(buffer);
        }
        return tokens;
    }

    protected int getOperatorPriority(String token) {
        if (token == null) {
            return 0;
        } else switch (token) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public String createRpfText(String text) {
        String buffer = "";
        if (text == null) return buffer;

        for (String token : tokenizeText(text)) {
            if (isOperand(token)) {
                buffer += (token + " ");
            } else if (token.equals("(")) {
                mOperatorStack.push(token);
            } else if (token.equals(")")) {
                while(mOperatorStack.size() > 0 && !mOperatorStack.lastElement().equals("(")) {
                    buffer += (mOperatorStack.pop() + " ");
                }
                mOperatorStack.pop();
            } else if (isOperator(token)) {
                while (mOperatorStack.size() > 0 && getOperatorPriority(mOperatorStack.lastElement()) >= getOperatorPriority(token)) {
                    buffer += (mOperatorStack.pop() + " ");
                }

                mOperatorStack.push(token);
            }
        }

        while (mOperatorStack.size() > 0) {
            buffer += (mOperatorStack.pop() + " ");
        }

        return buffer;
    }
}
