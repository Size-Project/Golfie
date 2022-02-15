import React from 'react';
import { StyledFeedCard } from './styled';
import FeedCardUser from './FeedCardUser';
import FeedCardImage from './FeedCardImage';
import FeedCardContent from './FeedCardContent';

const FeedCard = ({ feed }) => {
  const { author, content, id, likeCount, liking, following, imageUrls } = feed;
  return (
    <StyledFeedCard>
      <FeedCardUser author={author} following={following} />
      <div className="content-wrap">
        <FeedCardImage imageUrls={imageUrls} likeCount={likeCount} />
        <FeedCardContent id={id} content={content} liking={liking} />
      </div>
    </StyledFeedCard>
  );
};

export default FeedCard;
