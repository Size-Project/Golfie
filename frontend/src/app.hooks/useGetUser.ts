import { useStoreSSR } from 'app.store/rootStore';

export const useGetUser = () => {
  return useStoreSSR((state: any) => state.getUser);
};

export default useGetUser;
