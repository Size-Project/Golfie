import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const GlobalStyle = createGlobalStyle`
  :root {
    --color-main: #29ae6f;
    --color-sub: #6dd195;

    --color-purple: #bca1ff;
    --color-blue: #7eb8f2;
    --color-yello: #f7c844;
    --color-lemon: #fffc88;

    --color-black: #1c1c1c;
    --color-gray-300: #797979;
    --color-gray-200: #c1c1c1;
    --color-gray-100: #efefef;
    --color-white: #ffffff;
  }
  
  @font-face {
    font-family: 'CoHeadline';
    src: url("./assets/fonts/Co Headline Corp.ttf");
  }

  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  ::-webkit-scrollbar{
    display: none;
  }
  
  ${reset};
`;
