import styled from 'styled-components';

const Wrapper = styled.section`
  margin-bottom: 52px;
  margin-left: 20px;

  .title {
    margin-bottom: 20px;
    .intro,
    .intro-body {
      font-size: 18px;
      font-weight: 500;
      span {
        font-weight: bold;
      }
    }
    .intro-body {
      position: relative;
      .show-all {
        position: absolute;
        top: 0;
        right: 10px;
        color: var(--color-main);
        font-size: 12px;
        font-weight: 500;
      }
    }
  }
  .place-list {
    display: flex;
    gap: 20px;
    overflow-y: hidden;
    overflow-x: auto;
  }
`;

export { Wrapper };
