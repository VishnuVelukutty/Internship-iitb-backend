package dev.vishnu.project.Controller;

import org.json.JSONObject;
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

import dev.vishnu.project.Service.InstanceService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")	

public class InstanceController {
	
	 @Autowired
	    private InstanceService instanceService;

	    @PostMapping({"/instances"})
	    public ResponseEntity<?> addCourseCntrl(@RequestBody String requestData) {
	        JSONObject requestJson = new JSONObject(requestData);
	        JSONObject responseJson = instanceService.addInstanceSrv(requestJson);
	        return new ResponseEntity<>(responseJson.toString(),HttpStatus.OK);
	    }

		@GetMapping({"/instances"})
		public ResponseEntity<?> getCourseCntrl() {
	        JSONObject responseJson = instanceService.getInstanceSrv();
	        return new ResponseEntity<>(responseJson.toString(),HttpStatus.OK);
	    } 

	    @GetMapping({"/instances/{year}/{sem}"})
	    public ResponseEntity<?> getCourses(@PathVariable int year,@PathVariable int sem) {
			JSONObject requestData = new JSONObject();
			requestData.put("year", year);
			requestData.put("sem", sem);
			JSONObject responseJson = instanceService.getYearSemSrv(requestData);
	        return new ResponseEntity<>(responseJson.toString(),HttpStatus.OK);
	    }


	    @GetMapping({"/instances/{year}/{sem}/{id}"})
	    public ResponseEntity<?> getCoursesById(@PathVariable int year,@PathVariable int sem,@PathVariable int id) {
			JSONObject requestData = new JSONObject();
			requestData.put("year", year);
			requestData.put("sem", sem);
			requestData.put("id", id);
			
			JSONObject responseJson = instanceService.getYearSemIdSrv(requestData);
			
	        return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
	    }

	    @DeleteMapping({"/instances/{year}/{sem}/{id}"})
	    public ResponseEntity<String> deleteCoursesById(@PathVariable int year,@PathVariable int sem,@PathVariable int id){
	    	
			JSONObject requestData = new JSONObject();
			requestData.put("year", year);
			requestData.put("sem", sem);
			requestData.put("id", id);
			
			JSONObject responseJson = instanceService.deleteYearSemIdSrv(requestData);
	        return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);

	    }

}
