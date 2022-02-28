/* SSR */
const processEnv = process.env.FIELD_TRIP_NODE_ENV;

export const isTestEnvironment =
  processEnv === 'development';

/* CSR */
/*export const isTestServer = (host) => {
  return (
    host.includes('localhost')
    || host.includes('test-game.cashwalk')
    || host.includes('qa-game.cashwalk')
  );
};*/
