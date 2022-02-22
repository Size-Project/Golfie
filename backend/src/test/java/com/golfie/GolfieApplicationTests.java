package com.golfie;

import com.golfie.acceptance.AcceptanceTest;
import com.golfie.common.util.DatabaseCleanup;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@SpringBootTest
class GolfieApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @BeforeEach
    void databaseSetup() {
        userRepository.deleteAll();
        feedRepository.deleteAll();
    }

    @DisplayName("어플리케이션을 실행한다.")
    @Test
    void contextLoads() {
    }

}
