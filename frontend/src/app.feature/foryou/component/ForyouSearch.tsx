import React from 'react';
import styled from 'styled-components';
import SVGSearch from '../../../../public/images/svg/ic-search.svg';

const ForyouSearch = ({ userInfo }) => {
  return (
    <StyledWrapper>
      <div className="search-title">
        <b>{userInfo.nickname}</b>님의 필드 트립에 맞는
        <br />
        플레이스 키워드를 선택해 보세요!
      </div>
      <div className="search-box-wrap">
        <div className="search-box">
          <input placeholder="원하는 키워드를 찾아보세요" />
          <SVGSearch />
        </div>
      </div>
    </StyledWrapper>
  );
};

export default ForyouSearch;

const StyledWrapper = styled.div`
  background-color: var(--color-gray-100);
  padding: 50px 0;
  position: relative;

  .search-title {
    padding: 0 20px;
    font-size: 20px;
    font-weight: 500;
    line-height: 1.3;
  }

  .search-box-wrap {
    position: absolute;
    top: 128px;
    width: 100%;
    height: 48px;
    padding: 0 20px;

    .search-box {
      position: relative;
      display: flex;
      align-items: center;
      background-color: var(--color-white);
      border-radius: 24px;
      box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);

      input {
        outline: none;
        width: 100%;
        height: 48px;
        padding: 0 18px 0 50px;
        border-radius: 20px;
        border: 0;
      }

      svg {
        position: absolute;
        left: 18px;
        path {
          stroke: var(--color-sub) !important;
        }
      }
    }
  }
`;
