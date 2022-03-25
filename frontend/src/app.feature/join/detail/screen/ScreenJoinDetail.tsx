import React from 'react';
import styled from 'styled-components';
import JoinDetailInfo from 'app.feature/join/detail/component/JoinDetailInfo';
import JoinDetailHeader from 'app.feature/join/detail/component/JoinDetailHeader';
import JoinDetailAuthor from 'app.feature/join/detail/component/JoinDetailAuthor';
import JoinDetailDone from 'app.feature/join/detail/component/JoinDetailDone';
import JoinDetailRecommend from 'app.feature/join/detail/component/JoinDetailRecommend';
import useQueryFn from 'app.hooks/useQueryFn';
import { API_ROUNDINGS_DETAIL } from 'app.modules/api/fieldtrip.join';
import JoinDetailButton from 'app.feature/join/detail/component/JoinDetailButton';
import useGetUser from 'app.hooks/useGetUser';

const ScreeJoinDetail = ({ joinId }) => {
  const { isLoading, data, error } = useQueryFn(API_ROUNDINGS_DETAIL(joinId));
  const getUser = useGetUser();

  if (getUser?.isLoading) return null;
  if (isLoading || !data) return null;
  return (
    <StyledWrapper>
      <JoinDetailHeader />
      <JoinDetailInfo joinDetailData={data} />
      <JoinDetailAuthor joinDetailData={data} />
      <JoinDetailDone joinDetailData={data} />
      {/*<JoinDetailRecommend />*/}
      <JoinDetailButton
        joinDetailData={data}
        joinId={joinId}
        login={getUser?.login}
        userInfo={getUser?.info}
      />
    </StyledWrapper>
  );
};

export default ScreeJoinDetail;

const StyledWrapper = styled.div`
  position: relative;
`;
