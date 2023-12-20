package dev.vishnu.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vishnu.project.Service.CoursesService;
import org.json.JSONObject;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")	
public class CoursesController {

	@Autowired
	private CoursesService coursesService;

	@PostMapping({ "/courses" })
	public ResponseEntity<?> addCourse(@RequestBody String requestData) {
		JSONObject requestJson = new JSONObject(requestData);
		JSONObject responseJson = coursesService.addCoursesSrv(requestJson);
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}

	@GetMapping({ "/courses" })
	public ResponseEntity<?> getCourses() {
		// JSONObject requestJson = new JSONObject(requestData);
		JSONObject responseJson = coursesService.getCoursesSrv();
		return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
	}

	@GetMapping({ "/courses/{id}" })
	public ResponseEntity<?> getCoursesById(@PathVariable int id) {
		JSONObject requestData = new JSONObject();
		requestData.put("CourseId", id);
		JSONObject responseJson = coursesService.getCoursesByIdSrv(requestData);
		return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
	}

	@DeleteMapping({ "/courses/{id}" })
	public ResponseEntity<?> deleteCoursesById(@PathVariable int id) {
		JSONObject requestData = new JSONObject();
		requestData.put("CourseId", id);
		JSONObject responseJson = coursesService.deleteCoursesById(requestData);
		return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
	}

}