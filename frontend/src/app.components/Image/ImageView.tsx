import React, { useState } from 'react';
import styled, { css } from 'styled-components';
import Image from 'app.components/Image/Image';
import { fadeIn } from 'app.styled/keyframe';

type TProps = {
  src: string;
  style?: object | any;
  className?: string;
  errorImg?: string;
  onLoad?: () => void;
  onError?: () => void;
  onClick?: () => void;
};

const ImageView: React.FC<TProps> = ({ src, className = '', ...props }) => {
  const [loaded, setLoaded] = useState(false);

  return (
    <StyledWrapper loaded={loaded} className={`img-view ${className}`}>
      <Image {...props} src={src} onLoad={() => setLoaded(true)} />
    </StyledWrapper>
  );
};

export default ImageView;

const StyledWrapper = styled.div`
  display: flex;
  overflow: hidden;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  align-items: center;

  img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: block;
    object-fit: cover;
    background: #eee;
  }

  ${({ loaded }) => css`
    opacity: 0;
    animation: ${loaded ? fadeIn : null} 0.2s forwards;
  `}
`;
