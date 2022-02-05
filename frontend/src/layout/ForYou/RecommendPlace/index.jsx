import React from 'react';
import { Wrapper } from './styled';
import PlaceItem from 'component/ForYou/PlaceItem';

const RecommendPlace = () => {
  return (
    <Wrapper>
      <div className="title">
        <div className="intro">편안한 마무리</div>
        <div className="intro-body">
          <span>파크벨리cc </span>근처의 <span>숙소</span>
          <div className="show-all">전체 보기</div>
        </div>
      </div>
      {/* <div className="tag-list">
        <span># 풀빌라</span>
        <span># 호텔</span>
        <span># 펜션</span>
        <span># 글램핑/캠핑</span>
        <span># 모텔</span>
      </div> */}
      <div className="place-list">
        <PlaceItem></PlaceItem>
        <PlaceItem></PlaceItem>
        <PlaceItem></PlaceItem>
        <PlaceItem></PlaceItem>
        <PlaceItem></PlaceItem>
      </div>
    </Wrapper>
  );
};

export default RecommendPlace;
