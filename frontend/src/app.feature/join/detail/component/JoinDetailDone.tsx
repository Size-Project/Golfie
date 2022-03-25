import React from 'react';
import styled from 'styled-components';

const JoinDetailDone = ({ joinDetailData }) => {
  const { attendees, joinNum } = joinDetailData;

  return (
    <StyledWrapper>
      <div className="join-done-header">조인 완료</div>
      <div className="join-done-wrap">
        {attendees &&
          attendees.map((done, doneIdx) => (
            <div className="done-item" key={doneIdx}>
              <div className="user-img">
                <img src={done.imageUrl} />
              </div>
              <div className="user-info">
                {done.nickname} / {done.ageRange}세
              </div>
            </div>
          ))}
        {attendees.length < joinNum && (
          <div className="undo-item">
            <div className="user-img">
              <div className="user-img">
                <img src="/images/svg/plus.svg" />
              </div>
            </div>
            <div className="user-info">친구에게 공유하기</div>
          </div>
        )}
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

    .done-item,
    .undo-item {
      .user-img {
        margin: 0 auto 6px;
        max-height: 48px;
        max-width: 48px;
        min-width: 48px;
        min-height: 48px;
        border-radius: 50%;
        overflow: hidden;
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
      }

      .user-info {
        font-size: 8px;
        font-weight: 500;
      }
    }

    .undo-item {
      object-fit: contain;
      .user-img {
        background-color: var(--color-gray-100);
        box-shadow: none;

        img {
          padding: 9px;
          width: 48px;
        }
      }
    }
  }
`;
