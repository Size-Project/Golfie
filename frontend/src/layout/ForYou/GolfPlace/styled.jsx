import styled from 'styled-components';

const Wrapper = styled.section`
  font-size: 20px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: 1.3;

  .intro,
  .category-intro {
    margin-left: 20px;
  }
  .category-intro {
    font-weight: bold;
    color: #00b169;
  }
  .golf-list {
    display: flex;
    gap: 10px;
    overflow-y: hidden;
    overflow-x: auto;
    padding-top: 1px;
    padding-bottom: 50px;
  }
`;

export { Wrapper };
