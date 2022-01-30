package com.golfie.feed.presentation;

import com.golfie.auth.presentation.dto.LoginUser;
import com.golfie.auth.util.LoggedInUser;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/feeds/save")
    public ResponseEntity<Void> save(@LoggedInUser LoginUser loginUser, FeedCreateRequest feedCreateRequest) throws IOException {
        feedService.save(loginUser.getId(), feedCreateRequest);
        return ResponseEntity.ok().build();
    }
}
