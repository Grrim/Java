package com.example.spring_projekt.Domain;

import com.example.spring_projekt.API.UserApi;
import com.example.spring_projekt.Storage.StorageFileNotFoundException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.mail.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.validation.Valid;


@Controller
public class UserController implements UserApi {

    @Autowired
    UserRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage templateMessage;

    public ResponseEntity addUser(@RequestBody User user) {
        repository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteUser(@PathVariable String name) {
        User user = repository.findUserByName(name);
        if (user != null) {
            repository.delete(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> getUser(@PathVariable String name) {
        User user = repository.findUserByName(name);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity editUser(@PathVariable String name, @RequestBody User updatedUser) {
        User existingUser = repository.findUserByName(name);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            repository.save(existingUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/adduser")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping(value = "/adduser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String userSubmit(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             @ModelAttribute @Valid User user,
                             BindingResult bindingResult,
                             Model model) throws IOException {
        if (bindingResult.hasFieldErrors("name") || bindingResult.hasFieldErrors("email")) {
            return "add-user";
        }
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            user.setFile(bytes);
            Path path = Paths.get("D:/blobFiles/" + file.getOriginalFilename());
            System.out.println("Path to save file: " + path);
            Files.write(path, bytes);
        }

        model.addAttribute("user", user);
        repository.save(user);

        sendEmailWithAttachment(user.getEmail(), user);

        return "user-info";
    }


    private void sendEmailWithAttachment(String to, User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(templateMessage.getFrom());
            helper.setTo(to);
            helper.setSubject(templateMessage.getSubject());
            helper.setText(String.format(templateMessage.getText(), user.toString()));

            // Dodaj załącznik
            if (user.getFile() != null && user.getFile().length > 0) {
                InputStreamSource attachment = new ByteArrayResource(user.getFile());
                helper.addAttachment("attachment", attachment);
            }

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/userinfo/{username}")
    public String userInfo(@PathVariable String username, Model model) {
        model.addAttribute("user", repository.findUserByName(username));
        return "user-info";
    }

    @GetMapping("/edituser/{username}")
    public String editUserForm(@PathVariable String username, Model model) {
        model.addAttribute("user", repository.findUserByName(username));
        return "edit-user";
    }

    @PostMapping("/deleteuser/{username}")
    public String userDelete(@PathVariable String username, Model model) {
        User user = repository.findUserByName(username);

        model.addAttribute("user", user);

        repository.deleteById(user.getId());

        return "redirect:/";
    }

    @PostMapping("/edituser/{username}")
    public String editUser(@PathVariable String username, @ModelAttribute @Valid User updatedUser, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        }

        User existingUser = repository.findUserByName(username);

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        repository.save(existingUser);

        return "redirect:/edituser/" + existingUser.getName();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
