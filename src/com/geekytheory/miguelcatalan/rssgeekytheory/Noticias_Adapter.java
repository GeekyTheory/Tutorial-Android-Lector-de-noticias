package com.geekytheory.miguelcatalan.rssgeekytheory;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Noticias_Adapter extends ArrayAdapter<Object> {
	Context context;
	private ArrayList<Noticia> noticias;
	RequestQueue requestQueue;
	ImageLoader imageLoader;

	public Noticias_Adapter(Context context, ArrayList<Noticia> noticias) {
		super(context, R.layout.item_noticias);
		this.context = context;
		this.noticias = noticias;
		
		requestQueue = Volley.newRequestQueue(context);
		imageLoader = new ImageLoader(requestQueue, new BitmapCache(10));
	}

	@Override
	public int getCount() {
		return noticias.size();
	}

	private static class PlaceHolder {

		TextView title;
		TextView time;
		TextView content;
		
		ImageView picture;

		public static PlaceHolder generate(View convertView) {
			PlaceHolder placeHolder = new PlaceHolder();
			placeHolder.title = (TextView) convertView
					.findViewById(R.id.noticia_textview_title);
			placeHolder.time = (TextView) convertView
					.findViewById(R.id.noticia_textview_time);
			placeHolder.content = (TextView) convertView
					.findViewById(R.id.noticia_textview_content);
			
			placeHolder.picture = (ImageView) convertView
					.findViewById(R.id.noticia_imageView);
			return placeHolder;
		}

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		PlaceHolder placeHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_noticias, null);
			placeHolder = PlaceHolder.generate(convertView);
			convertView.setTag(placeHolder);
		} else {
			placeHolder = (PlaceHolder) convertView.getTag();
		}
		placeHolder.title.setText(noticias.get(position).getTitulo());
		placeHolder.time.setText(""
				+ noticias.get(position).getFecha().getDate() + "/"
				+ noticias.get(position).getFecha().getMonth());
		placeHolder.content.setText(noticias.get(position).getResumen());
		
		imageLoader.get(noticias.get(position).getImagen(), ImageLoader.getImageListener(placeHolder.picture, R.drawable.ic_launcher, R.drawable.ic_launcher));
		return (convertView);
	}

}
