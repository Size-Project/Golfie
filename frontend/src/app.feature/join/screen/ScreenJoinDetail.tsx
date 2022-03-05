import React from 'react';
import styled from 'styled-components';
import JoinDetailInfo from 'app.feature/join/component/JoinDetailInfo';
import JoinDetailHeader from 'app.feature/join/component/JoinDetailHeader';
import JoinDetailAuthor from 'app.feature/join/component/JoinDetailAuthor';
import JoinDetailDone from 'app.feature/join/component/JoinDetailDone';
import JoinDetailRecommend from 'app.feature/join/component/JoinDetailRecommend';

const ScreeJoinDetail = () => {
  return (
    <StyledWrapper>
      <JoinDetailHeader />
      <JoinDetailInfo />
      <JoinDetailAuthor />
      <JoinDetailDone />
      <JoinDetailRecommend />
    </StyledWrapper>
  );
};

export default ScreeJoinDetail;

const StyledWrapper = styled.div``;
