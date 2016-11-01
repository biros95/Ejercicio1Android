package com.example.marcosportatil.ejercicio1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.marcosportatil.ejercicio1.Resultado.Resultado;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText fromDateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private EditText nameEdit;
   private CheckBox fuma;
    private CheckBox alcohol;
    private CheckBox drogas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  /* Inicializamos el EditText y pedimos el focus */

        nameEdit = (EditText) findViewById(R.id.EtNombre);
       fuma =(CheckBox) findViewById(R.id.CkFumas);
        alcohol =(CheckBox) findViewById(R.id.CkAlcohol);
        drogas =(CheckBox) findViewById(R.id.CkDrogas);

        fromDateEtxt = (EditText) findViewById(R.id.EtNacimiento);
        Button btnCalcular = (Button) findViewById(R.id.Btnresultado);
        btnCalcular.setOnClickListener(this);
       /* fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();*/
        fromDateEtxt.setFocusable(false);

            /* Ponemos el Listener al EditText */
        fromDateEtxt.setOnClickListener(this);
            /* Creamos el DatePickerDialog a partir de la fechaActual */
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, this, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            /* El constructor del DatePickerDialog accepta per paràmetres
               1. El context (o l'Activity on és llençat)
               2. L'objecte que el "vigila" i que implementa la interfície onDateSetListener
               3. Any, mes i dia del calendar que es mostren per defecte
             */


    }

        private boolean comprobar() { //Comprobamos que están todos los campos completados
            String nombre = nameEdit.getText().toString().trim();
            String ed_data = fromDateEtxt.getText().toString().trim();

        //miramos si el texto está vacio
        if(nombre.isEmpty() || nombre.length() == 0 || nombre.equals("") || nombre == null
                ||ed_data.isEmpty() || ed_data.length() == 0 || ed_data.equals("") || ed_data == null)
        {
            //Recuperamos el valor del string en el toast para poder cambiarlo según el idioma
            Toast.makeText(this,
                    this.getResources().getString(R.string.error),
                    Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;


        }
    private boolean comprobarFecha() { //Comprobamos que están todos los campos completados
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        try {
            cal.setTime(sdf.parse(fromDateEtxt.getText().toString().trim()));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (cal2.get(Calendar.YEAR)-cal.get(Calendar.YEAR)<18 || cal2.get(Calendar.YEAR)-cal.get(Calendar.YEAR)>120) {
            Toast.makeText(this,
                    this.getResources().getString(R.string.fecha),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;


    }
    private String muerte() { //Comprobamos que están todos los campos completados
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        int añomuerte=0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        try {
            cal.setTime(sdf.parse(fromDateEtxt.getText().toString().trim()));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Random Rand= new Random();
        switch(1)
        {
            case 1: if(cal2.get(Calendar.YEAR)-cal.get(Calendar.YEAR)<30)
                añomuerte=Rand.nextInt(80 - 10)+10;
            case 2: if(cal2.get(Calendar.YEAR)-cal.get(Calendar.YEAR)<50)
                añomuerte=Rand.nextInt(50 - 5)+5;
            case 3: if(cal2.get(Calendar.YEAR)-cal.get(Calendar.YEAR)<70)
                añomuerte=Rand.nextInt(40 - 5)+5;
                break;
            default :
                añomuerte=Rand.nextInt(25 - 1)+1;

                break;
        }
        if (fuma.isChecked()||drogas.isChecked()){
            añomuerte=(int)(añomuerte*(90.0f/100.0f));
        }
        if (alcohol.isChecked()){
            añomuerte=(int)(añomuerte*(98.0f/100.0f));
        }

        String[] array = getResources().getStringArray(R.array.causas);
        String causa = array[new Random().nextInt(array.length)];
        añomuerte+=cal2.get(Calendar.YEAR)+1;
        String causamuerte= (nameEdit.getText().toString())+" el "+(Rand.nextInt(31 - 1)+1) +" " +new DateFormatSymbols().getMonths()[Rand.nextInt(12 - 1)]+" del "+ añomuerte +" " +causa;

        return causamuerte;

    }



    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {

            case R.id.EtNacimiento:
                fromDatePickerDialog.show();

                break;

            case R.id.Btnresultado:



                Intent in = new Intent(getApplicationContext(), Resultado.class);

                in.putExtra("myname", muerte());
                if (comprobar()&&comprobarFecha()) {
                    startActivity(in);
                }


                break;


            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        Calendar newDate = Calendar.getInstance();
        newDate.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));


    }


}

