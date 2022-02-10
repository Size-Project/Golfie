import React, { useEffect, useState } from 'react';
import { StyledFeedAll } from './styled';
import FeedCard from '../FeedCard/FeedCard';
import API from 'api';

const FeedAll = () => {
  const [feedAll, setFeedAll] = useState([]);

  const requestFeedAll = async () => {
    try {
      const res = await API.GET('/api/feeds');
      setFeedAll(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    requestFeedAll();
  }, []);

  return (
    <StyledFeedAll>
      {feedAll.map((feed, idx) => (
        <FeedCard key={idx} feed={feed} />
      ))}
    </StyledFeedAll>
  );
};

export default FeedAll;
