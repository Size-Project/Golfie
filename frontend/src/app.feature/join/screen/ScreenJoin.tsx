import React from 'react';
import styled from 'styled-components';
import JoinHeader from 'app.feature/join/component/JoinHeader';
import JoinSearch from 'app.feature/join/component/JoinSearch';
import JoinRecommend from 'app.feature/join/component/JoinRecommend';
import JoinNewRecommend from 'app.feature/join/component/JoinNewRecommend';

const ScreeJoin = () => {
  return (
    <StyledWrapper>
      <JoinHeader />
      <JoinSearch />
      <JoinRecommend />
      <JoinNewRecommend />
    </StyledWrapper>
  );
};

export default ScreeJoin;

const StyledWrapper = styled.div``;
