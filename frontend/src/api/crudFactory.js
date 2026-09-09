import axiosClient from "./axiosClient";

// Builds the standard save / update / get / list / delete calls that every
// controller in the backend exposes at the same shape:
//   POST   /resource
//   PUT    /resource/{id}
//   GET    /resource/{id}
//   GET    /resource
//   DELETE /resource/{id}
export default function createCrudApi(resourcePath, idKey) {
  return {
    list: () => axiosClient.get(resourcePath).then((r) => r.data),
    getById: (id) => axiosClient.get(`${resourcePath}/${id}`).then((r) => r.data),
    create: (payload) => axiosClient.post(resourcePath, payload).then((r) => r.data),
    update: (id, payload) => axiosClient.put(`${resourcePath}/${id}`, payload).then((r) => r.data),
    remove: (id) => axiosClient.delete(`${resourcePath}/${id}`).then((r) => r.data),
    count: () => axiosClient.get(`${resourcePath}/count`).then((r) => r.data),
    idKey,
  };
}
