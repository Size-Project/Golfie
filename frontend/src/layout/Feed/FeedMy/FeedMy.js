import React, { useEffect, useState } from 'react';
import { StyledFeedMy } from './styled';
import API from 'api';
import FeedMyInfo from './FeedMyInfo';
import FeedMyTab from './FeedMyTab';

const FeedMy = () => {
  const [feedMy, setFeedMy] = useState([]);
  const [userInfo, setUserInfo] = useState(null);

  const requestUserInfo = async () => {
    try {
      const res = await API.GET('/api/users/me');
      setUserInfo(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  const requestFeedMy = async () => {
    try {
      const res = await API.GET('/api/feeds/me');
      setFeedMy(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    requestUserInfo();
    requestFeedMy();
  }, []);

  return (
    <StyledFeedMy>
      <FeedMyInfo userInfo={userInfo} />
      <FeedMyTab feedMy={feedMy} />
    </StyledFeedMy>
  );
};

export default FeedMy;
