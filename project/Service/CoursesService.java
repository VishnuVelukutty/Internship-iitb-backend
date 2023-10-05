package dev.vishnu.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vishnu.project.Model.CoursesModel;
import dev.vishnu.project.Repository.CoursesRepository;

@Service
public class CoursesService {

	@Autowired
	private CoursesRepository coursesRepository;

	JSONObject responseJson = new JSONObject();

	public JSONObject addCoursesSrv(JSONObject requestData) {

		String courseCode = requestData.getString("CourseCode");
		String courseTitle = requestData.getString("CourseTitle");
		String CourseDesc = requestData.getString("CourseDesc");

		CoursesModel courses = new CoursesModel();
		courses.setCourseCode(courseCode);
		courses.setCourseTitle(courseTitle);
		courses.setCourseDesc(CourseDesc);

		try {
			coursesRepository.addCoursesDao(courses);
			responseJson.put("Status", "Success");
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}
		return responseJson;

	}

	public JSONObject getCoursesSrv(JSONObject requestData) {
		List<CoursesModel> courseDetails = new ArrayList<CoursesModel>();

		try {
			courseDetails = coursesRepository.getCoursesDao();
			responseJson.put("List", courseDetails);
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}

		return responseJson;
	}

	public JSONObject getCoursesByIdSrv(JSONObject requestData) {
		int courseId = requestData.getInt("CourseId");

		CoursesModel courseDetails = new CoursesModel();
		try {
			courseDetails = coursesRepository.getCoursesByIdDao(courseId);

			if (courseDetails == null) {
				responseJson.put("Error", "Course not found with ID: " + courseId);
				return responseJson;
			}

			JSONObject courseJson = new JSONObject();
			courseJson.put("courseId", courseDetails.getCourseId());
			courseJson.put("courseCode", courseDetails.getCourseCode());
			courseJson.put("courseTitle", courseDetails.getCourseTitle());
			courseJson.put("courseDesc", courseDetails.getCourseDesc());
			responseJson.put("Details for ID", courseJson);

			// responseJson.put("Details for ID", courseDetails);
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}
		return responseJson;
	}

	public JSONObject deleteCoursesById(JSONObject requestData) {
		int courseId = requestData.getInt("CourseId");
		try {
			coursesRepository.deleteByIdDao(courseId);
			responseJson.put("Deleted for ID", "Success");
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}
		return responseJson;
	}

}
