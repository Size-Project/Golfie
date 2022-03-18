import React from 'react';
import styled from 'styled-components';

const PlusFloating = ({ onClick = () => {} }) => {
  return (
    <StyledWrapper onClick={onClick}>
      <img src="/images/common/1725@3x.png" />
    </StyledWrapper>
  );
};

export default PlusFloating;

const StyledWrapper = styled.div`
  bottom: 101px;
  width: 78px;
  right: 0;
  z-index: 100;
  position: fixed;
`;
