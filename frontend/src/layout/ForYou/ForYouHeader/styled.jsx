import styled from 'styled-components';

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  position: relative;
  .top-header {
    padding-top: 44px;
    padding-bottom: 57px;
    .subtitle {
      font-size: 22px;
      margin-left: 20px;
      margin-right: 196px;
    }
    .icon {
      width: 19.7px;
      height: 19.7px;
    }
    .alram-icon {
      margin-right: 18.3px;
    }
  }
  .intro {
    margin-left: 20px;
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
    .search-bar {
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
    }
  }
`;

export { Wrapper };
