import React from 'react';
import styled from 'styled-components';
import Select from 'app.components/Select/Select';
import useQueryFn from 'app.hooks/useQueryFn';
import { API_COURSES } from 'app.modules/api/fieldtrip.join';
import { useFormContext } from 'react-hook-form';

const JoinCreateStepOne = ({ setStep }) => {
  const { register, watch } = useFormContext();
  const { isLoading, data = [] } = useQueryFn(API_COURSES);

  if (isLoading) return null;
  return (
    <StyledWrapper>
      <div className="step-one-title">
        <div className="main-title">Step 01.</div>
        <div className="sub-title">
          조인에 필요한 필수적인 라운딩 정보를 입력해주세요.
        </div>
      </div>
      <div className="step-one-form">
        <div className="form-item-wrap">
          <div className="form-item-label">
            <img src="/images/Join/map-pin@3x.png" />
            <div className="label-text">골프장</div>
          </div>
          <Select
            name="courseName"
            style={{ width: '100%' }}
            options={data.map((item) => item.name)}
            placeholder="골프장을 선택해주세요."
          />
        </div>
        <div className="form-item-wrap">
          <div className="form-item-label">
            <img src="/images/Join/calendar@3x.png" />
            <div className="label-text">날짜</div>
          </div>
          <Select
            name="dateYear"
            options={Array.from(
              { length: 5 },
              (v, i) => new Date().getFullYear() + i
            )}
            placeholder="년"
          />
          <Select
            name="dateMonth"
            options={Array.from({ length: 12 }, (v, i) => i + 1)}
            placeholder="월"
          />
          <Select
            name="dateDay"
            options={Array.from(
              {
                length: new Date(
                  Number(watch().dateYear),
                  !watch()?.dateMonth ? Number(watch().dateMonth) : 1,
                  0
                ).getDate(),
              },
              (v, i) => i + 1
            )}
            placeholder="일"
          />
        </div>
        <div className="form-item-wrap">
          <div className="form-item-label">
            <img src="/images/Join/alarm-outline@3x.png" />
            <div className="label-text">시간</div>
          </div>
          <Select
            name="timeHour"
            options={Array.from(
              {
                length: 12,
              },
              (v, i) => i + 1
            )}
            placeholder="시간"
          />
          <Select
            name="timeMinute"
            options={Array.from(
              {
                length: 60,
              },
              (v, i) => i
            )}
            placeholder="분"
          />
          <Select
            name="timeDay"
            options={['오전', '오후']}
            defaultValue={['오전']}
            placeholder="오전 / 오후"
          />
        </div>
        <div className="form-item-wrap">
          <div className="form-item-label">
            <img src="/images/Join/user@3x.png" />
            <div className="label-text">인원수</div>
          </div>
          <Select
            name="joinNum"
            options={Array.from(
              {
                length: 8,
              },
              (v, i) => i + 1
            )}
            placeholder="명"
          />
        </div>
        <div className="form-item-wrap">
          <div className="form-item-label">
            <img src="/images/Join/cash-outline@3x.png" />
            <div className="label-text">가격</div>
          </div>
          <input
            {...register('price')}
            type="number"
            placeholder="가격을 입력하세요."
          />
        </div>
        <div className="form-item-wrap">
          <div className="form-item-label">
            <img src="/images/Join/message-square@3x.png" />
            <div className="label-text">제목 & 메모</div>
          </div>
          <input
            {...register('title')}
            style={{ marginBottom: '10px', width: '100%' }}
            placeholder="제목을 입력하세요."
          />
          <textarea
            {...register('content')}
            placeholder="이번 조인에 대한 내용 or  요청 사항을 입력하세요."
          />
        </div>
      </div>
      <div className="next-step-button-wrap">
        <div
          className="next-step-button"
          onClick={() => {
            if (
              !!watch()?.courseName &&
              !!watch()?.dateYear &&
              !!watch()?.dateMonth &&
              !!watch()?.dateDay &&
              !!watch()?.timeHour &&
              !!watch()?.timeMinute &&
              !!watch()?.timeDay &&
              !!watch()?.joinNum &&
              !!watch()?.title &&
              !!watch()?.content
            )
              setStep(2);
          }}
        >
          다음 단계
        </div>
      </div>
    </StyledWrapper>
  );
};

export default JoinCreateStepOne;

const StyledWrapper = styled.div`
  padding: 30px 20px 0px;

  .step-one-title {
    margin-bottom: 24px;
    .main-title {
      font-family: AcuminPro;
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 4px;
      color: var(--color-pri);
    }

    .sub-title {
      font-size: 14px;
      line-height: 20px;
    }
  }

  .step-one-form {
    .form-item-wrap {
      width: 100%;
      margin-bottom: 20px;

      .form-item-label {
        display: flex;
        align-items: center;
        margin-bottom: 10px;

        img {
          margin-right: 6px;
          width: 12px;
        }

        .label-text {
          font-size: 14px;
          font-weight: 500;
          line-height: 20px;
        }
      }

      input {
        padding: 10px;
        height: 40px;
        border-radius: 5px;
        border: solid 1px #6dd094;
      }

      textarea {
        padding: 10px;
        width: calc(100% - 22px);
        display: block;
        height: 80px;
        border-radius: 5px;
        border: solid 1px #6dd094;
      }
    }
  }

  .next-step-button-wrap {
    width: 100%;
    bottom: 35px;
    position: fixed;
    left: 0;

    .next-step-button {
      width: 80%;
      border-radius: 20px;
      background-color: var(--color-sub);
      color: var(--color-white);
      text-align: center;
      margin: 0 auto;
      line-height: 20px;
      padding: 10px 0;
    }
  }
`;
