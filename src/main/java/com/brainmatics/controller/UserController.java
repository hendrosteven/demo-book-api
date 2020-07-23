package com.brainmatics.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainmatics.dto.LoginForm;
import com.brainmatics.dto.ResponseData;
import com.brainmatics.entity.User;
import com.brainmatics.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> register(@Valid @RequestBody User user, Errors errors){

        ResponseData response = new ResponseData();
        if(errors.hasErrors()){
            for(ObjectError err: errors.getAllErrors()){
                response.getMessages().add(err.getDefaultMessage());
            }
            response.setStatus(false);
            return ResponseEntity.ok(response);
        }
        try {
            User output = userService.save(user);
            response.setPayload(output);
            response.setStatus(true);
            return ResponseEntity.ok(response);
        }catch (Exception ex){
            response.getMessages().add(ex.getMessage());
            response.setStatus(false);
            return ResponseEntity.ok(response);
        }
    }
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm form) throws Exception {
		ResponseData response = new ResponseData();
		User user = userService.login(form.getEmail(), form.getPassword());
		if(user==null) {
			response.setStatus(false);
			response.getMessages().add("Login fail");
		}else {
			String baseStr = user.getEmail()+":"+user.getPassword();
			String token = Base64.getEncoder().encodeToString(baseStr.getBytes());
			response.setStatus(true);
			response.setPayload(token);
		}
		return ResponseEntity.ok(response);
    }
}
