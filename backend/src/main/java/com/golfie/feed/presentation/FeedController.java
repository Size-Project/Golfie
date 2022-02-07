package com.golfie.feed.presentation;

import com.golfie.auth.exception.NotAuthenticatedException;
import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.auth.util.Authentication;
import com.golfie.feed.application.FeedService;
import com.golfie.feed.presentation.dto.FeedCreateRequest;
import com.golfie.feed.presentation.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

import static com.golfie.common.exception.ErrorCode.NOT_AUTHENTICATED_USER;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/feeds/save")
    public ResponseEntity<Void> save(@Authentication CurrentUser currentUser, FeedCreateRequest feedCreateRequest) throws IOException {
        if (currentUser.isGuest()) {
            throw new NotAuthenticatedException(NOT_AUTHENTICATED_USER);
        }
        feedService.save(currentUser.getId(), feedCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/feeds")
    public ResponseEntity<List<FeedResponse>> read(@Authentication CurrentUser currentUser, Pageable pageable) {
        List<FeedResponse> feedResponses = feedService.read(currentUser, pageable);
        return ResponseEntity.ok(feedResponses);
    }

}
