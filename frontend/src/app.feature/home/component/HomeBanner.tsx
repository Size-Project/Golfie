import React from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import bannerBackground from '../../../../public/images/common/banner-green.png';

import SwiperCore, { Navigation, Pagination } from 'swiper';
SwiperCore.use([Navigation, Pagination]);

const HomeBanner = () => {
  return (
    <StyledWrapper>
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
    </StyledWrapper>
  );
};

export default HomeBanner;

const StyledWrapper = styled.div`
  padding: 30px 20px 40px;
  font-family: Noto Sans Light;

  .swiper-pagination-horizontal {
    bottom: 0 !important;
  }

  .swiper-pagination {
    .swiper-pagination-bullet {
      width: 8px;
      height: 8px;
      margin: 0 3.5px !important;
    }

    .swiper-pagination-bullet-active {
      background-color: var(--color-main);
    }
  }

  .swiper-slide {
    height: 268px;
    .banner-item {
      span {
        font-family: Noto Sans Regular;
      }

      .banner-text {
        padding: 20px;
      }

      background: url(${bannerBackground}) var(--color-main);
      background-size: 100%;
      background-repeat: no-repeat;
      background-position: bottom;
      font-weight: 300;
      line-height: 35px;
      height: 240px;
      color: var(--color-white);
      font-size: 24px;
      border-radius: 10px;
    }
  }
`;
