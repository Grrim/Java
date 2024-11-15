package com.example.spring_projekt.API;

import com.example.spring_projekt.Domain.User;
import com.example.spring_projekt.Domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")  // Dodajemy prefiks /api do wszystkich endpointów
public class UserRestController {

    @Autowired
    UserRepository repository;

    @PostMapping("/add_user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            repository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(new Result(1));  // Result to obiekt, który należy zainicjować
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Forbidden", "Proces zakonczony niepowodzeniem!")); // Error to obiekt, który należy zainicjować
        }
    }

    @DeleteMapping("/delete_user/{name}")
    public ResponseEntity<?> deleteUser(@PathVariable String name) {
        try {
            User user = repository.findUserByName(name);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("Conflict", "Nie ma takiego uzytkownika"));
            }
            repository.deleteById(user.getId());
            return ResponseEntity.status(HttpStatus.OK).body(new Result(1));  // Result to obiekt, który należy zainicjować
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Forbidden", "Proces zakonczony niepowodzeniem!")); // Error to obiekt, który należy zainicjować
        }
    }

    @GetMapping("/get_user/{name}")
    public ResponseEntity<?> getUser(@PathVariable String name) {
        try {
            User user = repository.findUserByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Forbidden", "Proces zakonczony niepowodzeniem!")); // Error to obiekt, który należy zainicjować
        }
    }

    @PostMapping("/edit_user/{name}")
    public ResponseEntity<?> editUser(@PathVariable String name, @RequestBody User user) {
        try {
            User existingUser = repository.findUserByName(name);
            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new Error("Conflict", "Nie ma takiego uzytkownika"));
            }
            existingUser.setEmail(user.getEmail());
            existingUser.setName(user.getName());
            repository.save(existingUser);
            return ResponseEntity.status(HttpStatus.OK).body(new Result(1));  // Result to obiekt, który należy zainicjować
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Forbidden", "Proces zakonczony niepowodzeniem!")); // Error to obiekt, który należy zainicjować
        }
    }
}
