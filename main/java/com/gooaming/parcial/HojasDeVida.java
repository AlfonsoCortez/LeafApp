package com.gooaming.parcial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hojas_de_vida);

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



}
