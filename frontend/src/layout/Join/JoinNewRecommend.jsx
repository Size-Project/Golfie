import React, { useState } from 'react';
import { StyledJoinNewRecommend } from './styled';
import JoinCardVertical from '../../component/JoinCard/JoinCardVertical';

const JoinNewRecommend = () => {
  const [active, setActive] = useState('week');

  const handleChangeActive = (key) => {
    setActive(key);
  };
  return (
    <StyledJoinNewRecommend>
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
    </StyledJoinNewRecommend>
  );
};

export default JoinNewRecommend;
