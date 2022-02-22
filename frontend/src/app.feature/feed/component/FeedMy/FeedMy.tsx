import React, { useEffect, useState } from 'react';
import API from 'app.modules/api';
import FeedMyInfo from './FeedMyInfo';
import FeedMyTab from './FeedMyTab';
import useGetUser from 'app.hooks/useGetUser';
import styled from 'styled-components';

const FeedMy = () => {
  const getUser = useGetUser();
  const [feedMy, setFeedMy] = useState([]);

  const requestFeedMy = async () => {
    try {
      const res = await API.GET({ url: '/api/feeds/me', data: {} });
      setFeedMy(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    requestFeedMy();
  }, []);

  if (!getUser?.info) return null;
  return (
    <StyledWrapper>
      <FeedMyInfo userInfo={getUser.info} />
      <FeedMyTab feedMy={feedMy} />
    </StyledWrapper>
  );
};

export default FeedMy;

export const StyledWrapper = styled.div``;
