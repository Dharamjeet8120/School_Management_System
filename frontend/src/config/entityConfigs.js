import { classApi, examApi, feeApi, resultApi, studentApi, subjectApi, teacherApi } from "../api/entityApis";
import {
  EXAM_TYPE_OPTIONS,
  FEE_STATUS_OPTIONS,
  FEE_TYPE_OPTIONS,
  GENDER_OPTIONS,
  PAYMENT_MODE_OPTIONS,
  STUDENT_STATUS_OPTIONS,
  TEACHER_TYPE_OPTIONS,
} from "./enums";

// Field `type` values understood by <RecordForm>: text, number, date,
// select, lookup (a select populated from another resource), textarea,
// checkbox.
// Column `type` values understood by <DataTable>: text (default), date,
// currency, lookup, status, boolean.

export const studentConfig = {
  key: "students",
  api: studentApi,
  idKey: "studentId",
  title: "Students",
  singular: "Student",
  eyebrow: "Admissions register",
  description: "Every student enrolled at Ujjwal Public Vidhya Mandir, with class, family and admission details.",
  roles: ["ADMIN", "TEACHER"],
  searchFields: ["firstName", "lastName", "admissionNumber", "studentEmail", "studentMobile"],
  columns: [
    { key: "admissionNumber", label: "Admission no." },
    { key: "name", label: "Name", render: (row) => `${row.firstName || ""} ${row.lastName || ""}`.trim() },
    { key: "classId", label: "Class", type: "lookup", lookupType: "classes" },
    { key: "section", label: "Section" },
    { key: "rollNumber", label: "Roll no.", type: "num" },
    { key: "studentMobile", label: "Mobile" },
    { key: "status", label: "Status", type: "status", statusMap: { ACTIVE: "forest", GRADUATED: "forest", INACTIVE: "amber", TRANSFERRED: "amber", SUSPENDED: "maroon" } },
  ],
  defaultValues: { active: true, gender: "MALE", status: "ACTIVE" },
  sections: [
    {
      title: "Identity",
      fields: [
        { name: "admissionNumber", label: "Admission number", type: "text", required: true },
        { name: "firstName", label: "First name", type: "text", required: true },
        { name: "lastName", label: "Last name", type: "text", required: true },
        { name: "dateOfBirth", label: "Date of birth", type: "date" },
        { name: "gender", label: "Gender", type: "select", options: GENDER_OPTIONS },
        { name: "bloodGroup", label: "Blood group", type: "text" },
        { name: "religion", label: "Religion", type: "text" },
        { name: "caste", label: "Caste", type: "text" },
        { name: "nationality", label: "Nationality", type: "text" },
      ],
    },
    {
      title: "Class & enrolment",
      fields: [
        { name: "classId", label: "Class", type: "lookup", lookupType: "classes" },
        { name: "teacherId", label: "Class teacher", type: "lookup", lookupType: "teachers" },
        { name: "section", label: "Section", type: "text" },
        { name: "rollNumber", label: "Roll number", type: "number" },
        { name: "academicYear", label: "Academic year", type: "text", placeholder: "2025-2026" },
        { name: "status", label: "Status", type: "select", options: STUDENT_STATUS_OPTIONS },
        { name: "active", label: "Currently active", type: "checkbox" },
      ],
    },
    {
      title: "Contact",
      fields: [
        { name: "studentEmail", label: "Student email", type: "text" },
        { name: "studentMobile", label: "Student mobile", type: "text" },
      ],
    },
    {
      title: "Parent / guardian",
      fields: [
        { name: "fatherName", label: "Father's name", type: "text" },
        { name: "fatherMobile", label: "Father's mobile", type: "text" },
        { name: "fatherOccupation", label: "Father's occupation", type: "text" },
        { name: "motherName", label: "Mother's name", type: "text" },
        { name: "motherMobile", label: "Mother's mobile", type: "text" },
        { name: "motherOccupation", label: "Mother's occupation", type: "text" },
        { name: "guardianName", label: "Guardian's name", type: "text" },
        { name: "guardianMobile", label: "Guardian's mobile", type: "text" },
      ],
    },
    {
      title: "Address",
      fields: [
        { name: "addressLine1", label: "Address line 1", type: "text", span: "full" },
        { name: "addressLine2", label: "Address line 2", type: "text", span: "full" },
        { name: "city", label: "City", type: "text" },
        { name: "state", label: "State", type: "text" },
        { name: "pincode", label: "Pincode", type: "text" },
      ],
    },
    {
      title: "Admission & documents",
      fields: [
        { name: "admissionDate", label: "Admission date", type: "date" },
        { name: "admissionFee", label: "Admission fee", type: "number" },
        { name: "previousSchool", label: "Previous school", type: "text" },
        { name: "birthCertificateNumber", label: "Birth certificate no.", type: "text" },
        { name: "aadhaarNumber", label: "Aadhaar number", type: "text" },
        { name: "transferCertificateNumber", label: "Transfer certificate no.", type: "text" },
      ],
    },
  ],
};

