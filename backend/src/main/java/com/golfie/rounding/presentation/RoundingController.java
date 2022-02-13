package com.golfie.rounding.presentation;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authentication;
import com.golfie.rounding.application.RoundingService;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class RoundingController {

    private final RoundingService roundingService;

    @PostMapping("/roundings")
    public ResponseEntity<Void> save(@Authentication CurrentUser currentUser,
                                     @RequestBody RoundingSaveRequest roundingSaveRequest) {
        roundingService.save(currentUser, roundingSaveRequest);
        return ResponseEntity.ok().build();
    }
}
