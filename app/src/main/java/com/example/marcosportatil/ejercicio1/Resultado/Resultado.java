package com.example.marcosportatil.ejercicio1.Resultado;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marcosportatil.ejercicio1.R;

/**
 * Created by MarcosPortatil on 30/10/2016.
 */

    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.TextView;

    public class Resultado extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            TextView display = (TextView) findViewById(R.id.tResultado);

            Intent in = getIntent();

            String name = in.getStringExtra("myname");

            display.setText(name);

    }


}

