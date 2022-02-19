package com.golfie.acceptance;

import com.golfie.common.util.DatabaseCleanup;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected CourseRepository courseRepository;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @BeforeEach
    void databaseSetup() {
        databaseCleanup.afterPropertiesSet();
        databaseCleanup.execute();
    }
}
