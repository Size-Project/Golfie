import React from 'react';
import { Wrapper } from './styled';
import { golf, locationIcon } from 'assets/images/ForYou';

const PlaceItem = () => {
  return (
    <Wrapper>
      <img className="place-image" src={golf} alt="" />
      <div className="place-name">존엔제인 풀빌라</div>
      <div className="place-distance-box">
        <img src={locationIcon} alt="" />
        <span className="place-distance">13.5km</span>
      </div>
      {/* <div className="rating">
            <img src="" alt="" />
            <img src="" alt="" />
            <img src="" alt="" />
            <img src="" alt="" />
            <img src="" alt="" />
            <span>4.2</span>
          </div> */}
    </Wrapper>
  );
};

export default PlaceItem;
