import React from 'react';
import styled from 'styled-components';
import FeedCard from '../../../../app.components/FeedCard/FeedCard';
import useQueryFn from 'app.hooks/useQueryFn';
import { API_FEEDS_ALL } from 'app.modules/api/fieldtrip.feed';

const FeedAll = () => {
  const { isLoading, data: feedAll }: any = useQueryFn(API_FEEDS_ALL);

  if (isLoading) return null;
  return (
    <StyledFeedAll>
      {feedAll.map((feed, idx) => (
        <FeedCard key={idx} feed={feed} />
      ))}
    </StyledFeedAll>
  );
};

export default FeedAll;

export const StyledFeedAll = styled.div`
  padding: 0 20px;
`;
