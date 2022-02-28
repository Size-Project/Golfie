import React from 'react';
import styled from 'styled-components';
import HomeHeader from 'app.feature/home/component/HomeHeader';
import HomeBanner from 'app.feature/home/component/HomeBanner';
import HomeSchedule from 'app.feature/home/component/HomeSchedule';
import HomeRecommend from 'app.feature/home/component/HomeRecommend';
import HomeNearLocation from 'app.feature/home/component/HomeNearLocation';
import HomeFriendLocation from 'app.feature/home/component/HomeFriendLocation';

const ScreenHome = () => {
  return (
    <StyledWrapper>
      <HomeHeader />
      <HomeBanner />
      <HomeSchedule />
      <HomeRecommend />
      <HomeNearLocation />
      <HomeFriendLocation />
    </StyledWrapper>
  );
};

export default ScreenHome;

const StyledWrapper = styled.div``;
