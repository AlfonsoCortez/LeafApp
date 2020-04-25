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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Register extends AppCompatActivity {

	private Button btnIniciarSesion, btnRegistrarse;
	private TextView twAdvertencia;
	private EditText etNombre, etContraseña;
	private RequestQueue queue;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		etNombre = (EditText)findViewById(R.id.etNombre);
		etContraseña = (EditText)findViewById(R.id.etContraseña);
		twAdvertencia = (TextView)findViewById(R.id.twAdvertencia);

		btnIniciarSesion = (Button)findViewById(R.id.btnIniciarSesion);
		btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);

		queue = Volley.newRequestQueue(this);

		btnRegistrarse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("Usuario: ", etNombre.getText().toString());
				Log.i("Contraseña: ", etContraseña.getText().toString());


				String name =  etNombre.getText().toString();
				String pass = etContraseña.getText().toString();

				if((name != null) && (!name.equals("")) && (pass != null) && (!pass.equals(""))){
					Log.i("Usuario valido: ", name);
					Log.i("Contraseña valido: ", pass);
					IngresarDatos(name, pass);
				}else{
					Toast.makeText(getApplicationContext(), "Algun campo esta vacio o un campo con un dato invalido", Toast.LENGTH_SHORT).show();
				}
				//IngresarDatos(name, pass);
			}
		});

		btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent irLogin = new Intent(getApplicationContext(), login.class);
				startActivity(irLogin);
			}
		});


	}


	private void IngresarDatos(final String nombre, final String contraseña){
		final String nombreInp = nombre;
		final String contraseñaInp = contraseña;

		Log.i("Usuario desde aca: ", nombreInp);
		Log.i("Contraseña desde aca: ", contraseñaInp);

		String url2 = "http://leaafapp.herokuapp.com/api/usuarios/register?nombre=" + nombre + "&contraseña=" + contraseñaInp;
		Log.i("url:", url2);


		JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try{

					Intent irHome2 = new Intent(Register.this,Home.class);
					startActivity(irHome2);

				}catch(Exception error){
					Log.i("Mensaje error lel:", ""+error);
				}

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("Mensaje de Error2: ", ""+error);
				Toast.makeText(getApplicationContext(), "Ocurrio un error con la API", Toast.LENGTH_SHORT).show();
			}
		});

		queue.add(peticion);
	}

}
