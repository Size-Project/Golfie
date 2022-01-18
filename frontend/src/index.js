import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import AppRouter from './AppRouter';
import rootReducer from './services';
import { GlobalStyle } from './GlobalStyle';

const store = createStore(rootReducer, composeWithDevTools());

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <GlobalStyle />
      <AppRouter />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);
