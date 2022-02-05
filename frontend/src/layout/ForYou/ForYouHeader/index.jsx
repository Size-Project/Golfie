import IconSearch from 'assets/images/common/icon-search.png';
import { Wrapper } from './styled';
import ForYouHeaderTop from '../ForYouHeaderTop';
import ForYouHeaderSearch from '../ForYouSearch';

const ForYouHeader = () => {
  return (
    <Wrapper>
      <div className="foryou-header-top">
        <ForYouHeaderTop></ForYouHeaderTop>
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
        <ForYouHeaderSearch />
      </div>
    </Wrapper>
  );
};

export default ForYouHeader;
