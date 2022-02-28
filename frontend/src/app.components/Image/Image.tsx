import React from 'react';

type TProps = {
  src: string;
  errorSrc: string;
};

const Image: React.FC<TProps | any> = ({
  src,
  errorSrc = '/images/common/logo.png',
  ...props
}) => {
  const onError = ({ target }) => (target.src = errorSrc);
  return <img src={src} onError={onError} {...props} />;
};

export default Image;
