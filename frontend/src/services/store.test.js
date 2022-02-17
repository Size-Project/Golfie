import create from 'zustand';

export const useStoreTest = create((set, get) => ({
  test: {
    testData: 0,
  },

  updateTest: (newData) => {
    set((state) => ({
      test: {
        testData: state.test.testData + 1,
      },
    }));
  },
}));
