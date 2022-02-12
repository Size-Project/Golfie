import React, { useState } from 'react';
import { StyledFeedCardUser } from './styled';

const FeedCardUser = ({ author, following }) => {
  const [follow, setFollow] = useState(following);

  const handleFollow = () => {
    setFollow(!follow);
  };

  return (
    <StyledFeedCardUser>
      <div className="user-info-wrap">
        <div className="user-image">
          {!!author?.imageUrl && (
            <img src={author.imageUrl} alt="profile-img" />
          )}
        </div>
        <div className="user-info">
          <div className="user-name">{author.nickname}</div>
          <div className="user-detail">
            {author.ageRange}세 / {author.job} / {author.averageHit}타
          </div>
        </div>
      </div>
      <div className={`user-follow ${String(follow)}`} onClick={handleFollow}>
        {follow ? 'Following' : 'Follow'}
      </div>
    </StyledFeedCardUser>
  );
};

export default FeedCardUser;
