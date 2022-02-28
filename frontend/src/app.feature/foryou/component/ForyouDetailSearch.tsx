import React from 'react';
import styled from 'styled-components';
import SVGSearch from '../../../../public/images/svg/ic-search.svg';

const ForyouDetailSearch = () => {
  return (
    <StyledWrapper>
      <div className="search-box">
        <input placeholder="원하는 플레이스를 찾아보세요" />
        <SVGSearch />
      </div>
    </StyledWrapper>
  );
};

export default ForyouDetailSearch;

const StyledWrapper = styled.div`
  padding: 30px 20px 40px;

  .search-box {
    position: relative;
    display: flex;
    align-items: center;
    background-color: var(--color-white);
    border-radius: 24px;
    box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);

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
`;
