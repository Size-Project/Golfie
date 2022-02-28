import { parseCookies } from 'nookies';
import axios from 'axios';

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

        const response = await axios.get(
          `${process.env.FIELD_TRIP_API_URI}/api/users/me`,
          config
        );

        console.log(response);
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
  };
};
