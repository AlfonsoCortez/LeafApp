package com.gooaming.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class BienvenidoSplah extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bienvenido_splah);



		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(BienvenidoSplah.this,Home.class);
				startActivity(intent);
				finish();
			}
		},3000);
	}
}
