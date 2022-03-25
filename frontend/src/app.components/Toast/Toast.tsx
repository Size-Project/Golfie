import React from 'react';
import ReactDOM from 'react-dom';
import { errorMessage } from 'app.modules/constant/errorMessage';
import ToastAlert from './ToastAlert';
import ToastSuccess from 'app.components/Toast/ToastSuccess';
import ToastError from 'app.components/Toast/ToastError';

const __toast_alert = '__toast_alert';
const __toast_success = '__toast_success';
const __toast_error = '__toast_error';

const removeComponent = (id) => {
  const createModalDom = document.getElementById(id);
  ReactDOM.unmountComponentAtNode(createModalDom);
};

const openComponent = (props) => {
  if (typeof window === 'undefined') return;
  if (!props.component) return;

  let createModalDom = document.getElementById(props.id);

  if (!createModalDom) {
    createModalDom = document.createElement('div');
    createModalDom.id = props.id;
    document.body.appendChild(createModalDom);
  }

  setTimeout(() => {
    ReactDOM.render(<props.component {...props} />, createModalDom);
  });
};

const alert = (text = errorMessage.failed, type = 'top') => {
  openComponent({
    text,
    type,
    id: __toast_alert,
    component: ToastAlert,
  });

  setTimeout(() => {
    removeComponent(__toast_alert);
  }, 5000);
};

const success = (text = '성공했습니다.', type = 'top') => {
  openComponent({
    text,
    type,
    id: __toast_success,
    component: ToastSuccess,
  });

  setTimeout(() => {
    removeComponent(__toast_success);
  }, 5000);
};

const error = (text = '성공했습니다.', type = 'top') => {
  openComponent({
    text,
    type,
    id: __toast_error,
    component: ToastError,
  });

  setTimeout(() => {
    removeComponent(__toast_error);
  }, 5000);
};

const Toast = {
  alert: alert,
  success: success,
  error: error,
};

export default Toast;
