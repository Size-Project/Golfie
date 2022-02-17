import React, { useState } from 'react';
import { StyledFeedCardUser } from './styled';
import API from 'api';
import { useGetUser } from '../../../services/store.intoAPP';

const FeedCardUser = ({ author, following }) => {
  const [follow, setFollow] = useState(following);
  const getUser = useGetUser();

  const handleFollow = async () => {
    try {
      if (!follow) {
        const formData = new FormData();
        formData.append('userId', author.id);

        await API.POST({
          url: '/api/users/follow',
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data',
            type: 'formData',
          },
        });
      } else {
        await API.DELETE({
          url: `/api/users/unfollow?userId=${author.id}`,
        });
      }

      setFollow(!follow);
    } catch (err) {
      console.log(err);
    }
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
      {getUser.info.id !== author.id && (
        <div className={`user-follow ${String(follow)}`} onClick={handleFollow}>
          {follow ? 'Following' : 'Follow'}
        </div>
      )}
    </StyledFeedCardUser>
  );
};

export default FeedCardUser;
