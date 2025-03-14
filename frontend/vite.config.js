import { defineConfig } from 'vite';
import inject from '@rollup/plugin-inject';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react(),
  inject({
    $: 'jquery',
    jQuery: 'jquery',
    _: 'underscore',
    Backbone: 'backbone'
  })],
  server: {
    proxy: {
      '/api': 'http://localhost:8080'
    }
  }
});