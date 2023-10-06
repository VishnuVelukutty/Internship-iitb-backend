package dev.vishnu.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vishnu.project.Model.InstanceModel;
import dev.vishnu.project.Repository.InstanceRepository;

@Service
public class InstanceService {

	@Autowired
	private InstanceRepository instanceRepository;

	JSONObject responseJson = new JSONObject();

	public JSONObject addInstanceSrv(JSONObject requestData) {

		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");

		InstanceModel instance = new InstanceModel();
		instance.setCourseYear(year);
		instance.setCourseSemester(sem);

		try {
			instanceRepository.addInstanceDao(instance);
			responseJson.put("Status", "Success");
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}
		return responseJson;
	}

	public JSONObject getYearSemSrv(JSONObject requestData) {
		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");
		List<InstanceModel> courseInstance = new ArrayList<InstanceModel>();

		try {
			courseInstance = instanceRepository.getYearSemDao(year, sem);
			responseJson.put("List", courseInstance);
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}

		return responseJson;
	}

	public JSONObject getYearSemIdSrv(JSONObject requestData) {
		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");
		int id = requestData.getInt("id");
		InstanceModel instance = new InstanceModel();

		try {
			instance = instanceRepository.getYearSemIdDao(year,sem,id);
			responseJson.put("Details", instance);
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}
		return responseJson;
	}

	public JSONObject deleteYearSemIdSrv(JSONObject requestData) {
		int year = requestData.getInt("year");
		int sem = requestData.getInt("sem");
		int id = requestData.getInt("id");

		try {
			instanceRepository.deleteYearSemIdDao(year,sem,id);
			responseJson.put("Status", "Success");
		} catch (Exception e) {
			responseJson.putOpt("Error", e);
		}

		return responseJson;
	}

}