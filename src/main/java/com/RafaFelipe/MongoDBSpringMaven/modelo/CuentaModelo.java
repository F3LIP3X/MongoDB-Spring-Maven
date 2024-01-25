package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.sql.rowset.FilteredRowSet;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.cglib.core.EmitUtils.ArrayDelimiters;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;

public class CuentaModelo {

	CuentaBancaria cuentaBancaria;
	MongoCollection<Document> doc;
	public ArrayList<CuentaBancaria> cuenta;
	public ArrayList<CuentaBancaria> numeroCuenta;
	Document cuentaModeloDocument;
	Date fecha1Parse, fecha2Parse;
	String fechaFormat;
	Date fecha1Final, fecha2Final;
	Boolean cuentaCreada = false;
	String estado;
	double saldoAnterior;
	double saldoActualizado;

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
	
	//Cuarto Metodo
	
	public boolean insertarCuenta(String nCuentas, ArrayList<String> titulares, double saldo) {
		cuentaBancaria = new CuentaBancaria();
		cuenta = new ArrayList<>();

		MongoClient mongo = new MongoClient("localhost", 27017);

		try {
			MongoDatabase database = mongo.getDatabase("banco");
			MongoCollection<Document> collection = database.getCollection("cuenta");

			Iterator<Document> it = collection.find(Filters.eq("NumeroCuenta", nCuentas)).iterator();
			
			if(!it.hasNext()) {
				cuentaModeloDocument = new Document()
						.append("NumeroCuenta", nCuentas)
						.append("ListaTitulares", titulares)
						.append("Saldo", saldo)
						.append("FechaApertura", new Date())
						.append("Borrada", false);
				
				collection.insertOne(cuentaModeloDocument);
				
				cuentaCreada = true;
				
			}else {
				
				cuentaCreada = false;
				
			}
		} catch (Exception e) {
			cuentaCreada = false;
			e.printStackTrace();
		} finally {
			mongo.close();
		}
		return cuentaCreada;
	}
	
	//Quinto Metodo
	
	public void actualizarCuenta(String nCuentas, CuentaBancaria nueva) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		try {
			MongoDatabase database = mongo.getDatabase("banco");
			MongoCollection<Document> collection = database.getCollection("cuenta");
			Bson Filtro = Filters.eq("NumeroCuenta", nCuentas);
			Iterator<Document> it = collection.find(Filters.eq("NumeroCuenta", nCuentas)).iterator();
			
			cuentaModeloDocument = new Document()
					.append("ListaTitulares", nueva.getListaTitulares())
					.append("Saldo", nueva.getSaldo())
					.append("FechaApertura", nueva.getFechaApertura())
					.append("Borrada", nueva.isExistente());
			
			collection.updateOne(Filtro, new Document("$set", cuentaModeloDocument));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			mongo.close();
		}
	}
	
	//Sexto Metodo
	
	public boolean borrarCuenta(String nCuentas) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("banco");
		MongoCollection<Document> collection = database.getCollection("cuenta");
		cuentaCreada = false;
		
		try {
			collection.deleteMany(Filters.eq("NumeroCuenta", nCuentas));
			cuentaCreada=true;
		}catch (Exception e) {
			cuentaCreada = true;
			e.printStackTrace();
		}finally {
			mongo.close();
		}
		return cuentaCreada;
	}
	
	//Septimo Metodo
	
	public String ingresarDinero(String nCuentas, double ingreso) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("banco");
		MongoCollection<Document> collection = database.getCollection("cuenta");
		estado="";
		try {
			saldoAnterior = numeroCuenta.get(0).getSaldo();
			saldoActualizado = ingreso + saldoAnterior;
			
			if(ingreso >= 0.0) {
				estado = "Se ha realizado un ingreso de " + ingreso + " euros." + "\n"
						+ "Al numero de cuenta " + nCuentas + " el saldo total es de "
						+ saldoActualizado;
				collection.updateOne(Filters.eq("NumeroCuenta", nCuentas),
						Updates.set("Saldo", saldoActualizado));
			}else {
				estado= "Inserte una cantidad valida";
			}
		}catch (Exception e) {
			e.printStackTrace();
			estado= "No ha podido realizarse el ingreso";
		}finally {
			mongo.close();
		}
		return estado;
	}
	
	// Octavo Metodo
	
	public String retirarDinero(String nCuentas, double retiro) {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase database = mongo.getDatabase("banco");
		MongoCollection<Document> collection = database.getCollection("cuenta");
		estado="";
		try {
			saldoAnterior = numeroCuenta.get(0).getSaldo();
			saldoActualizado = saldoAnterior - retiro;
			
			if(retiro > 0.0 && saldoActualizado > 0) {
				estado = "Se ha realizado un retiro de " + retiro + " euros." + "\n"
						+ "Al numero de cuenta " + nCuentas + " el saldo toal es de "
						+ saldoActualizado;
				collection.updateOne(Filters.eq("NumeroCuenta", nCuentas),
						Updates.set("Saldo", saldoActualizado));
			}else if(retiro > 0.0 && saldoActualizado < 0) {
				saldoActualizado = 0.0;
				estado = "El saldo es de :"+ saldoActualizado + " euros";
				
				collection.updateOne(Filters.eq("NumeroCuenta", nCuentas),
						Updates.set("Saldo", saldoActualizado));
			}else {
				estado = "Inserte una cantidad valida";
			}
		}catch (Exception e) {
			e.printStackTrace();
			estado= "No ha podido realizarse el ingreso";
		}finally {
			mongo.close();
		}
		return estado;
	}
}