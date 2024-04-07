import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from 'react';
import Toast from 'react-native-toast-message';
import { useNavigation } from '@react-navigation/native';

// Type import
import { AppNavigatorRoutesProps } from '@routes/index';

// Storage import
import { storageUserGet, storageUserSave } from '@storage/storageUser';

// Service import
import { api } from '@services/api';

// Type import
import { User } from '@dtos/index';
import {
  removeAuthToken,
  storageAuthTokenGet,
  storageAuthTokenSave,
} from '@storage/storageAuthToken';

interface AuthProps {
  usuario: User;
  token: string;
}

interface AuthContextData {
  user: User;
  handleSignIn: (data: SignInProps) => Promise<void>;
  sigInLoading: boolean;
  handleUpdateUser: (user: User) => Promise<void>;
  updateLoading: boolean;

  handleSignOut(): Promise<void>;
}

interface AuthProviderProps {
  children: React.ReactNode;
}

interface SignInProps {
  email: string;
  password: string;
}

const AuthContext = createContext<AuthContextData>({} as AuthContextData);

const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  // Hook
  const { navigate } = useNavigation<AppNavigatorRoutesProps>();

  // State
  const [user, setUser] = useState<User>({} as User);
  const [sigInLoading, setSignInLoading] = useState(false);
  const [updateLoading, setUpdateLoading] = useState(false);

  const handleSignIn = useCallback(async ({ email, password }: SignInProps) => {
    try {
      setSignInLoading(true);

      const body = { email, senha: password };
      const { data } = await api.post<AuthProps>('usuarios/login', body);

      const token = `Bearer ${data.token}`;

      if (!data.usuario || !token) throw new Error();

      setUser(data.usuario);

      await storageAuthTokenSave({ token });
      await storageUserSave(data.usuario);

      await registryToken();

      return navigate('Main');
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: String(error) ?? 'Não foi possível realizar login.',
      });

      throw error;
    } finally {
      setSignInLoading(false);
    }
  }, []);

  async function registryToken() {
    const token = await storageAuthTokenGet();

    // Interceptor token
    api.defaults.headers.Authorization = `Bearer ${token}`;
  }

  const handleUpdateUser = useCallback(async (finalUserData: User) => {
    try {
      setUpdateLoading(true);

      const userId = finalUserData.id;
      const values = Object.values(finalUserData);
      const insufficientInformation = values.some(
        value =>
          typeof value === 'undefined' ||
          typeof value === undefined ||
          typeof value === null,
      );

      if (insufficientInformation)
        throw new Error('Um ou mais atributos estão vazios.');

      if (!userId) throw new Error('É necessário o id do usuário');

      const body = {
        nome: finalUserData.nome,
        email: finalUserData.email,
        senha: finalUserData.senha,
        urlImagem: finalUserData.urlImagem,
        cnpj: finalUserData.cnpj,
        isFornecedor: finalUserData.isFornecedor,
      };

      console.log(body);

      const { data } = await api.put(`/usuarios/${userId}`, body);

      setUser(data);

      return Toast.show({
        type: 'success',
        text1: 'Editado com sucesso',
        text2: 'Sua conta foi atualizada.',
      });
    } catch (error: any) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível atualizar sua conta.',
      });

      throw error;
    } finally {
      setUpdateLoading(false);
    }
  }, []);

  async function handleSignOut() {
    setUser({} as User);
    await removeAuthToken();
    navigate('SignIn');
  }

  useEffect(() => {
    async function userIsAuthenticated() {
      const user = await storageUserGet();
      const token = await storageAuthTokenGet();

      if (user && token) {
        navigate('Main');
        await registryToken();
        return setUser(user);
      }
    }

    userIsAuthenticated();
  }, []);

  return (
    <AuthContext.Provider
      value={{
        user,
        handleSignIn,
        sigInLoading,
        handleUpdateUser,
        updateLoading,

        handleSignOut,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

function useAuth(): AuthContextData {
  const context = useContext(AuthContext);

  if (!context) throw new Error('useAuth must be used within a AuthProvider');

  return context;
}

export { AuthProvider, useAuth };
