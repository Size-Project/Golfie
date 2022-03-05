import React from 'react';
import styled from 'styled-components';
import SVGLeft from '../../../../public/images/svg/arrow-left.svg';

const JoinDetailHeader = () => {
  return (
    <StyledWrapper>
      <SVGLeft />
    </StyledWrapper>
  );
};

export default JoinDetailHeader;

const StyledWrapper = styled.div`
  height: 44px;
  width: 100%;
  background: var(--color-white);
  padding: 7px;
  z-index: 2;
  position: sticky;

  svg {
    width: 30px;
    height: 30px;

    path {
      fill: var(--color-gray-300);
    }
  }
`;
