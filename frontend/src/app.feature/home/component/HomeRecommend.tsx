import React from 'react';
import styled from 'styled-components';
import SVGRefresh from '../../../../public/images/svg/refresh-cw.svg';
import SVGLocation from '../../../../public/images/svg/tag-location.svg';
import SVGSchedule from '../../../../public/images/svg/tag-schedule.svg';
import SVGMessage from '../../../../public/images/svg/tag-message.svg';

const HomeRecommend = () => {
  return (
    <StyledWrapper>
      <div className="top-title">
        <div className="today">Jan, 21</div>
        <div className="user">
          <b>민영</b>님을 위한 추천
        </div>
        <div className="refresh-button">
          <div />
          <SVGRefresh />
        </div>
      </div>
      <div className="recommend-wrap">
        <div className="recommend-content">
          <div className="profile-img" />
          <div className="info-wrap">
            <div className="user">임현주님 주최</div>
            <div className="title">골린이를 위한 조인</div>
          </div>
        </div>
        <div className="recommend-desc">
          <div className="info-desc">
            <div>
              <SVGLocation />
              파크벨리cc&nbsp;|&nbsp;<span>강원 원주</span>
            </div>
            <div>
              <SVGSchedule />
              Jan.02 AM 11:00
            </div>
          </div>
          <div className="info-desc">
            <SVGMessage />
            골린이라고 두려워하지 말아요!
          </div>
        </div>
      </div>
    </StyledWrapper>
  );
};

export default HomeRecommend;

const StyledWrapper = styled.div`
  color: var(--color-black);
  padding: 0 20px;
  margin-bottom: 40px;

  .top-title {
    margin-bottom: 30px;
    position: relative;

    .today {
      font-size: 22px;
      line-height: 29px;
    }

    .user {
      font-size: 18px;
      line-height: 26px;

      b {
        font-weight: bold;
        color: var(--color-purple);
      }
    }

    .refresh-button {
      width: 22px;
      height: 22px;
      position: absolute;
      bottom: 0;
      right: 0;

      div {
        width: 100%;
        height: 100%;
        position: absolute;
        opacity: 0.4;
        top: 0;
        border-radius: 50%;
        background-color: var(--color-purple);
      }

      svg {
        position: absolute;
        width: 12.2px;
        height: 10px;
        margin: 6px 4.9px;
      }
    }
  }

  .recommend-wrap {
    border-radius: 10px;
    box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.16);
    width: 100%;
    overflow: hidden;
    .recommend-content {
      height: 117px;
      background-color: gray;
      padding: 15px;
      display: flex;
      align-items: end;

      .profile-img {
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
        width: 32px;
        height: 32px;
        margin-right: 8px;
        background-color: #fff;
        border-radius: 50%;
      }

      .info-wrap {
        color: var(--color-white);

        .user {
          font-size: 8px;
          line-height: 11px;
          margin-bottom: 1px;
        }

        .title {
          font-size: 14px;
          font-weight: bold;
          line-height: 20px;
        }
      }
    }

    .recommend-desc {
      padding: 13px 15px 10px;
      .info-desc {
        margin-bottom: 4px;
        display: flex;
        align-items: center;
        font-size: 10px;
        line-height: 15px;
        color: var(--color-black);

        div {
          display: flex;
          align-items: center;
          margin-right: 10px;
        }

        span {
          color: var(--color-gray-300);
        }

        svg {
          margin-right: 4px;
        }
      }
    }
  }
`;
