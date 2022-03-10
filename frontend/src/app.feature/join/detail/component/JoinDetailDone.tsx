import React from 'react';
import styled from 'styled-components';

const JoinDetailDone = () => {
  return (
    <StyledWrapper>
      <div className="join-done-header">조인 완료</div>
      <div className="join-done-wrap">
        {[1, 2, 3].map((done, doneIdx) => (
          <div className="done-item" key={doneIdx}>
            <div className="user-img"></div>
            <div className="user-info">현유진 / 26세</div>
          </div>
        ))}
      </div>
    </StyledWrapper>
  );
};

export default JoinDetailDone;

const StyledWrapper = styled.div`
  padding: 0 20px;
  margin-bottom: 30px;

  .join-done-header {
    font-size: 18px;
    font-weight: 500;
    line-height: 26px;
    margin-bottom: 11px;
  }

  .join-done-wrap {
    border-radius: 10px;
    box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
    padding: 14px;
    display: flex;
    gap: 20px;

    .done-item {
      .user-img {
        margin: 0 auto 6px;
        max-height: 48px;
        max-width: 48px;
        min-width: 48px;
        min-height: 48px;
        border-radius: 50%;
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
      }

      .user-info {
        font-size: 8px;
        font-weight: 500;
      }
    }
  }
`;
