import React from 'react';
import styled from 'styled-components';
import SVGLocation from '../../../public/images/svg/tag-location.svg';
import SVGSchedule from '../../../public/images/svg/tag-schedule.svg';
import SVGCash from '../../../public/images/svg/tag-cash.svg';
import SVGUser from '../../../public/images/svg/tag-user.svg';
import moment from 'moment';

const JoinCardVertical = ({ data, onClick }) => {
  const { course, host, title, dateTime, price, joinNum } = data;
  return (
    <StyledWrapper onClick={onClick}>
      <div className="card-left" />
      <div className="card-content-wrap">
        <div className="user-img">
          <img src={host.imageUrl} alt="" />
        </div>
        <div className="info-wrap">
          <div className="user-name">{host.nickname}님 주최</div>
          <div className="join-title">{title}</div>
          <div className="join-tag-wrap">
            <div className="join-tag location">
              <div>
                <SVGLocation />
              </div>
              <div>
                {course.name} | <span>{course.address}</span>
              </div>
            </div>
            <div className="join-tag schedule">
              <div>
                <SVGSchedule />
              </div>
              <div>{moment(dateTime).format('MMMM.DD A hh:mm')}</div>
            </div>
            <div className="join-tag cash">
              <div>
                <SVGCash />
              </div>
              <div>{price.toLocaleString()} ₩</div>
            </div>
            <div className="join-tag user">
              <div>
                <SVGUser />
              </div>
              <div>{joinNum}명</div>
            </div>
          </div>
        </div>
      </div>
    </StyledWrapper>
  );
};

export default JoinCardVertical;

const StyledWrapper = styled.div`
  border-radius: 10px;
  box-shadow: 0 5px 15px 0 rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
  display: flex;

  :before {
    content: '';
    border-radius: 10px 0 0 10px;
    display: block;
    width: 20px;
    min-width: 20px;
    height: auto;
    background-color: var(--color-main);
  }

  .card-content-wrap {
    display: flex;
    gap: 14px;
    padding: 12px 10px;
    width: 100%;

    .user-img {
      overflow: hidden;
      border-radius: 50%;
      width: 36px;
      height: 36px;
      min-width: 36px;
      box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
    }

    .user-name {
      line-height: 11px;
      margin-bottom: 1px;
      font-size: 8px;
      color: var(--color-gray-200);
    }

    .join-title {
      font-size: 14px;
      font-weight: 500;
      line-height: 20px;
      margin-bottom: 6px;
    }

    .join-tag-wrap {
      display: flex;
      flex-wrap: wrap;

      .join-tag {
        svg {
          margin-right: 4px;
        }

        margin-right: 10px;
        font-weight: 500;
        font-size: 10px;
        display: flex;
        align-items: center;
      }

      .location,
      .user {
        font-family: 'Noto Sans';

        span {
          color: var(--color-gray-300);
        }
      }

      .schedule,
      .cash {
        font-family: 'Acumin Pro';
      }
    }
  }
`;
