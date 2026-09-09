import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

export default function ProtectedRoute({ roles, children }) {
  const { isAuthenticated, role } = useAuth();
  const location = useLocation();

  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  if (roles && !roles.includes(role)) {
    return (
      <div className="page-body">
        <div className="panel" style={{ padding: 32 }}>
          <h2>Access restricted</h2>
          <p>Your role ({role}) doesn't have permission to view this section.</p>
        </div>
      </div>
    );
  }

  return children;
}
