import React from 'react';
import styled from 'styled-components';
import FeedHeader from 'app.feature/feed/component/FeedHeader';
import FeedCreateForm from 'app.feature/feed/component/FeedCreateForm';

const ScreenFeedCreate = () => {
  return (
    <StyledWrapper>
      <FeedHeader />
      <FeedCreateForm />
    </StyledWrapper>
  );
};

export default ScreenFeedCreate;

const StyledWrapper = styled.div``;
