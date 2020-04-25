package com.gooaming.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {


	ImageButton btnAñadir, btnVerHojas, btnInformacionApp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);



		btnAñadir = (ImageButton)findViewById(R.id.btnAñadir);
		btnVerHojas = (ImageButton)findViewById(R.id.btnVerHojas);
		btnInformacionApp = (ImageButton)findViewById(R.id.btnInformacionApp);


		Bundle miBudle  = this.getIntent().getExtras();
		final String idUser = miBudle.getString("IdUsuario");


		Log.i("Desde añadir:",  idUser );


		btnAñadir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent irAñadir = new Intent(getApplicationContext(), AnadirHojas.class);

				Bundle miBundle2 = new Bundle();

				miBundle2.putString("idUsuario", idUser);
				irAñadir.putExtras(miBundle2);

				startActivity(irAñadir);
			}
		});

		btnVerHojas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent irVerHome = new Intent(getApplicationContext(), HojasDeVida.class);

				Bundle miBundle3 = new Bundle();

				miBundle3.putString("idUsuario", idUser);
				irVerHome.putExtras(miBundle3);

				startActivity(irVerHome);
			}
		});

		btnInformacionApp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent irVerInfo = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(irVerInfo);
			}
		});
	}
}
