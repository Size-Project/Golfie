import React from 'react';
import { StyledFeedTabPane } from './styled';
import FeedAll from './FeedAll/FeedAll';
import FeedMy from './FeedMy/FeedMy';
import { useGetUser } from '../../services/store.intoAPP';

const FeedTabPane = ({ tabKey, getUser }) => {
  return (
    <StyledFeedTabPane stage={tabKey}>
      <div className="stage-view">
        <div className={`stage stage-${tabKey}`}>
          <div className="stage-child">{tabKey === 0 && <FeedAll />}</div>
        </div>
        {getUser.login && (
          <div className={`stage stage-${tabKey}`}>
            <div className="stage-child">{tabKey === 1 && <FeedMy />}</div>
          </div>
        )}
      </div>
    </StyledFeedTabPane>
  );
};

export default FeedTabPane;
