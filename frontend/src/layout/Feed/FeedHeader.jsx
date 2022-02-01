import React from 'react';
import IconAlarm from '../../assets/images/common/icon-alarm.png';
import IconMessage from '../../assets/images/common/icon-message.png';
import IconSearch from '../../assets/images/common/icon-search.png';
import { StyledFeedHeader } from './styled';

const FeedHeader = () => {
  return (
    <StyledFeedHeader>
      <div className="logo">Feed</div>
      <div className="header-button">
        <img src={IconSearch} alt="검색" />
        <img src={IconAlarm} alt="알림" />
        <img src={IconMessage} alt="메신저" />
      </div>
    </StyledFeedHeader>
  );
};

export default FeedHeader;
