import styled from 'styled-components';

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
  background: var(--color-main);
  color: var(--color-white);
  .home-icon {
    width: 55px;
    height: 55px;
  }
  .login-icon {
    width: 86px;
    height: 86px;
  }
  .home {
    margin-top: 239.5px;
    margin-bottom: 48.5px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .home-icon {
      margin-bottom: 9.7px;
    }
    div {
      font-size: 22px;
      line-height: 28px;
    }
  }
  .login-box {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .home-button {
      font-size: 10px;
      font-weight: 500;
    }
  }
`;

export { Wrapper };
