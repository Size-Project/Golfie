import React from 'react';
import { Wrapper } from './styled';
import ForYouHeader from 'layout/ForYou/ForYouHeader';
import ForYouMain from 'layout/ForYou/ForYouMain';

const ForYouPage = () => {
  return (
    <Wrapper>
      <header className="header">
        <ForYouHeader />
      </header>
      <main className="main">
        <ForYouMain />
      </main>
    </Wrapper>
  );
};

export default ForYouPage;
