import React from 'react';
import styled from 'styled-components';
import JoinHeader from 'app.feature/join/list/component/JoinHeader';
import JoinSearch from 'app.feature/join/list/component/JoinSearch';
import JoinRecommend from 'app.feature/join/list/component/JoinRecommend';
import JoinNewRecommend from 'app.feature/join/list/component/JoinNewRecommend';
import useQueryFn from 'app.hooks/useQueryFn';
import { API_ROUNDINGS_ALL } from 'app.modules/api/fieldtrip.join';
import PlusFloating from 'app.components/PlusFloating/PlusFloating';
import { useRouter } from 'next/router';

const ScreeJoin = () => {
  const router = useRouter();
  const { isLoading, data } = useQueryFn(API_ROUNDINGS_ALL);

  if (isLoading) return null;
  return (
    <StyledWrapper>
      <PlusFloating onClick={() => router.push('/join/create')} />
      <JoinHeader />
      <JoinSearch />
      <JoinRecommend />
      <JoinNewRecommend roundingData={data} />
    </StyledWrapper>
  );
};

export default ScreeJoin;

const StyledWrapper = styled.div`
  position: relative;
`;
