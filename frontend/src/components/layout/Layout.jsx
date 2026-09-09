import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

const NAV_ITEMS = [
  { to: "/", label: "Dashboard", roles: ["ADMIN", "TEACHER", "ACCOUNTS"] },
  { to: "/students", label: "Students", roles: ["ADMIN", "TEACHER"] },
  { to: "/teachers", label: "Teachers", roles: ["ADMIN"] },
  { to: "/classes", label: "Classes", roles: ["ADMIN", "TEACHER"] },
  { to: "/subjects", label: "Subjects", roles: ["ADMIN", "TEACHER"] },
  { to: "/exams", label: "Exams", roles: ["ADMIN", "TEACHER"] },
  { to: "/results", label: "Results", roles: ["ADMIN", "TEACHER"] },
  { to: "/fees", label: "Fees", roles: ["ADMIN", "ACCOUNTS"] },
];

export default function Layout({ title, children }) {
  const { role, username, logout } = useAuth();
  const navigate = useNavigate();
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const visibleItems = NAV_ITEMS.filter((item) => item.roles.includes(role));

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <div className="app-shell">
      {sidebarOpen && <div className="sidebar-scrim" onClick={() => setSidebarOpen(false)} />}
      <aside className={`sidebar ${sidebarOpen ? "open" : ""}`}>
        <div className="sidebar-brand">
          <div className="sidebar-brand-mark">Ujjwal Public</div>
          <div className="sidebar-brand-sub">Vidhya Mandir — School Office</div>
        </div>
        <nav className="sidebar-nav">
          {visibleItems.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              end={item.to === "/"}
              className={({ isActive }) => `sidebar-link ${isActive ? "active" : ""}`}
              onClick={() => setSidebarOpen(false)}
            >
              {item.label}
            </NavLink>
          ))}
        </nav>
        <div className="sidebar-foot">Signed in as {username}</div>
      </aside>

      <div className="app-main">
        <header className="topbar">
          <div style={{ display: "flex", alignItems: "center", gap: 12 }}>
            <button className="menu-toggle" onClick={() => setSidebarOpen(true)} aria-label="Open menu">
              ☰
            </button>
            <h1 className="topbar-title">{title}</h1>
          </div>
          <div className="topbar-right">
            <span className="role-chip">{role}</span>
            <button type="button" className="btn btn-outline btn-sm" onClick={handleLogout}>
              Log out
            </button>
          </div>
        </header>
        <main className="page-body">{children}</main>
      </div>
    </div>
  );
}
