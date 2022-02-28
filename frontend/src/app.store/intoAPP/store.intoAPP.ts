import { parseCookies } from 'nookies';
import axios from 'axios';
import create from 'zustand';
import API from 'app.modules/api';

export const useStoreUserInfo = create<any>((set, get) => ({
  getUser: {
    login: false,
    isLoading: true,
    info: null,
  },

  requestAuthUser: async () => {
    try {
      const response = await API.GET({
        url: `/api/users/me`,
        data: {},
      });

      if (!response.data) throw response;
      response.data &&
        set((state) => ({
          getUser: {
            ...state.getUser,
            login: true,
            isLoading: false,
            info: response.data,
          },
        }));
    } catch (err) {
      console.log(err);
      set((state) => ({
        getUser: {
          login: false,
          isLoading: false,
          info: null,
        },
      }));
    }
  },
}));
