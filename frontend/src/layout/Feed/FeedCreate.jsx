import React from 'react';
import { StyledFeedCreate } from './styled';
import API from 'api';
import { useForm } from 'react-hook-form';

const FeedCreate = () => {
  const { handleSubmit, register } = useForm();

  const handleOnSubmit = async (values) => {
    try {
      const formData = new FormData();
      formData.append('content', values.content);
      for (let i = 0; i < values.feedImages.length; i++) {
        formData.append('feedImages', values.feedImages[i]);
      }

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
