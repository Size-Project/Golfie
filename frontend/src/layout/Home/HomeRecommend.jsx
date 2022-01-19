import React from 'react';
import { StyledHomeRecommend } from './styled';
import { ReactComponent as SVGRefresh } from '../../assets/images/svg/refresh-cw.svg';
import { ReactComponent as SVGLocation } from '../../assets/images/svg/tag-location.svg';
import { ReactComponent as SVGSchedule } from '../../assets/images/svg/tag-schedule.svg';
import { ReactComponent as SVGMessage } from '../../assets/images/svg/tag-message.svg';

const HomeRecommend = () => {
  return (
    <StyledHomeRecommend>
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
    </StyledHomeRecommend>
  );
};

export default HomeRecommend;
