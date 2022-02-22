import React from 'react';
import styled from 'styled-components';
import ScreenHome from 'app.feature/home/screen/ScreenHome';

const Page_Index: any = () => {
  return (
    <StyledWrapper>
      <ScreenHome />
      <div style={{ height: '130px' }} />
    </StyledWrapper>
  );
};

export default Page_Index;

const StyledWrapper = styled.div``;
