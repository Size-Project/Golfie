import { useQuery } from 'react-query';
import API from 'app.modules/api';

const requestFn = async ({ queryKey, data }) => {
  try {
    const response = await API.GET({ url: queryKey, data: { ...data } });
    return response?.data;
  } catch (error) {
    throw error;
  }
};

const useQueryFn = <T>(queryKeys, options = {}) => {
  const queryKey = Array.isArray(queryKeys) ? queryKeys[0] : queryKeys;
  const data = Array.isArray(queryKeys) ? queryKeys[1] : null;

  return useQuery<T>(
    queryKeys,
    async () => await requestFn({ queryKey, data }),
    {
      enabled: !!queryKeys,
      onError: (err) => {
        // Toast.alert('예기치 못한 에러가 발생했습니다.');
      },
      ...options,
    }
  );
};

export default useQueryFn;
