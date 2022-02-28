import React from 'react';
import styled from 'styled-components';

const HomeNearLocation = () => {
  return (
    <StyledWrapper>
      <div className="top-title">
        <div>
          <b>파크밸리cc</b> 근처의 추천장소
        </div>
        <div className="all-button">전체보기</div>
      </div>
      <div className="location-list-wrap">
        <div className="location-list-item"></div>
        <div className="location-list-item"></div>
        <div className="location-list-item"></div>
        <div className="location-list-item"></div>
      </div>
    </StyledWrapper>
  );
};

export default HomeNearLocation;

export const StyledWrapper = styled.div`
  color: var(--color-black);
  margin-bottom: 30px;

  .top-title {
    display: flex;
    justify-content: space-between;
    align-items: end;
    padding: 0 20px;
    line-height: 24px;
    font-size: 16px;
    font-weight: 500;

    b {
      font-weight: bold;
    }

    .all-button {
      height: 17px;
      font-size: 12px;
      color: var(--color-main);
    }
  }

  .location-list-wrap {
    display: flex;
    gap: 20px;
    padding: 20px 0;
    overflow-y: hidden;
    overflow-x: auto;
    overscroll-behavior: contain;

    &::-webkit-scrollbar {
      display: none;
    }

    .location-list-item {
      :first-child {
        margin-left: 20px;
      }

      :last-child {
        margin-right: 20px;
      }

      min-width: 120px;
      width: 120px;
      min-height: 116px;
      height: 116px;
      box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.16);
      border-radius: 50%;
    }
  }
`;
