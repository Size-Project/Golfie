import React, { useState } from 'react';
import { StyledFeedCardContent } from './styled';
import { ReactComponent as IconHeart } from '../../../assets/images/svg/ic-heart.svg';
import { ReactComponent as IconMessage } from '../../../assets/images/svg/ic-message.svg';
import API from 'api';

const FeedCardContent = ({ id, content, liking, setCount }) => {
  const [like, setLike] = useState(liking);

  const handleLike = async () => {
    try {
      if (!like) {
        const formData = new FormData();
        formData.append('feedId', id);

        await API.POST({
          url: '/api/feeds/like',
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data',
            type: 'formData',
          },
        });
        setCount((count) => count + 1);
      } else {
        await API.DELETE({
          url: `/api/feeds/like/undo?feedId=${id}`,
        });
        setCount((count) => count - 1);
      }

      setLike(!like);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <StyledFeedCardContent>
      <div className="content-text">{content}</div>
      <div className="content-detail-wrap">
        <div className="content-detail-left">
          <div className={`content-like ${String(like)}`} onClick={handleLike}>
            <IconHeart />
          </div>
          <div className="content-comment">
            <IconMessage />
          </div>
        </div>
        <div className="content-detail-more"></div>
      </div>
    </StyledFeedCardContent>
  );
};

export default FeedCardContent;
