package com.TradeShift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/account")
public class AccountController {

    @Autowired
    private AuthService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(Authentication auth) {
        String username = auth.getName();
        userService.deleteAccount(username);
        return ResponseEntity.ok("Your account and all related orders have been deleted.");
    }
}

