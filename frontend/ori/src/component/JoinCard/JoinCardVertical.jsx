import React from 'react';
import { StyledJoinCardVertical } from './styled';
import { ReactComponent as SVGLocation } from '../../assets/images/svg/tag-location.svg';
import { ReactComponent as SVGSchedule } from '../../assets/images/svg/tag-schedule.svg';
import { ReactComponent as SVGCash } from '../../assets/images/svg/tag-cash.svg';
import { ReactComponent as SVGUser } from '../../assets/images/svg/tag-user.svg';

const JoinCardVertical = () => {
  return (
    <StyledJoinCardVertical>
      <div className="card-left" />
      <div className="card-content-wrap">
        <div className="user-img"></div>
        <div className="info-wrap">
          <div className="user-name">임현주님 주최</div>
          <div className="join-title">골프치고 카페투어 가실 분</div>
          <div className="join-tag-wrap">
            <div className="join-tag location">
              <div>
                <SVGLocation />
              </div>
              <div>
                파크밸리cc | <span>파크밸리cc</span>
              </div>
            </div>
            <div className="join-tag schedule">
              <div>
                <SVGSchedule />
              </div>
              <div>Jan.02 AM 11:00</div>
            </div>
            <div className="join-tag cash">
              <div>
                <SVGCash />
              </div>
              <div>70,000 ₩</div>
            </div>
            <div className="join-tag user">
              <div>
                <SVGUser />
              </div>
              <div>4명</div>
            </div>
          </div>
        </div>
      </div>
    </StyledJoinCardVertical>
  );
};

export default JoinCardVertical;
