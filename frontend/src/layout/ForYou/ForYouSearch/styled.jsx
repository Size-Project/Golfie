import styled from 'styled-components';

const Wrapper = styled.div`
  margin: auto;
  width: 335px;
  height: 48px;
  border-radius: 24px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  z-index: 1;
  background-color: var(--color-white);
  padding: 13px 0 17px 18px;
  .search-label {
    margin-right: 14px;
    .search-icon {
      width: 18px;
      height: 18px;
      color: var(--color-sub);
    }
  }
  .search-input {
    margin: auto;
    height: 20px;
    border: 0;
  }
`;

export { Wrapper };
