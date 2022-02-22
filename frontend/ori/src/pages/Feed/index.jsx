import React, { useState } from 'react';
import FeedHeader from '../../layout/Feed/FeedHeader';
import FeedTab from '../../layout/Feed/FeedTab';
import FeedTabPane from '../../layout/Feed/FeedTabPane';
import { useGetUser } from '../../services/store.intoAPP';

const FeedPage = () => {
  const [tabKey, setTabKey] = useState(0);
  const getUser = useGetUser();

  if (getUser.isLoading) return null;
  return (
    <div>
      <FeedHeader />
      <FeedTab tabKey={tabKey} setTabKey={setTabKey} getUser={getUser} />
      <FeedTabPane tabKey={tabKey} getUser={getUser} />
      <div style={{ height: '80px' }} />
    </div>
  );
};

export default FeedPage;
