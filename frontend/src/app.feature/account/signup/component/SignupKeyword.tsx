import React from 'react';
import styled from 'styled-components';
import { useForm, useFormContext } from 'react-hook-form';
import SignupSelect from 'app.feature/account/signup/component/SignupSelect';
import SVGLogo from '../../../../../public/images/svg/1703.svg';

const SignupKeyword = () => {
  const { watch } = useFormContext();
  console.log(watch());
  return (
    <StyledWrapper>
      <div className="sign-up-header">키워드 선택</div>
      <div className="keyword-input-box">
        <SignupSelect
          label="함께하고 싶은 평균 타수"
          name="preferredHit"
          options={[
            { key: '120-150', name: '120타 이상' },
            { key: '100-120', name: '100 - 120타' },
            { key: '90-100', name: '90 - 100타' },
            { key: '80-90', name: '80 - 90타' },
            { key: '0-80', name: '80타 이하' },
          ]}
        />
        <SignupSelect
          label="함께하고 싶은 연령대"
          name="preferredAge"
          options={[
            { key: '-20', name: '20세 이하' },
            { key: '21-25', name: '20 - 25세' },
            { key: '26-30', name: '26 - 30세' },
            { key: '31-35', name: '31 - 35세' },
            { key: '36-40', name: '36 - 40세' },
            { key: '41-', name: '40세 초과' },
          ]}
        />
        <SignupSelect
          label="좋아하는 분위기"
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
      <button
        type="submit"
        className={`submit-button ${String(
          !!watch()?.preferredHit &&
            !!watch()?.preferredAge &&
            !!watch()?.preferredMood
        )}`}
        disabled={
          !watch()?.preferredHit ||
          !watch()?.preferredAge ||
          !watch()?.preferredMood
        }
      >
        <div>
          <SVGLogo />
          JOIN!
        </div>
      </button>
    </StyledWrapper>
  );
};

export default SignupKeyword;

const StyledWrapper = styled.div`
  .sign-up-header {
    margin-bottom: 30px;
    height: 44px;
    background: var(--color-main);
    color: var(--color-white);
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .keyword-input-box {
    padding: 0 20px;
  }

  .submit-button {
    width: 100%;
    text-align: center;
    margin: 0 auto;
    margin-bottom: 67px;
    background: none;
    color: var(--color-main);
    font-family: 'Acumin Pro';
    font-size: 19px;
    font-weight: bold;
    div {
      text-align: center;
    }

    svg {
      margin: 0 auto;
      margin-bottom: 10px;
      display: block;
    }

    &.false {
      color: var(--color-gray-200) !important;

      svg {
        path {
          fill: var(--color-gray-200) !important;
        }
      }
    }
  }
`;
