import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { ReactComponent as SVGMenuHome } from '../../assets/images/svg/menu-home.svg';
import { ReactComponent as SVGMenuJoin } from '../../assets/images/svg/menu-join.svg';
import { ReactComponent as SVGMenuFeed } from '../../assets/images/svg/menu-feed.svg';
import { ReactComponent as SVGMenuForYou } from '../../assets/images/svg/menu-for-you.svg';
import { ReactComponent as SVGMenuMy } from '../../assets/images/svg/menu-my.svg';
import { StyledNavigationBar } from './styled';

const NavigationBar = () => {
  const { pathname: page } = useLocation();

  return (
    <StyledNavigationBar>
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
    </StyledNavigationBar>
  );
};

export default NavigationBar;
