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
import MedicalFacilityCard from './Components/MedicalFacilityCard';
import MedicalFacilityList from './Components/MedicalFacilityList';
import ChatPage from './Components/ChatPage';
import Search from './Components/Search';
import BookingPage from './Components/BookingPage';
import Specialty from './Components/Specialty';
import SelectSpecialty from './Components/SelectSpecialty';

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
            <Route path="/dat-kham-theo-co-so" element={<MedicalFacilityList />} />
            <Route path="/chat-truc-tuyen" element={<ChatPage />} />
            <Route path="/tim-kiem?" element={<Search />} />
            <Route path="/dat-lich-kham" element={<SelectSpecialty />} />
        </Routes>
        <Footer />
        </BrowserRouter>
      </MyDispatcherContext.Provider>
      </MyUserContext.Provider>
    </div>
  );
}

export default App;