export const teacherConfig = {
  key: "teachers",
  api: teacherApi,
  idKey: "teacherId",
  title: "Teachers",
  singular: "Teacher",
  eyebrow: "Staff register",
  description: "Teaching staff, their subjects, class-teacher assignments and service records.",
  roles: ["ADMIN"],
  searchFields: ["firstName", "lastName", "employeeId", "email", "mobileNumber"],
  columns: [
    { key: "employeeId", label: "Employee ID" },
    { key: "name", label: "Name", render: (row) => `${row.firstName || ""} ${row.lastName || ""}`.trim() },
    { key: "teacherType", label: "Type", type: "enum" },
    { key: "subjectId", label: "Subject", type: "lookup", lookupType: "subjects" },
    { key: "mobileNumber", label: "Mobile" },
    { key: "active", label: "Active", type: "boolean" },
  ],
  defaultValues: { active: true, gender: "MALE", teacherType: "SUBJECT_TEACHER" },
  sections: [
    {
      title: "Identity",
      fields: [
        { name: "employeeId", label: "Employee ID", type: "text", required: true },
        { name: "firstName", label: "First name", type: "text", required: true },
        { name: "lastName", label: "Last name", type: "text", required: true },
        { name: "gender", label: "Gender", type: "select", options: GENDER_OPTIONS },
        { name: "dateOfBirth", label: "Date of birth", type: "date" },
        { name: "email", label: "Email", type: "text" },
        { name: "mobileNumber", label: "Mobile number", type: "text" },
        { name: "aadhaarNumber", label: "Aadhaar number", type: "text" },
      ],
    },
    {
      title: "Professional",
      fields: [
        { name: "qualification", label: "Qualification", type: "text" },
        { name: "specialization", label: "Specialization", type: "text" },
        { name: "experienceYears", label: "Experience (years)", type: "number" },
        { name: "joiningDate", label: "Joining date", type: "date" },
        { name: "salary", label: "Salary", type: "number" },
        { name: "teacherType", label: "Teacher type", type: "select", options: TEACHER_TYPE_OPTIONS },
        { name: "subjectId", label: "Subject taught", type: "lookup", lookupType: "subjects" },
        { name: "classTeacherOf", label: "Class teacher of", type: "text", placeholder: "e.g. 10-A" },
        { name: "active", label: "Currently active", type: "checkbox" },
      ],
    },
    {
      title: "Address",
      fields: [
        { name: "addressLine1", label: "Address line 1", type: "text", span: "full" },
        { name: "addressLine2", label: "Address line 2", type: "text", span: "full" },
        { name: "city", label: "City", type: "text" },
        { name: "state", label: "State", type: "text" },
        { name: "pincode", label: "Pincode", type: "text" },
      ],
    },
    {
      title: "Emergency contact",
      fields: [
        { name: "emergencyContactName", label: "Contact name", type: "text" },
        { name: "emergencyContactNumber", label: "Contact number", type: "text" },
        { name: "relationship", label: "Relationship", type: "text" },
      ],
    },
    {
      title: "Bank details",
      fields: [
        { name: "bankName", label: "Bank name", type: "text" },
        { name: "accountNumber", label: "Account number", type: "text" },
        { name: "ifscCode", label: "IFSC code", type: "text" },
      ],
    },
  ],
};

