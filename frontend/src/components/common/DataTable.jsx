import { useLookups } from "../../context/LookupsContext";
import { humanizeEnum } from "../../config/enums";

function formatDate(value) {
  if (!value) return "—";
  const d = new Date(value);
  if (Number.isNaN(d.getTime())) return value;
  return d.toLocaleDateString("en-IN", { day: "2-digit", month: "short", year: "numeric" });
}

function formatCurrency(value) {
  if (value === null || value === undefined || value === "") return "—";
  return `₹${Number(value).toLocaleString("en-IN")}`;
}

function CellValue({ column, row }) {
  const { getLabel } = useLookups();

  if (column.render) {
    return <>{column.render(row) || "—"}</>;
  }

  const value = row[column.key];

  switch (column.type) {
    case "date":
      return <>{formatDate(value)}</>;
    case "currency":
      return <>{formatCurrency(value)}</>;
    case "boolean":
      return (
        <span className={`status-text ${value ? "status-forest" : "status-ink"}`}>{value ? "Active" : "Inactive"}</span>
      );
    case "enum":
      return <>{humanizeEnum(value)}</>;
    case "lookup":
      return <>{getLabel(column.lookupType, value)}</>;
    case "status": {
      const tone = column.statusMap?.[String(value)] || "ink";
      const display = column.displayMap ? column.displayMap[String(value)] : humanizeEnum(String(value));
      return <span className={`status-text status-${tone}`}>{value === null || value === undefined ? "—" : display}</span>;
    }
    default:
      return <>{value === null || value === undefined || value === "" ? "—" : String(value)}</>;
  }
}

export default function DataTable({ columns, rows, idKey, onEdit, onDelete, loading, emptyLabel }) {
  if (loading) {
    return <div className="loading-row">Loading records…</div>;
  }

  if (!rows.length) {
    return (
      <div className="empty-state">
        <h3>Nothing here yet</h3>
        <p>{emptyLabel || "Add the first record to get started."}</p>
      </div>
    );
  }

  return (
    <div className="table-wrap">
      <table className="data-table">
        <thead>
          <tr>
            {columns.map((col) => (
              <th key={col.key} className={col.type === "num" || col.type === "currency" ? "num" : undefined}>
                {col.label}
              </th>
            ))}
            <th></th>
          </tr>
        </thead>
        <tbody>
          {rows.map((row) => (
            <tr key={row[idKey]}>
              {columns.map((col) => (
                <td key={col.key} className={col.type === "num" || col.type === "currency" ? "num" : undefined}>
                  <CellValue column={col} row={row} />
                </td>
              ))}
              <td>
                <div className="row-actions">
                  <button type="button" className="btn-text" onClick={() => onEdit(row)}>
                    Edit
                  </button>
                  <button type="button" className="btn-text" onClick={() => onDelete(row)}>
                    Delete
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
