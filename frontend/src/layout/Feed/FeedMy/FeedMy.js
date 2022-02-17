import React, { useEffect, useState } from 'react';
import { StyledFeedMy } from './styled';
import API from 'api';
import FeedMyInfo from './FeedMyInfo';
import FeedMyTab from './FeedMyTab';
import { useGetUser } from '../../../services/store.intoAPP';

const FeedMy = () => {
  const getUser = useGetUser();
  const [feedMy, setFeedMy] = useState([]);

  const requestFeedMy = async () => {
    try {
      const res = await API.GET('/api/feeds/me');
      setFeedMy(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    requestFeedMy();
  }, []);

  return (
    <StyledFeedMy>
      <FeedMyInfo userInfo={getUser.info} />
      <FeedMyTab feedMy={feedMy} />
    </StyledFeedMy>
  );
};

export default FeedMy;
