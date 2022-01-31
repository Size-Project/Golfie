import React from 'react';
import { Wrapper } from './styled';
import { ballonIcon, calendarIcon, locationIcon } from 'assets/images/ForYou';

const GolfItem = () => {
  return (
    <Wrapper>
      <div className="golf-item-box">
        <div className="title-box">
          <div className="title">
            <div className="profile"></div>
            <div className="intro-message">
              <div className="top-message">임현주님 주최</div>
              <div className="mid-message">골린이를 위한 조인</div>
            </div>
            <div className="date-box">
              <div className="date">D-04</div>
            </div>
          </div>
        </div>
        <div className="info">
          <div className="location">
            <img src={locationIcon} alt="" />
            <span> 파크벨리cc |강원 원주</span>
          </div>
          <div className="date">
            <img src={calendarIcon} alt="" />
            <span>Jan.02 AM 11:00</span>
          </div>
          <div className="body-text">
            <img src={ballonIcon} alt="" />
            <span>골린이라고 두려워하지 말아요!</span>
          </div>
        </div>
      </div>
    </Wrapper>
  );
};

export default GolfItem;
