import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    // Matches the origin allowed by SecurityConfig's CorsConfigurationSource
    // bean in the backend (it only permits localhost:3000, regardless of
    // the separate WebMvcConfigurer CorsConfig class also on that project).
    port: 3000,
  },
});
