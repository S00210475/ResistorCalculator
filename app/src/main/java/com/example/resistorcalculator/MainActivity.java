package com.example.resistorcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    View[] band = new View[4];
    NumberPicker[] picker = new NumberPicker[band.length];
    TextView totalResistance;
    TextView toleranceResult;
    public static double firstBand, secondBand, multiplierBand;
    public static String toleranceBand = "+/-1%";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Setup();
    }
    void Setup()
    {
        String[][] bandColor = {{"Black","Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Gray", "White", "Gold", "Silver"},
                {"#17202A", "#6E2C00", "#C0392B", "#E67E22", "#F4D03F", "#229954", "#2471A3", "#884EA0","#717D7E", "#FDFEFE", "#F1C40F", "#ECF0F1"}};
        String[][] toleranceColor = {{"Brown", "Red", "Green", "Blue", "Violet", "Gray", "Gold", "Silver", "None"},
                {"#6E2C00", "#C0392B", "#229954", "#2471A3", "#884EA0","#717D7E", "#F1C40F", "#ECF0F1", "#00000000"},
                {"+/-1%", "+/-2%", "+/-0.5%", "+/-0.25%", "+/-1%", "+/-0.05%", "+/-5%", "+/-10%", "+/-20%"}};
        totalResistance = findViewById(R.id.result);
        toleranceResult = findViewById(R.id.toleranceResult);
        Log.i("Result", "Test 1");
        picker[0] = findViewById(R.id.numberPicker0);
        picker[1] = findViewById(R.id.numberPicker1);
        picker[2] = findViewById(R.id.numberPicker2);
        picker[3] = findViewById(R.id.numberPicker3);
        band[0] = findViewById(R.id.firstBand);
        band[1] = findViewById(R.id.secondBand);
        band[2] = findViewById(R.id.multiplierBand);
        band[3] = findViewById(R.id.toleranceBand);
        for(int i = 0; i < picker.length - 1; i++) {
            picker[i].setDisplayedValues(bandColor[0]);
            picker[i].setMaxValue(bandColor[0].length - 1);
            picker[i].setMinValue(0);
        }
        picker[picker.length-1].setDisplayedValues(toleranceColor[0]);
        picker[picker.length-1].setMaxValue(toleranceColor[0].length - 1);
        picker[picker.length-1].setMinValue(0);
        Log.i("Result", "Test 2");
        totalResistance.setText("0");
        toleranceResult.setText(toleranceBand + "Ω");
        Log.i("Result", "Test 3");
        picker[0].setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker = picker[0].getValue();
                Log.i("picker value", bandColor[0][valuePicker]);
                band[0].setBackgroundColor(Color.parseColor(bandColor[1][valuePicker]));
                firstBand = valuePicker;
                Calculate(totalResistance);
            }
        });
        picker[1].setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker = picker[1].getValue();
                Log.i("picker value", String.valueOf(valuePicker));
                band[1].setBackgroundColor(Color.parseColor(bandColor[1][valuePicker]));
                secondBand = valuePicker;
                Calculate(totalResistance);
            }
        });
        picker[2].setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker = picker[2].getValue();
                double multiplier = 1;
                Log.i("picker value", String.valueOf(valuePicker));
                band[2].setBackgroundColor(Color.parseColor(bandColor[1][valuePicker]));
                switch (valuePicker) {
                    case 11:
                        multiplier = 0.01;
                        Log.i("Case 11", "Silver");
                        break;
                    case 10:
                        multiplier = 0.1;
                        Log.i("Case 10", "Gold");
                        break;
                    default:
                        for (int j = 0; j < valuePicker; j++) {
                            multiplier = multiplier * 10;
                        }
                        break;
                }
                multiplierBand = multiplier;
                Calculate(totalResistance);
            }
        });
        picker[3].setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int valuePicker = picker[3].getValue();
                Log.i("picker value", String.valueOf(valuePicker));
                band[3].setBackgroundColor(Color.parseColor(toleranceColor[1][valuePicker]));
                toleranceBand = toleranceColor[2][valuePicker];
                toleranceResult.setText(toleranceBand + "Ω");
            }
        });
    }
    static void Calculate(TextView totalResistance)
    {
        Log.i("Calculate", "Begin Calculation");
        double result = 0;
        result = ((firstBand * 10) + secondBand) * multiplierBand;
        totalResistance.setText(Double.toString(result));
    }

    public void Clear(View view) {
        for(int i = 0; i < picker.length - 1; i++) {
            picker[i].setValue(0);
        }
    }
}