package com.geekytheory.miguelcatalan.rssgeekytheory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Activity_Articulo extends Activity {

	Noticia articulo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articulo);
		recogerparametro();
		populateWebView();
	}

	private void recogerparametro() {
		articulo = (Noticia) getIntent().getExtras().getSerializable(
				"parametro");
	}

	private void populateWebView() {
		WebView webview = (WebView) findViewById(R.id.articulo_Webview);
		webview.loadDataWithBaseURL(null, "<!DOCTYPE HTML>"
				+ populateHTML(R.raw.htmlnoticia), "text/html", "UTF-8", null);
	}

	private String populateHTML(int resourceID) {
		String html;
		html = readTextFromResource(resourceID);
		html = html.replace("_TITLE_", articulo.getTitulo());
		html = html.replace("_PUBDATE_", "" + articulo.getFecha());
		html = html.replace("_CONTENT_", articulo.getContenido());
		return html;
	}

	private String readTextFromResource(int resourceID) {
		InputStream raw = getResources().openRawResource(resourceID);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int i;
		try {
			i = raw.read();
			while (i != -1) {
				stream.write(i);
				i = raw.read();
			}
			raw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream.toString();
	}

}
