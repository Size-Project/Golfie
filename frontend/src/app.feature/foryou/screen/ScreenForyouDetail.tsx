import React from 'react';
import styled from 'styled-components';
import ForyouHeader from 'app.feature/foryou/component/ForyouHeader';
import useGetUser from 'app.hooks/useGetUser';
import ForyouDetailSearch from 'app.feature/foryou/component/ForyouDetailSearch';
import ForyouDetailPlace from 'app.feature/foryou/component/ForyouDetailPlace';

const ScreenForyouDetail = () => {
  const getUser = useGetUser();

  return (
    <StyledWrapper>
      <ForyouHeader />
      <ForyouDetailSearch />
      <ForyouDetailPlace userInfo={getUser.info} />
    </StyledWrapper>
  );
};

export default ScreenForyouDetail;

const StyledWrapper = styled.div`
  .foryou-header-wrap {
    background: var(--color-white) !important;
  }
`;
