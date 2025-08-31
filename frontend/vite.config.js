import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,   // default is 5173, but 3000 feels more "React-like"
  },
  build: {
    outDir: 'dist',   // default
  },
})
