import React from 'react';
import styled from 'styled-components';
import { useRouter } from 'next/router';

const ForyouTag = () => {
  const router = useRouter();

  return (
    <StyledWrapper>
      <div className="tag-wrap">
        <div className="tag-item HEALING">
          <div className="tag-item-text">#HEALING</div>
        </div>
        <div className="tag-item TASTE">
          <div className="tag-item-text">#TASTE</div>
        </div>
        <div className="tag-item ACTIVITY">
          <div className="tag-item-text">#ACTIVITY</div>
        </div>
        <div className="tag-item CHATTY">
          <div className="tag-item-text">#CHATTY</div>
        </div>
      </div>
      <div className="button-wrap">
        <button
          className="all-button"
          onClick={() => router.push('/foryou/detail')}
        >
          전체보기
        </button>
      </div>
    </StyledWrapper>
  );
};

export default ForyouTag;

const StyledWrapper = styled.div`
  padding: 55px 20px 0;

  .tag-wrap {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    margin-bottom: 20px;

    .tag-item {
      position: relative;
      width: 100%;
      height: 100%;
      padding: 50%;
      background-repeat: no-repeat;

      .tag-item-text {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-family: 'Co Text';
        font-size: 18px;
        color: var(--color-white);
      }

      &.HEALING {
        background: url('/images/ForYou/HEALING.png');
      }

      &.TASTE {
        background: url('/images/ForYou/TASTE.png');
      }

      &.ACTIVITY {
        background: url('/images/ForYou/ACTIVITY.png');
      }

      &.CHATTY {
        background: url('/images/ForYou/CHATTY.png');
      }
    }
  }

  .button-wrap {
    width: 100%;

    .all-button {
      display: block;
      margin: 0 auto;
      color: var(--color-white);
      width: 174px;
      height: 37px;
      box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
      background: var(--color-main);
      border-radius: 18px;
    }
  }
`;
