package dev.vishnu.project.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vishnu.project.Model.InstanceModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class InstanceRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void addInstanceDao(InstanceModel instance) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(instance);
	}

	public List<InstanceModel> getYearSemDao(int year, int sem) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<InstanceModel> query = currentSession.createQuery("FROM InstanceModel WHERE courseYear = :year AND courseSemester = :sem", InstanceModel.class);
		query.setParameter("year", year);
		query.setParameter("sem", sem);
		return query.list();
	}
	
	

	public InstanceModel getYearSemIdDao(int year, int sem, int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<InstanceModel> query = currentSession.createQuery(
				"FROM InstanceModel WHERE courseYear = :year AND courseSemester = :sem AND instanceId = :id", InstanceModel.class);
		query.setParameter("year", year);
		query.setParameter("sem", sem);
		query.setParameter("id", id);
		return query.uniqueResult();
	}


	@Transactional
	public int deleteYearSemIdDao(int year, int sem, int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<InstanceModel> query = currentSession.createQuery(
				"FROM InstanceModel WHERE courseYear = :year AND courseSemester = :sem AND instanceId = :id", InstanceModel.class);
		query.setParameter("year", year);
		query.setParameter("sem", sem);
		query.setParameter("id", id);
		InstanceModel course = query.uniqueResult();

		if (course != null) {
			currentSession.remove(course);
			return 1;
		}else{
			return 0;
		}
	}

	public List<InstanceModel> getInstancesDao() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<InstanceModel> query = currentSession.createQuery("from InstanceModel", InstanceModel.class);
		List<InstanceModel> list = query.getResultList();
		return list;
	}

}
