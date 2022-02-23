import React from 'react';
import styled from 'styled-components';
import FeedCard from '../../../../app.components/FeedCard/FeedCard';

const FeedMyList = ({ feedMy }) => {
  return (
    <StyledWrapper>
      {feedMy.map((feed, feedIdx) => (
        <FeedCard feed={feed} key={feedIdx} />
      ))}
    </StyledWrapper>
  );
};

export default FeedMyList;

export const StyledWrapper = styled.div``;
