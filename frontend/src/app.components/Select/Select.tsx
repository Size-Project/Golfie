import React from 'react';
import styled from 'styled-components';
import { useFormContext } from 'react-hook-form';
import SVGDown from '../../../public/images/svg/arrow-down.svg';

const Select = ({
  options,
  placeholder,
  name,
  defaultValue = [],
  ...props
}) => {
  const { register } = useFormContext();

  return (
    <StyledWrapper
      {...register(name)}
      placeholder={placeholder}
      defaultValue={defaultValue}
      {...props}
    >
      <SVGDown />
      <option disabled selected className="none">
        {placeholder}
      </option>
      {options.map((option, optionIdx) => (
        <option key={optionIdx} value={option}>
          {option}
        </option>
      ))}
    </StyledWrapper>
  );
};

export default Select;

const StyledWrapper = styled.select`
  background-color: #fff;
  width: 80px;
  padding: 10px;
  height: 40px;
  border-radius: 5px;
  border: solid 1px #6dd094;
  position: relative;
  margin-right: 15px;

  svg {
    transform: rotate(180deg);
    right: 10px;
    position: absolute;
    path {
      fill: #c1c1c1;
    }
  }

  option {
    position: absolute;
    background: white;
  }
`;
