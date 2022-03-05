import { createGlobalStyle } from 'styled-components';
import { common } from 'app.styled/common';
import resetCSS from './resetCSS';

export const GlobalStyle = createGlobalStyle`
  :root {
    --color-main: #29ae6f;
    --color-sub: #3bd48e;
    --color-pri: #00b169;

    --color-purple: #bca1ff;
    --color-blue: #7eb8f2;
    --color-yellow: #f7c844;
    --color-lemon: #fffc88;

    --color-black: #1c1c1c;
    --color-gray-300: #797979;
    --color-gray-200: #c1c1c1;
    --color-gray-100: #efefef;
    --color-white: #ffffff;
  }
  
  ${resetCSS};
  
  // 백그라운스 스크롤 방지 스타일
  body.__modal_background_scroll_stop {
    overflow: hidden !important;
  }

  .win-max-width {
    max-width: 664px;
  }
  
  ${common};
`;

export const GlobalStyleHeight100 = createGlobalStyle`
  html, body, #__next {
    height: 100%;
  }
`;

export const GlobalStyleBackGroundColor = createGlobalStyle`
  html, body, #__next {
    background: #f9f9f9;
  }
`;
