import React from 'react';
import { StyledFeedCard } from './styled';
import FeedCardUser from './FeedCardUser';
import FeedCardImage from './FeedCardImage';
import FeedCardContent from './FeedCardContent';

const FeedCard = () => {
  return (
    <StyledFeedCard>
      <FeedCardUser />
      <div className="content-wrap">
        <FeedCardImage />
        <FeedCardContent />
      </div>
    </StyledFeedCard>
  );
};

export default FeedCard;
