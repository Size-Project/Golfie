import axios from 'axios';
import { qs } from 'app.modules/util/qs';
import { parseCookies } from 'nookies';

const jwt = parseCookies()?.jwt ?? 'guest';

let axiosClient = axios.create({
  baseURL: process.env.FIELD_TRIP_API_URI,
  headers: {
    Authorization: `Bearer ${jwt}`,
  },
});

export const request: any = async ({ url, method, data = null }) => {
  try {
    console.log(url);
    const response: any = await axiosClient({
      method,
      url,
      data,
    });

    return response;
  } catch (error) {
    console.error('#error-web-client: ', error.toString());
    if (error.toString().includes('Network Error')) {
      // await Toast.alert(
      //   '네트워크 오류로 처리되지 않았습니다.\n다시 시도해주세요.'
      // );
    }
    // await Toast.alert(errorMessage.failed);
    return error;
  }
};

class API {
  async CALL({ method, url, data = null }) {
    return request({ method, url, data });
  }

  GET({ url, ...params }) {
    return this.CALL({
      method: 'GET',
      url: url + qs.stringURL(params.data),
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
