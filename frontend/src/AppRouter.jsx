import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HomePage from 'pages/Home';
import LoginPage from 'pages/Login';
import SignupPage from 'pages/Signup';
import ErrorPage from 'pages/Error';
import NavigationBar from './component/NavigationBar/NavigationBar';
import FeedPage from './pages/Feed';
import ForYouPage from 'pages/ForYou';
import ForYouDetailPage from 'pages/ForYou/Detail';
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
        <Route path="/feed" element={<FeedPage />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/account/login/*" element={<LoginPage />} />
        <Route path="/account/signup/*" element={<SignupPage />} />
        <Route path="/foryou" element={<ForYouPage />} />
        <Route path="/foryou/detail" element={<ForYouDetailPage />} />
        <Route path="/*" element={<ErrorPage />} />
      </Routes>
      <NavigationBar />
    </BrowserRouter>
  );
}

export default AppRouter;
