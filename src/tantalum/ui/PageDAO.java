package tantalum.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaHelper;

import tantalum.entities.MetaTable;
import tantalum.entities.Model;

public class PageDAO {
	EntityManagerFactory emf;

	public PageDAO() {
		emf = Persistence.createEntityManagerFactory("punit");
	}

	public Model getWebPageDefinition(String name) {
		// System.out.println("Getting page definition for " + pageName);
		EntityManager em = null;
		Model page = null;

		try {
			em = emf.createEntityManager();
			// TODO we'll need to do this when we want to read the data from the
			// database fresh
			JpaHelper.getEntityManager(em).getServerSession()
					.getIdentityMapAccessor().invalidateAll();
			Query query = em
					.createQuery("SELECT p FROM Model p WHERE p.name = :name");
			query.setParameter("name", name);
			page = (Model) query.getSingleResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return page;
	}

	public MetaTable getTable(String name) {
		// System.out.println("Getting page definition for " + pageName);
		EntityManager em = null;
		MetaTable table = null;

		try {
			em = emf.createEntityManager();
			// TODO we'll need to do this when we want to read the data from the
			// database fresh
			JpaHelper.getEntityManager(em).getServerSession()
					.getIdentityMapAccessor().invalidateAll();
			Query query = em.createQuery("SELECT p FROM MetaTable p WHERE p.name = :name");
			query.setParameter("name", name);
			table = (MetaTable) query.getSingleResult();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return table;
	}
}
