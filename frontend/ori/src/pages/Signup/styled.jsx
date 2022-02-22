import styled from 'styled-components';

export const Wrapper = styled.div`
  .signup-form {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .input-text {
    width: 335px;
    /* margin: auto; */
    margin-bottom: 16px;
    .input-header {
      display: flex;
      margin-bottom: 12px;
      img {
        margin-right: 5px;
      }
      .input-title {
        font-size: 16px;
        font-weight: bold;
        color: var(--color-sub);
      }
    }
    input {
      width: 100%;
      border: 0;
      border-bottom: solid 1px;
      border-color: #c1c1c1;
      margin-bottom: 3px;
    }
    .error-message {
      font-size: 8px;
      font-weight: 500;
      color: #f7c844;
    }
  }
  .input-select {
    width: 335px;
    margin-bottom: 50px;
    .input-select-title {
      font-siez: 16px;
      font-weight: bold;
      color: #3bd48e;
      margin-bottom: 20px;
    }
    .select-item-box {
      display: flex;
      flex-wrap: wrap;
      .select-item {
        height: 33px;
        border-radius: 16px;
        border: solid 1px #3bd48e;
        display: flex;
        padding: 6px 15px 6px 15px;
        margin-right: 6px;
        margin-bottom: 10px;

        .item-text {
          margin: auto;
        }
      }
    }
  }
`;
