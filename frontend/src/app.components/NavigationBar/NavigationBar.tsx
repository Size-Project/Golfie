import React from 'react';
import styled from 'styled-components';
import { Link, useLocation } from 'react-router-dom';
import SVGMenuHome from '../../../public/images/svg/menu-home.svg';
import SVGMenuJoin from '../../../public/images/svg/menu-join.svg';
import SVGMenuFeed from '../../../public/images/svg/menu-feed.svg';
import SVGMenuForYou from '../../../public/images/svg/menu-for-you.svg';
import SVGMenuMy from '../../../public/images/svg/menu-my.svg';

const NavigationBar = () => {
  const { pathname: page } = useLocation();

  return (
    <StyledWrapper>
      <Link to="/" className={`menu-item-wrap ${String(page === '/')}`}>
        <SVGMenuHome />
        <div className="menu-text">HOME</div>
      </Link>
      <Link to="/join" className={`menu-item-wrap ${String(page === '/join')}`}>
        <SVGMenuJoin />
        <div className="menu-text">JOIN</div>
      </Link>
      <Link to="/feed" className={`menu-item-wrap ${String(page === '/feed')}`}>
        <SVGMenuFeed />
        <div className="menu-text">FEED</div>
      </Link>
      <Link
        to="/forYou"
        className={`menu-item-wrap ${String(page === '/forYou')}`}
      >
        <SVGMenuForYou />
        <div className="menu-text">FOR YOU</div>
      </Link>
      <Link to="/my" className={`menu-item-wrap ${String(page === '/my')}`}>
        <SVGMenuMy />
        <div className="menu-text">MY</div>
      </Link>
    </StyledWrapper>
  );
};

export default NavigationBar;

export const StyledWrapper = styled.div`
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
