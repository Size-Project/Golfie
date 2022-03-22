import React, { useState } from 'react';
import styled from 'styled-components';
import { useForm, FormProvider } from 'react-hook-form';
import JoinCreateHeader from 'app.feature/join/create/component/JoinCreateHeader';
import JoinCreateStepOne from 'app.feature/join/create/component/JoinCreateStepOne';
import JoinCreateStepTwo from 'app.feature/join/create/component/JoinCreateStepTwo';
import API from 'app.modules/api';
import {
  API_ROUNDINGS_ALL,
  API_ROUNDINGS_CREATE,
} from 'app.modules/api/fieldtrip.join';
import { useRouter } from 'next/router';
import moment from 'moment';
import { useQueryClient } from 'react-query';

const ScreenJoinCreate = () => {
  const router = useRouter();
  const methods = useForm();
  const queryClient = useQueryClient();
  const [step, setStep] = useState(1);

  const handleCreateJoin = async (formData) => {
    try {
      const dateTime = `${formData.dateYear}-${formData.dateMonth}-${
        formData.dateDay
      } ${
        formData.timeDay === '오전'
          ? formData.timeHour
          : Number(formData.timeHour) + 12
      }:${formData.timeMinute}`;

      await API.POST({
        url: API_ROUNDINGS_CREATE,
        data: {
          courseName: formData.courseName,
          title: formData.title,
          content: formData.content,
          price: formData.price,
          joinNum: formData.joinNum,
          dateTime: moment(dateTime).format('YYYY-MM-DDTHH:mm:00'),
          preferredHit: formData.preferredHit,
          preferredAge: formData.preferredAge,
          preferredMood: formData.preferredMood,
        },
      });
      queryClient.removeQueries(API_ROUNDINGS_ALL);
      router.push('/join');
    } catch (err) {}
  };

  return (
    <StyledWrapper>
      <FormProvider {...methods}>
        <form onSubmit={methods.handleSubmit(handleCreateJoin)}>
          <JoinCreateHeader />
          {step === 1 && <JoinCreateStepOne setStep={setStep} />}
          {step === 2 && (
            <JoinCreateStepTwo handleCreateJoin={handleCreateJoin} />
          )}
        </form>
      </FormProvider>
    </StyledWrapper>
  );
};

export default ScreenJoinCreate;

const StyledWrapper = styled.div``;
