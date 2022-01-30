import { Wrapper } from './styled';
import IconSearch from 'assets/images/common/icon-search.png';

const ForYouHeaderSearch = () => {
  return (
    <Wrapper>
      <label className="search-label" htmlFor="search">
        <img src={IconSearch} className="search-icon" alt="" />
      </label>
      <input
        type="text"
        name="search"
        className="search-input"
        placeholder="원하는 키워드를 입력하세요"
      />
    </Wrapper>
  );
};

export default ForYouHeaderSearch;
