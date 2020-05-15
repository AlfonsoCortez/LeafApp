package com.gooaming.parcial;

import android.content.Intent;
import android.net.Uri;
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

	public void btnVisitarPag(View View){

		String URL = "http://leaafapp.herokuapp.com/";
		Uri uri = Uri.parse(URL);
		Intent inte = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(inte);
	}

	public void btnGithub(View View){

		String URL = "https://github.com/AlfonsoCortez/LeafApp";
		Uri uri = Uri.parse(URL);
		Intent inte = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(inte);
	}
}