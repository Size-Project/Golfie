import React, { useState } from 'react';
import styled from 'styled-components';
import { useForm, FormProvider } from 'react-hook-form';
import JoinCreateHeader from 'app.feature/join/create/component/JoinCreateHeader';
import JoinCreateStepOne from 'app.feature/join/create/component/JoinCreateStepOne';
import JoinCreateStepTwo from 'app.feature/join/create/component/JoinCreateStepTwo';

const ScreenJoinCreate = () => {
  const methods = useForm();
  const [step, setStep] = useState(1);

  return (
    <StyledWrapper>
      <FormProvider {...methods}>
        <form>
          <JoinCreateHeader />
          {step === 1 && <JoinCreateStepOne setStep={setStep} />}
          {step === 2 && <JoinCreateStepTwo />}
        </form>
      </FormProvider>
    </StyledWrapper>
  );
};

export default ScreenJoinCreate;

const StyledWrapper = styled.div``;
