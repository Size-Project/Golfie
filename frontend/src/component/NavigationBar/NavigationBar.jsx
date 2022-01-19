import React from 'react';
import { StyledNavigationBar } from './styled';
import { ReactComponent as SVGMenuHome } from '../../assets/images/svg/menu-home.svg';
import { ReactComponent as SVGMenuJoin } from '../../assets/images/svg/menu-join.svg';
import { ReactComponent as SVGMenuFeed } from '../../assets/images/svg/menu-feed.svg';
import { ReactComponent as SVGMenuForYou } from '../../assets/images/svg/menu-for-you.svg';
import { ReactComponent as SVGMenuMy } from '../../assets/images/svg/menu-my.svg';

const NavigationBar = () => {
  return (
    <StyledNavigationBar>
      <div className="menu-item-wrap">
        <SVGMenuHome />
        <div className="menu-text">HOME</div>
      </div>
      <div className="menu-item-wrap">
        <SVGMenuJoin />
        <div className="menu-text">JOIN</div>
      </div>
      <div className="menu-item-wrap">
        <SVGMenuFeed />
        <div className="menu-text">FEED</div>
      </div>
      <div className="menu-item-wrap">
        <SVGMenuForYou />
        <div className="menu-text">FOR YOU</div>
      </div>
      <div className="menu-item-wrap">
        <SVGMenuMy />
        <div className="menu-text">MY</div>
      </div>
    </StyledNavigationBar>
  );
};

export default NavigationBar;
