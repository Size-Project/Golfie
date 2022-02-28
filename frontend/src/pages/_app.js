import React, { useEffect } from 'react';
import Head from 'next/head';
import { isTestEnvironment } from 'app.modules/constant/environment';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';
import { Hydrate, dehydrate } from 'react-query/hydration';
import {
  initializeStore,
  StoreProvider,
  useCreateStore,
} from 'app.store/rootStore';
import AppLayout from '../app.layout/AppLayout';
import { useStoreUserInfo } from 'app.store/intoAPP/store.intoAPP';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: false,
      refetchOnMount: false,
      refetchOnWindowFocus: false,
      refetchIntervalInBackground: false,
      notifyOnChangeProps: 'tracked',
      // cacheTime: 30000
    },
  },
});

const AppContainer = ({ Component, pageProps }) => {
  isTestEnvironment;
  const createStore = useCreateStore(pageProps.initialZustandState);
  const requestAuthUser = useStoreUserInfo((state) => state.requestAuthUser);

  useEffect(() => {
    requestAuthUser();
  }, []);

  return (
    <>
      <Head>
        <title>필드트립</title>
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
        />
      </Head>
      <StoreProvider createStore={createStore}>
        <QueryClientProvider client={queryClient}>
          {isTestEnvironment && <ReactQueryDevtools initialIsOpen={false} />}
          <Hydrate state={pageProps.dehydratedState}>
            <AppLayout Component={Component} pageProps={pageProps} />
          </Hydrate>
        </QueryClientProvider>
      </StoreProvider>
    </>
  );
};

AppContainer.getInitialProps = async ({ Component, ctx }) => {
  /* server side rendering */
  const zustandStore = initializeStore();

  // if (!!ctx.req) {
  //   await zustandStore.getState().intoAPPPrefetch(ctx);
  // }

  return {
    pageProps: {
      initialZustandState: zustandStore.getState(),
      ...(Component.getInitialProps
        ? await Component.getInitialProps(ctx)
        : {}),
      dehydratedState: dehydrate(queryClient),
      ...(Component.getInitialProps
        ? await Component.getInitialProps(ctx)
        : {}),
    },
  };
};

export default AppContainer;
