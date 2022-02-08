import styled from 'styled-components';
import BannerGreenSmall from '../../assets/images/common/banner-green-small.png';

export const StyledJoinHeader = styled.div`
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

export const StyledJoinSearch = styled.div`
  padding: 30px 20px;

  .top-title {
    font-family: 'Noto Sans';
    font-size: 20px;
    font-weight: 500;
    line-height: 26px;
    color: var(--color-black);
    margin-bottom: 20px;

    b {
      font-weight: bold;
      color: var(--color-main);
    }
  }

  .search-box-wrap {
    background: url(${BannerGreenSmall}) no-repeat 120%;
    background-size: 150%;
    width: 100%;
    background-position: center;
    border-radius: 10px;
    position: relative;
    padding: 30px 15px;
    max-height: 166px;

    .search-box-title {
      top: 30px;
      left: 15px;
      font-family: 'Noto Sans';
      font-size: 16px;
      font-weight: 500;
      line-height: 22px;
      color: var(--color-white);
      margin-bottom: 20px;

      b {
        color: var(--color-lemon);
      }
    }

    .search-box {
      position: relative;
      display: flex;
      align-items: center;
      background-color: var(--color-white);
      border-radius: 20px;

      input {
        outline: none;
        width: 100%;
        height: 40px;
        padding: 0 18px 0 50px;
        border-radius: 20px;
        border: 0;
      }

      svg {
        position: absolute;
        left: 18px;
      }
    }
  }
`;

export const StyledJoinRecommend = styled.div`
  padding: 10px 0;

  .top-title {
    padding: 0 20px;
    line-height: 26px;
    font-size: 20px;
    margin-bottom: 10px;

    b {
      color: var(--color-main);
      font-weight: bold;
    }
  }

  .recommend-slide-wrap {
    padding: 10px 0;
    display: flex;
    overflow-y: visible;
    overflow-x: scroll;
    width: 100%;
    gap: 20px;
    height: 100%;
  }
`;

export const StyledJoinNewRecommend = styled.div`
  padding: 30px 20px 0;

  .top-title {
    display: flex;
    align-items: end;
    font-size: 20px;
    font-family: 'Acumin Pro';
    margin-bottom: 10px;

    .bold {
      font-weight: bold;
      font-size: 22px !important;
    }
  }

  .new-recommend-wrap {
    .tab-title-wrap {
      margin-bottom: 20px;
      display: flex;
      gap: 8px;

      .tab-item {
        padding: 8px 12px;
        border-radius: 15px;
        font-size: 14px;
        font-weight: 500;
        color: var(--color-white);
        cursor: pointer;
        transition: 200ms;

        &.true {
          background-color: var(--color-sub);
          color: var(--color-white);
        }

        &.false {
          color: var(--color-gray-200);
          background-color: var(--color-gray-100);
        }
      }
    }
  }
`;
