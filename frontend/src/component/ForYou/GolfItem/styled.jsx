import styled from 'styled-components';
import { golf } from 'assets/images/ForYou';

const Wrapper = styled.div`
  :first-child {
    margin-left: 20px;
  }
  :last-child {
    margin-right: 20px;
  }
  min-width: 335px;
  min-height: 186px;
  border-radius: 10px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  background-color: var(--color-white);
  .golf-item-box {
    width: 100%;
    height: 100%;
    padding: 10px 10px 14px 10px;
  }
  .title-box {
    background: url(${golf});

    width: 315px;
    height: 117px;
    border-radius: 10px;
    .title {
      display: flex;
      padding: 75px 10px 10px 10px;
      .profile {
        width: 32px;
        height: 32px;
        margin-right: 8px;
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
        border-radius: 50%;
        background-color: var(--color-white);
      }
      .intro-message {
        color: var(--color-white);
        .top-message {
          font-size: 8px;
        }
        .mid-message {
          font-size: 14px;
        }
      }
      .date-box {
        display: flex;
        padding-left: 86px;
        .date {
          width: 38px;
          height: 18px;
          text-align: center;
          margin: auto;
          border-radius: 9px;
          background-color: var(--color-lemon);
          font-size: 10px;
        }
      }
    }
  }
  .info {
    display: flex;
    flex-flow: row wrap;
    font-size: 10px;
    padding-top: 10px;
    .location,
    .date,
    .body-text {
      position: relative;
      span {
        position: absolute;
        top: 0;
        padding-left: 4px;
      }
    }
    .location {
      width: 40%;
    }
    .date {
      width: 60%;
    }
    .body-text {
      width: 100%;
      margin-top: 4px;
    }
  }
`;

export { Wrapper };
