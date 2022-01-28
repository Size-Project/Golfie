import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HomePage from 'pages/Home';
import LoginPage from 'pages/Login';
import SignupPage from 'pages/Signup';
import ErrorPage from 'pages/Error';
import axios from 'axios';
import { getCookie } from 'utils/cookie';

const getUser = async () => {
  const jwt = getCookie('jwt');
  const config = {
    headers: { Authorization: `Bearer ${jwt}` },
  };

  const response = await axios.get('/api/users/me', config);
  console.log(response.data);
};

getUser();

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/account/login/*" element={<LoginPage />} />
        <Route path="/account/signup/*" element={<SignupPage />} />
        <Route path="/*" element={<ErrorPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;
