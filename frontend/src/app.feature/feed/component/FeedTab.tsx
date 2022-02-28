import React from 'react';
import styled, { css } from 'styled-components';

const FeedTab = ({ tabKey, setTabKey, getUser }) => {
  const handleTabKey = (key) => {
    setTabKey(key);
  };

  return (
    <StyledWrapper stage={tabKey} login={getUser.login}>
      <div className="tabs-title-wrap">
        <div
          className={`tabs-title ${String(tabKey === 0)}`}
          onClick={() => handleTabKey(0)}
        >
          둘러보기
        </div>
        {getUser.login && (
          <div
            className={`tabs-title ${String(tabKey === 1)}`}
            onClick={() => handleTabKey(1)}
          >
            내 피드
          </div>
        )}
      </div>
    </StyledWrapper>
  );
};

export default FeedTab;

const StyledWrapper = styled.div`
  ${({ stage, login }) => css`
    padding: 20px 20px 30px;
    //TO Do fontFamily
    .tabs-title-wrap {
      width: 100%;
      display: flex;

      .tabs-title {
        cursor: pointer;
        width: ${login ? '50%' : '100%'};
        text-align: center;
        padding-bottom: 5px;

        &.true {
          color: var(--color-main);
          font-weight: bold;
        }
      }
    }

    &:after {
      content: '';
      display: block;
      width: ${login ? '50%' : '100%'};
      height: 1px;
      background-color: var(--color-main);
      transform: translateX(${stage}00%);
      transition-duration: 0.2s;
    }
  `}
`;
