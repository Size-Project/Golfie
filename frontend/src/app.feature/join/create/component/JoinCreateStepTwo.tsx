import React from 'react';
import styled from 'styled-components';
import { useFormContext } from 'react-hook-form';
import SignupSelect from 'app.feature/account/signup/component/SignupSelect';
import JoinSelect from 'app.feature/join/create/component/JoinSelect';

const JoinCreateStepTwo = ({ handleCreateJoin }) => {
  const { handleSubmit } = useFormContext();

  return (
    <StyledWrapper>
      <div className="step-two-title">
        <div className="main-title">Step 02.</div>
        <div className="sub-title">
          취향이 맞는 친구들과의 라운딩을
          <br />
          위해 이번 조인의 컨셉을 선택해주세요.
        </div>
      </div>

      <div className="step-two-form">
        <div className="form-item-wrap">
          <div className="tag-item-wrap">
            <JoinSelect
              label="# 함께하고 싶은 평균 타수"
              name="preferredHit"
              options={[
                { key: '120-', name: '120타 이상' },
                { key: '100-120', name: '100 - 120타' },
                { key: '90-100', name: '90 - 100타' },
                { key: '80-90', name: '80 - 90타' },
                { key: '-80', name: '80타 이하' },
              ]}
            />
          </div>
        </div>
        <div className="form-item-wrap">
          <JoinSelect
            label="# 함께하고 싶은 연령대"
            name="preferredAge"
            options={[
              { key: '10-19', name: '10대 이하' },
              { key: '20-29', name: '20대' },
              { key: '30-39', name: '30대' },
              { key: '40-49', name: '40대' },
              { key: '50-59', name: '50대' },
              { key: '60-69', name: '60대' },
              { key: '70-', name: '70세 이상' },
            ]}
          />
        </div>
        <div className="form-item-wrap">
          <SignupSelect
            label="# 좋아하는 분위기"
            name="preferredMood"
            options={[
              { key: '편안한', name: '편안한' },
              { key: '즐거운', name: '즐거운' },
              { key: '열정적인', name: '열정적인' },
              { key: '교훈적인', name: '교훈적인' },
              { key: '가벼운', name: '가벼운' },
              { key: '진지한', name: '진지한' },
              { key: '칭찬하는', name: '칭찬하는' },
              { key: '침착한', name: '침착한' },
              { key: '예의바른', name: '예의바른' },
              { key: '긍정적인', name: '긍정적인' },
              { key: '조용한', name: '조용한' },
              { key: '존중하는', name: '존중하는' },
            ]}
          />
        </div>
      </div>
      <div className="submit-button-wrap">
        <div className="submit-button" onClick={handleSubmit(handleCreateJoin)}>
          조인하기
        </div>
      </div>
    </StyledWrapper>
  );
};

export default JoinCreateStepTwo;

const StyledWrapper = styled.div`
  padding: 30px 20px 0px;

  .step-two-title {
    margin-bottom: 30px;

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

  .form-item-wrap {
    margin-bottom: 30px;

    .form-item-label {
      margin-bottom: 10px;
    }

    input {
      padding: 10px;
      height: 40px;
      border-radius: 5px;
      border: solid 1px #6dd094;
    }

    .tag-item-wrap {
      display: flex;
      flex-wrap: wrap;
      column-gap: 6px;
      row-gap: 10px;

      .tag-item {
        padding: 7px 15px;
        border-radius: 16.5px;
        border: solid 1px var(--color-sub);
      }
    }
  }

  .submit-button-wrap {
    width: 100%;
    bottom: 35px;
    position: fixed;
    left: 0;

    .submit-button {
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
