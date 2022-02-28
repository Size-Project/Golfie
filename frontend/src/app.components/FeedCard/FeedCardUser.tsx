import React, { useState } from 'react';
import styled from 'styled-components';
import API from 'app.modules/api';
import useGetUser from 'app.hooks/useGetUser';

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
    <StyledWrapper>
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
      {getUser.login && getUser.info.id !== author.id && (
        <div className={`user-follow ${String(follow)}`} onClick={handleFollow}>
          {follow ? 'Following' : 'Follow'}
        </div>
      )}
    </StyledWrapper>
  );
};

export default FeedCardUser;

const StyledWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;

  .user-info-wrap {
    display: flex;
    align-items: center;

    .user-image {
      width: 45px;
      height: 45px;
      border-radius: 50%;
      margin-right: 10px;
      overflow: hidden;
      box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);

      img {
        object-fit: contain;
        width: 100%;
        height: 100%;
      }
    }

    .user-info {
      font-weight: 500;

      .user-name {
        font-size: 16px;
        color: var(--color-black);
        line-height: 20px;
      }

      .user-detail {
        font-size: 12px;
        color: var(--color-gray-300);
        line-height: 20px;
      }
    }
  }

  .user-follow {
    border-radius: 14px;
    box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.15);
    width: 80px;
    height: 28px;
    text-align: center;
    font-size: 12px;
    line-height: 28px;

    &.true {
      color: var(--color-sub);
      background-color: var(--color-white);
    }

    &.false {
      color: var(--color-white);
      background-color: var(--color-main);
    }
  }
`;
