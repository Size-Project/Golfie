package com.golfie.feed.presentation;

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

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/feeds")
    public ResponseEntity<Void> save(@Authentication CurrentUser currentUser,
                                     FeedCreateRequest feedCreateRequest) throws IOException {
        feedService.save(currentUser.getId(), feedCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/feeds")
    public ResponseEntity<List<FeedResponse>> read(@Authentication CurrentUser currentUser, Pageable pageable) {
        List<FeedResponse> feedResponses = feedService.read(currentUser, pageable);
        return ResponseEntity.ok(feedResponses);
    }

    @GetMapping("/feeds/me")
    public ResponseEntity<List<FeedResponse>> readMy(@Authentication CurrentUser currentUser, Pageable pageable) {
        List<FeedResponse> feedResponses = feedService.readMy(currentUser, pageable);
        return ResponseEntity.ok(feedResponses);
    }

    @PostMapping("/feeds/like")
    public ResponseEntity<Void> doLike(@Authentication CurrentUser currentUser,
                                       @RequestParam("feedId") Long feedId) {
        feedService.doLike(currentUser, feedId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/feeds/like/undo")
    public ResponseEntity<Void> undoLike(@Authentication CurrentUser currentUser,
                                         @RequestParam("feedId") Long feedId) {
        feedService.undoLike(currentUser, feedId);
        return ResponseEntity.ok().build();
    }
}
