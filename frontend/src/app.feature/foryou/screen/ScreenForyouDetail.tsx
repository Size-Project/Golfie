import React from 'react';
import styled from 'styled-components';
import ForyouHeader from 'app.feature/foryou/component/ForyouHeader';
import ForyouDetailSearch from 'app.feature/foryou/component/ForyouDetailSearch';
import ForyouDetailPlace from 'app.feature/foryou/component/ForyouDetailPlace';

const ScreenForyouDetail = () => {
  return (
    <StyledWrapper>
      <ForyouHeader />
      <ForyouDetailSearch />
      <ForyouDetailPlace />
    </StyledWrapper>
  );
};

export default ScreenForyouDetail;

const StyledWrapper = styled.div`
  .foryou-header-wrap {
    background: var(--color-white) !important;
  }
`;
