import React from 'react';
import { Wrapper } from './styled';
import ForYouHeaderTop from 'layout/ForYou/ForYouHeaderTop';
import ForYouHeaderSearch from 'layout/ForYou/ForYouSearch';
import GolfPlace from 'layout/ForYou/GolfPlace';
import RecommendPlace from 'layout/ForYou/RecommendPlace';

const ForYouDetailPage = () => {
  return (
    <Wrapper>
      <header>
        <div className="header-top-box">
          <ForYouHeaderTop />
        </div>
        <div className="search-box">
          <ForYouHeaderSearch />
        </div>
      </header>
      <main>
        <GolfPlace />
        <RecommendPlace name="foodCourt"></RecommendPlace>
        <RecommendPlace name="accommodation"></RecommendPlace>
        <RecommendPlace name="tour"></RecommendPlace>
      </main>
    </Wrapper>
  );
};

export default ForYouDetailPage;
