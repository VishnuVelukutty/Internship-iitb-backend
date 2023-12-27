package dev.vishnu.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vishnu.project.Model.InstanceModel;
import dev.vishnu.project.Repository.InstanceRepository;

@Service
public class InstanceService {

	@Autowired
	private InstanceRepository instanceRepository;


	public JSONObject addInstanceSrv(JSONObject requestData) {

			JSONObject responseJson = new JSONObject();

		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");
		int courseId = requestData.getInt("courseId");

		InstanceModel instance = new InstanceModel();
		instance.setCourseYear(year);
		instance.setCourseSemester(sem);
		instance.setCourseId(courseId);

		try {
			instanceRepository.addInstanceDao(instance);
			responseJson.put("Status", "Success");
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}
		return responseJson;
	}

	public JSONObject getYearSemSrv(JSONObject requestData) {
			JSONObject responseJson = new JSONObject();

		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");

		List <InstanceModel> instances = new ArrayList<InstanceModel>();

		try {
			instances = instanceRepository.getYearSemDao(year, sem);

			if(instances == null){
				responseJson.put("Info", String.format("Course not found with ID: %d, %d", year, sem));
			}

			JSONArray instArray = new JSONArray();

			for(InstanceModel inst : instances ){

			JSONObject instJson = new JSONObject();
			instJson.put("courseId",inst.getCourseId());
			instJson.put("sem",inst.getCourseSemester());
			instJson.put("year",inst.getCourseYear());
			instJson.put("instId",inst.getInstanceId());
			instArray.put(instJson);
		}
			responseJson.put("Data", instArray);
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}

		return responseJson;
	}

	public JSONObject getYearSemIdSrv(JSONObject requestData) {
			JSONObject responseJson = new JSONObject();

		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");
		int id = requestData.getInt("id");
		InstanceModel instance = new InstanceModel();

		try {
			instance = instanceRepository.getYearSemIdDao(year, sem, id);

			if (instance == null) {
				responseJson.put("Info", String.format("Course not found with ID: %d, %d, %d", year, sem, id) );
					return responseJson;

			}

			JSONObject instJson = new JSONObject();
			instJson.put("year",instance.getCourseYear());
			instJson.put("sem",instance.getCourseSemester());
			instJson.put("courseId",instance.getCourseId());
			instJson.put("instance",instance.getCourseId());


			responseJson.put("Details", instJson);
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}
		return responseJson;
	}

	public JSONObject deleteYearSemIdSrv(JSONObject requestData) {
			JSONObject responseJson = new JSONObject();

		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");
		int id = requestData.getInt("id");

		try {
			int statCode = instanceRepository.deleteYearSemIdDao(year, sem, id);
			if(statCode == 1){ 
				responseJson.put("Status", "Success");
			}else{
				responseJson.put("Info", "No record Found");
			}

		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}

		return responseJson;
	}

	public JSONObject getInstanceSrv() {
			JSONObject responseJson = new JSONObject();

		List<InstanceModel> instances = new ArrayList<InstanceModel>();

		try {
			instances = instanceRepository.getInstancesDao();
			JSONArray instanceArray = new JSONArray();

			for (InstanceModel inst : instances) {
				JSONObject instancesJson = new JSONObject();
				instancesJson.put("year", inst.getCourseYear());
				instancesJson.put("sem", inst.getCourseSemester());
				instancesJson.put("courseId", inst.getCourseId());
				instancesJson.put("instId", inst.getInstanceId());
				instanceArray.put(instancesJson);
			}

			responseJson.put("Data", instanceArray);
		} catch (Exception e) {
			responseJson.putOpt("Error", e);

		}

		return responseJson;
	}

}
