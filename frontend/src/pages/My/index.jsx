import React from 'react';
import { Wrapper } from './styled';
import Header from 'layout/Header';
import { useGetUser } from 'services/store.intoAPP';

const MyPage = () => {
  const user = useGetUser();

  return (
    <Wrapper>
      <header>
        <Header theme="My" />
      </header>
      {user.login ? (
        <section className="user-info">
          <div className="user-top">
            <div className="user-face"></div>
            <div className="user-top-right">
              <div className="user-name">{user.info.nickname}</div>
              <div className="user-tags">
                <div className="user-tags-content"></div>
              </div>
            </div>
          </div>
          <div className="user-bottom">
            <div className="user-join">
              <div>Join</div>
              <div>{user.info.joinCount}</div>
            </div>
            <div className="user-follower">
              <div>Follower</div>
              <div>{user.info.followerCount}</div>
            </div>
            <div className="user-feed">
              <div>Feed</div>
              <div>{user.info.feedCount}</div>
            </div>
          </div>
        </section>
      ) : (
        <div>로그인되지 않았습니다</div>
      )}

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
