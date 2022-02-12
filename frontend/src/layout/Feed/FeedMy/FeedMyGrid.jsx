import React from 'react';
import { StyledFeedMyGrid } from './styled';

const FeedMyGrid = ({ feedMy }) => {
  console.log(feedMy);
  return (
    <StyledFeedMyGrid>
      {feedMy.map((feed, feedIdx) => (
        <div key={feedIdx} className="feed-grid-item">
          d
        </div>
      ))}
    </StyledFeedMyGrid>
  );
};

export default FeedMyGrid;
