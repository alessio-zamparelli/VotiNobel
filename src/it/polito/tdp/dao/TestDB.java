package it.polito.tdp.dao;

import java.util.List;

import it.polito.tdp.model.Esame;
import it.polito.tdp.model.Model;

public class TestDB {

	public static void main(String[] args) {
		/*
		 * This is a main to check the DB connection
		 */

		try {
			EsameDAO vdao = new EsameDAO();
			vdao.getTuttiEsami();
			System.out.println("TestDB passed");

		} catch (RuntimeException e) {
			System.err.println("TestDB failed");
		}
		
		EsameDAO esameDAO = new EsameDAO();
		List<Esame> esami = esameDAO.getTuttiEsami();
		System.out.println(esami);
		Model m = new Model();
		List<Esame> res = m.calcolaSottoinsiemeEsami(20);
		
		System.out.println(res);
		
		int res2 = esami.stream().mapToInt(a->a.getCrediti()).sum();
		System.out.println(res2);
		
		
	}

}
