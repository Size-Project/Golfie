import React from 'react';
import FeedMyInfo from './FeedMyInfo';
import FeedMyTab from './FeedMyTab';
import useGetUser from 'app.hooks/useGetUser';
import styled from 'styled-components';
import useQueryFn from 'app.hooks/useQueryFn';
import { API_FEEDS_ME } from 'app.modules/api/fieldtrip.feed';

const FeedMy = () => {
  const getUser = useGetUser();
  const { isLoading, data: feedMy } = useQueryFn(API_FEEDS_ME);

  if (isLoading || !getUser?.info) return null;
  return (
    <StyledWrapper>
      <FeedMyInfo userInfo={getUser.info} />
      <FeedMyTab feedMy={feedMy} />
    </StyledWrapper>
  );
};

export default FeedMy;

export const StyledWrapper = styled.div``;
