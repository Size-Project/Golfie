import styled from 'styled-components';

export const StyledJoinCardHorizontal = styled.div`
  border-radius: 8px;
  position: relative;
  width: 172px !important;
  min-width: 172px !important;
  height: 100%;
  padding: 0;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);

  &:first-child {
    margin-left: 20px;
  }

  &:last-child {
    margin-right: 20px;
  }

  .card-image {
    display: flex;
    align-items: end;
    padding: 10px 15px;
    width: 100%;
    height: 138px !important;
    min-height: 138px !important;
    background-color: gray;

    .card-bottom {
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-items: end;
      color: var(--color-white);

      svg {
        width: 18.3px;
        height: 16.9px;
        fill: var(--color-white);
      }

      .card-bottom-left {
        .user-count-wrap {
          max-height: 16px;
        }

        .user-count {
          display: flex;
          align-items: center;
          height: 16px;
          width: min-content;
          margin-bottom: 2px;
          border-radius: 8px;
          padding: 0 6px;
          background-color: var(--color-white);
          opacity: 0.8;

          svg {
            width: 10px;
            margin-right: 1px;
          }

          .count {
            color: var(--color-gray-300);
            font-size: 6px !important;
            font-weight: 500;
          }
        }

        .field-info {
          line-height: 15px;
          font-size: 10px;

          b {
            font-weight: 500;
          }
        }
      }
    }
  }

  .card-content {
    padding: 12px 15px;
    font-size: 10px;
    color: var(--color-black);
    font-family: 'Acumin Pro';

    .content-tag-wrap {
      display: flex;
      flex-wrap: wrap;
      margin-bottom: 4px;

      .content-desc-tag {
        svg {
          margin-right: 4px;
        }

        display: flex;
        align-items: center;
        font-size: 10px;
        margin-right: 10px;
      }
    }
    .content-hash-tag {
      line-height: 15px;
      font-weight: bold;
    }
  }
`;

export const StyledJoinCardVertical = styled.div`
  border-radius: 10px;
  box-shadow: 0 5px 15px 0 rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
  display: flex;

  :before {
    content: '';
    border-radius: 10px 0 0 10px;
    display: block;
    width: 20px;
    min-width: 20px;
    height: auto;
    background-color: var(--color-main);
  }

  .card-content-wrap {
    display: flex;
    gap: 14px;
    padding: 12px 10px;
    width: 100%;

    .user-img {
      border-radius: 50%;
      width: 36px;
      height: 36px;
      min-width: 36px;
      box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
    }

    .user-name {
      line-height: 11px;
      margin-bottom: 1px;
      font-size: 8px;
      color: var(--color-gray-200);
    }

    .join-title {
      font-size: 14px;
      font-weight: 500;
      line-height: 20px;
      margin-bottom: 6px;
    }

    .join-tag-wrap {
      display: flex;
      flex-wrap: wrap;

      .join-tag {
        svg {
          margin-right: 4px;
        }

        margin-right: 10px;
        font-weight: 500;
        font-size: 10px;
        display: flex;
        align-items: center;
      }

      .location,
      .user {
        font-family: 'Noto Sans';

        span {
          color: var(--color-gray-300);
        }
      }

      .schedule,
      .cash {
        font-family: 'Acumin Pro';
      }
    }
  }
`;
