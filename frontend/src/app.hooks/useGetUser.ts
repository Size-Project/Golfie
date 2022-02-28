import { useStoreSSR } from 'app.store/rootStore';
import { useStoreUserInfo } from 'app.store/intoAPP/store.intoAPP';

export const useGetUser = () => {
  return useStoreUserInfo((state: any) => state.getUser);
};

export default useGetUser;
