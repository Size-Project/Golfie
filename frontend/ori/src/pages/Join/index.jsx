import React from 'react';
import JoinHeader from '../../layout/Join/JoinHeader';
import JoinSearch from '../../layout/Join/JoinSearch';
import JoinRecommend from '../../layout/Join/JoinRecommend';
import JoinNewRecommend from '../../layout/Join/JoinNewRecommend';

const JoinPage = () => {
  return (
    <div>
      <JoinHeader />
      <JoinSearch />
      <JoinRecommend />
      <JoinNewRecommend />
      <div style={{ height: '130px' }} />
    </div>
  );
};

export default JoinPage;
