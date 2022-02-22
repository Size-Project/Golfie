import React from 'react';
import styled from 'styled-components';

const FeedMyInfo = ({ userInfo }) => {
  return (
    <StyledWrapper>
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
    </StyledWrapper>
  );
};

export default FeedMyInfo;

const StyledWrapper = styled.div`
  padding: 0 20px 20px;
  border-radius: 0 0 20px 20px;
  box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.1);
  background-color: var(--white);
  margin-bottom: 30px;

  .user-info-top {
    display: flex;
    justify-content: space-between;
    margin-bottom: 15px;

    .top-left {
      display: flex;
      justify-content: space-between;

      .user-image {
        width: 68px;
        height: 68px;
        overflow: hidden;
        border-radius: 50%;
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
        margin-right: 13px;

        img {
          width: 100%;
          height: 100%;
        }
      }

      .info {
        display: flex;
        flex-direction: column;
        justify-content: center;

        .nickname {
          margin-bottom: 5px;
          font-family: 'Noto Sans';
          font-size: 16px;
          font-weight: 500;
          line-height: 1.25;
          color: var(--color-black);
        }

        .detail {
          color: var(--color-gray-300);
          font-size: 10px;
          padding: 0 15px;
          height: 27px;
          border-radius: 13.5px;
          box-shadow: inset 0 3px 3px 0 rgba(0, 0, 0, 0.16);
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
    }
  }

  .user-info-bottom {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-family: 'Co Text';
    font-size: 16px;

    .info-count {
      width: 33.33%;
      text-align: center;

      .title {
        line-height: 21px;
        margin-bottom: 2px;
      }

      .count {
        line-height: 26px;
        font-size: 18px;
      }
    }
  }
`;
