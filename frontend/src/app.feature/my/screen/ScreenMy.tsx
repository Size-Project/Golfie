import React from 'react';
import styled from 'styled-components';
import MyHeader from 'app.feature/my/component/MyHeader';
import useGetUser from 'app.hooks/useGetUser';
import MyInfo from 'app.feature/my/component/MyInfo';

const ScreenMy = () => {
  const getUser = useGetUser();

  return (
    <StyledWrapper>
      <MyHeader />
      {getUser?.login && <MyInfo userInfo={getUser?.info} />}
    </StyledWrapper>
  );
};

export default ScreenMy;

const StyledWrapper = styled.div``;
