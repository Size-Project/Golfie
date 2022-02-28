import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { FormProvider, useForm } from 'react-hook-form';
import SignupProfile from 'app.feature/account/signup/component/SignupProfile';
import SignupKeyword from 'app.feature/account/signup/component/SignupKeyword';
import { useRouter } from 'next/router';
import API from 'app.modules/api';
import { setCookie } from 'nookies';

const ScreenSignup = () => {
  const router = useRouter();

  const methods = useForm();
  const [stage, setStage] = useState('profile');
  const [user, setUser] = useState({});

  const handleChangeStage = (tab) => {
    setStage(tab);
  };

  const handleOnSubmit = async (formData) => {
    try {
      const signupData = {
        ...formData,
        ...user,
      };
      const res = await API.POST({
        url: '/api/signup/oauth',
        data: signupData,
      });
      setCookie(null, 'jwt', res.data.accessToken, {
        path: '/',
        maxAge: 30000000,
      });
      router.push('/');
    } catch (err) {}
  };

  const requestPrepareSignup = async (code) => {
    try {
      const res = await API.POST({
        url: '/api/signup/oauth/prepare',
        data: { code, providerName: 'KAKAO' },
      });
      setUser({ ...res.data });
    } catch (error) {
      alert('카카오 회원가입 에러');
    }
  };

  useEffect(() => {
    const { code } = router.query;
    code && requestPrepareSignup(code);
  }, []);

  return (
    <StyledWrapper>
      <FormProvider {...methods}>
        <form onSubmit={methods.handleSubmit(handleOnSubmit)}>
          {stage === 'profile' && (
            <SignupProfile handleChangeStage={handleChangeStage} />
          )}
          {stage === 'keyword' && <SignupKeyword />}
        </form>
      </FormProvider>
    </StyledWrapper>
  );
};

export default ScreenSignup;

const StyledWrapper = styled.div``;
