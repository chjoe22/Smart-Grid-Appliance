import { Navigate } from "react-router-dom";
import { useAuth } from "../authContext/AuthContext";

const PrivateRouting = ({ children }) => {
    const { user } = useAuth();

    return user ? children : <Navigate to="/login" replace />;
};

export default PrivateRouting;