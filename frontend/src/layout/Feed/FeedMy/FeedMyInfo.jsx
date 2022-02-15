import React from 'react';
import { StyledFeedMyInfo } from './styled';

const FeedMyInfo = ({ userInfo }) => {
  return (
    <StyledFeedMyInfo>
      <div className="user-info-top">
        <div className="top-left">
          <div className="user-image">
            {!!userInfo?.imageUrl && (
              <img src={userInfo?.imageUrl} alt="user" />
            )}
          </div>
          <div className="info">
            <div className="nickname">{userInfo?.nickname}</div>
            <div className="detail">
              {userInfo?.ageRange}세 / 대학생 / 120타
            </div>
          </div>
        </div>
        <div className="top-right"></div>
      </div>
      <div className="user-info-bottom">
        <div className="info-count">
          <div className="title">Join</div>
          <div className="count">10</div>
        </div>
        <div className="info-count">
          <div className="title">Friend</div>
          <div className="count">100</div>
        </div>
        <div className="info-count">
          <div className="title">Feed</div>
          <div className="count">15</div>
        </div>
      </div>
    </StyledFeedMyInfo>
  );
};

export default FeedMyInfo;
