import React, { useState } from 'react';
import styled from 'styled-components';
import FeedMyGrid from './FeedMyGrid';
import FeedMyList from './FeedMyList';

const FeedMyTab = ({ feedMy }) => {
  const [active, setActive] = useState('grid');

  const handleActiveTab = (tab) => {
    setActive(tab);
  };

  return (
    <StyledWrapper>
      <div className="feed-my-tab">
        <div
          className={`tab ${String(active === 'grid')}`}
          onClick={() => handleActiveTab('grid')}
        >
          <img src="/images/Feed/FeedGrid.png" alt="grid" />
        </div>
        <div
          className={`tab ${String(active === 'list')}`}
          onClick={() => handleActiveTab('list')}
        >
          <img src="/images/Feed/FeedList.png" alt="list" />
        </div>
      </div>
      {active === 'grid' && <FeedMyGrid feedMy={feedMy} />}
      {active === 'list' && <FeedMyList feedMy={feedMy} />}
    </StyledWrapper>
  );
};

export default FeedMyTab;

const StyledWrapper = styled.div`
  padding: 0 20px;

  .feed-my-tab {
    display: flex;
    justify-content: space-between;
    gap: 3px;
    margin-bottom: 30px;

    .tab {
      width: 100%;
      text-align: center;
      height: 30px;
      border-radius: 15px;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: var(--color-gray-100);
      transition: all 200ms;

      img {
        width: 11.5px;
      }

      &.true {
        background-color: var(--color-main);
      }
    }
  }
`;
