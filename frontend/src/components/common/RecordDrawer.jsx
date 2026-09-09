import { Fragment, useEffect, useState } from "react";
import { useLookups } from "../../context/LookupsContext";

function FieldInput({ field, value, onChange, error }) {
  const { getOptions } = useLookups();

  const commonProps = {
    id: field.name,
    name: field.name,
  };

  if (field.type === "select") {
    return (
      <select {...commonProps} value={value ?? ""} onChange={(e) => onChange(e.target.value || null)}>
        <option value="">Select…</option>
        {field.options.map((opt) => (
          <option key={opt} value={opt}>
            {opt.replaceAll("_", " ")}
          </option>
        ))}
      </select>
    );
  }

  if (field.type === "lookup") {
    const options = getOptions(field.lookupType);
    return (
      <select
        {...commonProps}
        value={value ?? ""}
        onChange={(e) => onChange(e.target.value ? Number(e.target.value) : null)}
      >
        <option value="">Select…</option>
        {options.map((opt) => (
          <option key={opt.value} value={opt.value}>
            {opt.label}
          </option>
        ))}
      </select>
    );
  }

  if (field.type === "textarea") {
    return (
      <textarea {...commonProps} value={value ?? ""} onChange={(e) => onChange(e.target.value)} placeholder={field.placeholder} />
    );
  }

  if (field.type === "checkbox") {
    return (
      <input
        {...commonProps}
        type="checkbox"
        checked={Boolean(value)}
        onChange={(e) => onChange(e.target.checked)}
      />
    );
  }

  if (field.type === "number") {
    return (
      <input
        {...commonProps}
        type="number"
        step="any"
        value={value === null || value === undefined ? "" : value}
        onChange={(e) => onChange(e.target.value === "" ? null : Number(e.target.value))}
        placeholder={field.placeholder}
      />
    );
  }

  if (field.type === "date") {
    return (
      <input
        {...commonProps}
        type="date"
        value={value || ""}
        onChange={(e) => onChange(e.target.value || null)}
      />
    );
  }

  return (
    <input
      {...commonProps}
      type="text"
      value={value ?? ""}
      onChange={(e) => onChange(e.target.value)}
      placeholder={field.placeholder}
      autoComplete="off"
    />
  );
}

export default function RecordDrawer({ config, open, record, onClose, onSubmit, submitting, errorMessage }) {
  const [values, setValues] = useState({});
  const [fieldErrors, setFieldErrors] = useState({});

  useEffect(() => {
    if (open) {
      setValues(record ? { ...record } : { ...(config.defaultValues || {}) });
      setFieldErrors({});
    }
  }, [open, record, config]);

  if (!open) return null;

  const isEdit = Boolean(record);

  const setField = (name, value) => {
    setValues((prev) => ({ ...prev, [name]: value }));
    if (fieldErrors[name]) {
      setFieldErrors((prev) => ({ ...prev, [name]: undefined }));
    }
  };

  const validate = () => {
    const errors = {};
    config.sections.forEach((section) => {
      section.fields.forEach((field) => {
        if (field.required) {
          const v = values[field.name];
          if (v === null || v === undefined || v === "") {
            errors[field.name] = "This field is required.";
          }
        }
      });
    });
    setFieldErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!validate()) return;
    onSubmit(values);
  };

  return (
    <>
      <div className="drawer-scrim" onClick={onClose} />
      <div className="drawer" role="dialog" aria-modal="true" aria-label={`${isEdit ? "Edit" : "Add"} ${config.singular}`}>
        <div className="drawer-header">
          <h2>
            {isEdit ? `Edit ${config.singular.toLowerCase()}` : `Add ${config.singular.toLowerCase()}`}
          </h2>
          <button type="button" className="close-x" onClick={onClose} aria-label="Close">
            ×
          </button>
        </div>
        <form id="record-form" className="drawer-form" onSubmit={handleSubmit}>
          <div className="drawer-body">
            {errorMessage && <div className="alert alert-error">{errorMessage}</div>}
            <div className="form-grid">
              {config.sections.map((section) => (
                <Fragment key={section.title}>
                  {config.sections.length > 1 && <div className="form-section-title">{section.title}</div>}
                  {section.fields.map((field) => (
                    <div
                      key={field.name}
                      className={`field ${field.span === "full" ? "full" : ""} ${field.type === "checkbox" ? "field-checkbox" : ""} ${fieldErrors[field.name] ? "has-error" : ""}`}
                    >
                      {field.type === "checkbox" ? (
                        <>
                          <FieldInput field={field} value={values[field.name]} onChange={(v) => setField(field.name, v)} />
                          <label htmlFor={field.name}>{field.label}</label>
                        </>
                      ) : (
                        <>
                          <label htmlFor={field.name}>
                            {field.label}
                            {field.required ? " *" : ""}
                          </label>
                          <FieldInput field={field} value={values[field.name]} onChange={(v) => setField(field.name, v)} />
                        </>
                      )}
                      {fieldErrors[field.name] && <span className="field-error">{fieldErrors[field.name]}</span>}
                    </div>
                  ))}
                </Fragment>
              ))}
            </div>
          </div>
          <div className="drawer-footer">
            <button type="button" className="btn btn-outline" onClick={onClose}>
              Cancel
            </button>
            <button type="submit" className="btn btn-primary" disabled={submitting}>
              {submitting ? "Saving…" : isEdit ? `Save ${config.singular.toLowerCase()}` : `Add ${config.singular.toLowerCase()}`}
            </button>
          </div>
        </form>
      </div>
    </>
  );
}
