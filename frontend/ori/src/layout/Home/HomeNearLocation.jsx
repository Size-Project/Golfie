import React from 'react';
import { StyledHomeNearLocation } from './styled';

const HomeNearLocation = () => {
  return (
    <StyledHomeNearLocation>
      <div className="top-title">
        <div>
          <b>파크밸리cc</b> 근처의 추천장소
        </div>
        <div className="all-button">전체보기</div>
      </div>
      <div className="location-list-wrap">
        <div className="location-list-item"></div>
        <div className="location-list-item"></div>
        <div className="location-list-item"></div>
        <div className="location-list-item"></div>
      </div>
    </StyledHomeNearLocation>
  );
};

export default HomeNearLocation;
