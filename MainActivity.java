
package com.example.losowaniekostek;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextLiczbaKostek;
    Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    GridLayout gridKostki;
    TextView textViewWyniki;

    ArrayList<Integer> wyniki = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek =
                findViewById(R.id.editTextLiczbaKostek);

        buttonLosuj =
                findViewById(R.id.buttonLosuj);

        buttonRosnaco =
                findViewById(R.id.buttonRosnaco);

        buttonMalejaco =
                findViewById(R.id.buttonMalejaco);

        gridKostki =
                findViewById(R.id.gridKostki);

        textViewWyniki =
                findViewById(R.id.textViewWyniki);

        buttonLosuj.setOnClickListener(v -> losujKostki());

        buttonRosnaco.setOnClickListener(v -> {
            Collections.sort(wyniki);
            wyswietlWyniki();
        });

        buttonMalejaco.setOnClickListener(v -> {
            Collections.sort(wyniki, Collections.reverseOrder());
            wyswietlWyniki();
        });
    }

    private void losujKostki() {

        String tekst = editTextLiczbaKostek.getText().toString();

        if (tekst.isEmpty()) {
            Toast.makeText(this,
                    "Podaj liczbę kostek!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int liczba;

        try {
            liczba = Integer.parseInt(tekst);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Niepoprawna liczba!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba <= 0) {
            Toast.makeText(this,
                    "Liczba kostek musi być większa od 0!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        wyniki.clear();

        Random random = new Random();

        for (int i = 0; i < liczba; i++) {
            int wynik = random.nextInt(6) + 1;
            wyniki.add(wynik);
        }

        wyswietlWyniki();
    }

    private void wyswietlWyniki() {

        gridKostki.removeAllViews();

        for (int wynik : wyniki) {

            TextView kostka = new TextView(this);

            kostka.setText("🎲 " + wynik);
            kostka.setTextSize(24);
            kostka.setGravity(Gravity.CENTER);
            kostka.setTextColor(Color.BLACK);
            kostka.setPadding(12, 12, 12, 12);

            GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();

            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            kostka.setLayoutParams(params);

            gridKostki.addView(kostka);
        }

        textViewWyniki.setText(
                wyniki.toString().replace("[", "").replace("]", "")
        );
    }
}

