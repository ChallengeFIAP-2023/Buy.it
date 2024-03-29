import axios from 'axios';

export const api = axios.create({
  baseURL: 'http://192.168.15.10:8080',
  headers: {
    "Authorization": "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJCdXlpdCIsInN1YiI6ImFkbUBrYWJ1bS5jb20uYnIiLCJleHAiOjE3MTE3MzIxNjl9.B1Us2H9tMB3gOOULvOOESpBGb_YbjRgP68Qp4q90HsHwFcwpjbzscEE2aF8HWj_f65pcG1B3HYprmA92-rkUDQ"
  }
});
