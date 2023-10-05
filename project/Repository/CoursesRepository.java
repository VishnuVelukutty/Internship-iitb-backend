package dev.vishnu.project.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dev.vishnu.project.Model.CoursesModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Repository
public class CoursesRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void addCoursesDao(CoursesModel courses) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(courses);
	}

	public List<CoursesModel> getCoursesDao() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CoursesModel> query = currentSession.createQuery("from CoursesModel", CoursesModel.class);
		List<CoursesModel> list = query.getResultList();
		return list;
	}

	@Transactional
	public CoursesModel getCoursesByIdDao(int courseId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<CoursesModel> query = currentSession.createQuery("FROM CoursesModel WHERE courseId = :courseId",
				CoursesModel.class);
		query.setParameter("courseId", courseId);
		CoursesModel course = currentSession.get(CoursesModel.class, courseId);
		return course;
	}

	@Transactional
	public void deleteByIdDao(int courseId) {
		Session currentSession = entityManager.unwrap(Session.class);
		CoursesModel course = currentSession.get(CoursesModel.class, courseId);

		if (course != null) {
			currentSession.remove(course);
		} else {
			throw new EntityNotFoundException("Course not found with ID: " + courseId);
		}
	}

}