const withPlugins = require('next-compose-plugins');
const withTM = require('next-transpile-modules')(['urlencoded-body-parser']);

const processEnv = process.env;

const NEXT_CONFIG = {
  distDir: './.next',
  swcMinify: true,

  webpack: (config) => {
    config.resolve.modules = [...config.resolve.modules, '../src'];
    return config;
  },

  env: {
    FIELD_TRIP_NODE_ENV: processEnv.FIELD_TRIP_NODE_ENV,
    FIELD_TRIP_API_URI: processEnv.FIELD_TRIP_API_URI,
    FIELD_TRIP_KAKAO_CLIENT_ID: processEnv.FIELD_TRIP_KAKAO_CLIENT_ID,
    FIELD_TRIP_NAVER_CLIENT_ID: processEnv.FIELD_TRIP_NAVER_CLIENT_ID,
    FIELD_TRIP_NAVER_STATE: processEnv.FIELD_TRIP_NAVER_STATE,
    FIELD_TRIP_KAKAO_REDIRECT_URI_LOGIN: processEnv.FIELD_TRIP_KAKAO_REDIRECT_URI_LOGIN,
    FIELD_TRIP_KAKAO_REDIRECT_URI_SIGNUP: processEnv.FIELD_TRIP_KAKAO_REDIRECT_URI_SIGNUP,
    FIELD_TRIP_NAVER_REDIRECT_URI: processEnv.FIELD_TRIP_NAVER_REDIRECT_URI,
    FIELD_TRIP_KAKAO_RESPONSE_TYPE: processEnv.FIELD_TRIP_KAKAO_RESPONSE_TYPE,
  },
};

const PLUGINS = [[withTM]];

module.exports = withPlugins(PLUGINS, NEXT_CONFIG);
