import styled from 'styled-components';

export const StyledNavigationBar = styled.div`
  position: fixed;
  bottom: 35px;
  width: calc(100% - 30px);
  height: 66px;
  border-radius: 33px;
  box-shadow: 0 3px 10px 0 rgba(0, 0, 0, 0.16);
  background-color: var(--color-white);
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 15px;

  .menu-item-wrap {
    &:first-child {
      padding-left: 33.2px;
    }

    &:last-child {
      padding-right: 33.2px;
    }

    width: 20%;
    text-align: center;
    // TODO: 폰트 추가 필요
    font-family: AcuminPro;
    font-size: 8px;
    font-weight: 500;
    color: var(--color-gray-200);

    svg {
      margin-bottom: 6.6px;
      path {
        fill: var(--color-gray-200) !important;
      }
    }
  }
`;
