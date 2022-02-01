import React, { useState } from 'react';
import FeedHeader from '../../layout/Feed/FeedHeader';
import FeedTab from '../../layout/Feed/FeedTab';
import FeedTabPane from '../../layout/Feed/FeedTabPane';

const FeedPage = () => {
  const [tabKey, setTabKey] = useState(0);

  return (
    <div>
      <FeedHeader />
      <FeedTab tabKey={tabKey} setTabKey={setTabKey} />
      <FeedTabPane tabKey={tabKey} />
      <div style={{ height: '80px' }} />
    </div>
  );
};

export default FeedPage;
