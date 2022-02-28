import React from 'react';
import styled from 'styled-components';
import { useFormContext } from 'react-hook-form';

const SignupSelect = ({
  onChange = null,
  name,
  label,
  options = [],
  ...props
}) => {
  const { setValue, watch } = useFormContext();

  const handleChange = (event) => {
    setValue(name, event.target.value);
  };

  return (
    <StyledWrapper>
      <div className="profile-label">{label}</div>
      {options.map((option, optionIdx) => (
        <option
          key={optionIdx}
          onClick={handleChange}
          value={option.key}
          className={`option-${watch()?.[name] === option.key}`}
        >
          {option.name}
        </option>
      ))}
    </StyledWrapper>
  );
};

export default SignupSelect;

const StyledWrapper = styled.div`
  margin-bottom: 50px;

  .profile-label {
    margin-bottom: 20px;
    font-size: 16px;
    color: var(--color-sub);
    font-weight: bold;
  }

  option {
    justify-content: center;
    align-content: center;
    align-self: center;
    padding: 6px 15px;
    font-size: 14px;
    font-weight: 500;
    line-height: 20px;
    display: inline-block;
    color: var(--color-gray-300);
    border-radius: 16.5px;
    border: solid 1px var(--color-sub);
    margin-bottom: 10px;
    margin-right: 6px;
  }

  .option-true {
    color: var(--color-white);
    background: var(--color-sub);
  }
`;
