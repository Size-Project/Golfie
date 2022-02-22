import React from 'react';
import styled, { css } from 'styled-components';
import FeedAll from 'app.feature/feed/component/FeedAll/FeedAll';
import FeedMy from 'app.feature/feed/component/FeedMy/FeedMy';

const FeedTabPane = ({ tabKey, getUser }) => {
  return (
    <StyledWrapper stage={tabKey}>
      <div className="stage-view">
        <div className={`stage stage-${tabKey}`}>
          <div className="stage-child">{tabKey === 0 && <FeedAll />}</div>
        </div>
        {getUser.login && (
          <div className={`stage stage-${tabKey}`}>
            <div className="stage-child">{tabKey === 1 && <FeedMy />}</div>
          </div>
        )}
      </div>
    </StyledWrapper>
  );
};

export default FeedTabPane;

export const StyledWrapper = styled.div`
  ${({ stage }) => css`
    position: relative;
    height: 100%;
    flex: 0 0 auto;
    overflow: hidden;

    .stage-view {
      position: relative;
      height: 100%;
      display: flex;
      flex-flow: row;
      flex-wrap: nowrap;
      transition-duration: 0.2s;
      transform: translateX(-${stage}00%);

      .stage {
        position: relative;
        width: 100%;
        height: 100%;
        flex: 0 0 auto;
        overflow: hidden;
        overflow-y: auto;
        -webkit-overflow-scrolling: touch;

        &::-webkit-scrollbar {
          display: none;
        }

        .stage-child {
          position: relative;
        }
      }
    }
  `}
`;
