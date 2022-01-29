import IconAlarm from 'assets/images/common/icon-alarm.png';
import IconMessage from 'assets/images/common/icon-message.png';
import IconSearch from 'assets/images/common/icon-search.png';
import { Wrapper } from './styled';

const ForYouHeader = () => {
  return (
    <Wrapper>
      <div className="top-header">
        <span className="subtitle">For You</span>
        <span className="icons">
          <img src={IconAlarm} className="icon alram-icon" alt="" />
          <img src={IconMessage} className="icon message-icon" alt="" />
        </span>
      </div>
      <div className="intro">
        <div>
          <span className="user-name">민영</span>님의 필드트립에 맞는
        </div>
        <div>
          <span className="bold">플레이스 키워드</span>를 선택해보세요
        </div>
      </div>
      <div className="search-bar-box">
        <div className="search-bar">
          <label className="search-label" for="search">
            <img src={IconSearch} className="search-icon" alt="" />
          </label>
          <input
            type="text"
            name="search"
            className="search-input"
            placeholder="원하는 키워드를 입력하세요"
          />
        </div>
      </div>
    </Wrapper>
  );
};

export default ForYouHeader;
