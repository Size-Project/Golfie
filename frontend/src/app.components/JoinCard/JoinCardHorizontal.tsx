import React, { useState } from 'react';
import styled from 'styled-components';
import SVGSchedule from '../../../public/images/svg/tag-schedule.svg';
import SVGCash from '../../../public/images/svg/tag-cash.svg';
import SVGHeart from '../../../public/images/svg/ic-heart.svg';
import SVGUser from '../../../public/images/svg/ic-user.svg';

const JoinCardHorizontal = () => {
  const [like, setLike] = useState(false);

  const handleJoinLike = () => {
    setLike(!like);
  };

  return (
    <StyledWrapper>
      <div className="card-image">
        <div className="card-bottom">
          <div className="card-bottom-left">
            <div className="user-count-wrap">
              <div className="user-count">
                <SVGUser />
                <div className="count">4</div>
              </div>
            </div>
            <div className="field-info">
              <b>파크밸리cc | </b>강원 원주
            </div>
          </div>
          <div className={`join-like ${String(like)}`}>
            <SVGHeart onClick={handleJoinLike} />
          </div>
        </div>
      </div>
      <div className="card-content">
        <div className="content-tag-wrap">
          <div className="content-desc-tag">
            <div>
              <SVGSchedule />
            </div>
            <div>01.02 11:00</div>
          </div>
          <div className="content-desc-tag">
            <div>
              <SVGCash />
            </div>
            <div>70,000 ₩</div>
          </div>
        </div>
        <div className="content-hash-tag"># 실력 상관없이 누구나</div>
      </div>
    </StyledWrapper>
  );
};

export default JoinCardHorizontal;

const StyledWrapper = styled.div`
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

      .join-like {
        display: flex;
        align-items: end;
        transition: 200ms;
        &.true {
          svg {
            fill: var(--color-main);
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
