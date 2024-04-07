import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { CompositeScreenProps } from '@react-navigation/native';
import { ArrowRight } from 'phosphor-react-native';

// Type import
import { MainNavigationRoutes } from '@routes/index';
import { SignUpRoutes } from '..';

// Theme import
import theme from '@theme/index';

// Type import
import { User } from '@dtos/user';

// Hook import
import { useSignUpForm } from '@hooks/useSignUpForm';
import { useAuth } from '@hooks/useAuth';

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent,
  WrapperPage,
  Requirement,
} from '@components/index';

// Style import
import { Fieldset, Content } from './styles';
import { ScrollableContent } from '@global/styles/index';
import { useState } from 'react';

export const Step5: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step5'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // Hook
  const { user, handleRegisterUser } = useSignUpForm();
  const { handleSignIn } = useAuth();

  // State
  const [hasEnoughCharacters, setHasEnoughCharacters] = useState(false);
  const [hasUppercase, setHasUppercase] = useState(false);
  const [hasLowercase, setHasLowercase] = useState(false);
  const [password, setPassword] = useState('');

  const isValid = hasEnoughCharacters && hasUppercase && hasLowercase;

  const onSubmit = async () => {
    const email = user.email;
    const senha = password;

    const finalUserData: User = Object.assign(user, { senha });

    try {
      await handleRegisterUser(finalUserData);

      if (!email || !senha) return;

      await handleSignIn({ email, password: senha });
    } catch (error) {
      console.error(error);
    }
  };

  function validatePassword(password: string) {
    setHasEnoughCharacters(password.length >= 8);
    setHasUppercase(/[A-Z]/.test(password));
    setHasLowercase(/[a-z]/.test(password));
  }

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          headerProps={{ goBack: () => navigation.goBack() }}
          highlightProps={{
            subtitle: 'Passo 5 de 5',
            title: 'Defina uma senha',
            highlightedText: 'senha',
          }}
          key="default-component-step5"
        />

        <DecreasingContainer>
          <Content>
            <Fieldset>
              <Input
                value={password}
                onChangeText={text => {
                  setPassword(text);
                  validatePassword(text);
                }}
                label="Senha"
                placeholder="********"
                autoCapitalize="none"
                secureTextEntry
              />
            </Fieldset>

            <Requirement
              label="pelo menos 8 caracteres"
              fulfilled={hasEnoughCharacters}
            />

            <Requirement
              label="pelo menos uma letra minúscula"
              fulfilled={hasLowercase}
            />

            <Requirement
              label="pelo menos uma letra maiúscula"
              fulfilled={hasUppercase}
            />
          </Content>
        </DecreasingContainer>
      </ScrollableContent>

      <Button
        label="Criar conta"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        disabled={!isValid}
        onPress={onSubmit}
      />
    </WrapperPage>
  );
};
