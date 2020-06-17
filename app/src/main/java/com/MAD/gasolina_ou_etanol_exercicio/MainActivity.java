package com.MAD.gasolina_ou_etanol_exercicio;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import android.text.Editable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private TextView ethanolValueTextView;
    private TextView gasolineValueTextView;
    private TextView yourBestChoiceIs;
    private ImageView imagemAux; //qual foi a anta que colocou a var em pt?
    private double ethanolValue = 0.0;
    private double gasolineValue = 0.0;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ethanolValueTextView = findViewById(R.id.ethanolValueTextView);
        gasolineValueTextView = findViewById(R.id.gasolineValueTextView);
        yourBestChoiceIs = findViewById(R.id.yourBestChoiceIs);
        imagemAux = findViewById(R.id.imagemAux);

        SeekBar gasolineSeekBar = findViewById(R.id.gasolineSeekBar);
        gasolineSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListenerGasoline);

        SeekBar ethanolSeekBar = findViewById(R.id.ethanolSeekBar);
        ethanolSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListenerEthanol);

        //TextWatcher textWatcher = null;
        ethanolValueTextView.addTextChangedListener(textWatcher);
        gasolineValueTextView.addTextChangedListener(textWatcher);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListenerGasoline =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    gasolineValue = progress / 100D;
                    double gasolineFinalValue = gasolineValue;
                    gasolineValueTextView.setText(currencyFormat.format(gasolineFinalValue));

                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };


    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListenerEthanol =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    ethanolValue = progress / 100D;
                    double ethanolFinalValue = ethanolValue;
                    ethanolValueTextView.setText(currencyFormat.format(ethanolFinalValue));
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        //private double ethanolValue = 0.0;
        //private double gasolineValue = 0.0;
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            double bestPrice = ethanolValue / gasolineValue;

            if (bestPrice > 0.7) {
                yourBestChoiceIs.setText(getResources().getString(R.string.gasoline));
                imagemAux.setImageResource(R.drawable.gasoline);
            }else if(bestPrice < 0.7){
                yourBestChoiceIs.setText(getResources().getString(R.string.ethanol));
                imagemAux.setImageResource(R.drawable.ethanol);
            }
            else if(ethanolValue == 0 && gasolineValue ==0){
                yourBestChoiceIs.setText(getResources().getString(R.string.bestChoice));
                imagemAux.setImageResource(R.drawable.neutral);
            }
            else if (ethanolValue == bestPrice){
                yourBestChoiceIs.setText(getResources().getString(R.string.Draw));
                imagemAux.setImageResource(R.drawable.same);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
