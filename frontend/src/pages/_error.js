import React, { useEffect } from 'react';
import styled from 'styled-components';

const Page_Error = ({ statusCode }) => {
  return (
    <>
      <StyledWrapper id="errPage">
        앗! 페이지를 찾을 수 없습니다.
        <br />
        잠시 후 다시 시도해주세요.
      </StyledWrapper>
    </>
  );
};

Page_Error.getInitialProps = ({ res, jsonPageRes }) => {
  const statusCode = res
    ? res.statusCode
    : jsonPageRes
      ? jsonPageRes.status
      : null;
  return {
    statusCode
  };
};

export default Page_Error;

const StyledWrapper = styled.div`
  position: relative;
  height: 100vh;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;

  .__img {
    display: block;
    width: 52px;
    margin: 0px 0 40px -14px;
  }

  .__err_code {
    margin: 0 0 20px;

    span {
      margin: 15px 0;
      padding: 5px 15px;
      border-radius: 100px;
      background: #000;
      font-size: 13px;
      color: #fff;
      letter-spacing: 0.5px;
    }
  }

  .__err_text {
    font-size: 16px;
    font-weight: normal;
    font-stretch: normal;
    font-style: normal;
    line-height: 1.56;
    letter-spacing: normal;
    text-align: center;
    color: #383838;
  }
`;
