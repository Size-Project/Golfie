import API from 'app.modules/api';
import { parseCookies } from 'nookies';

export const useStoreIntoAPP = (set) => {
  return {
    intoAPPPrefetch: async (ctx) => {
      try {
        const jwt = parseCookies(ctx)?.jwt ?? 'guest';
        const config = {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        };

        const response = await API.GET({
          url: '/api/users/me',
          data: { ...config },
        });

        set((state) => ({
          getUser: {
            ...state.getUser,
            login: true,
            isLoading: false,
            info: response.data,
          },
        }));
      } catch (err) {
        set((state) => ({
          getUser: {
            login: false,
            isLoading: false,
            info: null,
          },
        }));
      }
    },
  };
};