export const classConfig = {
  key: "classes",
  api: classApi,
  idKey: "classId",
  title: "Classes",
  singular: "Class",
  eyebrow: "Class register",
  description: "Class and section groupings, room assignments and the class teacher responsible for each.",
  roles: ["ADMIN", "TEACHER"],
  searchFields: ["className", "section", "academicYear"],
  columns: [
    { key: "className", label: "Class" },
    { key: "section", label: "Section" },
    { key: "roomNumber", label: "Room", type: "num" },
    { key: "capacity", label: "Capacity", type: "num" },
    { key: "academicYear", label: "Academic year" },
    { key: "teacherId", label: "Class teacher", type: "lookup", lookupType: "teachers" },
  ],
  defaultValues: {},
  sections: [
    {
      title: "Class details",
      fields: [
        { name: "className", label: "Class name", type: "text", required: true, placeholder: "e.g. 10" },
        { name: "section", label: "Section", type: "text", required: true, placeholder: "e.g. A" },
        { name: "roomNumber", label: "Room number", type: "number" },
        { name: "capacity", label: "Capacity", type: "number" },
        { name: "academicYear", label: "Academic year", type: "text", placeholder: "2025-2026" },
        { name: "teacherId", label: "Class teacher", type: "lookup", lookupType: "teachers" },
      ],
    },
  ],
};

export const subjectConfig = {
  key: "subjects",
  api: subjectApi,
  idKey: "subjectId",
  title: "Subjects",
  singular: "Subject",
  eyebrow: "Curriculum",
  description: "The subjects taught across the school, with their code and marks scheme.",
  roles: ["ADMIN", "TEACHER"],
  searchFields: ["subjectName", "subjectCode"],
  columns: [
    { key: "subjectCode", label: "Code" },
    { key: "subjectName", label: "Subject" },
    { key: "maximumMarks", label: "Max marks", type: "num" },
    { key: "passingMarks", label: "Passing marks", type: "num" },
    { key: "active", label: "Active", type: "boolean" },
  ],
  defaultValues: { active: true },
  sections: [
    {
      title: "Subject details",
      fields: [
        { name: "subjectCode", label: "Subject code", type: "text", required: true },
        { name: "subjectName", label: "Subject name", type: "text", required: true },
        { name: "maximumMarks", label: "Maximum marks", type: "number" },
        { name: "passingMarks", label: "Passing marks", type: "number" },
        { name: "active", label: "Currently taught", type: "checkbox" },
        { name: "description", label: "Description", type: "textarea", span: "full" },
      ],
    },
  ],
};

export const examConfig = {
  key: "exams",
  api: examApi,
  idKey: "examId",
  title: "Exams",
  singular: "Exam",
  eyebrow: "Examination calendar",
  description: "Scheduled exams across the academic year, with their marking scheme.",
  roles: ["ADMIN", "TEACHER"],
  searchFields: ["examName", "academicYear"],
  columns: [
    { key: "examName", label: "Exam" },
    { key: "examType", label: "Type", type: "enum" },
    { key: "examDate", label: "Date", type: "date" },
    { key: "totalMarks", label: "Total marks", type: "num" },
    { key: "passingMarks", label: "Passing marks", type: "num" },
    { key: "academicYear", label: "Academic year" },
  ],
  defaultValues: { examType: "UNIT_TEST" },
  sections: [
    {
      title: "Exam details",
      fields: [
        { name: "examName", label: "Exam name", type: "text", required: true },
        { name: "examType", label: "Exam type", type: "select", options: EXAM_TYPE_OPTIONS },
        { name: "examDate", label: "Exam date", type: "date" },
        { name: "totalMarks", label: "Total marks", type: "number" },
        { name: "passingMarks", label: "Passing marks", type: "number" },
        { name: "academicYear", label: "Academic year", type: "text", placeholder: "2025-2026" },
        { name: "description", label: "Description", type: "textarea", span: "full" },
      ],
    },
  ],
};

