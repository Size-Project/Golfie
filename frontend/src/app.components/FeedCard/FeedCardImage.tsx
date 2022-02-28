import React, { useState } from 'react';
import styled from 'styled-components';
import { Swiper, SwiperSlide } from 'swiper/react';
import IconHeart from '../../../public/images/svg/ic-heart.svg';
import IconBookmark from '../../../public/images/svg/ic-bookmark.svg';

import 'swiper/css';
import 'swiper/css/pagination';

import SwiperCore, { Navigation, Pagination } from 'swiper';
SwiperCore.use([Navigation, Pagination]);

const FeedCardImage = ({ imageUrls, likeCount }) => {
  const [bookmark, setBookmark] = useState(false);

  const handleBookmark = () => {
    setBookmark(!bookmark);
  };

  return (
    <StyledWrapper>
      <Swiper
        spaceBetween={10}
        slidesPerView={1}
        modules={[Navigation, Pagination]}
        navigation
        pagination={{ clickable: true }}
      >
        <div className="img-bottom">
          <div className="img-bottom-left">
            <div className="count-wrap">
              <div className="count-img">
                <IconHeart />
              </div>
              <div className="count-num">{likeCount.toLocaleString()}</div>
            </div>
            {/*<div className="count-wrap">*/}
            {/*  <div className="count-img">*/}
            {/*    <IconMessage />*/}
            {/*  </div>*/}
            {/*  <div className="count-num">{(1234).toLocaleString()}</div>*/}
            {/*</div>*/}
          </div>
          <div
            className={`bookmark-img ${String(bookmark)}`}
            onClick={handleBookmark}
          >
            <IconBookmark />
          </div>
        </div>
        {imageUrls.map((image, imageIdx) => (
          <SwiperSlide key={imageIdx}>
            <div className="content-image-wrap">
              <div className="content-image">
                <img src={image} alt="content" />
              </div>
              <div className="content-etc" />
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </StyledWrapper>
  );
};

export default FeedCardImage;

const StyledWrapper = styled.div`
  width: 100%;
  position: relative;

  .img-bottom {
    z-index: 9;
    padding: 0 15px;
    width: 100%;
    height: 42px;
    display: flex;
    position: absolute;
    bottom: 0;
    align-items: center;
    justify-content: space-between;

    .img-bottom-left {
      display: flex;
      align-items: center;

      .count-wrap {
        margin-right: 6px;
        border-radius: 10.5px;
        background-color: #ededed;
        height: 21px;
        line-height: 21px;
        font-size: 12px;
        color: var(--color-gray-300);
        padding: 0 8px;
        display: flex;
        align-items: center;
        .count-img {
          margin-right: 5px;
          height: 100%;

          svg {
            width: 9px;
          }
        }
      }
    }

    .bookmark-img {
      &.false {
        svg {
          path {
            fill: var(--color-gray-200);
          }
        }
      }

      &.true {
        svg {
          path {
            fill: var(--color-main);
          }
        }
      }
    }
  }

  .swiper-pagination {
    width: 30% !important;
    margin: 0 calc(35%);
    align-self: center;
    justify-self: center;
    position: absolute;
    bottom: 13px !important;

    .swiper-pagination-bullet {
      width: 8px;
      height: 8px;
      margin: 0 3.5px !important;
    }

    .swiper-pagination-bullet-active {
      background-color: var(--color-main);
    }
  }

  .content-image-wrap {
    width: 100%;
    border-radius: 15px 15px 0 0;
    overflow: hidden;

    .content-image {
      position: relative;
      width: 100%;
      height: 335px;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }

    .content-etc {
      height: 42px;
    }
  }

  .location-wrap {
    color: var(--color-white);
    position: absolute;
    bottom: 6px;
    left: 6px;
    width: calc(100% - 12px);
    padding: 12px 17px;
    border-radius: 8px;
    background: rgba(255, 255, 255, 0.5);
    display: flex;
    justify-content: space-between;
    align-items: center;

    .location-left {
      display: flex;
      align-items: center;

      .location-image {
        margin-right: 10px;
      }

      .location-info {
        line-height: 18px;

        .location-title {
          font-weight: bold;
          font-size: 14px;
        }

        .location-desc {
          font-size: 12px;
        }
      }
    }
  }
`;
