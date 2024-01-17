package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class CuentaModelo {

	MongoCollection<Document> doc;

	public ArrayList<CuentaBancaria> cuenta;

	
	// Primer Metodo (Falta el condicionande de borrado)
	public ArrayList<CuentaBancaria> mostrarTodasLasCuentas() {
		cuenta = new ArrayList<>();

		MongoClient mongo = new MongoClient("localhost", 27017);

		try {
			MongoDatabase database = mongo.getDatabase("banco");
			MongoCollection<Document> collection = database.getCollection("cuenta");

			Iterator<Document> it = collection.find().iterator();
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