export const feeConfig = {
  key: "fees",
  api: feeApi,
  idKey: "feeId",
  title: "Fees",
  singular: "Fee",
  eyebrow: "Accounts ledger",
  description: "Fee dues, payments received and outstanding balances for every student.",
  roles: ["ADMIN", "ACCOUNTS"],
  searchFields: ["feeType", "transactionId", "remarks"],
  columns: [
    { key: "studentId", label: "Student", type: "lookup", lookupType: "students" },
    { key: "feeType", label: "Fee type" },
    { key: "amount", label: "Amount", type: "currency" },
    { key: "paidAmount", label: "Paid", type: "currency" },
    { key: "remainingAmount", label: "Remaining", type: "currency" },
    { key: "dueDate", label: "Due date", type: "date" },
    { key: "status", label: "Status", type: "status", statusMap: { PAID: "forest", PARTIAL: "amber", PENDING: "maroon" } },
  ],
  defaultValues: { status: "PENDING" },
  sections: [
    {
      title: "Fee details",
      fields: [
        { name: "studentId", label: "Student", type: "lookup", lookupType: "students", required: true },
        { name: "feeType", label: "Fee type", type: "select", options: FEE_TYPE_OPTIONS },
        { name: "amount", label: "Amount", type: "number" },
        { name: "paidAmount", label: "Paid amount", type: "number" },
        { name: "remainingAmount", label: "Remaining amount", type: "number" },
        { name: "dueDate", label: "Due date", type: "date" },
        { name: "status", label: "Status", type: "select", options: FEE_STATUS_OPTIONS },
      ],
    },
    {
      title: "Payment",
      fields: [
        { name: "paymentDate", label: "Payment date", type: "date" },
        { name: "paymentMode", label: "Payment mode", type: "select", options: PAYMENT_MODE_OPTIONS },
        { name: "transactionId", label: "Transaction ID", type: "text" },
        { name: "remarks", label: "Remarks", type: "textarea", span: "full" },
      ],
    },
  ],
};

export const resultConfig = {
  key: "results",
  api: resultApi,
  idKey: "resultId",
  title: "Results",
  singular: "Result",
  eyebrow: "Examination results",
  description: "Marks obtained per student, per exam and subject, with grade and pass status.",
  roles: ["ADMIN", "TEACHER"],
  searchFields: ["grade", "remarks"],
  columns: [
    { key: "studentId", label: "Student", type: "lookup", lookupType: "students" },
    { key: "examId", label: "Exam", type: "lookup", lookupType: "exams" },
    { key: "subjectId", label: "Subject", type: "lookup", lookupType: "subjects" },
    { key: "marks", label: "Marks", render: (row) => `${row.obtainedMarks ?? "—"} / ${row.totalMarks ?? "—"}` },
    { key: "grade", label: "Grade" },
    { key: "passStatus", label: "Result", type: "status", statusMap: { true: "forest", false: "maroon" }, displayMap: { true: "Pass", false: "Fail" } },
  ],
  defaultValues: { passStatus: true },
  sections: [
    {
      title: "Result details",
      fields: [
        { name: "studentId", label: "Student", type: "lookup", lookupType: "students", required: true },
        { name: "examId", label: "Exam", type: "lookup", lookupType: "exams", required: true },
        { name: "subjectId", label: "Subject", type: "lookup", lookupType: "subjects", required: true },
        { name: "obtainedMarks", label: "Obtained marks", type: "number" },
        { name: "totalMarks", label: "Total marks", type: "number" },
        { name: "grade", label: "Grade", type: "text", placeholder: "e.g. A+" },
        { name: "passStatus", label: "Passed", type: "checkbox" },
        { name: "remarks", label: "Remarks", type: "textarea", span: "full" },
      ],
    },
  ],
};

export const allEntityConfigs = [studentConfig, teacherConfig, classConfig, subjectConfig, examConfig, feeConfig, resultConfig];
