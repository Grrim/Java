package com.example.spring_projekt.API;

import com.example.spring_projekt.Domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping
public interface UserApi {

    @ApiResponses(value = {
            @ApiResponse(description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class))
            }),@ApiResponse(responseCode = "400", description = "Invalid name/email supplied", content = @Content), @ApiResponse(responseCode = "404", description = "User already exist", content = @Content)
    })
    @Operation(summary = "Create user", description = "This can only be done by the non-logged in user", tags = { "user" })
    @Tag(name = "user", description = "the user API")
    @PostMapping("/users")
    ResponseEntity addUser(@RequestBody User user);

    @Parameter(name = "name", description = "The name that needs to be deleted", required = true)
    @ApiResponses(value = {
            @ApiResponse(description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class))
            }),@ApiResponse(responseCode = "400", description = "Invalid name supplied", content = @Content), @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @Operation(summary = "Delete user", description = "This can only be done by the logged in user.", tags = { "user" })
    @Tag(name = "user", description = "the user API")
    @DeleteMapping("/users/{name}")
    ResponseEntity deleteUser(@PathVariable String name);

    @Parameter(name = "name", description = "The name that needs to be shown", required = true)
    @ApiResponses(value = {
            @ApiResponse(description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class))
            }),@ApiResponse(responseCode = "400", description = "Invalid name supplied", content = @Content), @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @Operation(summary = "Get user", description = "This can only be done by the logged in user.", tags = { "user" })
    @Tag(name = "user", description = "the user API")
    @GetMapping("/users/{name}")
    ResponseEntity<User> getUser(@PathVariable String name);

    @Parameter(name = "name", description = "The name that needs to be edited", required = true)
    @ApiResponses(value = {
            @ApiResponse(description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class))
            }),@ApiResponse(responseCode = "400", description = "Invalid name supplied", content = @Content), @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @Operation(summary = "Edit user", description = "This can only be done by the logged in user.", tags = { "user" })
    @Tag(name = "user", description = "the user API")
    @PutMapping("/users/{name}")
    ResponseEntity editUser(@PathVariable String name, @RequestBody User user);
}
