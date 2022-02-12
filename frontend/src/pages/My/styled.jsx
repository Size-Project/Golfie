import styled from 'styled-components';

export const Wrapper = styled.div`
  header {
    padding-top: 52px;
    padding-bottom: 37px;
    padding-left: 20px;
    padding-right: 20px;
  }

  .user-info {
    width: 335px;
    height: 207px;
    margin: auto;
    border-radius: 10px;
    padding: 30px 20px;
    box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
    background-color: #6dd195;
    .user-top {
      display: flex;
      height: 50%;
      .user-face {
        width: 72px;
        height: 72px;
        border-radius: 50%;
        background-color: var(--color-white);
        margin-right: 10px;
      }
      .user-top-right {
        .user-name {
          height: 50%;
          padding-top: 2px;
          font-size: 20px;
          color: var(--color-white);
        }
        .user-tags {
          height: 50%;
          position: relative;
          .user-tags-content {
            position: absolute;
            bottom: 0;
            width: 213px;
            height: 27px;
            background-color: var(--color-white);
            border-radius: 13.5px;
            line-height: 13px;
            font-size: 10px;
            font-weight: 500;
            color: #797979;
            text-align: center;
          }
        }
      }
    }
    .user-bottom {
      height: 50%;
      display: flex;
      padding-top: 30px;
      .user-join,
      .user-follower,
      .user-feed {
        flex: 1;
        text-align: center;
        color: var(--color-white);
        display: flex;
        flex-direction: column;
        justify-content: space-between;
      }
      .user-join,
      .user-follower {
        border-right: solid white 0.5px;
      }
    }
  }
`;
