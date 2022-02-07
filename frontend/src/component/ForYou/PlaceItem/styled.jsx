import styled from 'styled-components';

const Wrapper = styled.div`
  :first-child {
    margin-left: 20px;
  }
  :last-child {
    margin-right: 20px;
  }
  min-width: 131px;
  min-height: 187px;
  .place-image {
    width: 131px;
    height: 131px;
    border-radius: 8px;
    box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.1);
    margin-bottom: 10px;
  }
  .place-name {
    font-size: 14px;
    font-weight: 500;
    margin-bottom: 5px;
  }
  .place-distance-box {
    position: relative;
    .place-distance {
      font-size: 12px;
      position: absolute;
      top: 0;
    }
  }
`;

export { Wrapper };
