import styled from 'styled-components';
import bannerBackground from '../../assets/images/common/banner-green.png';

export const StyledHomeHeader = styled.div`
  height: 44px;
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  //TO DO: 헤더 sticky로 구현할지
  //position: sticky;
  //top: 0;
  //z-index: 10;
  //background-color: var(--color-white);

  img {
    &:first-child {
      margin-right: 18.3px;
    }
    padding: 16px 0 8px;
    width: 20px;
  }
`;

export const StyledHomeBanner = styled.div`
  padding: 30px 20px 40px;

  .swiper-pagination-horizontal {
    bottom: 0 !important;
  }

  .swiper-pagination {
    .swiper-pagination-bullet {
      width: 8px;
      height: 8px;
      margin: 0 3.5px !important;
    }

    .swiper-pagination-bullet-active {
      background-color: var(--color-main);
    }
  }
  .swiper-slide {
    height: 268px;
    .banner-item {
      span {
        font-weight: 500;
      }

      .banner-text {
        padding: 20px;
      }

      background: url(${bannerBackground}) var(--color-main);
      background-size: 100%;
      background-repeat: no-repeat;
      background-position: bottom;
      font-weight: 300;
      line-height: 35px;
      height: 240px;
      color: var(--color-white);
      font-size: 24px;
      border-radius: 10px;
    }
  }
`;

export const StyledHomeSchedule = styled.div`
  padding: 0 20px;
  margin-bottom: 50px;

  .schedule-title {
    font-size: 22px;
    line-height: 28px;
    margin-bottom: 20px;
    color: var(--color-black);
  }

  .schedule-list-wrap {
    .schedule-list-item {
      display: flex;
      margin-bottom: 15px;

      &:last-child {
        margin-bottom: 0;
      }

      .profile-img {
        margin: 10px 15px 0 0;
        min-width: 45px;
        min-height: 45px;
        height: 45px;
        width: 45px;
        border-radius: 50%;
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
      }

      .join-content {
        width: 100%;
        padding: 12px 15px 10px;
        border-radius: 10px;
        box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.16);

        .join-user {
          font-size: 8px;
          color: var(--color-gray-200);
          margin-bottom: 1px;
        }

        .join-title {
          line-height: 20px;
          font-size: 14px;
          font-weight: bold;
          color: var(--color-black);
          margin-bottom: 5px;
        }

        .join-info {
          .info-desc {
            margin-bottom: 4px;
            display: flex;
            align-items: center;
            font-size: 10px;
            line-height: 15px;
            color: var(--color-black);

            div {
              display: flex;
              align-items: center;
            }

            span {
              color: var(--color-gray-300);
            }

            svg {
              margin-right: 4px;
            }
          }
        }
      }
    }
  }
`;

export const StyledHomeRecommend = styled.div`
  color: var(--color-black);
  padding: 0 20px;
  margin-bottom: 40px;

  .top-title {
    margin-bottom: 30px;
    position: relative;

    .today {
      font-size: 22px;
      line-height: 29px;
    }

    .user {
      font-size: 18px;
      line-height: 26px;

      b {
        font-weight: bold;
        color: var(--color-purple);
      }
    }

    .refresh-button {
      width: 22px;
      height: 22px;
      position: absolute;
      bottom: 0;
      right: 0;

      div {
        width: 100%;
        height: 100%;
        position: absolute;
        opacity: 0.4;
        top: 0;
        border-radius: 50%;
        background-color: var(--color-purple);
      }

      svg {
        position: absolute;
        width: 12.2px;
        height: 10px;
        margin: 6px 4.9px;
      }
    }
  }

  .recommend-wrap {
    border-radius: 10px;
    box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.16);
    width: 100%;
    overflow: hidden;
    .recommend-content {
      height: 117px;
      background-color: gray;
      padding: 15px;
      display: flex;
      align-items: end;

      .profile-img {
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
        width: 32px;
        height: 32px;
        margin-right: 8px;
        background-color: #fff;
        border-radius: 50%;
      }

      .info-wrap {
        color: var(--color-white);

        .user {
          font-size: 8px;
          line-height: 11px;
          margin-bottom: 1px;
        }

        .title {
          font-size: 14px;
          font-weight: bold;
          line-height: 20px;
        }
      }
    }

    .recommend-desc {
      padding: 13px 15px 10px;
      .info-desc {
        margin-bottom: 4px;
        display: flex;
        align-items: center;
        font-size: 10px;
        line-height: 15px;
        color: var(--color-black);

        div {
          display: flex;
          align-items: center;
          margin-right: 10px;
        }

        span {
          color: var(--color-gray-300);
        }

        svg {
          margin-right: 4px;
        }
      }
    }
  }
`;

export const StyledHomeNearLocation = styled.div``;
