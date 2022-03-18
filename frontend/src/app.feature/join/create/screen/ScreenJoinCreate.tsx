import React, { useState } from 'react';
import styled from 'styled-components';
import { useForm, FormProvider } from 'react-hook-form';
import JoinCreateHeader from 'app.feature/join/create/component/JoinCreateHeader';
import JoinCreateStepOne from 'app.feature/join/create/component/JoinCreateStepOne';
import JoinCreateStepTwo from 'app.feature/join/create/component/JoinCreateStepTwo';
import API from 'app.modules/api';
import { API_ROUNDINGS_CREATE } from 'app.modules/api/fieldtrip.join';
import { useRouter } from 'next/router';

const ScreenJoinCreate = () => {
  const router = useRouter();
  const methods = useForm();
  const [step, setStep] = useState(1);

  const handleCreateJoin = async (formData) => {
    try {
      await API.POST({
        url: API_ROUNDINGS_CREATE,
        data: {
          courseName: '서울 C.C',
          title: '맛집 탐방까지 같이하실 분 구함',
          content: '골프 치고 맛집 탐방할 사람 구합니다~!!',
          price: 50000,
          joinNum: 4,
          dateTime: '2022-04-27T01:01:00',
          preferredHit: '100-120',
          preferredAge: '20-29',
          preferredMood: '즐거운',
        },
      });
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
