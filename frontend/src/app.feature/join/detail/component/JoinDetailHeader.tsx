import React from 'react';
import styled from 'styled-components';
import SVGLeft from '../../../../../public/images/svg/arrow-left.svg';
import { useRouter } from 'next/router';

const JoinDetailHeader = () => {
  const router = useRouter();

  return (
    <StyledWrapper>
      <SVGLeft onClick={() => router.back()} />
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
