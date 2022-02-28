import React from 'react';
import styled from 'styled-components';
import ForyouHeader from 'app.feature/foryou/component/ForyouHeader';
import ForyouSearch from 'app.feature/foryou/component/ForyouSearch';
import useGetUser from 'app.hooks/useGetUser';
import ForyouTag from 'app.feature/foryou/component/ForyouTag';

const ScreenForyou = () => {
  const getUser = useGetUser();

  return (
    <StyledWrapper>
      <ForyouHeader />
      {getUser?.login && <ForyouSearch userInfo={getUser.info} />}
      <ForyouTag />
    </StyledWrapper>
  );
};

export default ScreenForyou;

const StyledWrapper = styled.div``;
