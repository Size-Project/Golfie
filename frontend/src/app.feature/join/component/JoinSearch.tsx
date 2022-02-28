import React from 'react';
import styled from 'styled-components';
import SVGSearch from '../../../../public/images/svg/ic-search.svg';

const JoinSearch = () => {
  return (
    <StyledWrapper>
      <div className="top-title">
        <b>#골린이</b>를 위한
        <br />
        라운딩 30개 둘러보세요!
      </div>
      <div className="search-box-wrap">
        <div className="search-box-title">
          <b>120개</b>의 모집중인 라운딩 중<br />
          원하는 라운딩을 검색해보세요!
        </div>
        <div className="search-box">
          <input />
          <SVGSearch />
        </div>
      </div>
    </StyledWrapper>
  );
};

export default JoinSearch;

export const StyledWrapper = styled.div`
  padding: 30px 20px;

  .top-title {
    font-family: 'Noto Sans';
    font-size: 20px;
    font-weight: 500;
    line-height: 26px;
    color: var(--color-black);
    margin-bottom: 20px;

    b {
      font-weight: bold;
      color: var(--color-main);
    }
  }

  .search-box-wrap {
    background: url('/images/common/banner-green-small.png') no-repeat 120%;
    background-size: 150%;
    width: 100%;
    background-position: center;
    border-radius: 10px;
    position: relative;
    padding: 30px 15px;
    max-height: 166px;

    .search-box-title {
      top: 30px;
      left: 15px;
      font-family: 'Noto Sans';
      font-size: 16px;
      font-weight: 500;
      line-height: 22px;
      color: var(--color-white);
      margin-bottom: 20px;

      b {
        color: var(--color-lemon);
      }
    }

    .search-box {
      position: relative;
      display: flex;
      align-items: center;
      background-color: var(--color-white);
      border-radius: 20px;

      input {
        outline: none;
        width: 100%;
        height: 40px;
        padding: 0 18px 0 50px;
        border-radius: 20px;
        border: 0;
      }

      svg {
        position: absolute;
        left: 18px;
      }
    }
  }
`;
