import React from 'react';
import { Wrapper } from './styled';
import GolfItem from 'component/ForYou/GolfItem';

const GolfPlace = () => {
  return (
    <Wrapper>
      <div className="intro">다가오는 라운딩을 위한</div>
      <div className="category-intro">#HEALING 플레이스</div>
      <div className="golf-list">
        <GolfItem />
        <GolfItem />
        <GolfItem />
      </div>
    </Wrapper>
  );
};

export default GolfPlace;
