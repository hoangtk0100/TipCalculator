package com.kirito.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    //Khai báo các TextView
    private TextView amountTextView;
    private TextView percentLabelTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    private double amount = 0.0;    //Bien luu so tien nhap
    private double percent = 0.15;  //Bien luu phan tram Tip

    //Dinh dang tien te
    private static final NumberFormat numberCurrency= NumberFormat.getCurrencyInstance();
    //Dinh dang phan tram
    private static final NumberFormat numberPercent = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView =(TextView) findViewById(R.id.amountTextView);
        percentLabelTextView = (TextView) findViewById(R.id.percentLabelTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        //Khoi tao gia tri ban dau cho Tip and Total TextView
        tipTextView.setText(numberCurrency.format(0));
        totalTextView.setText(numberCurrency.format(0));

        final EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekbarListener);
    }

    private void calc(){
        double tip = amount * percent;
        double total = amount + tip;

        tipTextView.setText(numberCurrency.format(tip));
        totalTextView.setText(numberCurrency.format(total));
    }

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try {
                amount = Double.parseDouble(s.toString()) / 100;
                amountTextView.setText(numberCurrency.format(amount));
            } catch (NumberFormatException e) {
                amountTextView.setText("");
                amount = 0.0;
            }
            calc();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final SeekBar.OnSeekBarChangeListener seekbarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            percent = i /100.0;
            percentLabelTextView.setText(numberPercent.format(percent));
            calc();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
