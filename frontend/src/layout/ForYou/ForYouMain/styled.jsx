import styled from 'styled-components';
import { ACTIVITY, CHATTY, HEALING, TASTE } from 'assets/images/ForYou';

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  margin-top: 52px;
  .category {
    display: grid;
    grid-template-columns: 168px 168px;
    grid-template-rows: 168px 168px;
    margin: auto;
    .tag-button {
      background-repeat: no-repeat;
      color: var(--color-white);
      font-size: 18px;
      font-weight: 300;
      font-stretch: normal;
      font-style: normal;
      line-height: 168px;
      letter-spacing: normal;
      text-align: center;
      color: var(--color-white: #ffffff);
    }
    .HEALING {
      background: url(${HEALING});
    }
    .TASTE {
      background: url(${TASTE});
    }
    .ACTIVITY {
      background: url(${ACTIVITY});
    }
    .CHATTY {
      background: url(${CHATTY});
    }
  }
  .showall-button-box {
    display: flex;
    .showall-button {
      margin: auto;
      margin-top: 20px;
      width: 174px;
      height: 37px;
      box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
      background-color: #00b169;
      border: 0;
      border-radius: 18px;
      font-size: 14px;
      font-weight: 300;
      color: var(--color-white);
    }
  }
`;

export { Wrapper };
