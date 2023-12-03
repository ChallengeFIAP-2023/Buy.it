import React, { createContext, useContext, useEffect, useState } from 'react';

// Type import
import { User } from "@dtos/index"

interface SignUpFormContextData {
  user: User;
  setUser: (user: User) => void;
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
  const [user, setUser] = useState<User>({} as User);

  useEffect(() => console.log("\n\n\n", user), [user])

  return (
    <SignUpFormContext.Provider value={{ user, setUser }}>
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
