import axiosClient from "./axiosClient";
import createCrudApi from "./crudFactory";

export const studentApi = {
  ...createCrudApi("/students", "studentId"),
  search: (keyword) => axiosClient.get("/students/search", { params: { keyword } }).then((r) => r.data),
};

export const teacherApi = createCrudApi("/teachers", "teacherId");

export const classApi = createCrudApi("/classes", "classId");

export const subjectApi = createCrudApi("/subjects", "subjectId");

export const examApi = createCrudApi("/exams", "examId");

export const feeApi = {
  ...createCrudApi("/fees", "feeId"),
  byStudent: (studentId) => axiosClient.get(`/fees/student/${studentId}`).then((r) => r.data),
  countByStatus: (status) => axiosClient.get(`/fees/count/status/${status}`).then((r) => r.data),
};

export const resultApi = {
  ...createCrudApi("/results", "resultId"),
  byStudent: (studentId) => axiosClient.get(`/results/student/${studentId}`).then((r) => r.data),
  countPassed: () => axiosClient.get("/results/count/passed").then((r) => r.data),
};
