import React from 'react';
import { StyledFeedAll } from './styled';
import FeedCard from '../FeedCard/FeedCard';

const FeedAll = () => {
  return (
    <StyledFeedAll>
      {[1, 2, 3, 4, 5].map((feed, idx) => (
        <FeedCard key={idx} />
      ))}
    </StyledFeedAll>
  );
};

export default FeedAll;
