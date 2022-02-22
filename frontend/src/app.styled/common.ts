import { css } from 'styled-components';

export const common = css`
  .font-init {
    font-family: -apple-system, BlinkMacSystemFont, 'Helvetica Neue',
      'Apple SD Gothic Neo', Arial, sans-serif !important;
  }

  .line-clamp-3 {
    word-wrap: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  .line-clamp-1 {
    word-wrap: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  
  .fx-center {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  // responsive
  @media screen and (min-width: 600px) {
    .responsive-2n {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;

      > div {
        display: flex;
        width: 49%;
        padding: 0 5px;
      }
    }

    .responsive-3n {
      > div {
        width: 33.3% !important;
        padding: 0 5px !important;
      }
    }
  }
  
  
  .img-cover {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: block;
    object-fit: cover;
  }
`;
