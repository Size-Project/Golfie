import React from 'react';
import styled from 'styled-components';
import { useFormContext } from 'react-hook-form';
import SVGDown from '../../../public/images/svg/arrow-down.svg';

const Select = ({ options, placeholder, name, ...props }) => {
  const { register } = useFormContext();

  console.log(
    Array.from({ length: 5 }, (v, i) => new Date().getFullYear() + i)
  );
  return (
    <StyledWrapper {...register(name)} placeholder={placeholder} {...props}>
      <SVGDown />
      {options.map((option, optionIdx) => (
        <option key={optionIdx} value={option}>
          {option}
        </option>
      ))}
    </StyledWrapper>
  );
};

export default Select;

const StyledWrapper = styled.div`
  width: 80px;
  padding: 10px;
  height: 40px;
  border-radius: 5px;
  border: solid 1px #6dd094;
  position: relative;

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
