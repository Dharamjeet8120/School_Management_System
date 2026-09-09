import { useEffect, useMemo, useState } from "react";
import Layout from "../components/layout/Layout";
import DataTable from "../components/common/DataTable";
import RecordDrawer from "../components/common/RecordDrawer";
import ConfirmDialog from "../components/common/ConfirmDialog";
import { extractErrorMessage } from "../api/axiosClient";
import { useLookups } from "../context/LookupsContext";

export default function EntityCrudPage({ config }) {
  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);
  const [loadError, setLoadError] = useState("");
  const [query, setQuery] = useState("");

  const [drawerOpen, setDrawerOpen] = useState(false);
  const [editingRecord, setEditingRecord] = useState(null);
  const [submitting, setSubmitting] = useState(false);
  const [formError, setFormError] = useState("");

  const [pendingDelete, setPendingDelete] = useState(null);
  const [deleting, setDeleting] = useState(false);

  const { invalidate } = useLookups();

  const load = () => {
    setLoading(true);
    setLoadError("");
    config.api
      .list()
      .then((data) => setRows(data))
      .catch((err) => setLoadError(extractErrorMessage(err, "Couldn't load records.")))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    load();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [config.key]);

  const filteredRows = useMemo(() => {
    if (!query.trim()) return rows;
    const q = query.trim().toLowerCase();
    return rows.filter((row) =>
      config.searchFields.some((field) => String(row[field] ?? "").toLowerCase().includes(q))
    );
  }, [rows, query, config.searchFields]);

  const openAdd = () => {
    setEditingRecord(null);
    setFormError("");
    setDrawerOpen(true);
  };

  const openEdit = (row) => {
    setEditingRecord(row);
    setFormError("");
    setDrawerOpen(true);
  };

  const closeDrawer = () => {
    if (submitting) return;
    setDrawerOpen(false);
  };

  const handleSubmit = async (values) => {
    setSubmitting(true);
    setFormError("");
    try {
      if (editingRecord) {
        await config.api.update(editingRecord[config.idKey], values);
      } else {
        await config.api.create(values);
      }
      setDrawerOpen(false);
      invalidate(config.key);
      load();
    } catch (err) {
      setFormError(extractErrorMessage(err, "Couldn't save this record. Check the fields and try again."));
    } finally {
      setSubmitting(false);
    }
  };

  const confirmDelete = async () => {
    if (!pendingDelete) return;
    setDeleting(true);
    try {
      await config.api.remove(pendingDelete[config.idKey]);
      setPendingDelete(null);
      invalidate(config.key);
      load();
    } catch (err) {
      setLoadError(extractErrorMessage(err, "Couldn't delete this record."));
      setPendingDelete(null);
    } finally {
      setDeleting(false);
    }
  };

  return (
    <Layout title={config.title}>
      <div className="page-header">
        <div>
          <div className="page-header-eyebrow">{config.eyebrow}</div>
          <h2>{config.title}</h2>
          <p>{config.description}</p>
        </div>
        <button type="button" className="btn btn-brass" onClick={openAdd}>
          Add {config.singular.toLowerCase()}
        </button>
      </div>

      {loadError && <div className="alert alert-error">{loadError}</div>}

      <div className="panel">
        <div style={{ padding: "16px 16px 0" }}>
          <div className="toolbar">
            <input
              className="search-input"
              type="text"
              placeholder={`Search ${config.title.toLowerCase()}…`}
              value={query}
              onChange={(e) => setQuery(e.target.value)}
            />
            <span className="muted">
              {filteredRows.length} {filteredRows.length === 1 ? "record" : "records"}
            </span>
          </div>
        </div>
        <DataTable
          columns={config.columns}
          rows={filteredRows}
          idKey={config.idKey}
          onEdit={openEdit}
          onDelete={setPendingDelete}
          loading={loading}
          emptyLabel={`Add the first ${config.singular.toLowerCase()} to get started.`}
        />
      </div>

      <RecordDrawer
        config={config}
        open={drawerOpen}
        record={editingRecord}
        onClose={closeDrawer}
        onSubmit={handleSubmit}
        submitting={submitting}
        errorMessage={formError}
      />

      <ConfirmDialog
        open={Boolean(pendingDelete)}
        title={`Delete this ${config.singular.toLowerCase()}?`}
        message="This can't be undone."
        onConfirm={confirmDelete}
        onCancel={() => setPendingDelete(null)}
        busy={deleting}
      />
    </Layout>
  );
}
