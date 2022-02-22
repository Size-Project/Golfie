import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import FeedCard from '../FeedCard/FeedCard';
import API from 'app.modules/api';

const FeedAll = () => {
  const [feedAll, setFeedAll] = useState([]);

  const requestFeedAll = async () => {
    try {
      const res = await API.GET({ url: '/api/feeds', data: {} });
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

export const StyledFeedAll = styled.div`
  padding: 0 20px;
`;
