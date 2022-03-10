import React from 'react';
import styled from 'styled-components';
import SVGMenuHome from '../../../public/images/svg/menu-home.svg';
import SVGMenuJoin from '../../../public/images/svg/menu-join.svg';
import SVGMenuFeed from '../../../public/images/svg/menu-feed.svg';
import SVGMenuForYou from '../../../public/images/svg/menu-for-you.svg';
import SVGMenuMy from '../../../public/images/svg/menu-my.svg';
import { useRouter } from 'next/router';
import useGetUser from 'app.hooks/useGetUser';

const NavigationBar = () => {
  const router = useRouter();
  const page = router.pathname;

  const handleLink = (link) => {
    router.push(link);
  };

  const getUser = useGetUser();

  if (getUser?.isLoading) return null;
  if (router.pathname.includes('account') || router.pathname.includes('create'))
    return null;
  return (
    <StyledWrapper>
      <div
        onClick={() => handleLink('/')}
        className={`menu-item-wrap ${String(page === '/')}`}
      >
        <SVGMenuHome />
        <div className="menu-text">HOME</div>
      </div>
      <div
        onClick={() => handleLink('/join')}
        className={`menu-item-wrap ${String(page === '/join')}`}
      >
        <SVGMenuJoin />
        <div className="menu-text">JOIN</div>
      </div>
      <div
        onClick={() => handleLink('/feed')}
        className={`menu-item-wrap ${String(page === '/feed')}`}
      >
        <SVGMenuFeed />
        <div className="menu-text">FEED</div>
      </div>
      <div
        onClick={() => handleLink('/foryou')}
        className={`menu-item-wrap ${String(page === '/foryou')}`}
      >
        <SVGMenuForYou />
        <div className="menu-text">FOR YOU</div>
      </div>
      <div
        onClick={() =>
          getUser?.login ? handleLink('/my') : router.push('/account/login')
        }
        className={`menu-item-wrap ${String(page === '/my')}`}
      >
        <SVGMenuMy />
        <div className="menu-text">MY</div>
      </div>
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
