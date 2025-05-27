import Header from './Components/Header';
import Home from './Components/Home';
import Footer from './Components/Footer';
import { BrowserRouter, Navigate, Outlet, Route, Routes } from 'react-router-dom';
import Login from './Components/Login';
import logo from './logo.svg';
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
import Search from './Components/Search';
import BookingPage from './Components/BookingPage';
import Specialty from './Components/Specialty';
import SelectSpecialty from './Components/SelectSpecialty';
import DoctorDashboard from './Components/DoctorDashboard';
import LichKham from './Components/DoctorSchedule';
import CapNhatHoSo from './Components/UpdateHoSoBenhNhan';
import BookingSummary from './Components/Appointment';
import DoctorDetail from './Components/DoctorDetail';
import CallWrapper from './Components/RoomCall';
import USERCHAT from './Components/USERCHATTEST';

function init(initialUser) {
  if (!initialUser) {
    const storedUser = localStorage.getItem("user");
    return storedUser ? JSON.parse(storedUser) : null;
  }
  return initialUser;
}

const ProtectedRoute = ({ allowedRoles, user }) => {
  if (!user) {
    return <Navigate to="/login" />;
  }

  if (!allowedRoles.includes(user.user.role)) {
    return <Navigate to="/" />;
  }
  return <Outlet />;
};

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
              <Route element={<ProtectedRoute allowedRoles={['ROLE_USER']} user={user} />}>
                <Route path="/patient" element={<Patient />} />
                <Route path="/lich-kham" element={<BookingSummary />} />
                <Route path="/tao-ho-so" element={<AddPatientRecord />} />
              </Route>
              {/* <Route path="/patient" element={<Patient />} />
                <Route path="/lich-kham" element={<BookingSummary />} />
                <Route path="/tao-ho-so" element={<AddPatientRecord />} /> */}
              
              <Route path="/dat-kham-theo-co-so" element={<MedicalFacilityList />} />
              <Route path="/tim-kiem?" element={<Search />} />
              <Route element={<ProtectedRoute allowedRoles={['ROLE_DOCTOR']} user={user} />}>
                <Route path="/bac-si/dashboard" element={<DoctorDashboard />} />
                <Route path="/bac-si-lich-kham" element={<LichKham />} />
                <Route path="/bac-si/lich-su-kham-benh/:hoSoId" element={<CapNhatHoSo />} />
              </Route>
              <Route path="/dat-lich-kham" element={<BookingPage />} />
              <Route path="/chi-tiet-bac-si" element={<DoctorDetail />} />
              <Route path="/chat-user" element={<USERCHAT />} />
            </Routes>
            <Footer />
          </BrowserRouter>
        </MyDispatcherContext.Provider>
      </MyUserContext.Provider>
    </div>
  );
}

export default App;