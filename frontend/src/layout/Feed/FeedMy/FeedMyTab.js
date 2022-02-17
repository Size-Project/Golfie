import React, { useState } from 'react';
import { StyledFeedMyTab } from './styled';
import FeedMyGrid from './FeedMyGrid';
import FeedMyList from './FeedMyList';
import FeedGrid from 'assets/images/Feed/FeedGrid.png';
import FeedList from 'assets/images/Feed/FeedList.png';

const FeedMyTab = ({ feedMy }) => {
  const [active, setActive] = useState('grid');

  const handleActiveTab = (tab) => {
    setActive(tab);
  };

  return (
    <StyledFeedMyTab>
      <div className="feed-my-tab">
        <div
          className={`tab ${String(active === 'grid')}`}
          onClick={() => handleActiveTab('grid')}
        >
          <img src={FeedGrid} alt="grid" />
        </div>
        <div
          className={`tab ${String(active === 'list')}`}
          onClick={() => handleActiveTab('list')}
        >
          <img src={FeedList} alt="list" />
        </div>
      </div>
      {active === 'grid' && <FeedMyGrid feedMy={feedMy} />}
      {active === 'list' && <FeedMyList feedMy={feedMy} />}
    </StyledFeedMyTab>
  );
};

export default FeedMyTab;
