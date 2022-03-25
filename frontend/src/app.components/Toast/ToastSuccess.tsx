import React from 'react';
import styled, { css } from 'styled-components';

const ToastSuccess = ({ text, type }) => {
  const isTop = type === 'top';
  return (
    <StyledModalWrapper isTop={isTop} isPosition={isTop ? 'top' : 'bottom'}>
      <div className="toast-body">
        <div className="toast-icon">
          <img src="/images/common/156@3x.png" />
        </div>
        <div className="toast-text">
          {text.split('\n').map((line, lineIdx) => {
            return (
              <span key={lineIdx}>
                {line}
                <br />
              </span>
            );
          })}
        </div>
      </div>
    </StyledModalWrapper>
  );
};
export default ToastSuccess;

const common = 80;
const StyledModalWrapper = styled.div`
  ${({ isTop, isPosition }) => css`
    position: fixed;
    bottom: -${common}px;
    left: 0;
    ${isTop && {
      bottom: 'initial',
      top: common,
    }}

    z-index: 9999;
    padding: 16px;
    color: #707070;
    line-height: 20px;
    height: auto;
    width: 100%;
    animation: toast--fadein 0.4s, toast--fadeout 0.4s 2.5s;
    animation-fill-mode: forwards;

    .toast-body {
      padding: 13px 30px;
      background: var(--color-white);
      border-radius: 20px;
      box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.16);
      display: flex;
      align-items: center;
      justify-content: center;
      font-family: AcuminPro;
      font-size: 14px;
      font-weight: 500;

      .toast-icon {
        margin-right: 15px;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 50%;
        width: 20px;
        height: 20px;
        min-width: 20px;
        min-height: 20px;
        max-width: 20px;
        max-height: 20px;
        background-color: var(--color-sub);

        img {
          width: 11px;
          height: 11px;
        }
      }
      > span {
        color: #707070;
      }
    }

    @keyframes toast--fadein {
      from {
        ${isPosition}: -${common}px;
      }
      to {
        ${isPosition}: 0;
      }
    }

    @keyframes toast--fadeout {
      from {
        ${isPosition}: 0;
      }
      to {
        ${isPosition}: -${common}px;
      }
    }
  `}
`;
