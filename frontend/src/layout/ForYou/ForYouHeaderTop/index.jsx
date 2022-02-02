import IconAlarm from 'assets/images/common/icon-alarm.png';
import IconMessage from 'assets/images/common/icon-message.png';
import { Wrapper } from './styled';

const ForYouHeaderTop = () => {
  return (
    <Wrapper>
      <div className="top-header">
        <span className="subtitle">For You</span>
        <span className="icons">
          <img src={IconAlarm} className="icon alram-icon" alt="" />
          <img src={IconMessage} className="icon message-icon" alt="" />
        </span>
      </div>
    </Wrapper>
  );
};

export default ForYouHeaderTop;
