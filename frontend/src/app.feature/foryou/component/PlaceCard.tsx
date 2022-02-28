import React from 'react';
import styled from 'styled-components';
import useGetUser from 'app.hooks/useGetUser';

const PlaceCard = () => {
  return (
    <StyledWrapper>
      <div className="place-img-wrap">
        <div className="place-img" />
        <div className="join-info-wrap">
          <div className="join-info">
            <div className="user-img"></div>
            <div className="join-main">
              <div className="author">임현주님 주최</div>
              <div className="title">골린이를 위한 조인</div>
            </div>
          </div>
          <div className="join-date">D-04</div>
        </div>
      </div>
      <div className="place-detail-wrap">
        <div className="detail-item-wrap">
          <img src="/images/ForYou/locationIcon.png" />
          <div className="detail-item-text">파크밸리cc | 강원 원주</div>
        </div>
        <div className="detail-item-wrap">
          <img src="/images/ForYou/calendarIcon.png" />
          <div className="detail-item-text">Jan 02 AM 11:00</div>
        </div>
        <div className="detail-item-wrap">
          <img src="/images/ForYou/ballonIcon.png" />
          <div className="detail-item-text">골린이라고 두려워하지 말아요!</div>
        </div>
      </div>
    </StyledWrapper>
  );
};

export default PlaceCard;

const StyledWrapper = styled.div`
  border-radius: 10px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  background-color: var(--white);
  padding: 10px 10px;

  .place-img-wrap {
    margin-bottom: 12px;
    background-color: var(--color-main);
    border-radius: 10px;
    height: 117px;
    position: relative;

    .join-info-wrap {
      width: 100%;
      position: absolute;
      bottom: 0;
      padding: 10px;
      display: flex;
      align-items: center;
      justify-content: space-between;

      .join-info {
        display: flex;

        .user-img {
          border-radius: 50%;
          overflow: hidden;
          width: 32px;
          height: 32px;
          box-shadow: inset 0 3px 6px 0 rgba(0, 0, 0, 0.16);
          margin-right: 8px;
        }

        .join-main {
          color: var(--color-white);

          .author {
            font-size: 8px;
            line-height: 11px;
            margin-bottom: 1px;
          }

          .title {
            font-size: 14px;
            line-height: 20px;
            font-weight: bold;
          }
        }
      }

      .join-date {
        background: var(--color-lemon);
        color: var(--color-gray-300);
        border-radius: 9px;
        font-size: 10px;
        font-weight: 500;
        padding: 5px 8px;
        font-family: 'Acumin Pro';
      }
    }
  }

  .place-detail-wrap {
    display: flex;
    flex-wrap: wrap;

    .detail-item-wrap {
      display: flex;
      margin-bottom: 4px;
      margin-right: 10px;
      font-size: 10px;
      font-weight: 500;

      img {
        margin-right: 4px;
        width: 14px;
        height: 14px;
      }
    }
  }
`;
