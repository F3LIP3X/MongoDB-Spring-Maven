package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.bson.Document;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class CuentaModelo {

	MongoCollection<Document> doc;

	public ArrayList<CuentaBancaria> cuenta;

	Date fecha1Parse, fecha2Parse;
	String fechaFormat;
	Date fecha1Final, fecha2Final;

	// Primer Metodo
	public ArrayList<CuentaBancaria> mostrarTodasLasCuentas() {
		cuenta = new ArrayList<>();

		MongoClient mongo = new MongoClient("localhost", 27017);

		try {
			MongoDatabase database = mongo.getDatabase("banco");
			MongoCollection<Document> collection = database.getCollection("cuenta");

			Iterator<Document> it = collection.find(Filters.eq("Borrada", false)).iterator();

			while (it.hasNext()) {
				Document document = it.next();
				cuenta.add(new CuentaBancaria(document));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}
		return cuenta;
	}

	// Segundo Metodo
	public ArrayList<CuentaBancaria> mostrarPorNumeroCuenta(String nCuenta) {
		cuenta = new ArrayList<>();

		MongoClient mongo = new MongoClient("localhost", 27017);

		try {
			MongoDatabase database = mongo.getDatabase("banco");
			MongoCollection<Document> collection = database.getCollection("cuenta");

			Iterator<Document> it = collection.find(Filters.eq("NumeroCuenta", nCuenta)).iterator();
			while (it.hasNext()) {
				Document document = it.next();
				cuenta.add(new CuentaBancaria(document));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}
		return cuenta;
	}

	// Tercer Metodo
	public ArrayList<CuentaBancaria> mostrarPorFecha(String fechaApertura, String fechaCierre) {
		cuenta = new ArrayList<>();
		MongoClient mongo = new MongoClient("localhost", 27017);

		try {

			MongoDatabase database = mongo.getDatabase("banco");
			MongoCollection<Document> collection = database.getCollection("cuenta");

			// Formato Fecha
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			// Parseo de fecha de apertura y cierre
			fecha1Parse = dateFormat.parse(fechaApertura);
			fecha2Parse = dateFormat.parse(fechaCierre);

			// Indicamos el formato final de la fecha para JSON
			SimpleDateFormat finalDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

			// Aplicamos formatos a ambas fechas
			fechaFormat = finalDateFormat.format(fecha1Parse);
			fecha1Final = finalDateFormat.parse(fechaFormat);

			fechaFormat = finalDateFormat.format(fecha2Parse);
			fecha2Final = finalDateFormat.parse(fechaFormat);

			Iterator<Document> it = collection.find(Filters.and(Filters.eq("Borrada", false)
					,Filters.gte("Fecha_Apertura", fecha1Final)
					,Filters.lt("Fecha_Apertura", fecha2Final))).iterator();
					
			while (it.hasNext()) {
				Document document = it.next();
				cuenta.add(new CuentaBancaria(document));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongo.close();
		}
		return cuenta;
	}
}