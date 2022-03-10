import React from 'react';
import styled from 'styled-components';

const JoinDetailAuthor = () => {
  return (
    <StyledWrapper>
      <div className="join-tag-wrap">
        {[1, 2, 3, 5].map((tag, tagIdx) => (
          <div className="join-tag" key={tagIdx}>
            20-25세
          </div>
        ))}
      </div>
      <div className="author-wrap">
        <div className="author-img"></div>
        <div className="author-right">
          <div className="author-info-wrap">
            <div className="author-info">
              <div className="author-name">지영주</div>
              <div className="author-profile">26세 / 회사원 / 100타</div>
            </div>
            <div className="author-question">
              <div className="question-background" />
              <img src="/images/Join/message-square@3x.png" />
              <div className="question-text">질문하기</div>
            </div>
          </div>
          <div className="join-message">
            골린이들끼리 열심히 치고 밥도 먹고 주변 예쁜카페도 함께 가실분이면
            좋겠어요!
          </div>
        </div>
      </div>
    </StyledWrapper>
  );
};

export default JoinDetailAuthor;

const StyledWrapper = styled.div`
  padding: 0 20px;
  margin-bottom: 30px;

  .join-tag-wrap {
    display: flex;
    align-items: center;
    gap: 6px;
    margin-bottom: 11px;

    .join-tag {
      border-radius: 13.5px;
      color: var(--color-white);
      font-size: 12px;
      font-weight: 500;
      line-height: 17px;
      background-color: var(--color-sub);
      padding: 4px 12px;
    }
  }

  .author-wrap {
    border-radius: 10px;
    border: solid 1px var(--color-sub);
    padding: 15px;
    display: flex;
    width: 100%;

    .author-img {
      max-height: 48px;
      max-width: 48px;
      min-width: 48px;
      min-height: 48px;
      margin-right: 10px;
      border-radius: 50%;
      box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
    }

    .author-right {
      .author-info-wrap {
        width: 100%;
        margin-bottom: 4px;
        display: flex;
        justify-content: space-between;
        align-items: center;

        .author-info {
          display: flex;
          align-items: center;

          .author-name {
            font-size: 16px;
            font-weight: 500;
            margin-right: 6px;
          }

          .author-profile {
            font-size: 12px;
            font-weight: 500;
            color: var(--color-gray-300);
          }
        }

        .author-question {
          width: 66px;
          height: 21px;
          padding: 4px 9px;
          color: var(--color-sub);
          font-size: 10px;
          font-weight: 500;
          display: flex;
          align-items: center;
          position: relative;

          .question-background {
            top: 0;
            left: 0;
            width: 66px;
            height: 21px;
            content: '';
            position: absolute;
            border-radius: 10.5px;
            z-index: -1;
            opacity: 0.3;
            background-color: var(--color-sub);
          }

          img {
            width: 8.8px;
            height: 8.8px;
            margin-right: 3px;
          }
        }
      }

      .join-message {
        font-size: 10px;
        font-weight: 500;
        color: var(--color-gray-200);
      }
    }
  }
`;
