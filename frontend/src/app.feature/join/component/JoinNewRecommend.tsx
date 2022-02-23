import React, { useState } from 'react';
import styled from 'styled-components';
import JoinCardVertical from 'app.components/JoinCard/JoinCardVertical';

const JoinNewRecommend = () => {
  const [active, setActive] = useState('week');

  const handleChangeActive = (key) => {
    setActive(key);
  };
  return (
    <StyledWrapper>
      <div className="top-title">
        <span className="bold">New&nbsp;</span>
        <span>추천</span>
      </div>
      <div className="new-recommend-wrap">
        <div className="tab-title-wrap">
          <div
            className={`tab-item ${active === 'week'}`}
            onClick={() => handleChangeActive('week')}
          >
            일주일 내
          </div>
          <div
            className={`tab-item ${active === 'month'}`}
            onClick={() => handleChangeActive('month')}
          >
            한달 내
          </div>
        </div>
        <div className="tab-pane-wrap">
          {[1, 2, 3].map((item, idx) => (
            <JoinCardVertical key={idx} />
          ))}
        </div>
      </div>
    </StyledWrapper>
  );
};

export default JoinNewRecommend;

const StyledWrapper = styled.div`
  padding: 30px 20px 0;

  .top-title {
    display: flex;
    align-items: end;
    font-size: 20px;
    font-family: 'Acumin Pro';
    margin-bottom: 10px;

    .bold {
      font-weight: bold;
      font-size: 22px !important;
    }
  }

  .new-recommend-wrap {
    .tab-title-wrap {
      margin-bottom: 20px;
      display: flex;
      gap: 8px;

      .tab-item {
        padding: 8px 12px;
        border-radius: 15px;
        font-size: 14px;
        font-weight: 500;
        color: var(--color-white);
        cursor: pointer;
        transition: 200ms;

        &.true {
          background-color: var(--color-sub);
          color: var(--color-white);
        }

        &.false {
          color: var(--color-gray-200);
          background-color: var(--color-gray-100);
        }
      }
    }
  }
`;
