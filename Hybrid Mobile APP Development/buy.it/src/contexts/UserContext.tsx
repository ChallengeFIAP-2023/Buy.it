import { ReactNode, createContext, useContext, useEffect, useState } from "react";
import AsyncStorage from '@react-native-async-storage/async-storage';

import type { User } from "src/@types/user";

interface UserContextType {
    token: string;
    user: User;
    handleLogin(logUser: User): Promise<void>;
    handleSignUp(): Promise<string>;
    handleLogout(): void;
    emailExists(email: string): Promise<boolean>;
    handleUpdateUser(user: User, id: number): Promise<void>;
}

const UserContext = createContext<UserContextType>({} as UserContextType);

export const UseAuth = () => useContext(UserContext);

interface UserProviderProps {
    children: ReactNode;
}

export function UserProvider({ children }: UserProviderProps) {
    const [user, setUser] = useState<User>({} as User);

    useEffect(() => {
        (async () => {
            const storagedUser = await AsyncStorage.getItem('user');
            console.debug('ASYNC STORAGE: ', await AsyncStorage.getItem('user'));
            if (storagedUser) setUser(JSON.parse(storagedUser));      
        })();
    }, []);

    async function emailExists(email: string) {
        // try {
        //   const response = await api.post('/user/emailExists', { email, });
        //   return response.data.exists;
        // } catch (error) {
        //   console.error('UserContext | emailExists(): ', error);
        // }

        return false;
    }

    async function handleSignUp() {
        // try {
        //   const response = await api.post('/user/create', {
        //     nomeUsuario: user.nome,
        //     emailUsuario: user.email,
        //     senhaUsuario: user.senha,
        //     fotoPerfilUsuario: user.logo,
        //   });
    
        //   if (response.data.error) {
        //     return response.data.error.toString();
        //   }
    
        //   const newUser: User = response.data.user;
          
        //   await AsyncStorage.setItem('user', JSON.stringify(newUser));
    
          return '';

        // } catch (error) {
        //   console.error('UserContext | handleRegister(): ', error);
        // }
    }

    async function handleLogout() {
        
    }

    async function handleUpdateUser() {
        
    }

    async function handleLogin() {
        
    }

    return (
        <UserContext.Provider
          value={{
            user,
            handleLogout,
            handleLogin,
            token: '',
            handleSignUp,
            emailExists,
            handleUpdateUser
          }}>
            {children}
        </UserContext.Provider>
      );
}
