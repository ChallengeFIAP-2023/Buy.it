export const tabs = ['abertas', 'aprovadas', 'finalizadas'] as const;
export type StatusFilterType = (typeof tabs)[number];
