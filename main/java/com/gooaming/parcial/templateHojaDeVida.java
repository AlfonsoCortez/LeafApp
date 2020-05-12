package com.gooaming.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class templateHojaDeVida extends AppCompatActivity {

	String idUser;
	String idHojas;
	TextView mensaje;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_template_hoja_de_vida);


		mensaje = (TextView) findViewById(R.id.mensaje);

		Bundle miBudle  = this.getIntent().getExtras();
		this.idUser = miBudle.getString("id");
		this.idHojas = miBudle.getString("idHoja");


		mensaje.setText(this.idHojas+"");
		Log.i("Id desde otro:", idUser+"");

		Toast.makeText(getApplicationContext(), "" + idUser , Toast.LENGTH_SHORT);
		Log.i("idUser: ", idUser+"");
	}
}
