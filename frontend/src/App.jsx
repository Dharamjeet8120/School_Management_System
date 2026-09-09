import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import NotFound from "./pages/NotFound";
import EntityCrudPage from "./pages/EntityCrudPage";
import ProtectedRoute from "./components/common/ProtectedRoute";
import {
  classConfig,
  examConfig,
  feeConfig,
  resultConfig,
  studentConfig,
  subjectConfig,
  teacherConfig,
} from "./config/entityConfigs";
import { useAuth } from "./context/AuthContext";

function PublicOnly({ children }) {
  const { isAuthenticated } = useAuth();
  if (isAuthenticated) return <Navigate to="/" replace />;
  return children;
}

export default function App() {
  return (
    <Routes>
      <Route
        path="/login"
        element={
          <PublicOnly>
            <Login />
          </PublicOnly>
        }
      />
      <Route
        path="/register"
        element={
          <PublicOnly>
            <Register />
          </PublicOnly>
        }
      />

      <Route
        path="/"
        element={
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        }
      />

      <Route
        path="/students"
        element={
          <ProtectedRoute roles={studentConfig.roles}>
            <EntityCrudPage config={studentConfig} />
          </ProtectedRoute>
        }
      />
      <Route
        path="/teachers"
        element={
          <ProtectedRoute roles={teacherConfig.roles}>
            <EntityCrudPage config={teacherConfig} />
          </ProtectedRoute>
        }
      />
      <Route
        path="/classes"
        element={
          <ProtectedRoute roles={classConfig.roles}>
            <EntityCrudPage config={classConfig} />
          </ProtectedRoute>
        }
      />
      <Route
        path="/subjects"
        element={
          <ProtectedRoute roles={subjectConfig.roles}>
            <EntityCrudPage config={subjectConfig} />
          </ProtectedRoute>
        }
      />
      <Route
        path="/exams"
        element={
          <ProtectedRoute roles={examConfig.roles}>
            <EntityCrudPage config={examConfig} />
          </ProtectedRoute>
        }
      />
      <Route
        path="/results"
        element={
          <ProtectedRoute roles={resultConfig.roles}>
            <EntityCrudPage config={resultConfig} />
          </ProtectedRoute>
        }
      />
      <Route
        path="/fees"
        element={
          <ProtectedRoute roles={feeConfig.roles}>
            <EntityCrudPage config={feeConfig} />
          </ProtectedRoute>
        }
      />

      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
