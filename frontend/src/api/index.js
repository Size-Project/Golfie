import axios from 'axios';
import { getCookie } from 'utils/cookie';

export let axiosClient = axios.create({
  // baseURL: 'http://sample',
  headers: {
    Authorization: `Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjoiMiIsImlhdCI6MTY0NDUwMDMxMiwiZXhwIjoxNjQ0NTg2NzEyfQ.jA7-uos06JGlPilHuxqdgFwGyWcU4eIJPh3FpasxeD6pasWSdSwVCQK9d5ae3W_3ggZlYhBIPV13oYRLiBoCOw`,
  },
});

axiosClient.defaults.withCredentials = true;

class API {
  async CALL({ method, url, data = null, headers }) {
    const response = axiosClient({
      url,
      method,
      data,
      headers,
    });

    response.catch((error) => {
      console.log(error?.response?.status);
      if (error.response && error.response.status === 401) {
        // 로그인 쿠키 제거
        // authClient.removeCookie();
        // location.href = '/error/errorAuth';
      }

      console.error('#error-client-axios: ', error);
    });
    return response;
  }

  GET(url) {
    return this.CALL({
      method: 'GET',
      url,
    });
  }

  POST({ url, ...params }) {
    return this.CALL({
      method: 'POST',
      url,
      ...params,
    });
  }

  PUT({ url, ...params }) {
    return this.CALL({
      method: 'PUT',
      url,
      ...params,
    });
  }

  DELETE({ url, ...params }) {
    return this.CALL({
      method: 'DELETE',
      url,
      ...params,
    });
  }
}

export default new API();
