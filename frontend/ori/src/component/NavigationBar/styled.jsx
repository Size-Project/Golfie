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
      margin-left: 15px;
    }

    &:last-child {
      margin-right: 15px;
    }

    width: 20%;
    text-align: center;
    font-family: 'Acumin Pro';
    font-size: 8px;
    font-weight: 500;
    text-decoration: unset;
    color: var(--color-gray-200);

    svg {
      margin-bottom: 6.6px;
      path {
        transition: all 200ms;
        fill: var(--color-gray-200) !important;
      }
    }

    &.true {
      color: var(--color-black);

      svg {
        path {
          fill: var(--color-black) !important;
        }
      }
    }
  }
`;
