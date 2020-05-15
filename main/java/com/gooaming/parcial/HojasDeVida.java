package com.gooaming.parcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class HojasDeVida extends AppCompatActivity {

	private RequestQueue queue;
	ArrayList<String> ListaDatos;
	RecyclerView Recycler;
	String idUser;
	//Vector arrayIds;
/*
	DrawerLayout drawerLayout2;
	ActionBarDrawerToggle actionBarDrawerToggle2;
	Toolbar toolbar2;
	NavigationView navigationView2;*/

	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hojas_de_vida);
/*
		toolbar2 = findViewById(R.id.Toolbar);
		setSupportActionBar(toolbar2);

		drawerLayout2 = findViewById(R.id.drawerHome);
		navigationView2= findViewById(R.id.nvNavegador);

		actionBarDrawerToggle2 = new ActionBarDrawerToggle(this,drawerLayout2, toolbar2, R.string.open, R.string.close);
		//actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open, R.string.close);
		drawerLayout2.addDrawerListener(actionBarDrawerToggle2);
		actionBarDrawerToggle2.setDrawerIndicatorEnabled(true);
		actionBarDrawerToggle2.syncState();

		navigationView2.setNavigationItemSelectedListener(this);*/



		Recycler = (RecyclerView)findViewById(R.id.rwContenedor);
		Recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));


		Bundle miBudle3  = this.getIntent().getExtras();
		this.idUser = miBudle3.getString("idUsuario");



		queue = Volley.newRequestQueue(this);
		traerDatos(idUser);


	}

	public void traerDatos(String id){
		Log.i("Aca en Hojas de Vida", id);

		String URL = "http://leaafapp.herokuapp.com/api/usuarios/mostrar/formulario?id="+ id;
		Log.i("URL", URL);

		JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try{
					JSONArray jsonArray = response.getJSONArray("consultado");

					Log.i("El jSon", ""+jsonArray);
					cargarListView(jsonArray);

				}catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("Mensaje de Error2: ", ""+error);
			}
		});

		queue.add(peticion);
	}



	public void cargarListView(JSONArray jsonArray) throws JSONException {

		ListaDatos = new ArrayList<String>();
		ArrayList arrayIds = new ArrayList<String>();


		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			Log.i("Id", obj.getString("id_hojas"));
			ListaDatos.add("  Descripcion: " + obj.getString("descripcion") + " \n  Fecha de Nacimiento: " + obj.getString("fecha_nacimiento") + " \n  Lugar de Nacimiento: " + obj.getString("lugar_nacimiento")  + " \n  Estado Civil: " + obj.getString("estado_civil")  + " \n  Dirección: " + obj.getString("direccion")  + " \n  Telefono personal: " + obj.getString("telefono") );
			Log.i("el valor del yeison", "" + obj.getString("descripcion"));

			arrayIds.add(obj.getString("id_hojas"));
			//Log.i("Lista de ids: ", arrayIds.toString());
		}

		AdapterDatos adapter = new AdapterDatos(ListaDatos, idUser, arrayIds);
		Recycler.setAdapter(adapter);
	}
/*
	@Override*/
	/*public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		// Handle navigation view item clicks here.
		//int id = item.getItemId();
		String UrlCodigo = "https://github.com/AlfonsoCortez/LeafApp";
		String UrlPagina = "http://leaafapp.herokuapp.com/";

		switch (item.getItemId()) {
			case R.id.itHome:

				Intent inte = new Intent(getApplicationContext(), Home.class);
				startActivity(inte);

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
		drawerLayout2.closeDrawer(GravityCompat.START);
		return true;
	}*/



}
