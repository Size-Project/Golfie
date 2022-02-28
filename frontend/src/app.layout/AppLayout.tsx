import React from 'react';
import NavigationBar from 'app.components/NavigationBar/NavigationBar';

const AppLayout = ({ Component, pageProps }) => {
  return (
    <div>
      <Component {...pageProps} />
      <NavigationBar />
    </div>
  );
};

export default AppLayout;
