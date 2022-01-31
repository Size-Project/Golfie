import React from 'react';
import JoinCardHorizontal from '../../component/JoinCard/JoinCardHorizontal';
import { StyledJoinRecommend } from './styled';

const JoinRecommend = () => {
  return (
    <StyledJoinRecommend>
      <div className="top-title">
        설레는 라운딩을 위한
        <br />
        <b>조인 추천</b>
      </div>
      <div className="recommend-slide-wrap">
        {[1, 2, 3, 4, 5, 6].map((item, idx) => (
          <JoinCardHorizontal key={idx} />
        ))}
      </div>
    </StyledJoinRecommend>
  );
};

export default JoinRecommend;
