export type StatusFilterType = { 
  label: string 
  key: "concluded" | "closed" | "approved" | "inProgress" | "repproved"
};

export const tabs: StatusFilterType[] = [
  { 
    label: "abertas",
    key: "inProgress" 
  },
  { 
    label: "aprovadas", 
    key: "approved"
  },
  { 
    label: "finalizadas",
    key: "concluded" 
  }
];

