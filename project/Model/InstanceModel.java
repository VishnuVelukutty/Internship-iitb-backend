package dev.vishnu.project.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="course_instance")
public class InstanceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instance_id")
	private int instanceId;

	@Column(name = "course_id")
	private int courseId;

	@Column(name = "course_year")
	private int courseYear;

	@Column(name = "course_semester")
	private int courseSemester;

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(int courseYear) {
		this.courseYear = courseYear;
	}

	public int getCourseSemester() {
		return courseSemester;
	}

	public void setCourseSemester(int courseSemester) {
		this.courseSemester = courseSemester;
	}
	
	
	
    
}
