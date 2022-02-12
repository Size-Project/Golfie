import React from 'react';
import { Wrapper } from './styled';
import IconAlarm from 'assets/images/common/icon-alarm.png';
import IconMessage from 'assets/images/common/icon-message.png';

const Header = ({ theme }) => {
  return (
    <Wrapper>
      <div>{theme}</div>
      <div>
        <img src={IconAlarm} className="icon alram-icon" alt="" />
        <img src={IconMessage} className="icon message-icon" alt="" />
      </div>
    </Wrapper>
  );
};

export default Header;
