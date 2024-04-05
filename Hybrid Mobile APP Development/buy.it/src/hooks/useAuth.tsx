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
import { storageUserGet } from '@storage/storageUser';

// Service import
import { api } from '@services/api';

// Type import
import { User, UserQuery } from '@dtos/index';
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
  handleUpdateUser: (user: UserQuery) => Promise<void>;
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

  const handleUpdateUser = useCallback(async (finalUserData: UserQuery) => {
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

      const parsedUser: UserQuery = {
        cnpj: finalUserData.cnpj,
        email: finalUserData.email,
        idsTags: finalUserData.idsTags,
        isFornecedor: finalUserData.isFornecedor,
        nome: finalUserData.nome,
        senha: finalUserData.senha,
        urlImagem: finalUserData.urlImagem,
      };

      if (insufficientInformation)
        throw new Error('Um ou mais atributos estão vazios.');

      if (!userId) throw new Error('É necessário o id do usuário');

      const body = parsedUser;

      const { data } = await api.put(`/usuarios/${userId}`, body);

      setUser(data);

      return Toast.show({
        type: 'success',
        text1: 'Editado com sucesso',
        text2: 'Sua conta foi atualizada.',
      });
    } catch (error: any) {
      console.log('error: ', error.message);
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
    await removeAuthToken();
    navigate('SignIn');
  }

  useEffect(() => {
    async function userIsAuthenticated() {
      const user = await storageUserGet();

      if (user) {
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
