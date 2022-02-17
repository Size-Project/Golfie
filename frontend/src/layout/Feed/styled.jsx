import styled, { css } from 'styled-components';

export const StyledFeedHeader = styled.div`
  height: 44px;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 10;
  background-color: var(--color-white);

  .logo {
    font-family: 'Co Headline';
    font-size: 22px;
    line-height: 29px;
  }

  .header-button {
    img {
      margin-right: 18.3px;

      &:last-child {
        margin-right: 0;
      }
      padding: 16px 0 8px;
      width: 20px;
    }
  }
`;

export const StyledFeedTab = styled.div`
  ${({ stage }) => css`
    padding: 20px 20px 30px;
    //TO Do fontFamily
    .tabs-title-wrap {
      width: 100%;
      display: flex;

      .tabs-title {
        cursor: pointer;
        width: 50%;
        text-align: center;
        padding-bottom: 5px;

        &.true {
          color: var(--color-main);
          font-weight: bold;
        }
      }
    }

    &:after {
      content: '';
      display: block;
      width: 50%;
      height: 1px;
      background-color: var(--color-main);
      transform: translateX(${stage}00%);
      transition-duration: 0.2s;
    }
  `}
`;

export const StyledFeedTabPane = styled.div`
  ${({ stage }) => css`
    position: relative;
    height: 100%;
    flex: 0 0 auto;
    overflow: hidden;

    .stage-view {
      position: relative;
      height: 100%;
      display: flex;
      flex-flow: row;
      flex-wrap: nowrap;
      transition-duration: 0.2s;
      transform: translateX(-${stage}00%);

      .stage {
        position: relative;
        width: 100%;
        height: 100%;
        flex: 0 0 auto;
        overflow: hidden;
        overflow-y: auto;
        -webkit-overflow-scrolling: touch;

        &::-webkit-scrollbar {
          display: none;
        }

        .stage-child {
          position: relative;
        }
      }
    }
  `}
`;

export const StyledFeedCreate = styled.div``;
