import React from 'react';
import styled from 'styled-components';
import PlaceCard from 'app.feature/foryou/component/PlaceCard';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination } from 'swiper';

import 'swiper/css';
import 'swiper/css/pagination';

const ForyouDetailPlace = () => {
  return (
    <StyledWrapper>
      <div className="place-title">
        다가오는 라운딩을 위한
        <br />
        <b>#HEALING 플레이스</b>
      </div>
      <Swiper
        spaceBetween={10}
        slidesPerView={1}
        modules={[Navigation, Pagination]}
      >
        <SwiperSlide>
          <PlaceCard />
        </SwiperSlide>
        <SwiperSlide>
          <PlaceCard />
        </SwiperSlide>
        <SwiperSlide>
          <PlaceCard />
        </SwiperSlide>
      </Swiper>
    </StyledWrapper>
  );
};

export default ForyouDetailPlace;

const StyledWrapper = styled.div`
  padding: 0 20px;
  margin-bottom: 50px;

  .place-title {
    font-size: 20px;
    font-weight: 500;
    line-height: 26px;
    margin-bottom: 20px;

    b {
      color: var(--color-main);
    }
  }

  .swiper {
    overflow: visible;
  }
  .swiper-wrapper {
    overflow: visible;
  }
`;
