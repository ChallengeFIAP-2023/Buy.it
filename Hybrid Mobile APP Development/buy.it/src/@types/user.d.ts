export type User = {
    id: number;
    nome: string;
    email: string;
    logo: string | null;
    isSupplier: boolean;
    senha: string;
    signed: boolean;
};