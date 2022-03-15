package com.golfie;

import com.golfie.feed.domain.Feed;
import com.golfie.feed.domain.FeedRepository;
import com.golfie.feed.domain.like.Likes;
import com.golfie.rounding.domain.Rounding;
import com.golfie.rounding.domain.RoundingRepository;
import com.golfie.rounding.domain.course.Course;
import com.golfie.rounding.domain.course.CourseRepository;
import com.golfie.style.domain.Style;
import com.golfie.style.domain.StyleRepository;
import com.golfie.user.domain.User;
import com.golfie.user.domain.UserRepository;
import com.golfie.user.domain.profile.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Profile("local")
@RequiredArgsConstructor
@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final StyleRepository styleRepository;
    private final RoundingRepository roundingRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public void loadData() {
        userRepository.deleteAll();
        feedRepository.deleteAll();

        Style style = Style.builder()
                .averageHit("100-120")
                .ageRange("20-30")
                .mood("편안한")
                .build();

        styleRepository.save(style);

        SocialProfile socialProfile = new SocialProfile(ProviderName.KAKAO, "junslee@test.com", "imageUrl.com", Gender.MALE, AgeRange.TWENTY);
        BasicProfile basicProfile = new BasicProfile("junslee", "job", 100);
        User junslee = new User(basicProfile, socialProfile, style);
        userRepository.save(junslee);

        SocialProfile socialProfile1 = new SocialProfile(ProviderName.KAKAO, "mary@test.com", "imageUrl.com", Gender.FEMALE, AgeRange.THIRTY);
        BasicProfile basicProfile1 = new BasicProfile("mary", "job", 70);
        User mary = new User(basicProfile1, socialProfile1, style);
        userRepository.save(mary);

        SocialProfile socialProfile2 = new SocialProfile(ProviderName.KAKAO, "john@test.com", "imageUrl.com", Gender.MALE, AgeRange.FORTY);
        BasicProfile basicProfile2 = new BasicProfile("john", "job", 110);
        User john = new User(basicProfile2, socialProfile2);
        userRepository.save(john);

        SocialProfile socialProfile3 = new SocialProfile(ProviderName.KAKAO, "anna@test.com", "imageUrl.com", Gender.FEMALE, AgeRange.TWENTY);
        BasicProfile basicProfile3 = new BasicProfile("anna", "job", 110);
        User anna = new User(basicProfile3, socialProfile3);
        userRepository.save(anna);

        Feed feed01 = new Feed(junslee, List.of("image1", "image2", "image3"), "This is Feed 01.");
        feedRepository.save(feed01);
        Feed feed02 = new Feed(junslee, List.of("image1", "image2", "image3"), "This is Feed 02.");
        feedRepository.save(feed02);

        junslee.addFeed(feed01);
        junslee.addFeed(feed02);

        Feed feed03 = new Feed(mary, List.of("image1", "image2", "image3"), "This is Feed 03.");
        feedRepository.save(feed03);
        Feed feed04 = new Feed(mary, List.of("image1", "image2", "image3"), "This is Feed 04.");
        feedRepository.save(feed04);

        mary.addFeed(feed03);
        mary.addFeed(feed04);

        Feed feed05 = new Feed(john, List.of("image1", "image2", "image3"), "This is Feed 05.");
        feedRepository.save(feed05);
        Feed feed06 = new Feed(anna, List.of("image1", "image2", "image3"), "This is Feed 06.");
        feedRepository.save(feed06);
        Feed feed07 = new Feed(john, List.of("image1", "image2", "image3"), "This is Feed 07.");
        feedRepository.save(feed07);
        Feed feed08 = new Feed(anna, List.of("image1", "image2", "image3"), "This is Feed 08.");
        feedRepository.save(feed08);
        Feed feed09 = new Feed(john, List.of("image1", "image2", "image3"), "This is Feed 09.");
        feedRepository.save(feed09);
        Feed feed10 = new Feed(anna, List.of("image1", "image2", "image3"), "This is Feed 10.");
        feedRepository.save(feed10);

        john.addFeed(feed05);
        anna.addFeed(feed06);
        john.addFeed(feed07);
        anna.addFeed(feed08);
        john.addFeed(feed09);
        anna.addFeed(feed10);

        feed01.doLike(Likes.of(feed01, mary));
        feed01.doLike(Likes.of(feed01, anna));
        feed01.doLike(Likes.of(feed01, john));

        feed02.doLike(Likes.of(feed02, mary));
        feed02.doLike(Likes.of(feed02, anna));
        feed02.doLike(Likes.of(feed02, john));

        feed03.doLike(Likes.of(feed03, junslee));
        feed03.doLike(Likes.of(feed03, anna));
        feed03.doLike(Likes.of(feed03, john));

        feed04.doLike(Likes.of(feed04, junslee));
        feed04.doLike(Likes.of(feed04, anna));
        feed04.doLike(Likes.of(feed04, john));

        junslee.addFollowing(john);
        john.addFollowing(junslee);
        john.addFollowing(mary);
        john.addFollowing(anna);
        anna.addFollowing(mary);
        mary.addFollowing(anna);

        Course suwon = new Course("수원 C.C", "경기도 수원시");
        Course seoul = new Course("서울 C.C", "서울특별시");
        courseRepository.save(suwon);
        courseRepository.save(seoul);

        Rounding johnRounding = roundingRepository.save(Rounding.builder()
                .course(suwon)
                .host(john)
                .style(style)
                .title("놀러오세요.")
                .content("내용입니다.")
                .joinNum(4)
                .price(100000)
                .dateTime(LocalDateTime.now())
                .build());

        john.addHostingRound(johnRounding);
        john.addAttendingRound(johnRounding);
    }
}
