package tantalum.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaHelper;

import tantalum.entities.Page;
import tantalum.entities.View;

public class PageDAO {
	EntityManagerFactory emf;

	public PageDAO() {
		emf = Persistence.createEntityManagerFactory("punit");
	}

	public Page getWebPageDefinition(String pageName) {
		// System.out.println("Getting page definition for " + pageName);
		new Page();
		EntityManager em = null;
		Page page = null;

		try {
			em = emf.createEntityManager();
			// TODO we'll need to do this when we want to read the data from the
			// database fresh
			JpaHelper.getEntityManager(em).getServerSession()
					.getIdentityMapAccessor().invalidateAll();
			Query query = em
					.createQuery("SELECT p FROM AppPage p WHERE p.name = :url");
			query.setParameter("url", pageName);
			page = (Page) query.getSingleResult();
			page.printAll();
			// em.refresh(page);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		// System.out.println(page);
		return page;
	}
}
