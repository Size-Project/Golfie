import React, { useState } from 'react';
import { StyledFeedCardUser } from './styled';

const FeedCardUser = () => {
  const [follow, setFollow] = useState(false);

  const handleFollow = () => {
    setFollow(!follow);
  };

  return (
    <StyledFeedCardUser>
      <div className="user-info-wrap">
        <div className="user-image"></div>
        <div className="user-info">
          <div className="user-name">김다라</div>
          <div className="user-detail">21세 / 대학생 / 120타</div>
        </div>
      </div>
      <div className={`user-follow ${String(follow)}`} onClick={handleFollow}>
        {follow ? 'Following' : 'Follow'}
      </div>
    </StyledFeedCardUser>
  );
};

export default FeedCardUser;
