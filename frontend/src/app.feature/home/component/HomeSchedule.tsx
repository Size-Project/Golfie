import React from 'react';
import styled from 'styled-components';
import SVGLocation from '../../../../public/images/svg/tag-location.svg';
import SVGSchedule from '../../../../public/images/svg/tag-schedule.svg';
import SVGMessage from '../../../../public/images/svg/tag-message.svg';

const HomeSchedule = () => {
  return (
    <StyledWRapper>
      <div className="schedule-title">Join schedule</div>
      <ul className="schedule-list-wrap">
        {[1, 2, 3].map((item, idx) => (
          <li key={idx} className="schedule-list-item">
            <div className="profile-img" />
            <div className="join-content">
              <div className="join-user">임현주님 주최</div>
              <div className="join-title">골린이를 위한 조인</div>
              <div className="join-info">
                <div className="info-desc">
                  <div>
                    <SVGLocation />
                    파크벨리cc&nbsp;|&nbsp;<span>강원 원주</span>
                  </div>
                  <div>
                    <SVGSchedule />
                    Jan.02 AM 11:00
                  </div>
                </div>
                <div className="info-desc">
                  <SVGMessage />
                  골린이라고 두려워하지 말아요!
                </div>
              </div>
            </div>
          </li>
        ))}
      </ul>
    </StyledWRapper>
  );
};

export default HomeSchedule;

const StyledWRapper = styled.div`
  padding: 0 20px;
  margin-bottom: 50px;

  .schedule-title {
    font-family: 'Co Text';
    font-size: 22px;
    line-height: 28px;
    margin-bottom: 20px;
    color: var(--color-black);
  }

  .schedule-list-wrap {
    .schedule-list-item {
      display: flex;
      margin-bottom: 15px;

      &:last-child {
        margin-bottom: 0;
      }

      .profile-img {
        margin: 10px 15px 0 0;
        min-width: 45px;
        min-height: 45px;
        height: 45px;
        width: 45px;
        border-radius: 50%;
        box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
      }

      .join-content {
        width: 100%;
        padding: 12px 15px 10px;
        border-radius: 10px;
        box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.16);

        .join-user {
          font-size: 8px;
          color: var(--color-gray-200);
          margin-bottom: 1px;
        }

        .join-title {
          line-height: 20px;
          font-size: 14px;
          font-weight: bold;
          color: var(--color-black);
          margin-bottom: 5px;
        }

        .join-info {
          .info-desc {
            margin-bottom: 4px;
            display: flex;
            align-items: center;
            font-size: 10px;
            line-height: 15px;
            color: var(--color-black);

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
  }
`;
