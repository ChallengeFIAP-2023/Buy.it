import React, { createContext, useCallback, useContext, useState } from 'react';
import Toast from 'react-native-toast-message';

// Service import
import { api } from "@services/api";

// Type import
import { User } from "@dtos/index"

interface AuthContextData {
  user: User;
  handleSignIn: ({ email, password }: SignInProps) => Promise<void>
  sigInLoading: boolean;
}

interface AuthProviderProps {
  children: React.ReactNode;
}

interface SignInProps {
  email: string;
  password: string;
}

const AuthContext = createContext<AuthContextData>({} as AuthContextData);

const AuthProvider: React.FC<AuthProviderProps> = ({
  children
}) => {
  const [user, setUser] = useState<User>({} as User);
  const [sigInLoading, setSignInLoading] = useState(false);

  const handleSignIn = useCallback(async ({ email, password }: SignInProps) => {
    try {
      setSignInLoading(true);

      const { data } = await api.post("/usuarios", { email, senha: password });
      // setUser(data.content)
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível realizar login.'
      });

      throw error;
    } finally {
      setSignInLoading(true);
    }
  }, [user])

  return (
    <AuthContext.Provider value={{
      user,
      handleSignIn,
      sigInLoading
    }}>
      {children}
    </AuthContext.Provider>
  );
};

function useAuth(): AuthContextData {
  const context = useContext(AuthContext);

  if (!context)
    throw new Error('useAuth must be used within a AuthProvider');

  return context;
}

export { AuthProvider, useAuth };
