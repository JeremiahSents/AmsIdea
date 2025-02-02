import Login from "./components/Auth/Login.jsx";
import Signup from "./components/Auth/Signup.jsx";
import { Routes, Route, Navigate } from 'react-router-dom';

function App() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" replace />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
        </Routes>
    );
}

export default App;
