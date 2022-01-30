import React from 'react';
import { StyledJoinCardHorizontal } from './styled';
import { ReactComponent as SVGSchedule } from '../../assets/images/svg/tag-schedule.svg';
import { ReactComponent as SVGCash } from '../../assets/images/svg/tag-cash.svg';
import { ReactComponent as SVGHeart } from '../../assets/images/svg/ic-heart.svg';
import { ReactComponent as SVGUser } from '../../assets/images/svg/ic-user.svg';

const JoinCardHorizontal = () => {
  return (
    <StyledJoinCardHorizontal>
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
          <SVGHeart />
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
    </StyledJoinCardHorizontal>
  );
};

export default JoinCardHorizontal;
