import React from 'react';
import styled from 'styled-components';

const MyInfo = ({ userInfo }) => {
  return (
    <StyledWrapper>
      <div className="my-info-wrap">
        <div className="my-info-top">
          <div className="user-image">
            <img src={userInfo.imageUrl} />
          </div>
          <div className="my-info-top-right">
            <div className="user-name-wrap">
              <div className="user-name">
                <strong>{userInfo.nickname}</strong>님
              </div>
            </div>
            <div className="user-tags-wrap"></div>
          </div>
        </div>
        <div className="my-info-bottom">
          <div className="info-box">
            <div className="title">Join</div>
            <div className="count">{userInfo.joinCount}</div>
          </div>
          <div className="info-box">
            <div className="title">Friend</div>
            <div className="count">{userInfo.followerCount}</div>
          </div>
          <div className="info-box">
            <div className="title">Feed</div>
            <div className="count">{userInfo.feedCount}</div>
          </div>
        </div>
      </div>
    </StyledWrapper>
  );
};

export default MyInfo;

const StyledWrapper = styled.div`
  padding: 30px 20px;

  .my-info-wrap {
    border-radius: 10px;
    box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
    background-color: var(--color-sub);
    padding: 30px 0;
  }

  .my-info-top {
    display: flex;
    align-content: center;
    padding: 0 20px;
    margin-bottom: 30px;

    .user-image {
      border-radius: 50%;
      overflow: hidden;
      object-fit: contain;
      width: 72px;
      height: 72px;
      margin-right: 10px;
      box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
    }

    .my-info-top-right {
      .user-name {
        margin-bottom: 10px;
        font-size: 14px;
        color: var(--color-white);

        strong {
          font-size: 20px;
          font-weight: bold;
          line-height: 1.3;
          margin-right: 4px;
        }
      }
    }
  }

  .my-info-bottom {
    display: flex;

    .info-box {
      text-align: center;
      width: calc(100% / 3);
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-content: center;
      color: var(--color-white);
      border-right: 1px solid var(--color-white);

      &:last-child {
        border-right: none;
      }

      .title {
        font-family: 'Co Text';
        font-size: 16px;
        margin-bottom: 4px;
      }

      .count {
        font-size: 14px;
      }
    }
  }
`;
