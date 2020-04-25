package com.gooaming.parcial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Informacion extends Fragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;

	public Informacion() {
		// Required empty public constructor
	}

	public static Informacion newInstance(String param1, String param2) {
		Informacion fragment = new Informacion();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		//Log.i("Desde bien dentro" + nombre);

		View view = inflater.inflate(R.layout.fragment_informacion, container, false);

		//Bundle datosHome = this.getIntent().getExtras();
		//String nombre = datosHome.getString("Nombre");
		return view;
	}
}
