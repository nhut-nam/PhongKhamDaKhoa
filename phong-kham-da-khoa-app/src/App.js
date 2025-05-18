import logo from './logo.svg';
import Header from './Components/Header';
import Home from './Components/Home';
import Footer from './Components/Footer';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './Components/Login';
import UserRegister from './Components/UserRegister';
import { MyDispatcherContext, MyUserContext } from './Configs/MyContexts';
import { useEffect, useReducer } from 'react';
import MyUserReducer from './reducers/MyUserReducer';
import DoctorRegister from './Components/DoctorRegister';
import ServicesPage from './Components/ServicesPage';
import UserProfile from './Components/UserProfile';
import Patient from './Components/Patient';
import AddPatientRecord from './Components/AddPatientRecord';

function init(initialUser) {
  if (!initialUser) {
    const storedUser = localStorage.getItem("user");
    return storedUser ? JSON.parse(storedUser) : null;
  }
  return initialUser;
}

function App() {
  const [user, dispatch] = useReducer(MyUserReducer, null, init);

  useEffect(() => {
    if (user) {
      localStorage.setItem("user", JSON.stringify(user));
    } else {
      localStorage.removeItem("user");
    }
  }, [user]);

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
            <Route path="/dich-vu" element={<ServicesPage />} />
            <Route path="/patient" element={<Patient />} />
            <Route path="/tao-ho-so" element={<AddPatientRecord />} />
        </Routes>
        <Footer />
        </BrowserRouter>
      </MyDispatcherContext.Provider>
      </MyUserContext.Provider>
    </div>
  );
}

export default App;
