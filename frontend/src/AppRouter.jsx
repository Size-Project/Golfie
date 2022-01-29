import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HomePage from 'pages/Home';
import LoginPage from 'pages/Login';
import SignupPage from 'pages/Signup';
import ErrorPage from 'pages/Error';
import NavigationBar from './component/NavigationBar/NavigationBar';
import FeedPage from './pages/Feed';
import ForYouPage from 'pages/ForYou';

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/feed" element={<FeedPage />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/account/login/*" element={<LoginPage />} />
        <Route path="/account/signup/*" element={<SignupPage />} />
        <Route path="/foryou" element={<ForYouPage />} />
        <Route path="/*" element={<ErrorPage />} />
      </Routes>
      <NavigationBar />
    </BrowserRouter>
  );
}

export default AppRouter;
