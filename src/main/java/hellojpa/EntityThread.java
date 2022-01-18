package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.domain.Member;

public class EntityThread extends Thread {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;

	public EntityThread(EntityManagerFactory emf) {
		this.emf = emf;
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
	}

	public EntityThread() {
		this.emf = Persistence.createEntityManagerFactory("hello");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
	}

/*
	@Override
	public void run() {
		final int NUM_OF_MEMBER = 1000;
		tx.begin();
		try {
			for (int i = 1; i <= NUM_OF_MEMBER; i++) {
				Member member = new Member("user " + i);
				em.persist(member);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		em.close();
	}
*/
}
