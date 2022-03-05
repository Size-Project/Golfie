import React from 'react';
import styled from 'styled-components';
import SVGLocation from '../../../../public/images/svg/tag-location.svg';
import SVGSchedule from '../../../../public/images/svg/tag-schedule.svg';
import SVGCash from '../../../../public/images/svg/tag-cash.svg';
import SVGUser from '../../../../public/images/svg/tag-user.svg';

const JoinDetailInfo = () => {
  return (
    <StyledWrapper>
      <div className="author">지영주님 주최</div>
      <div className="title">골프도 치고 카페투어 가실 분</div>
      <div className="detail-info-box">
        <div className="info-item">
          <SVGLocation />
          <div className="info-text">파크밸리cc | 강원 원주</div>
        </div>
        <div className="info-item">
          <SVGSchedule />
          <div className="info-text">Jan 02 AM 11:00</div>
        </div>
        <div className="info-item">
          <SVGCash />
          <div className="info-text">80,000원</div>
        </div>
        <div className="info-item">
          <SVGUser />
          <div className="info-text">6명 | 4명 신청 완료</div>
        </div>
      </div>
    </StyledWrapper>
  );
};

export default JoinDetailInfo;

const StyledWrapper = styled.div`
  width: 100%;
  padding: 20px 25px 30px;
  border-radius: 0 0 20px 20px;
  background: var(--color-white);
  box-shadow: 0 0px 10px 1px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;

  .author {
    font-size: 12px;
    font-weight: 500;
    color: var(--color-purple);
    margin-bottom: 1px;
    line-height: 17px;
  }

  .title {
    font-size: 20px;
    font-weight: bold;
    color: var(--color-black);
    line-height: 30px;
    margin-bottom: 10px;
  }

  .detail-info-box {
    display: flex;
    flex-direction: column;
    gap: 10px;

    .info-item {
      display: flex;
      align-items: center;
      font-size: 12px;
      font-weight: 500;

      svg {
        margin-right: 6px;
      }
    }
  }
`;
