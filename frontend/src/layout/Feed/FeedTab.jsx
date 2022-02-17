import React from 'react';
import { StyledFeedTab } from './styled';

const FeedTab = ({ tabKey, setTabKey, getUser }) => {
  const handleTabKey = (key) => {
    setTabKey(key);
  };

  return (
    <StyledFeedTab stage={tabKey}>
      <div className="tabs-title-wrap">
        <div
          className={`tabs-title ${String(tabKey === 0)}`}
          onClick={() => handleTabKey(0)}
        >
          둘러보기
        </div>
        {getUser.login && (
          <div
            className={`tabs-title ${String(tabKey === 1)}`}
            onClick={() => handleTabKey(1)}
          >
            내 피드
          </div>
        )}
      </div>
    </StyledFeedTab>
  );
};

export default FeedTab;
