import React, { useState } from 'react';
import { StyledFeedCardContent } from './styled';
import { ReactComponent as IconHeart } from '../../../assets/images/svg/ic-heart.svg';
import { ReactComponent as IconMessage } from '../../../assets/images/svg/ic-message.svg';

const FeedCardContent = () => {
  const [like, setLike] = useState(false);

  const handleLike = () => {
    setLike(!like);
  };
  return (
    <StyledFeedCardContent>
      <div className="content-text">
        내 또래랑은 처음 나와봤는데 너무 재밌다… 다음에도 끝나고 맛집 투어
        해요..🥰
      </div>
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
