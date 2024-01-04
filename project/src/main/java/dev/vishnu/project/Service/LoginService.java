package dev.vishnu.project.Service;

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

    public JSONObject login(JSONObject requestData) {
        JSONObject responseJson = new JSONObject();

        String userName = requestData.getString("userName");
        String userPass = requestData.getString("userPass");

        List<LoginModel> doesExist;

        try {
            doesExist = loginRepository.verifyLogin(userName, userPass);
            if (!doesExist.isEmpty()) {
                boolean credentialsMatch = false;

                for (LoginModel loginModel : doesExist) {
                    if (userName.equals(loginModel.getUserName()) && userPass.equals(loginModel.getUserPass())) {
                        responseJson.put("Success", String.format("%s", loginModel.getUserType()));
                        credentialsMatch = true;
                        break; // Exit the loop once a match is found
                    }
                }

                if (!credentialsMatch) {
                    responseJson.putOpt("Info", "Invalid Credentials");
                    System.out.println("SHOULD PRINT IF ONE INCORRECT VALUES");
                }
            } else {
                responseJson.putOpt("Info", "No User Found");
                System.out.println("SHOULD PRINT IF ALL INCORRECT VALUES");
            }

        } catch (Exception e) {
            responseJson.putOpt("Error", e.getMessage());
        }
        return responseJson;
    }

    public JSONObject register(JSONObject requestData) {
        JSONObject responseJson = new JSONObject();
        String userName = requestData.getString("userName");
        String userPass = requestData.getString("userPass");
        String userType = requestData.getString("userType");
        String userRePass = requestData.getString("userRePass");


        System.out.println("USERNAME >>>"+userName);
        System.out.println("USERPASS >>>"+userPass);
        System.out.println("USERTYPE >>>"+userType);


        /**
         * 1. dont allow same username to have same pass for different types 
         * 2. dont allow same username with same pass to register
         */

        if (!userPass.equals(userRePass)) {
            responseJson.putOpt("Mismatch", "Password Mismatch");
        } else {
            try {
                String result = loginRepository.registerUser(userName, userPass, userType);
                System.out.println("Result for register >>>>>>>>"+result);


                if (result.equals("Success")) {
                    responseJson.put("Status", "Success");
                } else if (result.equals("Exists")) {
                    responseJson.putOpt("Failed", "Exists");
                    
                }
                System.out.println(responseJson);

            } catch (Exception e) {
                // TODO: handle exception
                responseJson.putOpt("Error", e);
            }
        }

        return responseJson;
    }

}
