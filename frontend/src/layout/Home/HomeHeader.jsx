import React from 'react';
import IconAlarm from '../../assets/images/common/icon-alarm.png';
import IconMessage from '../../assets/images/common/icon-message.png';
import LogoText from '../../assets/images/common/logo-text.png';
import { StyledHomeHeader } from './styled';

const HomeHeader = () => {
  return (
    <StyledHomeHeader>
      <div className="logo">
        <img src={LogoText} alt="로고 텍스트" />
      </div>
      <div className="header-button">
        <img src={IconAlarm} alt="알림" />
        <img src={IconMessage} alt="메신저" />
      </div>
    </StyledHomeHeader>
  );
};

export default HomeHeader;
