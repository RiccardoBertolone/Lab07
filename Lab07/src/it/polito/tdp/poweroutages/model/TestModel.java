package it.polito.tdp.poweroutages.model;

import java.util.HashSet;
import java.util.Set;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
//		System.out.println(model.getNercList());
		model.risolvi(model.nercMap.get(13), 4, 221) ;
		System.out.println(model.solOttima);
//		System.out.println(model.powerOutagesMap);
		
		
	}
}
