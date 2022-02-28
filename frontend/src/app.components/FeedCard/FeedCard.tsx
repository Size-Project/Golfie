import React, { useState } from 'react';
import styled from 'styled-components';
import FeedCardUser from './FeedCardUser';
import FeedCardImage from './FeedCardImage';
import FeedCardContent from './FeedCardContent';

const FeedCard = ({ feed }) => {
  const { author, content, id, likeCount, liking, following, imageUrls } = feed;
  const [count, setCount] = useState(likeCount);

  return (
    <StyledWrapper>
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
    </StyledWrapper>
  );
};

export default FeedCard;

const StyledWrapper = styled.div`
  margin-bottom: 50px;

  .content-wrap {
    border-radius: 15px;
    box-shadow: 0 5px 15px 0 rgba(0, 0, 0, 0.1);

    .content-text-wrap {
      .content-text {
        padding: 0 15px;
      }

      .content-detail-wrap {
        padding: 15px;
      }
    }
  }
`;
