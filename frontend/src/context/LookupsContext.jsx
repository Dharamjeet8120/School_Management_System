import { createContext, useCallback, useContext, useRef, useState } from "react";
import { classApi, examApi, studentApi, subjectApi, teacherApi } from "../api/entityApis";

const RESOURCES = {
  students: {
    api: studentApi,
    idKey: "studentId",
    label: (s) => `${s.firstName} ${s.lastName}` + (s.admissionNumber ? ` (${s.admissionNumber})` : ""),
  },
  teachers: {
    api: teacherApi,
    idKey: "teacherId",
    label: (t) => `${t.firstName} ${t.lastName}` + (t.employeeId ? ` (${t.employeeId})` : ""),
  },
  classes: { api: classApi, idKey: "classId", label: (c) => `${c.className} - ${c.section}` },
  subjects: { api: subjectApi, idKey: "subjectId", label: (s) => s.subjectName },
  exams: { api: examApi, idKey: "examId", label: (e) => e.examName },
};

const LookupsContext = createContext(null);

export function LookupsProvider({ children }) {
  const [data, setData] = useState({});
  const inFlight = useRef(new Set());

  const ensureLoaded = useCallback(
    (type) => {
      if (!RESOURCES[type]) return;
      if (data[type] || inFlight.current.has(type)) return;
      inFlight.current.add(type);
      RESOURCES[type].api
        .list()
        .then((list) => setData((prev) => ({ ...prev, [type]: list })))
        .catch(() => setData((prev) => ({ ...prev, [type]: [] })))
        .finally(() => inFlight.current.delete(type));
    },
    [data]
  );

  const invalidate = useCallback((type) => {
    setData((prev) => {
      const next = { ...prev };
      delete next[type];
      return next;
    });
  }, []);

  const getList = useCallback(
    (type) => {
      ensureLoaded(type);
      return data[type] || [];
    },
    [data, ensureLoaded]
  );

  const getLabel = useCallback(
    (type, id) => {
      if (id === null || id === undefined || id === "") return "—";
      ensureLoaded(type);
      const list = data[type];
      if (!list) return "Loading…";
      const idKey = RESOURCES[type].idKey;
      const match = list.find((item) => String(item[idKey]) === String(id));
      return match ? RESOURCES[type].label(match) : `#${id}`;
    },
    [data, ensureLoaded]
  );

  const getOptions = useCallback(
    (type) => {
      const list = getList(type);
      const idKey = RESOURCES[type].idKey;
      return list.map((item) => ({ value: item[idKey], label: RESOURCES[type].label(item) }));
    },
    [getList]
  );

  const value = { getList, getLabel, getOptions, invalidate };

  return <LookupsContext.Provider value={value}>{children}</LookupsContext.Provider>;
}

export function useLookups() {
  const ctx = useContext(LookupsContext);
  if (!ctx) throw new Error("useLookups must be used within a LookupsProvider");
  return ctx;
}
