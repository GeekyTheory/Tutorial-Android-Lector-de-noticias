package com.geekytheory.miguelcatalan.rssgeekytheory;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Activity_Noticias extends Activity {

	public ArrayList<Noticia> Array_Noticias = new ArrayList<Noticia>();
	private Noticias_Adapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias);

		rellenarNoticias();

		inicializarListView();
	}

	private void inicializarListView() {
		ListView lista = (ListView) findViewById(R.id.noticias_listview);
		adapter = new Noticias_Adapter(this, Array_Noticias);
		lista.setAdapter(adapter);
	}

	private void rellenarNoticias() {
		Noticia noticia_1 = new Noticia();
		noticia_1.setTitulo("Titulo noticia 1");
		noticia_1.setContenido("Contenido de la noticia 1");
		noticia_1.setFecha(new Date());

		Noticia noticia_2 = new Noticia();
		noticia_2.setTitulo("Titulo noticia 2");
		noticia_2.setContenido("Contenido de la noticia 2");
		noticia_2.setFecha(new Date());

		Array_Noticias.add(noticia_1);
		Array_Noticias.add(noticia_2);

	}

}
