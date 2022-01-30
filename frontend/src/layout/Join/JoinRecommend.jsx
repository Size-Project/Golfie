import React from 'react';
import { StyledJoinRecommend } from './styled';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';

import SwiperCore, { Navigation, Pagination } from 'swiper';
import JoinCardHorizontal from '../../component/JoinCard/JoinCardHorizontal';
SwiperCore.use([Navigation, Pagination]);

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
