import logo from './logo.svg';
import Header from './Components/Header';
import Home from './Components/Home';
import Footer from './Components/Footer';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './Components/Login';
import UserRegister from './Components/UserRegister';
import { MyDispatcherContext, MyUserContext } from './Configs/MyContexts';
import { useReducer } from 'react';
import MyUserReducer from './reducers/MyUserReducer';
import DoctorRegister from './Components/DoctorRegister';

function App() {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  return (
    <div>
      <MyUserContext.Provider value={user}>
      <MyDispatcherContext.Provider value={dispatch}>
        <BrowserRouter>
        <Header />
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/user-register" element={<UserRegister />} />
            <Route path="/doctor-register" element={<DoctorRegister />} />
        </Routes>
        <Footer />
        </BrowserRouter>
      </MyDispatcherContext.Provider>
      </MyUserContext.Provider>
    </div>
  );
}

export default App;
