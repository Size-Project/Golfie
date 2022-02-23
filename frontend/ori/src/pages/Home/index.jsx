import React from 'react';
import HomeHeader from '../../layout/Home/HomeHeader';
import HomeBanner from '../../layout/Home/HomeBanner';
import HomeSchedule from '../../layout/Home/HomeSchedule';
import HomeRecommend from '../../layout/Home/HomeRecommend';
import HomeNearLocation from '../../layout/Home/HomeNearLocation';
import HomeFriendLocation from '../../layout/Home/HomeFriendLocation';

const HomePage = () => {
  return (
    <div>
      <HomeHeader />
      <HomeBanner />
      <HomeSchedule />
      <HomeRecommend />
      <HomeNearLocation />
      <HomeFriendLocation />
      <div style={{ height: '130px' }} />
    </div>
  );
};

export default HomePage;