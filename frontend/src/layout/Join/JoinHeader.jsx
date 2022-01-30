import React from 'react';
import { StyledJoinHeader } from './styled';
import IconAlarm from '../../assets/images/common/icon-alarm.png';
import IconMessage from '../../assets/images/common/icon-message.png';

const JoinHeader = () => {
  return (
    <StyledJoinHeader>
      <div className="logo">Join</div>
      <div className="header-button">
        <img src={IconAlarm} alt="알림" />
        <img src={IconMessage} alt="메신저" />
      </div>
    </StyledJoinHeader>
  );
};

export default JoinHeader;
