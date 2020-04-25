package com.gooaming.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AnadirHojas extends AppCompatActivity {


	EditText Input1, Input2, Input3, Input4 ,Input6, Input7;
	String Input1Value, Input2Value, Input3Value, Input4Value, Input5Value,Input6Value, Input7Value;
	Button btnEnviarFormulario;

	Spinner Input5;

	private RequestQueue queue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anadir_hojas);

		//Input1 = (EditText)findViewById(R.id.Input1);
		Input2 = (EditText)findViewById(R.id.Input2);
		Input3 = (EditText)findViewById(R.id.Input3);
		Input4 = (EditText)findViewById(R.id.Input4);
		Input5 = (Spinner)findViewById(R.id.Input5);
		Input6 = (EditText)findViewById(R.id.Input6);
		Input7 = (EditText)findViewById(R.id.Input7);

		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.estado_civil, android.R.layout.simple_spinner_item);
		Input5.setAdapter(adaptador);

		btnEnviarFormulario = (Button)findViewById(R.id.btnEnviarFormulario);

		queue = Volley.newRequestQueue(this);

		Bundle miBudle3  = this.getIntent().getExtras();
		final String idUser = miBudle3.getString("idUsuario");

		Log.i("Por fin en el final:", idUser);

		btnEnviarFormulario.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//Obteniendo los datos
				//Input1Value = Input1.getText().toString();
				Input2Value = Input2.getText().toString();
				Input3Value = Input3.getText().toString();
				Input4Value = Input4.getText().toString();
				Input5Value = Input5.getSelectedItem().toString();
				Input6Value = Input6.getText().toString();
				Input7Value = Input7.getText().toString();




				//if(Input1Value == "" && Input2Value == "" && Input3Value == "" && Input4Value == "" && Input5Value == "" && Input6Value == "" && Input7Value == "" ){

				//if((Input2Value != null) && (!Input2Value.equals("")) && (Input3Value != null) && (!Input3Value.equals("")) && (Input4Value != null) && (!Input4Value.equals("")) && (Input5Value != null) && (!Input5Value.equals("")) && (Input6Value != null) && (!Input6Value.equals("")) ){
					if(esNumero(Input7Value) && Input7Value != null){
						guardarLosDatos(idUser, Input2Value, Input3Value, Input4Value, Input5Value, Input6Value, Input7Value);
					}else{
						Toast.makeText(getApplicationContext(), "Ingreso un dato invalido", Toast.LENGTH_SHORT).show();
					}
				/*}else {
					Toast.makeText(getApplicationContext(), "Algun campo esta vacio", Toast.LENGTH_SHORT).show();
				}*/

				/*}else{
					Toast.makeText(getApplicationContext(), "Algun campo esta vacio.", Toast.LENGTH_SHORT).show();
				}*/

			}
		});

	}



	private void guardarLosDatos(String id,  String Input2Value, String Input3Value, String Input4Value, String Input5Value, String Input6Value, String Input7Value){
		Log.i("id", id);
		//Log.i("input1", Input1Value);
		Log.i("input2", Input2Value);
		Log.i("input3", Input3Value);
		Log.i("input4", Input4Value);
		Log.i("input5", Input5Value);
		Log.i("input6", Input6Value);
		Log.i("input7", Input7Value);

		String URL = "http://leaafapp.herokuapp.com/api/usuarios/formulario?id="+ id +"&input2=" + Input2Value + "&input3=" + Input3Value + "&input4=" + Input4Value + "&input5=" +Input5Value + "&input6=" + Input6Value+ "&input7=" + Input7Value;

		Log.i("url:", URL);

		JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try{
					Toast.makeText(getApplicationContext(), "Se guardo correctamente. ", Toast.LENGTH_SHORT).show();

					//Intent irHome2 = new Intent(AnadirHojas.this,Home.class);
					//startActivity(irHome2);
				}catch(Exception error){
					Log.i("Mensaje error lel:", ""+error);
					Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
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

	public static boolean esNumero(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

}

