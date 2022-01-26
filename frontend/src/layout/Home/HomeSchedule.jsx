import React from 'react';
import { StyledHomeSchedule } from './styled';
import { ReactComponent as SVGLocation } from '../../assets/images/svg/tag-location.svg';
import { ReactComponent as SVGSchedule } from '../../assets/images/svg/tag-schedule.svg';
import { ReactComponent as SVGMessage } from '../../assets/images/svg/tag-message.svg';

const HomeSchedule = () => {
  return (
    <StyledHomeSchedule>
      <div className="schedule-title">Join schedule</div>
      <ul className="schedule-list-wrap">
        {[1, 2, 3].map((item, idx) => (
          <li key={idx} className="schedule-list-item">
            <div className="profile-img" />
            <div className="join-content">
              <div className="join-user">임현주님 주최</div>
              <div className="join-title">골린이를 위한 조인</div>
              <div className="join-info">
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
          </li>
        ))}
      </ul>
    </StyledHomeSchedule>
  );
};

export default HomeSchedule;
