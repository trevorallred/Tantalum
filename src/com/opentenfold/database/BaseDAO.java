package com.opentenfold.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAO {
	EntityManagerFactory emf;
	
	public BaseDAO() {
		emf = Persistence.createEntityManagerFactory("punit");
	}
}
