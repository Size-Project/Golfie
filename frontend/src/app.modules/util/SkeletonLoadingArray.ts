export const SkeletonLoadingArray = (count = 5) => {
  const arr = Array.from(Array(count).keys());
  return arr.map((v) => {
    return {
      isSkeleton: true,
    };
  });
};
