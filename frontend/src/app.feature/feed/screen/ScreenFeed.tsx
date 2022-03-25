import React, { useState } from 'react';
import styled from 'styled-components';
import useGetUser from 'app.hooks/useGetUser';
import FeedHeader from 'app.feature/feed/component/FeedHeader';
import FeedTab from 'app.feature/feed/component/FeedTab';
import FeedTabPane from 'app.feature/feed/component/FeedTabPane';
import PlusFloating from 'app.components/PlusFloating/PlusFloating';
import { useRouter } from 'next/router';

const ScreenFeed = () => {
  const router = useRouter();
  const getUser = useGetUser();
  const [tabKey, setTabKey] = useState(0);

  return (
    <StyledWrapper>
      <FeedHeader />
      <FeedTab tabKey={tabKey} setTabKey={setTabKey} getUser={getUser} />
      <FeedTabPane tabKey={tabKey} getUser={getUser} />
      <PlusFloating onClick={() => router.push('/feed/create')} />
    </StyledWrapper>
  );
};

export default ScreenFeed;

const StyledWrapper = styled.div``;
