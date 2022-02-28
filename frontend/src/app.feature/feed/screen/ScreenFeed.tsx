import React, { useState } from 'react';
import styled from 'styled-components';
import useGetUser from 'app.hooks/useGetUser';
import FeedHeader from 'app.feature/feed/component/FeedHeader';
import FeedTab from 'app.feature/feed/component/FeedTab';
import FeedTabPane from 'app.feature/feed/component/FeedTabPane';

const ScreenFeed = () => {
  const [tabKey, setTabKey] = useState(0);

  const getUser = useGetUser();
  return (
    <StyledWrapper>
      <FeedHeader />
      <FeedTab tabKey={tabKey} setTabKey={setTabKey} getUser={getUser} />
      <FeedTabPane tabKey={tabKey} getUser={getUser} />
    </StyledWrapper>
  );
};

export default ScreenFeed;

const StyledWrapper = styled.div``;
