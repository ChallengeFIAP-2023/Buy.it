import axios from 'axios';

export const api = axios.create({
  baseURL: 'http://192.168.15.10:8080',
  headers: {
    "Authorization": "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJCdXlpdCIsInN1YiI6InVzdWFyaW90ZXN0ZUBleGFtcGxlLmNvbSIsImV4cCI6MTcxMjM2MDgxNH0.1W4oUKbqCJcY3r6lq2n4KTNQE10rt8UaFc92eOj38vJyrhy2DyaeXFBwMzMTcahDH4rRsmZ5XloApkyoDKO5pA"
  }
});
