module.exports = {
  env: {
    NODE_ENV: '"production"',
  },
  defineConstants: {},
  weapp: {},
  h5: {
    devServer: {
      host: '0.0.0.0',
      port: 10086,
      proxy: {
        '/api': {
          target: 'http://0.0.0.0:8080',
          pathRewrite: { '^/api': '/' },
          secure: false,
        },
      },
    },
  },
};
