import axiosClient from "./axiosClient";

const authApi = {
  login: (username, password) =>
    axiosClient.post("/auth/login", { username, password }).then((r) => r.data),
  register: (username, password, role) =>
    axiosClient.post("/auth/register", { username, password, role }).then((r) => r.data),
};

export default authApi;
