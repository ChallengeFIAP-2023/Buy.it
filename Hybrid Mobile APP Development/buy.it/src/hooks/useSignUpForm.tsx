import React, {
  createContext,
  useCallback,
  useContext,
  useState,
  Dispatch,
} from 'react';
import Toast from 'react-native-toast-message';

// Service import
import { api } from '@services/api';

// Type import
import { User } from '@dtos/index';

interface SignUpFormContextData {
  user: User;
  setUser: Dispatch<React.SetStateAction<User>>;
  handleRegisterUser: (finalUserData: User) => Promise<void>;
  registerLoading: boolean;
}

interface SignUpFormProviderProps {
  children: React.ReactNode;
}

const SignUpFormContext = createContext<SignUpFormContextData>(
  {} as SignUpFormContextData,
);

const SignUpFormProvider: React.FC<SignUpFormProviderProps> = ({
  children,
}) => {
  const [user, setUser] = useState<User>({} as User);
  const [registerLoading, setRegisterLoading] = useState(false);

  const handleRegisterUser = useCallback(async (finalUserData: User) => {
    try {
      setRegisterLoading(true);

      const values = Object.values(finalUserData);
      const insufficientInformation = values.some(
        value =>
          typeof value === 'undefined' ||
          typeof value === undefined ||
          typeof value === null,
      );

      if (insufficientInformation) {
        throw new Error('Um ou mais atributos estão vazios.');
      }

      const body = finalUserData;

      await api.post('/usuarios', body);

      return Toast.show({
        type: 'success',
        text1: 'Bem vindo!',
        text2: 'Aproveite o melhor app de automação de compras. 😀',
      });
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível cadastrar esta conta.',
      });

      throw error;
    } finally {
      setRegisterLoading(true);
    }
  }, []);

  return (
    <SignUpFormContext.Provider
      value={{
        user,
        setUser,
        handleRegisterUser,
        registerLoading,
      }}
    >
      {children}
    </SignUpFormContext.Provider>
  );
};

function useSignUpForm(): SignUpFormContextData {
  const context = useContext(SignUpFormContext);

  if (!context)
    throw new Error('useSignUpForm must be used within a SignUpFormProvider');

  return context;
}

export { SignUpFormProvider, useSignUpForm };
