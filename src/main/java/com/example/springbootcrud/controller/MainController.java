package com.example.springbootcrud.controller;

import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class MainController {

    @Autowired
    private FirebaseService firebaseService;

    // Trang chủ
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Dashboard sau khi đăng nhập
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            model.addAttribute("user", principal);
        }
        return "dashboard";
    }

    // Hiển thị danh sách users
    @GetMapping("/users")
    public String listUsers(Model model) {
        CompletableFuture<List<User>> usersFuture = firebaseService.getAllUsers();
        try {
            List<User> users = usersFuture.get();
            model.addAttribute("users", users);
        } catch (Exception e) {
            model.addAttribute("error", "Không thể tải danh sách users: " + e.getMessage());
        }
        return "users/list";
    }

    // Form thêm user mới
    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    // Xử lý thêm user mới
    @PostMapping("/users")
    public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            CompletableFuture<String> future = firebaseService.createUser(user);
            String userId = future.get();
            redirectAttributes.addFlashAttribute("success", "Thêm user thành công!");
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm user: " + e.getMessage());
            return "redirect:/users/new";
        }
    }

    // Form chỉnh sửa user
    @GetMapping("/users/{id}/edit")
    public String editUserForm(@PathVariable String id, Model model) {
        try {
            CompletableFuture<User> future = firebaseService.getUserById(id);
            User user = future.get();
            if (user != null) {
                model.addAttribute("user", user);
                return "users/form";
            } else {
                return "redirect:/users";
            }
        } catch (Exception e) {
            return "redirect:/users";
        }
    }

    // Xử lý cập nhật user
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            user.setId(id);
            CompletableFuture<Void> future = firebaseService.updateUser(id, user);
            future.get();
            redirectAttributes.addFlashAttribute("success", "Cập nhật user thành công!");
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật user: " + e.getMessage());
            return "redirect:/users/" + id + "/edit";
        }
    }

    // Xóa user
    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            CompletableFuture<Void> future = firebaseService.deleteUser(id);
            future.get();
            redirectAttributes.addFlashAttribute("success", "Xóa user thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa user: " + e.getMessage());
        }
        return "redirect:/users";
    }
}