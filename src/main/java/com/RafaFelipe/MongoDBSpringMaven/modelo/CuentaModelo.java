package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.io.Closeable;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CuentaModelo {

	MongoCollection<Document> doc;
	private ArrayList<CuentaBancaria> cuenta;
	
	 MongoClient mongo = new MongoClient("localhost", 27017);
	 MongoDatabase database = mongo.getDatabase("banco");
	 
	 MongoCollection<Document> collection = database.getCollection("cuenta");
}