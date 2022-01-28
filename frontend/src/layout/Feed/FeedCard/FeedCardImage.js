import React, { useState } from 'react';
import { StyledFeedCardImage } from './styled';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import { ReactComponent as IconHeart } from '../../../assets/images/svg/ic-heart.svg';
import { ReactComponent as IconMessage } from '../../../assets/images/svg/ic-message.svg';
import { ReactComponent as IconBookmark } from '../../../assets/images/svg/ic-bookmark.svg';

import SwiperCore, { Navigation, Pagination } from 'swiper';
SwiperCore.use([Navigation, Pagination]);

const FeedCardImage = () => {
  const [bookmark, setBookmark] = useState(false);

  const handleBookmark = () => {
    setBookmark(!bookmark);
  };

  return (
    <StyledFeedCardImage>
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
              <div className="count-num">{(1234).toLocaleString()}</div>
            </div>
            <div className="count-wrap">
              <div className="count-img">
                <IconMessage />
              </div>
              <div className="count-num">{(1234).toLocaleString()}</div>
            </div>
          </div>
          <div
            className={`bookmark-img ${String(bookmark)}`}
            onClick={handleBookmark}
          >
            <IconBookmark />
          </div>
        </div>
        {[1, 2, 3].map((item, idx) => (
          <SwiperSlide>
            <div className="content-image-wrap">
              <div className="content-image"></div>
              <div className="content-etc" />
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </StyledFeedCardImage>
  );
};

export default FeedCardImage;
