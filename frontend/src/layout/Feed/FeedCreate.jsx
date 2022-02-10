import React from 'react';
import { StyledFeedCreate } from './styled';
import API from 'api';
import { useForm } from 'react-hook-form';

const FeedCreate = () => {
  const { handleSubmit, register } = useForm();

  const handleOnSubmit = async (values) => {
    try {
      const formData = new FormData();
      console.log(values.feedImages);
      formData.append('content', values.content);
      values.feedImages.forEach((item) => {
        formData.append('feedImages', item);
      });

      await API.POST({
        url: '/api/feeds',
        data: formData,
        headers: {
          'Content-Type': 'multipart/form-data',
          type: 'formData',
        },
      });
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <StyledFeedCreate>
      <form onSubmit={handleSubmit(handleOnSubmit)}>
        <input {...register('content')} />
        <input {...register('feedImages')} type="file" multiple />
        <button type="submit">제출</button>
      </form>
    </StyledFeedCreate>
  );
};

export default FeedCreate;
