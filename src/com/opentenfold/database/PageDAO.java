package com.opentenfold.database;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.opentenfold.model.AppPage;

public class PageDAO extends BaseDAO {

	public AppPage getWebPageDefinition(String pageName) {
		System.out.println("Getting page definition for " + pageName);
		new AppPage();
		EntityManager em = null;
		AppPage page = null;

		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("SELECT p FROM AppPage p WHERE p.url = :url");
			query.setParameter("url", pageName);
			page = (AppPage) query.getSingleResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		System.out.println(page);
		return page;
	}
}
