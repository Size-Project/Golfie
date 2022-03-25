import React, { useState } from 'react';
import styled from 'styled-components';
import JoinCardVertical from 'app.components/JoinCard/JoinCardVertical';
import { useRouter } from 'next/router';

const JoinNewRecommend = ({ roundingData }) => {
  const router = useRouter();
  const [active, setActive] = useState('week');

  const handleChangeActive = (key) => {
    setActive(key);
  };

  const data = {
    course: {
      id: 1,
      name: '수원 C.C',
      address: '경기도 수원시',
    },
    style: {
      ageRange: '20-30',
      averageHit: '100-120',
      mood: '편안한',
    },
    host: {
      id: '3',
      nickname: 'john',
      imageUrl: 'imageUrl.com',
      ageRange: '40-49',
      gender: 'MALE',
      job: 'job',
      averageHit: 110,
    },
    attendees: [
      {
        id: '5',
        nickname: '진아',
        imageUrl:
          'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',
        ageRange: '20-29',
        gender: 'FEMALE',
        job: '대학생',
        averageHit: 100,
      },
    ],
    title: '놀러오세요.',
    content: '내용입니다.',
    price: 100000,
    joinNum: 4,
    dateTime: '2022-03-17T13:15:01.505724',
  };
  console.log(roundingData);
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
          {roundingData &&
            roundingData?.map((item, idx) => (
              <JoinCardVertical
                data={item}
                key={idx}
                onClick={() => router.push(`/join/detail?id=${idx + 1}`)}
              />
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
