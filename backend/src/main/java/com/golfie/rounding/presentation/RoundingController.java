package com.golfie.rounding.presentation;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authentication;
import com.golfie.rounding.application.RoundingService;
import com.golfie.rounding.presentation.dto.RoundingResponse;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/roundings")
    public ResponseEntity<List<RoundingResponse>> findAll() {
        List<RoundingResponse> roundingResponses = roundingService.findAll();
        return ResponseEntity.ok(roundingResponses);
    }

}
