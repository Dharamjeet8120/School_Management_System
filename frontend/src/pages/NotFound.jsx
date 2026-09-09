import { Link } from "react-router-dom";

export default function NotFound() {
  return (
    <div className="auth-shell" style={{ background: "var(--paper)" }}>
      <div className="auth-card" style={{ textAlign: "center" }}>
        <h1 style={{ fontSize: 40, marginBottom: 6 }}>404</h1>
        <p>That page doesn't exist in the school office system.</p>
        <Link to="/" className="btn btn-primary" style={{ marginTop: 10 }}>
          Back to dashboard
        </Link>
      </div>
    </div>
  );
}
