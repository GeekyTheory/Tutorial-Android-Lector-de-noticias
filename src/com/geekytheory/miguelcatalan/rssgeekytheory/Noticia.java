package com.geekytheory.miguelcatalan.rssgeekytheory;

import java.util.Date;

public class Noticia {

	private String titulo;
	private String contenido;
	private String enlace;
	private String imagen;
	private Date fecha;

	public void setTitulo(String title) {
		this.titulo = title;
	}

	public void setContenido(String content) {
		this.contenido = content;
	}

	public void setEnlace(String url) {
		this.enlace = url;
	}

	public void setImage(String image) {
		this.imagen = image;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getContenido() {
		return this.contenido;
	}

	public String getEnlace() {
		return this.enlace;
	}

	public String getImagen() {
		return this.imagen;
	}

	public Date getFecha() {
		return this.fecha;
	}
}
