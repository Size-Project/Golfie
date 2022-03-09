package com.golfie.rounding.application;

import com.golfie.auth.presentation.dto.CurrentUser;
import com.golfie.rounding.domain.Rounding;
import com.golfie.rounding.domain.RoundingRepository;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.rounding.exception.CourseNotFoundException;
import com.golfie.rounding.exception.RoundingNotFoundException;
import com.golfie.rounding.presentation.dto.RoundingResponse;
import com.golfie.rounding.presentation.dto.RoundingSaveRequest;
import com.golfie.style.common.StyleFinder;
import com.golfie.style.domain.Style;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.golfie.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class RoundingService {

    private final RoundingRepository roundingRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final StyleFinder styleFinder;

    @Transactional
    public Rounding save(CurrentUser currentUser, RoundingSaveRequest roundingSaveRequest) {
        User user = findUserById(currentUser.getId());
        Course course = findCourseByName(roundingSaveRequest.getCourseName());
        Style style = styleFinder.findOrCreate(
                roundingSaveRequest.getPreferredHit(),
                roundingSaveRequest.getPreferredAge(),
                roundingSaveRequest.getPreferredMood()
        );

        Rounding rounding = roundingSaveRequest.toRounding(course, user, style);
        rounding.addAttendee(user);

        user.addHostingRound(rounding);
        user.addAttendingRound(rounding);

        return roundingRepository.save(rounding);
    }

    @Transactional(readOnly = true)
    public List<RoundingResponse> findAll() {
        return roundingRepository.findAll()
                .stream()
                .map(RoundingResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Rounding join(CurrentUser currentUser, Long roundingId) {
        User user = findUserById(currentUser.getId());
        Rounding rounding = findRoundingById(roundingId);

        rounding.addAttendee(user);
        return rounding;
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    private Rounding findRoundingById(Long id) {
        return roundingRepository.findById(id)
                .orElseThrow(() -> new RoundingNotFoundException(ROUNDING_NOT_FOUND));
    }

    private Course findCourseByName(String name) {
        return courseRepository.findByName(name)
                .orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND));
    }
}
