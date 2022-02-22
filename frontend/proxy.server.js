/* eslint-disable no-console */
const express = require('express');
const next = require('next');
const cors = require('cors');

let targetURI = 'http://field-trip.duckdns.org:8080';
if (process.env.FIELD_TRIP_NODE_ENV === 'development') {
  targetURI = 'http://localhost:8080';
}

const devProxy = {
  '': {
    target: targetURI,
    pathRewrite: { '^/v1': '/' },
    changeOrigin: true,
  },
};

const port = parseInt(process.env.PORT, 10) || 4001;
const env = process.env.FIELD_TRIP_NODE_ENV;

const dev = env !== 'production';
const _next = next({
  dir: './', // base directory where everything is, could move to src later
  dev,
});

const handle = _next.getRequestHandler();

_next
  .prepare()
  .then(() => {
    const app = express();
    app.use(cors());
    app.options('*', cors());

    // Set up the proxy.
    if (dev && devProxy) {
      const { createProxyMiddleware } = require('http-proxy-middleware');
      Object.keys(devProxy).forEach(function (context) {
        app.use(createProxyMiddleware(context, devProxy[context]));
      });
    }

    // Default catch-all handler to allow Next.js to handle all other routes
    app.all('*', (req, res) => handle(req, res));

    app.listen(port, (err) => {
      if (err) {
        throw err;
      }
      console.log(`> Ready on port ${port} env: [local-server]`);
    });
  })
  .catch((err) => {
    console.log('@ An error occurred, unable to start the server');
    console.log(err);
  });
