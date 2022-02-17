import React, { useState } from 'react';
import { StyledFeedCard } from './styled';
import FeedCardUser from './FeedCardUser';
import FeedCardImage from './FeedCardImage';
import FeedCardContent from './FeedCardContent';
import { useGetUser } from '../../../services/store.intoAPP';

const FeedCard = ({ feed }) => {
  const { author, content, id, likeCount, liking, following, imageUrls } = feed;
  const [count, setCount] = useState(likeCount);

  return (
    <StyledFeedCard>
      <FeedCardUser author={author} following={following} />
      <div className="content-wrap">
        <FeedCardImage imageUrls={imageUrls} likeCount={count} />
        <FeedCardContent
          id={id}
          content={content}
          liking={liking}
          setCount={setCount}
        />
      </div>
    </StyledFeedCard>
  );
};

export default FeedCard;
