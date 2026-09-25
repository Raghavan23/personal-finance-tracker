export const API_ENDPOINTS = {
  AUTH: {
    LOGIN: '/auth/login',
    REGISTER: '/auth/register',
    REFRESH: '/auth/refresh',
    ME: '/auth/me'
  },
  TRANSACTIONS: {
    BASE: '/transactions',
    BY_ID: (id: number | string) => `/transactions/${id}`,
    SUMMARY_MONTHLY: '/transactions/summary/monthly',
    SUMMARY_CATEGORY: '/transactions/summary/category'
  },
  CATEGORIES: {
    BASE: '/categories',
    BY_ID: (id: number | string) => `/categories/${id}`
  },
  USERS: {
    BASE: '/users',
    BY_ID: (id: number | string) => `/users/${id}`
  }
} as const;

export const APP_CONFIG = {
  DEFAULT_PAGE_SIZE: 20,
  DEFAULT_CURRENCY: 'USD',
  STORAGE_KEYS: {
    AUTH_TOKEN: 'pft_auth_token',
    REFRESH_TOKEN: 'pft_refresh_token',
    THEME: 'pft_theme_preference'
  }
} as const;
