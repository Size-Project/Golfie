import React from 'react';
import { StyledFeedMyList } from './styled';
import FeedCard from '../FeedCard/FeedCard';

const FeedMyList = ({ feedMy }) => {
  return (
    <StyledFeedMyList>
      {feedMy.map((feed, feedIdx) => (
        <FeedCard feed={feed} key={feedIdx} />
      ))}
    </StyledFeedMyList>
  );
};

export default FeedMyList;
