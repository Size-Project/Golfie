import React from 'react';
import { StyledJoinSearch } from './styled';
import { ReactComponent as SVGSearch } from '../../assets/images/svg/ic-search.svg';

const JoinSearch = () => {
  return (
    <StyledJoinSearch>
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
    </StyledJoinSearch>
  );
};

export default JoinSearch;
