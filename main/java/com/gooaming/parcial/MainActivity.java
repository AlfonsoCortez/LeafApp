package com.gooaming.parcial;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gooaming.parcial.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {


	ImageButton btnAñadir, btnVerHojas;
	TextView twMensajeBienvenida, twMensaje;

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
		ViewPager viewPager = findViewById(R.id.view_pager);


		viewPager.setAdapter(sectionsPagerAdapter);
		TabLayout tabs = findViewById(R.id.tabs);
		tabs.setupWithViewPager(viewPager);



		btnAñadir = (ImageButton)findViewById(R.id.btnAñadir);
		//btnVerHojas = (ImageButton)findViewById(R.id.btnVerHojas);
		twMensajeBienvenida = (TextView)findViewById(R.id.twMensajeBienvenida);


	}

	public void mensajeAñadir(View View){
		Toast.makeText(getApplicationContext(), "Añadirr", Toast.LENGTH_LONG).show();
	}

	public void mensajeVerHojas(View View){
		Toast.makeText(getApplicationContext(), "Ver tus hojas", Toast.LENGTH_LONG).show();
	}
}