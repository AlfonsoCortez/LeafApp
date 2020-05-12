package com.gooaming.parcial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Vector;


public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> implements View.OnClickListener {

	//Recibe una lista
	ArrayList<String> ListaDatos;
	private RequestQueue queue;
	String idUser;
	ArrayList arrayIds;
	Context cont;
	//static class viewGolder

	//Aca llegara
	public AdapterDatos(ArrayList<String> listaDatos, String idUser, ArrayList arrayIds) {
		this.ListaDatos = listaDatos;
		this.idUser = idUser;
		this.arrayIds = arrayIds;
	}



	//Enlazar el adaptador con el archivo item_list
	@NonNull
	@Override
	public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_list, null, false);

		return new ViewHolderDatos(view);
	}




	//Establecer la comunicacion entre el adaptador y viewHolderDatos
	@Override
	public void onBindViewHolder(ViewHolderDatos holder, final int position) {

		holder.asignarDatos(ListaDatos.get(position));

		cont = holder.itemView.getContext();
		queue = Volley.newRequestQueue(cont);
		final String id = this.idUser;

		String posicion = (String) arrayIds.get(position);

		holder.btnEliminar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Toast.makeText(cont.getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
				//Log.i("AD: ", arrayIds.get(position)+"");
				//Toast.makeText(cont.getApplicationContext(), "" + arrayIds.get(position), Toast.LENGTH_SHORT).show();
				Toast.makeText(cont.getApplicationContext(), "Dato eliminado correctamente", Toast.LENGTH_SHORT).show();
				eliminarHoja(arrayIds.get(position)+"", position+"" ,idUser);
			}

		});

		holder.btnActualizar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(cont, templateHojaDeVida.class);


				Bundle miBundle3 = new Bundle();

				miBundle3.putString("id", id);
				miBundle3.putString("idHoja", arrayIds.get(position)+"");
				intent.putExtras(miBundle3);


				cont.startActivity(intent);
			}
		});
		/*
		holder.btnActualizar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(cont, templateHojaDeVida.class);

				Log.i("id actualizar", id+"" );


				Bundle miBundle3 = new Bundle();
				miBundle3.putString("id", id);
				intent.putExtras(miBundle3);


				cont.startActivity(intent);
			}

			private Intent getIntent() {
				return null;
			}
		});
		*/

	}



	private void eliminarHoja(String id, String position, String idUser) {
		Log.i("Id usuario adaptador", idUser);
		String URL = "http://leaafapp.herokuapp.com/api/usuarios/eliminar/formulario?id="+ id+"&user="+idUser;
		Log.i("URL", URL);


		this.ListaDatos.remove(position);
		this.arrayIds.remove(id);

		JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {

				Log.i("Desde eliminar hoja", ""+response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("Mensaje de Error2: ", ""+error);
			}
		});

		queue.add(peticion);
	}



	private void actualizarHoja(){

	}




	@Override
	public int getItemCount() {
		return ListaDatos.size();
	}




	@Override
	public void onClick(View v) {

	}



	/*@Override
	public void onClick(View v) {

	}*/



	public class ViewHolderDatos extends RecyclerView.ViewHolder {

		TextView dato;
		Button btnEliminar;
		Button btnActualizar;

		public ViewHolderDatos(View itemView) {
			super(itemView);


			dato = (TextView) itemView.findViewById(R.id.idDato);
			btnEliminar = (Button) itemView.findViewById(R.id.btnEliminar);
			btnActualizar = (Button) itemView.findViewById(R.id.btnActualizar);

/*			btnActualizar.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

				}
			});*/

		}

		public void asignarDatos(String datos) {
			dato.setText(datos);
		}
	}
}
