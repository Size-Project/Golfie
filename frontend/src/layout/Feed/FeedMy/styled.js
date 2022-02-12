import styled from 'styled-components';

export const StyledFeedMy = styled.div``;

export const StyledFeedMyInfo = styled.div`
  padding: 0 20px 20px;
  border-radius: 0 0 20px 20px;
  box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.1);
  background-color: var(--white);
  margin-bottom: 30px;

  .user-info-top {
    display: flex;
    justify-content: space-between;
    margin-bottom: 15px;

    .top-left {
      display: flex;
      justify-content: space-between;

      .user-image {
        width: 68px;
        height: 68px;
        overflow: hidden;
        border-radius: 50%;
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
        margin-right: 13px;

        img {
          width: 100%;
          height: 100%;
        }
      }

      .info {
        display: flex;
        flex-direction: column;
        justify-content: center;

        .nickname {
          margin-bottom: 5px;
          font-family: 'Noto Sans';
          font-size: 16px;
          font-weight: 500;
          line-height: 1.25;
          color: var(--color-black);
        }

        .detail {
          color: var(--color-gray-300);
          font-size: 10px;
          padding: 0 15px;
          height: 27px;
          border-radius: 13.5px;
          box-shadow: inset 0 3px 3px 0 rgba(0, 0, 0, 0.16);
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }
    }
  }

  .user-info-bottom {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-family: 'Co Text';
    font-size: 16px;

    .info-count {
      width: 33.33%;
      text-align: center;

      .title {
        line-height: 21px;
        margin-bottom: 2px;
      }

      .count {
        line-height: 26px;
        font-size: 18px;
      }
    }
  }
`;

export const StyledFeedMyTab = styled.div`
  padding: 0 20px;

  .feed-my-tab {
    display: flex;
    justify-content: space-between;
    gap: 3px;
    margin-bottom: 30px;

    .tab {
      width: 100%;
      text-align: center;
      height: 30px;
      border-radius: 15px;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: var(--color-gray-100);
      transition: all 200ms;

      img {
        width: 11.5px;
      }

      &.true {
        background-color: var(--color-main);
      }
    }
  }
`;

export const StyledFeedMyGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  row-gap: 4px;
  column-gap: 4px;
  margin-bottom: 71px;
  .feed-grid-item {
    border-radius: 8px;
    box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.16);
  }
`;

export const StyledFeedMyList = styled.div``;
