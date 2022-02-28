import React from 'react';
import styled from 'styled-components';
import { useForm } from 'react-hook-form';
import API from 'app.modules/api';
import { API_FEEDS_CREATE } from 'app.modules/api/fieldtrip.feed';

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
        url: API_FEEDS_CREATE,
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
    <StyledWrapper>
      <form onSubmit={handleSubmit(handleOnSubmit)}>
        <input {...register('content')} />
        <input {...register('feedImages')} type="file" multiple />
        <button type="submit">제출</button>
      </form>
    </StyledWrapper>
  );
};

export default FeedCreate;

const StyledWrapper = styled.div``;
