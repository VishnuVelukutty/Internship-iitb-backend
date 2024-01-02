package dev.vishnu.project.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vishnu.project.Service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")	

public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @PostMapping({"/login"})
    public ResponseEntity<?> loginCntrl(@RequestBody String requestData){
        JSONObject requestJson = new JSONObject(requestData);
        JSONObject responseJson = loginService.login(requestJson);
        return new ResponseEntity<> (responseJson.toString(),HttpStatus.OK);
    }

 @PostMapping({"/register"})
    public ResponseEntity<?> registerCntrl(@RequestBody String requestData) {
        JSONObject requestJson = new JSONObject(requestData);
        JSONObject responseJson = loginService.register(requestJson);
        return new ResponseEntity<> (responseJson.toString(),HttpStatus.OK);
    } 
    

}
