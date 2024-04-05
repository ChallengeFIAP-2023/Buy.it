export const tabs = ['em andamento', 'canceladas', 'finalizadas'] as const;
export type StatusFilterType = (typeof tabs)[number];
