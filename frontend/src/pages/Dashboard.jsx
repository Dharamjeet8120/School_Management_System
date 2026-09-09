import { useEffect, useState } from "react";
import Layout from "../components/layout/Layout";
import { useAuth } from "../context/AuthContext";
import { classApi, examApi, feeApi, resultApi, studentApi, subjectApi, teacherApi } from "../api/entityApis";

function buildCards(role) {
  const cards = [];

  if (role === "ADMIN" || role === "TEACHER") {
    cards.push({ key: "students", label: "Students enrolled", fetch: studentApi.count });
    cards.push({ key: "classes", label: "Classes running", fetch: classApi.count });
    cards.push({ key: "subjects", label: "Subjects offered", fetch: subjectApi.count });
    cards.push({ key: "exams", label: "Exams scheduled", fetch: examApi.count });
    cards.push({ key: "results", label: "Results recorded", fetch: resultApi.count });
    cards.push({ key: "passed", label: "Passing results", fetch: resultApi.countPassed });
  }
  if (role === "ADMIN") {
    cards.push({ key: "teachers", label: "Teachers on staff", fetch: teacherApi.count });
  }
  if (role === "ADMIN" || role === "ACCOUNTS") {
    cards.push({ key: "fees", label: "Fee records", fetch: feeApi.count });
    cards.push({ key: "feesPending", label: "Fees pending", fetch: () => feeApi.countByStatus("PENDING") });
    cards.push({ key: "feesPaid", label: "Fees paid in full", fetch: () => feeApi.countByStatus("PAID") });
  }

  return cards;
}

export default function Dashboard() {
  const { role, username } = useAuth();
  const [values, setValues] = useState({});
  const [loading, setLoading] = useState(true);

  const cards = buildCards(role);

  useEffect(() => {
    let cancelled = false;
    setLoading(true);
    Promise.allSettled(cards.map((c) => c.fetch())).then((results) => {
      if (cancelled) return;
      const next = {};
      results.forEach((res, i) => {
        next[cards[i].key] = res.status === "fulfilled" ? res.value : null;
      });
      setValues(next);
      setLoading(false);
    });
    return () => {
      cancelled = true;
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [role]);

  return (
    <Layout title="Dashboard">
      <div className="page-header">
        <div>
          <div className="page-header-eyebrow">Overview</div>
          <h2>Welcome back, {username}</h2>
          <p>A snapshot of Ujjwal Public Vidhya Mandir's records as of today.</p>
        </div>
      </div>

      <div className="stat-grid">
        {cards.map((card) => (
          <div className="stat-card" key={card.key}>
            <div className="stat-value">{loading ? "…" : values[card.key] ?? "—"}</div>
            <div className="stat-label">{card.label}</div>
          </div>
        ))}
      </div>

      <div className="panel" style={{ padding: "20px 24px" }}>
        <h3 style={{ marginBottom: 8 }}>Getting around</h3>
        <p style={{ marginBottom: 0 }}>
          Use the sidebar to manage students, teachers, classes, subjects, exams, fees and results. Each
          section supports adding, editing, searching and deleting records, matching what the school office
          is authorised to see.
        </p>
      </div>
    </Layout>
  );
}
