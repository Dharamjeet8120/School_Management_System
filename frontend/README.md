# Ujjwal Public Vidhya Mandir — School Office (Frontend)

A React + Vite admin panel for the [School_Management_System](https://github.com/Dharamjeet8120/School_Management_System)
Spring Boot backend (`com.ujawal`). Covers students, teachers, classes, subjects,
exams, fees and results behind JWT login, with each section gated to the same
roles the backend enforces.

## Stack

- React 18 + Vite
- React Router v6
- Axios (JWT attached via a request interceptor, auto-logout on 401)
- Plain CSS design system (no UI kit) — see `src/index.css`

## Requirements

- Node.js 18+
- The backend running locally on **http://localhost:8080** (default `server.port`
  in `application.properties`), with MySQL available. The backend's
  `CorsConfig` already allows `http://localhost:5173`, which is Vite's
  default dev port, so no backend changes are needed.

## Setup

```bash
npm install
cp .env.example .env    # adjust VITE_API_BASE_URL if your backend runs elsewhere
npm run dev
```

Then open the printed local URL (default `http://localhost:5173`).

## First login

The backend has no seeded users. Open `/register`, create an account (pick a
role — Admin, Teacher, or Accounts), then sign in from `/login`. The
`/api/auth/**` endpoints are the only ones that don't require a token; every
other request automatically carries `Authorization: Bearer <token>`.

## Role access

This mirrors the `@PreAuthorize` rules on the backend controllers:

| Section  | Admin | Teacher | Accounts |
| -------- | :---: | :-----: | :------: |
| Dashboard |  ✔   |    ✔    |    ✔     |
| Students |  ✔   |    ✔    |          |
| Teachers |  ✔   |         |          |
| Classes  |  ✔   |    ✔    |          |
| Subjects |  ✔   |    ✔    |          |
| Exams    |  ✔   |    ✔    |          |
| Results  |  ✔   |    ✔    |          |
| Fees     |  ✔   |         |    ✔     |

The frontend hides sections a role can't reach; the backend still enforces
the same rule independently, so this is convenience, not the security
boundary.

## Project structure

```
src/
  api/            axios client + one module per REST resource
  config/         entity schemas (columns + form fields) and enum option lists
  context/        AuthContext (JWT/role) and LookupsContext (id → name caching)
  components/
    layout/       sidebar + topbar shell
    common/       DataTable, RecordDrawer (add/edit form), ConfirmDialog, ProtectedRoute
  pages/          Login, Register, Dashboard, and the generic EntityCrudPage
```

Students, Teachers, Classes, Subjects, Exams, Fees and Results all render
through one generic `EntityCrudPage`, configured per entity in
`src/config/entityConfigs.js`. To adjust a form field, table column, or add
a new entity, that config file is the only place to edit — no page
components to duplicate.

## Build

```bash
npm run build   # outputs to dist/
npm run preview # serve the production build locally
```
