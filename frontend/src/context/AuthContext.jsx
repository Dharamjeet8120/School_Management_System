import { createContext, useContext, useMemo, useState } from "react";
import authApi from "../api/authApi";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [token, setToken] = useState(() => localStorage.getItem("upvm_token"));
  const [role, setRole] = useState(() => localStorage.getItem("upvm_role"));
  const [username, setUsername] = useState(() => localStorage.getItem("upvm_username"));

  const login = async (usernameInput, password) => {
    const data = await authApi.login(usernameInput, password);
    localStorage.setItem("upvm_token", data.token);
    localStorage.setItem("upvm_role", data.role);
    localStorage.setItem("upvm_username", usernameInput);
    setToken(data.token);
    setRole(data.role);
    setUsername(usernameInput);
    return data;
  };

  const register = (usernameInput, password, roleInput) =>
    authApi.register(usernameInput, password, roleInput);

  const logout = () => {
    localStorage.removeItem("upvm_token");
    localStorage.removeItem("upvm_role");
    localStorage.removeItem("upvm_username");
    setToken(null);
    setRole(null);
    setUsername(null);
  };

  const value = useMemo(
    () => ({
      token,
      role,
      username,
      isAuthenticated: Boolean(token),
      login,
      register,
      logout,
    }),
    [token, role, username]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("useAuth must be used within an AuthProvider");
  return ctx;
}
