import React, { useState } from 'react';
import styled from 'styled-components';
import IconHeart from '../../../public/images/svg/ic-heart.svg';
import IconMessage from '../../../public/images/svg/ic-message.svg';
import API from 'app.modules/api';

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
    <StyledWrapper>
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
    </StyledWrapper>
  );
};

export default FeedCardContent;

const StyledWrapper = styled.div`
  border-radius: 15px;

  .content-text {
    padding: 0 15px;
    font-size: 14px;
    line-height: 20px;
    color: var(--color-black);
  }

  .content-detail-wrap {
    padding: 15px;

    .content-detail-left {
      display: flex;

      .content-like {
        margin-right: 15px;
        svg {
          path {
            fill: var(--color-gray-200);
          }
        }

        &.true {
          svg {
            path {
              fill: var(--color-main);
            }
          }
        }
      }

      .content-comment {
        svg {
          path {
            fill: var(--color-gray-200) !important;
          }
        }
      }
    }
  }
`;
