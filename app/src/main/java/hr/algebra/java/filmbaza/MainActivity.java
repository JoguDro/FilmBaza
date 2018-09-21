package hr.algebra.java.filmbaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String KEY_SIFRA = "sifra";
    public static final String KEY_IME = "ime";
    public static final String KEY_REZISER = "reziser";
    public static final String KEY_GODINA = "godina";
    public static final String KEY_TRAJANJE = "trajanje";
    public static final String KEY_VRSTA = "vrsta";
    private EditText etSifra;
    private EditText etImeFilma;
    private Spinner spReziser;
    private EditText etGodina;
    private EditText etTrajanje;


    //private RadioButton rbDomaci;
    //private RadioButton rbStrani;

    private Button btnSpremi;

    String ime;
    private Button btnExit;
    int radioid;
    private RadioGroup rgGrupa;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initWidgets();

        initSpinner();


        initButtons();


    }

    private void initButtons() {
        btnSpremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String podaci[] = {etSifra.getText().toString(),
                        etImeFilma.getText().toString(), ime,
                        etGodina.getText().toString(),
                        etTrajanje.getText().toString()};
                for (String spremljeno : podaci) {
                    Toast.makeText(MainActivity.this, spremljeno, Toast.LENGTH_SHORT).show();
                }

                radioid = rgGrupa.getCheckedRadioButtonId();
                radioButton = findViewById(radioid);
                Toast.makeText(MainActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra(KEY_SIFRA, "Sifra filma: " +  etSifra.getText().toString());
                i.putExtra(KEY_IME, "Ime filma: " +  etImeFilma.getText().toString());
                i.putExtra(KEY_REZISER, "Ime rezisera: " +  ime);
                i.putExtra(KEY_GODINA, "Godina: " +  etGodina.getText().toString());
                i.putExtra(KEY_TRAJANJE, "Trajanje filma: " +  etTrajanje.getText().toString());
                i.putExtra(KEY_VRSTA, "Vrsta filma: " +  radioButton.getText());

                startActivity(i);


            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(MainActivity.this, "Hvala na koristenju", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapterReziser = ArrayAdapter.createFromResource(this, R.array.imeRezisera, android.R.layout.simple_spinner_item);
        adapterReziser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spReziser.setAdapter(adapterReziser);
        spReziser.setOnItemSelectedListener(this);
    }

    private void initWidgets() {
        etSifra = findViewById(R.id.etSifra);
        etImeFilma = findViewById(R.id.etImeFilma);
        spReziser = findViewById(R.id.spReziser);
        etGodina = findViewById(R.id.etGodina);
        etTrajanje = findViewById(R.id.etTrajanje);
        ///rbDomaci = findViewById(R.id.rbDomaci);
        //rbStrani = findViewById(R.id.rbStrani);
        btnSpremi = findViewById(R.id.btnSpremi);
        rgGrupa = findViewById(R.id.rgGrupa);
        btnExit = findViewById(R.id.btnExit);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ime = parent.getItemAtPosition(position).toString();
        //Toast.makeText(this, ime, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void provjeriRadioBottun (View v){
        radioid = rgGrupa.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Hvala na koristenju", Toast.LENGTH_SHORT).show();
    }
}
