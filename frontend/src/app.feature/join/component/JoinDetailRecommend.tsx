import React from 'react';
import styled from 'styled-components';
import SVGHeart from '../../../../public/images/svg/menu-join.svg';
import SVGLocation from '../../../../public/images/svg/tag-location.svg';

const JoinDetailRecommend = () => {
  return (
    <StyledWrapper>
      <div className="top-title">
        영주님이 좋아하는
        <br />
        이런 <b>장소</b>는 어때요?
      </div>
      <div className="location-grid-wrap">
        {[1, 2, 3, 4, 5, 6].map((item, idx) => (
          <div key={idx} className="location-grid-item">
            <div className="location-img-wrap">
              <SVGHeart />
            </div>

            <div className="location-info">
              <div className="location-title">사려니 숲길 산책로</div>
              <div className="location-desc">
                <div>
                  <SVGLocation />
                </div>
                <div className="desc-text">
                  파크밸리cc<span>&nbsp;|&nbsp;강원 원주</span>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </StyledWrapper>
  );
};

export default JoinDetailRecommend;

export const StyledWrapper = styled.div`
  padding: 0 20px;

  .top-title {
    margin-bottom: 20px;
    line-height: 25px;
    font-size: 18px;
    font-weight: 500;
  }

  .location-grid-wrap {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 20px;

    .location-grid-item {
      .location-img-wrap {
        position: relative;
        padding: 50% 0;
        border-radius: 8px;
        box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.16);
        margin-bottom: 10px;

        svg {
          right: 10px;
          bottom: 10px;
          position: absolute;
          width: 18.5px;
          height: 17px;
          fill: var(--color-main);
        }
      }

      .location-info {
        padding: 0 5px;

        .location-title {
          margin-bottom: 5px;
          line-height: 20px;
          font-size: 14px;
          font-weight: bold;
        }

        .location-desc {
          display: flex;
          align-items: center;
          font-size: 10px;
          font-weight: 500;

          div {
            display: flex;
            align-items: center;
          }

          span {
            color: var(--color-gray-300);
          }
          svg {
            margin-right: 4px;
          }
        }
      }
    }
  }
`;
