import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HomePage from 'pages/Home';
import LoginPage from 'pages/Login';
import SignupPage from 'pages/Signup';
import ErrorPage from 'pages/Error';
import NavigationBar from './component/NavigationBar/NavigationBar';
import FeedPage from './pages/Feed';
import JoinPage from './pages/Join';
import ForYouPage from './pages/ForYou';
import ForYouDetailPage from 'pages/ForYou/Detail';
import MyPage from 'pages/My';
import FeedCreatePage from './pages/Feed/create';
import useStoreIntoAPP from './services/store.intoAPP';
import { useEffect } from 'react';

function AppRouter() {
  const requestUserInfo = useStoreIntoAPP((state) => state.requestUserInfo);

  useEffect(() => {
    requestUserInfo();
  });

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/join" element={<JoinPage />} />
        <Route path="/feed/create" element={<FeedCreatePage />} />
        <Route path="/feed" element={<FeedPage />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/account/login/*" element={<LoginPage />} />
        <Route path="/account/signup/*" element={<SignupPage />} />
        <Route path="/foryou" element={<ForYouPage />} />
        <Route path="/foryou/detail" element={<ForYouDetailPage />} />
        <Route path="/my" element={<MyPage />} />
        <Route path="/*" element={<ErrorPage />} />
      </Routes>
      <NavigationBar />
    </BrowserRouter>
  );
}

export default AppRouter;