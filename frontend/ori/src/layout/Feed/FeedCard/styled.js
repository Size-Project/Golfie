import styled from 'styled-components';

export const StyledFeedCard = styled.div`
  margin-bottom: 50px;

  .content-wrap {
    border-radius: 15px;
    box-shadow: 0 5px 15px 0 rgba(0, 0, 0, 0.1);

    .content-text-wrap {
      .content-text {
        padding: 0 15px;
      }

      .content-detail-wrap {
        padding: 15px;
      }
    }
  }
`;

export const StyledFeedCardUser = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;

  .user-info-wrap {
    display: flex;
    align-items: center;

    .user-image {
      width: 45px;
      height: 45px;
      border-radius: 50%;
      margin-right: 10px;
      overflow: hidden;
      box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);

      img {
        object-fit: contain;
        width: 100%;
        height: 100%;
      }
    }

    .user-info {
      font-weight: 500;

      .user-name {
        font-size: 16px;
        color: var(--color-black);
        line-height: 20px;
      }

      .user-detail {
        font-size: 12px;
        color: var(--color-gray-300);
        line-height: 20px;
      }
    }
  }

  .user-follow {
    border-radius: 14px;
    box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.15);
    width: 80px;
    height: 28px;
    text-align: center;
    font-size: 12px;
    line-height: 28px;

    &.true {
      color: var(--color-sub);
      background-color: var(--color-white);
    }

    &.false {
      color: var(--color-white);
      background-color: var(--color-main);
    }
  }
`;

export const StyledFeedCardImage = styled.div`
  width: 100%;
  position: relative;

  .img-bottom {
    z-index: 9;
    padding: 0 15px;
    width: 100%;
    height: 42px;
    display: flex;
    position: absolute;
    bottom: 0;
    align-items: center;
    justify-content: space-between;

    .img-bottom-left {
      display: flex;
      align-items: center;

      .count-wrap {
        margin-right: 6px;
        border-radius: 10.5px;
        background-color: #ededed;
        height: 21px;
        line-height: 21px;
        font-size: 12px;
        color: var(--color-gray-300);
        padding: 0 8px;
        display: flex;
        align-items: center;
        .count-img {
          margin-right: 5px;
          height: 100%;

          svg {
            width: 9px;
          }
        }
      }
    }

    .bookmark-img {
      &.false {
        svg {
          path {
            fill: var(--color-gray-200);
          }
        }
      }

      &.true {
        svg {
          path {
            fill: var(--color-main);
          }
        }
      }
    }
  }

  .swiper-pagination {
    width: 30%;
    margin: 0 calc(35%);
    align-self: center;
    justify-self: center;
    position: absolute;
    bottom: 13px !important;

    .swiper-pagination-bullet {
      width: 8px;
      height: 8px;
      margin: 0 3.5px !important;
    }

    .swiper-pagination-bullet-active {
      background-color: var(--color-main);
    }
  }

  .content-image-wrap {
    width: 100%;
    border-radius: 15px 15px 0 0;
    overflow: hidden;

    .content-image {
      position: relative;
      width: 100%;
      height: 335px;
      overflow: hidden;

      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }

    .content-etc {
      height: 42px;
    }
  }

  .location-wrap {
    color: var(--color-white);
    position: absolute;
    bottom: 6px;
    left: 6px;
    width: calc(100% - 12px);
    padding: 12px 17px;
    border-radius: 8px;
    background: rgba(255, 255, 255, 0.5);
    display: flex;
    justify-content: space-between;
    align-items: center;

    .location-left {
      display: flex;
      align-items: center;

      .location-image {
        margin-right: 10px;
      }

      .location-info {
        line-height: 18px;

        .location-title {
          font-weight: bold;
          font-size: 14px;
        }

        .location-desc {
          font-size: 12px;
        }
      }
    }
  }
`;

export const StyledFeedCardContent = styled.div`
  border-radius: 15px;

  .content-text {
    padding: 0 15px;
    font-size: 14px;
    line-height: 20px;
    color: var(--color-black);
  }

  .content-detail-wrap {
    padding: 15px;

    .content-detail-left {
      display: flex;

      .content-like {
        margin-right: 15px;
        svg {
          path {
            fill: var(--color-gray-200);
          }
        }

        &.true {
          svg {
            path {
              fill: var(--color-main);
            }
          }
        }
      }

      .content-comment {
        svg {
          path {
            fill: var(--color-gray-200) !important;
          }
        }
      }
    }
  }
`;
