package com.geekytheory.miguelcatalan.rssgeekytheory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_Articulo extends Activity {
	
	TextView texto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articulo);
		initilize();
		mostrarTexto();
	}

	private void initilize() {
		texto = (TextView) findViewById(R.id.tv_articulo);
	}
	
	private void mostrarTexto(){
		texto.setText(recogerparametro());
	}

	private String recogerparametro() {
		Noticia articulo;
		articulo = (Noticia)getIntent().getExtras().getSerializable("parametro");
		return articulo.getTitulo();
		//return getIntent().getExtras().getString("parametro");
	}
	

}
