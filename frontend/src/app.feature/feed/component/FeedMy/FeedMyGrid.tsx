import React from 'react';
import styled from 'styled-components';

const FeedMyGrid = ({ feedMy }) => {
  return (
    <StyledWrapper>
      {feedMy.map((feed, feedIdx) => (
        <div key={feedIdx} className="feed-grid-item">
          <div className="feed-image">
            <img src={feed.imageUrls[0]} alt="feed-img" />
          </div>
        </div>
      ))}
    </StyledWrapper>
  );
};

export default FeedMyGrid;

const StyledWrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  row-gap: 4px;
  column-gap: 4px;
  margin-bottom: 71px;

  .feed-grid-item {
    position: relative;
    border-radius: 8px;
    box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.16);
    width: 100%;
    overflow: hidden;

    &:before {
      content: '';
      display: block;
      padding-top: 100%; /* 1:1 비율 */
    }

    .feed-image {
      position: absolute;
      width: 100%;
      height: 100%;
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;

      img {
        object-fit: cover;
        width: 100%;
        height: 100%;
      }
    }
  }
`;
