import React from 'react';
import { Wrapper } from './styled';
import ForYouHeaderTop from 'layout/ForYou/ForYouHeaderTop';
import ForYouHeaderSearch from 'layout/ForYou/ForYouSearch';

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
        <section className="golf-place">
          <div className="intro">다가오는 라운딩을 위한</div>
          <div className="category-intro">#HEALING 플레이스</div>
          <div className="golf-list-box">
            <div className="golf"></div>
            <div className="golf"></div>
            <div className="golf"></div>
          </div>
        </section>
        <section>맛집</section>
        <section>숙소</section>
        <section>관광지</section>
      </main>
    </Wrapper>
  );
};

export default ForYouDetailPage;
