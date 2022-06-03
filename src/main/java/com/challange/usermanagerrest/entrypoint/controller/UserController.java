package com.challange.usermanagerrest.entrypoint.controller;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.core.service.UserService;
import com.challange.usermanagerrest.core.usecase.UserUseCase;
import com.challange.usermanagerrest.entrypoint.controller.request.UserRequest;
import com.challange.usermanagerrest.entrypoint.controller.response.DataModelResponse;
import com.challange.usermanagerrest.entrypoint.controller.response.UserDataModelResponse;
import com.challange.usermanagerrest.entrypoint.mapper.DataUserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserUseCase userUseCase;
    @Autowired
    private DataUserMapper dataUserMapper;

    @Operation(summary = "Get list of users (Ordered by name in descending order)," +
            " you must specify page number and page size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content)})
    @GetMapping(produces = { "application/json" })
    public ResponseEntity<DataModelResponse<User>> getUsers(
            @Parameter(description = "Page index (start from 0)") @RequestParam(value = "page") Integer page,
            @Parameter(description = "Number of records per page") @RequestParam(value = "pageSize") Integer pageSize) {

        List<User> userList = userService.listUsers(page,pageSize);

        return new ResponseEntity(new DataModelResponse<>(userList), HttpStatus.OK);
    }

    @Operation(summary = "Get a user by its cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid cpf supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content) })
    @GetMapping(value = "/by_cpf", produces = { "application/json" })
    public ResponseEntity<User> getuserByCpf(
            @Parameter(description = "cpf of user to be retrieved") @RequestParam String cpf){
        
        User user = userUseCase.validateAndFind(cpf);

        if(user!=null) {
            return new ResponseEntity(dataUserMapper.modelToResponse(user), HttpStatus.OK);
        }

        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "cpf already exists",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<DataModelResponse<UserDataModelResponse>> createUser(@RequestBody UserRequest request) {

        User user = userUseCase.validateAndSave(dataUserMapper.requestToModel(request));

        if(user!=null) {
            return new ResponseEntity(dataUserMapper.modelToResponse(user), HttpStatus.CREATED);
        }

        return new ResponseEntity(null, HttpStatus.NOT_MODIFIED);
    }
    
    @ExceptionHandler({ MissingServletRequestParameterException.class,
            InvalidParameterException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<String> handleUserInputException() {
        return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<String> handleRuntimeException() {
        return new ResponseEntity(handleRuntimeException().getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<String> handleDuplicateException() {
        return new ResponseEntity(null, HttpStatus.CONFLICT);
    }
}


