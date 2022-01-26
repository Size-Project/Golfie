import styled from 'styled-components';
import bannerBackground from '../../assets/images/common/banner-green.png';

export const StyledHomeHeader = styled.div`
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
    img {
      width: 81px;
      height: 29px;
    }
  }

  .header-button {
    img {
      &:first-child {
        margin-right: 18.3px;
      }
      padding: 16px 0 8px;
      width: 20px;
    }
  }
`;

export const StyledHomeBanner = styled.div`
  padding: 30px 20px 40px;
  font-family: Noto Sans Light;

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
        font-family: Noto Sans Regular;
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
    font-family: 'Co Text';
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

export const StyledHomeNearLocation = styled.div`
  color: var(--color-black);
  margin-bottom: 30px;

  .top-title {
    display: flex;
    justify-content: space-between;
    align-items: end;
    padding: 0 20px;
    line-height: 24px;
    font-size: 16px;
    font-weight: 500;

    b {
      font-weight: bold;
    }

    .all-button {
      height: 17px;
      font-size: 12px;
      color: var(--color-main);
    }
  }

  .location-list-wrap {
    display: flex;
    gap: 20px;
    padding: 20px 0;
    overflow-y: hidden;
    overflow-x: auto;
    overscroll-behavior: contain;

    &::-webkit-scrollbar {
      display: none;
    }

    .location-list-item {
      :first-child {
        margin-left: 20px;
      }

      :last-child {
        margin-right: 20px;
      }

      min-width: 120px;
      width: 120px;
      min-height: 116px;
      height: 116px;
      box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.16);
      border-radius: 50%;
    }
  }
`;

export const StyledHomeFriendLocation = styled.div`
  padding: 0 20px;

  .top-title {
    margin-bottom: 20px;
    line-height: 25px;
    font-size: 18px;
    font-weight: 500;
  }

  .location-grid-wrap {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;

    .location-grid-item {
      .location-img-wrap {
        position: relative;
        padding: 50% 0;
        border-radius: 8px;
        box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.16);
        margin-bottom: 10px;

        svg {
          right: 10px;
          bottom: 10px;
          position: absolute;
          width: 18.5px;
          height: 17px;
          fill: var(--color-main);
        }
      }

      .location-info {
        padding: 0 5px;

        .location-title {
          margin-bottom: 5px;
          line-height: 20px;
          font-size: 14px;
          font-weight: bold;
        }

        .location-desc {
          display: flex;
          align-items: center;
          font-size: 10px;
          font-weight: 500;

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
`;
