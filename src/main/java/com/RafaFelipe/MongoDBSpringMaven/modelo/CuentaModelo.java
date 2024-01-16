package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.time.LocalDate;


import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
	
	public class CuentaModelo {

	MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase database = mongoClient.getDatabase("Bankia");
	

}