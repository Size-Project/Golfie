import styled from 'styled-components';

const Wrapper = styled.div`
  .header-top-box {
    margin-top: 52px;
    margin-bottom: 37px;
  }
  .search-box {
    margin-bottom: 40px;
  }
  .golf-place {
    margin-left: 20px;
    font-size: 20px;
    font-weight: 500;
    font-stretch: normal;
    font-style: normal;
    line-height: 1.3;
    .category-intro {
      font-weight: bold;
      color: #00b169;
    }
    .golf-list-box {
      display: flex;
      gap: 10px;
      overflow-y: hidden;
      overflow-x: auto;
      /* overscroll-behavior: contain; */

      .golf {
        :last-child {
          margin-right: 20px;
        }
        min-width: 335px;
        min-height: 186px;
        border: solid;
      }
    }
  }
`;

export { Wrapper };
