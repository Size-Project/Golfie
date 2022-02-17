import create from 'zustand';
import { getCookie } from '../utils/cookie';
import axios from 'axios';

const useStoreIntoAPP = create((set, get) => ({
  getUser: {
    login: false,
    isLoading: true,
    info: null,
  },

  requestUserInfo: async () => {
    const jwt = getCookie('jwt');
    const config = {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    };

    const response = await axios.get('/api/users/me', config);
    console.log(response.data);

    if (response.data)
      set((state) => ({
        getUser: {
          ...state.getUser,
          login: true,
          isLoading: false,
          info: response.data,
        },
      }));
  },
}));

export default useStoreIntoAPP;

export const useGetUser = () => {
  return useStoreIntoAPP((state) => state.getUser);
};
