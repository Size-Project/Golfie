import React from 'react';
import styled from 'styled-components';

const JoinCreateStepTwo = () => {
  return (
    <StyledWrapper>
      <div className="submit-button-wrap">
        <div className="submit-button">조인하기</div>
      </div>
    </StyledWrapper>
  );
};

export default JoinCreateStepTwo;

const StyledWrapper = styled.div`
  padding: 30px 20px 0px;

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
