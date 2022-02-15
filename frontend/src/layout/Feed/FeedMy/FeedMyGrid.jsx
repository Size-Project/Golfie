import React from 'react';
import { StyledFeedMyGrid } from './styled';

const FeedMyGrid = ({ feedMy }) => {
  return (
    <StyledFeedMyGrid>
      {feedMy.map((feed, feedIdx) => (
        <div key={feedIdx} className="feed-grid-item">
          <div className="feed-image">
            <img src={feed.imageUrls[0]} alt="feed-img" />
          </div>
        </div>
      ))}
    </StyledFeedMyGrid>
  );
};

export default FeedMyGrid;
