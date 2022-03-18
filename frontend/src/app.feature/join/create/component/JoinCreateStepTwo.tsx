import React from 'react';
import styled from 'styled-components';
import { useFormContext } from 'react-hook-form';

const JoinCreateStepTwo = ({ handleCreateJoin }) => {
  const { register, handleSubmit } = useFormContext();

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
          <div className="form-item-label">
            # 영주님의 기본 조인 태그를 수정해주세요.
          </div>
          <div className="tag-item-wrap">
            <div className="tag-item">26-30세</div>
            <div className="tag-item">126타 이상</div>
            <div className="tag-item">즐거운</div>
            <div className="tag-item">친목골프</div>
            <div className="tag-item">골린이</div>
          </div>
        </div>
        <div className="form-item-wrap">
          <div className="form-item-label">
            # 이번 조인에 원하는 태그를 추가해보세요.
          </div>
          <input
            {...register('keyword')}
            placeholder="태그 키워드를 검색해보세요."
            style={{ width: '100%' }}
          />
        </div>
        <div className="form-item-wrap">
          <div className="form-item-label"># 추가 가능한 추천 태그.</div>
          <div className="tag-item-wrap">
            <div className="tag-item">가벼운</div>
            <div className="tag-item">진지한</div>
            <div className="tag-item">칭찬하는</div>
            <div className="tag-item">침착한</div>
            <div className="tag-item">예의바른</div>
            <div className="tag-item">긍정적인</div>
            <div className="tag-item">조용한</div>
            <div className="tag-item">존중하는</div>
          </div>
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
