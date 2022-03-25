import React from 'react';
import { useRouter } from 'next/router';
import ScreenJoinDetail from 'app.feature/join/detail/screen/ScreenJoinDetail';

const Page_JoinDetail = () => {
  const router = useRouter();
  const { id } = router.query;

  return (
    <div>
      <ScreenJoinDetail joinId={id} />
      <div style={{ height: '130px' }} />
    </div>
  );
};

export default Page_JoinDetail;
