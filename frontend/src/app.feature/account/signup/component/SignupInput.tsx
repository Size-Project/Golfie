import React from 'react';
import styled from 'styled-components';
import { get, useFormContext } from 'react-hook-form';

const SignupInput = ({
  onChange = null,
  name,
  label,
  placeholder = '입력해주세요',
  options = {},
  error = null,
  ...props
}) => {
  const { register } = useFormContext();

  return (
    <StyledWrapper>
      <div>
        <div className="signup-input-header">
          <img src="/icons/checkIcon.png" />
          <div className="signup-label">{label}</div>
        </div>
        <input
          {...register(name, { ...options })}
          placeholder={placeholder}
          onChange={onChange}
          {...props}
        />
      </div>
      {error && <div className="error-text">{error}</div>}
    </StyledWrapper>
  );
};

export default SignupInput;

const StyledWrapper = styled.div`
  margin-bottom: 30px;
  position: relative;

  .signup-input-header {
    display: flex;
    margin-bottom: 12px;

    img {
      width: 14px;
      margin-right: 5px;
    }

    .signup-label {
      font-size: 16px;
      font-weight: bold;
      color: var(--color-sub);
    }
  }

  input {
    width: 100%;
    border: 0;
    border-bottom: solid 1px;
    border-color: var(--color-gray-200);
    margin-bottom: 3px;

    &::placeholder {
      color: var(--color-gray-200);
      font-size: 12px;
    }
  }

  .error-text {
    position: absolute;
    font-size: 8px;
    color: var(--color-yellow);
  }
`;
