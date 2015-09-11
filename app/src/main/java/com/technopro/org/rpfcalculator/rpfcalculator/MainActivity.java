package com.technopro.org.rpfcalculator.rpfcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mMfView = null;
    private TextView mResultView = null;
    private TextView mRpfView = null;

    private RpfGenerator mRpfGenerator = null;
    private RpfCalculator mRpfCalculator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRpfGenerator = new RpfGenerator();
        mRpfCalculator = new RpfCalculator();

        mResultView = (TextView)findViewById(R.id.calc_result);
        mRpfView = (TextView)findViewById(R.id.rp_formula);
        mMfView = (TextView)findViewById(R.id.mathematica_formula);

        mMfView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = "";
                String rpf = mRpfGenerator.createRpfText(s.toString());
                mRpfView.setText(rpf);


                try {
                    result = Double.toString(mRpfCalculator.calculateRfp(rpf));
                } catch (ArithmeticException e) {
                    e.printStackTrace();
                    result = "Infinity";
                } finally {
                    mResultView.setText(result);
                }
            }
        });

        Button zeroButton = (Button)findViewById(R.id.zero);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "0");
            }
        });

        Button oneButton = (Button)findViewById(R.id.one);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "1");
            }
        });

        Button twoButton = (Button)findViewById(R.id.two);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "2");
            }
        });

        Button threeButton = (Button)findViewById(R.id.three);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "3");
            }
        });

        Button fourButton = (Button)findViewById(R.id.four);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "4");
            }
        });

        Button fiveButton = (Button)findViewById(R.id.five);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "5");
            }
        });

        Button sixButton = (Button)findViewById(R.id.six);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "6");
            }
        });

        Button sevenButton = (Button)findViewById(R.id.seven);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "7");
            }
        });

        Button eightButton = (Button)findViewById(R.id.eight);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "8");
            }
        });

        Button nineButton = (Button)findViewById(R.id.nine);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "9");
            }
        });

        Button dotButton = (Button)findViewById(R.id.dot);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + ".");
            }
        });

        Button clearButton = (Button)findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText("");
            }
        });

        Button backButton = (Button)findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int textLength = mMfView.getText().length();
                if (textLength > 0)
                    mMfView.setText(mMfView.getText().subSequence(0, textLength - 1));
            }
        });

        Button pBeginButton = (Button)findViewById(R.id.parenthesis_begin);
        pBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "(");
            }
        });

        Button pEndButton = (Button)findViewById(R.id.parenthesis_end);
        pEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + ")");
            }
        });

        Button addButton = (Button)findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "+");
            }
        });

        Button subButton = (Button)findViewById(R.id.sub);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "-");
            }
        });

        Button mulButton = (Button)findViewById(R.id.mul);
        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "*");
            }
        });

        Button devButton = (Button)findViewById(R.id.div);
        devButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMfView.setText(mMfView.getText() + "/");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
