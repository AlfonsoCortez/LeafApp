package com.gooaming.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

	//private Button btnIngresar, btnRegistrarse;
	private RequestQueue queue;
	private EditText etNombre, etContraseña;
	//private TextView twAdvertencia;
	private ImageButton btnIngresar, btnRegistrarse;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);


		etNombre = (EditText)findViewById(R.id.etNombre);
		etContraseña = (EditText)findViewById(R.id.etContraseña);
		//twAdvertencia = (TextView)findViewById(R.id.twAdvertencia);

		btnIngresar = (ImageButton)findViewById(R.id.btnIngresar);
		btnRegistrarse = (ImageButton)findViewById(R.id.btnRegistrarse);

		queue = Volley.newRequestQueue(this);



		btnIngresar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String name = etNombre.getText().toString();
				String pass = etContraseña.getText().toString();

				if((name != null) && (!name.equals("")) && (pass != null) && (!pass.equals(""))){
					Log.i("Usuario valido: ", name);
					Log.i("Contraseña valido: ", pass);
					obtenerDatos(name, pass);
				}else{
					Toast.makeText(getApplicationContext(), "Algun campo esta vacio o un campo con un dato invalido", Toast.LENGTH_SHORT).show();
				}
				//obtenerDatos(etNombre.getText().toString(),etContraseña.getText().toString());
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


					Intent irHome = new Intent(login.this,Home.class);
					Bundle datosHome = new Bundle();

					datosHome.putString("IdUsuario", id_usuario);

					Log.i("Punto 1", "Llegamos al 1");

					irHome.putExtras(datosHome);
					startActivity(irHome);

				}catch(Exception error){
					Log.i("Mensaje de Error: ", ""+error);
				}

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(getApplicationContext(), "Error en la conexión", Toast.LENGTH_SHORT).show();
				Log.i("Mensaje de Error2: ", ""+error);

			}
		});

		queue.add(peticion);
	}
}
