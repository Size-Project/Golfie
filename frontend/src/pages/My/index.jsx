import React from 'react';
import { Wrapper } from './styled';
import Header from 'layout/Header';

const MyPage = () => {
  return (
    <Wrapper>
      <header>
        <Header theme="My" />
      </header>
      <section className="user-info">
        <div className="user-top">
          <div className="user-face"></div>
          <div className="user-top-right">
            <div className="user-name">김민영</div>
            <div className="user-tags">
              <div className="user-tags-content"></div>
            </div>
          </div>
        </div>
        <div className="user-bottom">
          <div className="user-join">
            <div>Join</div>
            <div>3</div>
          </div>
          <div className="user-follower">
            <div>Follower</div>
            <div>146</div>
          </div>
          <div className="user-feed">
            <div>Feed</div>
            <div>6</div>
          </div>
        </div>
      </section>
      {/* <section className="user-info-list">
        <div>찜 목록</div>
        <div>조인 기록</div>
        <div>내가 남긴 글</div>
        <div>예약 목록</div>
      </section> */}
    </Wrapper>
  );
};

export default MyPage;
