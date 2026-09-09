// These option lists must match the backend enums in com.ujawal.entity
// exactly (name-for-name) since the raw enum name is sent/received as JSON.

export const GENDER_OPTIONS = ["MALE", "FEMALE", "OTHER"];

export const ROLE_OPTIONS = ["ADMIN", "TEACHER", "ACCOUNTS"];

export const EXAM_TYPE_OPTIONS = [
  "UNIT_TEST",
  "MONTHLY_TEST",
  "QUARTERLY",
  "HALF_YEARLY",
  "PRE_BOARD",
  "ANNUAL",
];

export const FEE_STATUS_OPTIONS = ["PAID", "PARTIAL", "PENDING"];

export const TEACHER_TYPE_OPTIONS = [
  "PRINCIPAL",
  "VICE_PRINCIPAL",
  "CLASS_TEACHER",
  "SUBJECT_TEACHER",
  "SPORTS_TEACHER",
  "COMPUTER_TEACHER",
  "LIBRARIAN",
  "MUSIC_TEACHER",
  "ART_TEACHER",
];

// The backend stores these as free-text strings rather than enums, but the
// office uses a consistent vocabulary day to day, so a dropdown of the
// common values keeps entries clean while still being backed by a plain
// String field.
export const STUDENT_STATUS_OPTIONS = ["ACTIVE", "INACTIVE", "GRADUATED", "TRANSFERRED", "SUSPENDED"];

export const FEE_TYPE_OPTIONS = ["TUITION", "ADMISSION", "TRANSPORT", "EXAM", "LIBRARY", "HOSTEL", "MISCELLANEOUS"];

export const PAYMENT_MODE_OPTIONS = ["CASH", "CARD", "UPI", "BANK_TRANSFER", "CHEQUE"];

// Turns SOME_ENUM_VALUE into "Some enum value" for display.
export function humanizeEnum(value) {
  if (!value) return "—";
  return value
    .toLowerCase()
    .split("_")
    .map((w) => w.charAt(0).toUpperCase() + w.slice(1))
    .join(" ");
}
