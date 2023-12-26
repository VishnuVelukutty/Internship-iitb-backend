package dev.vishnu.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vishnu.project.Model.LoginModel;
import dev.vishnu.project.Repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    JSONObject responseJson = new JSONObject();

    public JSONObject login(JSONObject requestData) {

        String userName = requestData.getString("userName");
        String userPass = requestData.getString("userPass");
        System.out.println("userName >>>>>>>>>>>." + userName);

        List<LoginModel> doesExist = new ArrayList<LoginModel>();

        try {
            doesExist = loginRepository.verifyLogin(userName, userPass);

         if (!doesExist.isEmpty()){
                String uName = "";
                String uPass = "";
                String type = "";
                for (LoginModel loginModel : doesExist) {
                    uName = loginModel.getUserName();
                    uPass = loginModel.getUserPass();
                    type = loginModel.getUserType();
                }

                 if (uName.equals(userName) && uPass.equals(userPass)) {
                        responseJson.put("Success", String.format("type" + type));
                    } else if(!uName.equals(userName) || !uPass.equals(userPass)) {
                        responseJson.put("Info", "Invalid Credentials");
                    }
            }

        } catch (Exception e) {
            responseJson.putOpt("Error", e);
        }
        return responseJson;
    }

    public JSONObject register(JSONObject requestData) {

        return null;
    }

}
