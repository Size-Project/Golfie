package com.golfie.feed.presentation;

import com.golfie.auth.presentation.dto.LoginUser;
import com.golfie.auth.util.LoggedInUser;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import com.golfie.feed.presentation.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/feeds")
    public ResponseEntity<List<FeedResponse>> read(@LoggedInUser LoginUser loginUser, Pageable pageable) {
        List<FeedResponse> feedResponses = feedService.read(loginUser, pageable);
        return ResponseEntity.ok(feedResponses);
    }

}
