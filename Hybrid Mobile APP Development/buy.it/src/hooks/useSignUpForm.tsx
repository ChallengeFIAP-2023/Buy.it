import React, { createContext, useCallback, useContext, useState } from 'react';
import Toast from 'react-native-toast-message';

// Service import
import { api } from "@services/api";

// Type import
import { User } from "@dtos/index"

interface SignUpFormContextData {
  user: User;
  setUser: (user: User) => void;
  handleRegisterUser: (user: User) => Promise<void>;
  registerLoading: boolean;
}

const initialUser: User = {
  cnpj: null,
  email: null,
  idsTags: [],
  isFornecedor: null,
  nome: null,
  senha: null,
  urlImagem: null
}

interface SignUpFormProviderProps {
  children: React.ReactNode;
}

const SignUpFormContext = createContext<SignUpFormContextData>(
  {} as SignUpFormContextData,
);

const SignUpFormProvider: React.FC<SignUpFormProviderProps> = ({
  children
}) => {
  const [user, setUser] = useState<User>(initialUser);
  const [registerLoading, setRegisterLoading] = useState(false);

  const handleRegisterUser = useCallback(async (finalUserData: User) => {
    try {
      setRegisterLoading(true);

      const values = Object.values(finalUserData)
      const insufficientInformation = values.some(value =>
        typeof value === 'undefined' ||
        typeof value === undefined ||
        typeof value === null
      )

      if (insufficientInformation) {
        console.log(finalUserData)
        throw new Error('Um ou mais atributos estão vazios.');
      }

      // const body = finalUserData;
      const body = {
        "email": "exemplo@email.com",
        "senha": "senha123",
        "nome": "Nome do Usuário",
        "urlImagem": "https://exemplo.com/imagem.jpg",
        "cnpj": "12345678901234",
        "isFornecedor": true,
        "idsTags": []
      }

      await api.post("/usuarios", body);

      return Toast.show({
        type: 'success',
        text1: 'Usuário cadastrado com sucesso!',
        text2: 'Realize login para prosseguir.'
      });
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Não foi possível cadastrar esta conta.'
      });

      throw error;
    } finally {
      setRegisterLoading(true);
    }
  }, [])

  return (
    <SignUpFormContext.Provider value={{
      user,
      setUser,
      handleRegisterUser,
      registerLoading
    }}>
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
