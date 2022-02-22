import React from 'react';
import { StyledHomeBanner } from './styled';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';

import SwiperCore, { Navigation, Pagination } from 'swiper';
SwiperCore.use([Navigation, Pagination]);

const HomeBanner = () => {
  return (
    <StyledHomeBanner>
      <Swiper
        spaceBetween={10}
        slidesPerView={1}
        modules={[Navigation, Pagination]}
        navigation
        pagination={{ clickable: true }}
      >
        <SwiperSlide>
          <div className="banner-item">
            <div className="banner-text">
              <span>페어웨이</span>에서의
              <br />
              필수 매너는 뭘까요?
            </div>
          </div>
        </SwiperSlide>
        <SwiperSlide>
          <div className="banner-item">
            <div className="banner-text">
              <span>페어웨이</span>에서의
              <br />
              필수 매너는 뭘까요?
            </div>
          </div>
        </SwiperSlide>
      </Swiper>
    </StyledHomeBanner>
  );
};

export default HomeBanner;
