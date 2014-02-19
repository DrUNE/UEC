package ru.sbrf.qrcode.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.sbrf.qrcode.events.user.ChangeUserPasswordEvent;
import ru.sbrf.qrcode.events.user.ChangeUserStatusEvent;
import ru.sbrf.qrcode.events.user.CreateUserEvent;
import ru.sbrf.qrcode.events.user.DeleteUserEvent;
import ru.sbrf.qrcode.events.user.UserDeletedEvent;
import ru.sbrf.qrcode.events.user.AllUsersSelectedEvent;
import ru.sbrf.qrcode.events.user.UserUpdatedEvent;
import ru.sbrf.qrcode.events.user.UserDetails;
import ru.sbrf.qrcode.json.UserData;
import ru.sbrf.qrcode.services.UserEventHandler;

/**
 * Контроллер для управления данными пользавателей
 * 
 * @author sbt-koshenkova-mv
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserEventHandler userEventHandler;
	
	/**
	 * Запрос на получение списка пользователей
	 */
	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<List<UserData>> getAllUsers(){
		List<UserData> users = new ArrayList<UserData>();
		
		AllUsersSelectedEvent selectedAllUsersEvent = userEventHandler.getAllUsers();
		for(UserDetails userDetails: selectedAllUsersEvent.getUsers())
			users.add(UserData.fromUserDetails(userDetails));
		
		return new ResponseEntity<List<UserData>>(users, HttpStatus.OK);
	}
	
	/**
	 * Добавление пользователя
	 */
	@RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<List<UserData>> createUser(@RequestBody UserData userData){
		CreateUserEvent createUserEvent = new CreateUserEvent(UserData.toUserDetails(userData));
		
		AllUsersSelectedEvent selectedAllUsersEvent = userEventHandler.createUser(createUserEvent);
		
		List<UserData> users = new ArrayList<UserData>();
		for(UserDetails userDetails: selectedAllUsersEvent.getUsers())
			users.add(UserData.fromUserDetails(userDetails));
		return new ResponseEntity<List<UserData>>(users, HttpStatus.OK);
	}
	
	/**
	 * Запрос на удаление пользователя
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<UserData> deleteUser(@PathVariable String id){
		DeleteUserEvent deleteUserEvent = new DeleteUserEvent(Integer.valueOf(id));
		UserDeletedEvent deletedUserEvent = userEventHandler.deleteUser(deleteUserEvent);
		if(!deletedUserEvent.isEntityFound())
			return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
		
		UserData resultUserData = UserData.fromUserDetails(deletedUserEvent.getUserDetails());
		
		if(deletedUserEvent.isDeleteSuccess())
			return new ResponseEntity<UserData>(resultUserData, HttpStatus.OK);
		
		return new ResponseEntity<UserData>(resultUserData, HttpStatus.FORBIDDEN);
	}
	
	/**
	 * Активация/диактивация пользователя
	 */
	@RequestMapping(value = {"/change_status"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<UserData> changeActiveStatus(@RequestBody UserData userData){
		ChangeUserStatusEvent updateEvent = new ChangeUserStatusEvent(userData.getId(), userData.isActive());
		UserUpdatedEvent updatedUserEvent = userEventHandler.changeUserActiveStatus(updateEvent);
		if(!updatedUserEvent.isEntityFound())
			return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
		
		UserData resultUserData = UserData.fromUserDetails(updatedUserEvent.getUserDetails());
		
		if(updatedUserEvent.isUpdateSuccess())
			return new ResponseEntity<UserData>(resultUserData, HttpStatus.OK);
		
		return new ResponseEntity<UserData>(resultUserData, HttpStatus.FORBIDDEN);
	}
	
	/**
	 * Смена пароля
	 */
	@RequestMapping(value = {"/change_password"}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<UserData> changePassword(@RequestBody UserData userData){
		ChangeUserPasswordEvent updateEvent = new ChangeUserPasswordEvent(userData.getId(), userData.getPassword());
		UserUpdatedEvent updatedUserEvent = userEventHandler.changeUserPassword(updateEvent);
		if(!updatedUserEvent.isEntityFound())
			return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
		
		UserData resultUserData = UserData.fromUserDetails(updatedUserEvent.getUserDetails());
		
		if(updatedUserEvent.isUpdateSuccess())
			return new ResponseEntity<UserData>(resultUserData, HttpStatus.OK);
		
		return new ResponseEntity<UserData>(resultUserData, HttpStatus.FORBIDDEN);
	}

}
