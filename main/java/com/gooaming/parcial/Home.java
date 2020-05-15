package com.gooaming.parcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gooaming.parcial.fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

import static android.content.ClipData.*;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


	ImageButton btnAñadir, btnVerHojas, btnInformacionApp;
	DrawerLayout drawerLayout;
	ActionBarDrawerToggle actionBarDrawerToggle;
	Toolbar toolbar;
	NavigationView navigationView;


	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);



		//--- Menu ---------
		toolbar = findViewById(R.id.Toolbar);
		setSupportActionBar(toolbar);

		drawerLayout = findViewById(R.id.drawerHome);
		navigationView= findViewById(R.id.nvNavegador);

		actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open, R.string.close);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
		actionBarDrawerToggle.syncState();

		navigationView.setNavigationItemSelectedListener(this);

		//Cargar fragment
		/*fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.flContenedor, new MainFragment());
		fragmentTransaction.commit();*/



		//actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, "open", "close");
		/*actionBarDrawerToggle = findViewById(R.id.nvNavegador);*/





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

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		// Handle navigation view item clicks here.
		//int id = item.getItemId();
		String UrlCodigo = "https://github.com/AlfonsoCortez/LeafApp";
		String UrlPagina = "http://leaafapp.herokuapp.com/";

		switch (item.getItemId()) {
			case R.id.itHome:
				Toast.makeText(this, "Ya estas en Inicio", Toast.LENGTH_SHORT).show();
				/*Intent inte = new Intent(getApplicationContext(), Home.class);
				startActivity(inte);*/

				break;


			case R.id.itPaginaWeb:

				Uri uri2 = Uri.parse(UrlPagina);
				Intent inte2 = new Intent(Intent.ACTION_VIEW, uri2);
				startActivity(inte2);
				break;

			case R.id.itVerCodigo:
				Uri uri = Uri.parse(UrlCodigo);
				Intent inte3 = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(inte3);
				break;
			case R.id.itInformacion:

				Intent irVerInfo = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(irVerInfo);

				break;
		}
		//close navigation drawer
		drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

}
