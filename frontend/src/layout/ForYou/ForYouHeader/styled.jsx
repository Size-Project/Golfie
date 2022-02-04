import styled from 'styled-components';

const Wrapper = styled.div`
  height: 100%;
  position: relative;
  .foryou-header-top {
    padding-top: 44px;
    padding-bottom: 57px;
  }
  .intro {
    font-size: 20px;
    .user-name,
    .bold {
      font-weight: bold;
    }
  }
  .search-bar-box {
    position: absolute;
    display: flex;
    width: 100%;
    bottom: 0;
    transform: translate(0, 50%);
  }
`;

export { Wrapper };
