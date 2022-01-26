import React from 'react';
import { StyledHomeFriendLocation } from './styled';
import { ReactComponent as SVGHeart } from '../../assets/images/svg/menu-join.svg';
import { ReactComponent as SVGLocation } from '../../assets/images/svg/tag-location.svg';

const HomeFriendLocation = () => {
  return (
    <StyledHomeFriendLocation>
      <div className="top-title">
        친구가 다녀온
        <br />
        이런 <b>장소</b>는 어때요?
      </div>
      <div className="location-grid-wrap">
        {[1, 2, 3, 4, 5, 6].map((item, idx) => (
          <div key={idx} className="location-grid-item">
            <div className="location-img-wrap">
              <SVGHeart />
            </div>

            <div className="location-info">
              <div className="location-title">사려니 숲길 산책로</div>
              <div className="location-desc">
                <div>
                  <SVGLocation />
                </div>
                <div className="desc-text">
                  파크밸리cc<span>&nbsp;|&nbsp;강원 원주</span>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </StyledHomeFriendLocation>
  );
};

export default HomeFriendLocation;
