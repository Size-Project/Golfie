import React from 'react';
import { StyledHomeHeader } from './styled';
import IconAlarm from '../../assets/images/common/icon-alarm.png';
import IconMessage from '../../assets/images/common/icon-message.png';

const HomeHeader = () => {
  return (
    <StyledHomeHeader>
      <div className="logo">fieldtrip</div>
      <div className="header-button">
        <img src={IconAlarm} />
        <img src={IconMessage} />
      </div>
    </StyledHomeHeader>
  );
};

export default HomeHeader;
