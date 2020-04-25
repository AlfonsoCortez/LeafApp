package com.gooaming.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class login extends AppCompatActivity {

	private Button btnIngresar, btnRegistrarse;
	private RequestQueue queue;
	private EditText etNombre, etContraseña;
	private TextView twAdvertencia;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);


		etNombre = (EditText)findViewById(R.id.etNombre);
		etContraseña = (EditText)findViewById(R.id.etContraseña);
		twAdvertencia = (TextView)findViewById(R.id.twAdvertencia);

		btnIngresar = (Button)findViewById(R.id.btnIngresar);
		btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);

		queue = Volley.newRequestQueue(this);



		btnIngresar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				obtenerDatos(etNombre.getText().toString(),etContraseña.getText().toString());
			}
		});

		btnRegistrarse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent irARegistro = new Intent(getApplicationContext(),Register.class);
				startActivity(irARegistro);
			}
		});


	}


	private void obtenerDatos(final String nombre, final String contraseña){
		final String nombreInp = nombre;
		final String contraseñaInp = contraseña;
		//String url = "http://localhost:3000/api/usuarios/" + nombre;
		String url2 = "http://leaafapp.herokuapp.com/api/usuarios?nombre=" + nombre + "&contraseña=" + contraseña;
		Log.i("url:", url2);

		JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try{
					String id_usuario = response.getString("id_usuario");
					String nombreRes = response.getString("nombre");
					String contraseñaRes = response.getString("contraseña");

					Log.i("Nombre del res ", nombreRes);
					Log.i("Contraseña del res ", contraseñaRes);

					if(nombreRes.equals(nombreInp) && contraseñaRes.equals(contraseñaInp)){
						Intent irHome = new Intent(login.this,Home.class);
						Bundle datosHome = new Bundle();

						/*datosHome.putString("Nombre", nombreRes);
						datosHome.putString("Contraseña", contraseñaRes);*/
						datosHome.putString("IdUsuario", id_usuario);

						Log.i("Punto 1", "Llegamos al 1");

						irHome.putExtras(datosHome);
						startActivity(irHome);
					}else{
						twAdvertencia.setText("Los datos ingresados no son correctos.");
					}


				}catch(Exception error){
					Log.i("Mensaje de Error: ", ""+error);
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
}
