package com.geekytheory.miguelcatalan.rssgeekytheory;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.util.Log;

public class XMLParser {
	private URL url;
	Context contextor;
	
	public XMLParser(String url,Context contexto) {
		contextor=contexto;
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Noticia> parse() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
		Noticia noticia;
		
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(this.url.openConnection().getInputStream());
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName("item");
			for (int i=0;i<items.getLength();i++){
				noticia = new Noticia();				
				Node item = items.item(i);
				NodeList properties = item.getChildNodes();
				for (int j=0;j<properties.getLength();j++){
					Node property = properties.item(j);
					String name = property.getNodeName();
					if (name.equalsIgnoreCase("title")){
						noticia.setTitulo(property.getFirstChild().getNodeValue());
					}else if (name.equalsIgnoreCase("content:encoded")){
						int delimiter = (property.getFirstChild().getNodeValue()).indexOf(" ] ");
						noticia.setContenido(property.getFirstChild().getNodeValue().substring(delimiter+1));
						
						int startdelimiterImage = property.getFirstChild().getNodeValue().indexOf("src=");
						if(startdelimiterImage!=-1){

							String urlpart = property.getFirstChild().getNodeValue().substring(startdelimiterImage+5);
							int enddelimiterImage = urlpart.indexOf("\"");						
							noticia.setImage(property.getFirstChild().getNodeValue().substring(startdelimiterImage+5,startdelimiterImage+5+enddelimiterImage));
							}
						
					}else if (name.equalsIgnoreCase("description")){
						int delimiter = (property.getFirstChild().getNodeValue()).indexOf(" ] ");
						noticia.setResumen(property.getFirstChild().getNodeValue().substring(delimiter+1));
						
					}else if (name.equalsIgnoreCase("link")){
						noticia.setEnlace(property.getFirstChild().getNodeValue());						
					}else if(name.equalsIgnoreCase("pubDate")){
						noticia.setFecha(formatter.parse(""+property.getFirstChild().getNodeValue()));
					}
					
				}
				noticias.add(noticia);
				Log.i("Parsher", "notcia:"+i);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 

		return noticias;
	}
	
}
