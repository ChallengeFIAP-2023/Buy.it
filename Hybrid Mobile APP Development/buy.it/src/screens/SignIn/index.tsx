import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import Toast from 'react-native-toast-message';

// Type import
import { MainNavigationRoutes } from '@routes/index';

// Hook import
import { useAuth } from '@hooks/useAuth';

// Validation import
import { SignInFormSchema } from '@validations/index';

// Theme import
import theme from '@theme/index';

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent,
  WrapperPage,
} from '@components/index';

// Style import
import {
  Content,
  Fieldset,
  RegisterText,
  Touchable,
  RegisterTextBold,
} from './styles';
import { ScrollableContent } from '@global/styles/index';

interface SignInForm {
  email: string;
  senha: string;
}

export function SignIn({
  navigation,
}: NativeStackScreenProps<MainNavigationRoutes, 'SignIn'>) {
  // Hook
  const { handleSignIn, sigInLoading } = useAuth();
  const {
    control,
    handleSubmit,
    resetField,
    formState: { errors },
  } = useForm<SignInForm>({
    resolver: yupResolver(SignInFormSchema),
  });

  const onSubmit: SubmitHandler<SignInForm> = async data => {
    try {
      const { email, senha } = data;
      await handleSignIn({ email, password: senha });
      resetField("email");
      resetField("senha");
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Credenciais inválidas.',
      });
    }
  };

  return (
    <WrapperPage>
      <ScrollableContent>
        <DefaultComponent
          highlightProps={{ title: 'Acesse sua conta', subtitle: 'Bem vindo!' }}
          statusBarProps={{ backgroundColor: theme.COLORS.GRAY_700 }}
          key="default-component-sing-in"
        />

        <DecreasingContainer>
          <Content>
            <Fieldset>
              <Controller
                control={control}
                name="email"
                render={({ field: { value, onChange } }) => (
                  <Input
                    value={value}
                    onChangeText={onChange}
                    label="E-mail"
                    autoCapitalize="none"
                    keyboardType="email-address"
                    placeholder="Johndoe@example.com"
                    error={errors.email?.message}
                  />
                )}
              />
            </Fieldset>

            <Fieldset>
              <Controller
                control={control}
                name="senha"
                render={({ field: { value, onChange } }) => (
                  <Input
                    value={value}
                    onChangeText={onChange}
                    label="Senha"
                    placeholder="********"
                    secureTextEntry
                    autoCapitalize="none"
                    error={errors.senha?.message}
                  />
                )}
              />
            </Fieldset>

            <Button
              label={sigInLoading ? 'Carregando...' : 'Entrar'}
              onPress={handleSubmit(onSubmit)}
            />

            <Touchable onPress={() => navigation.navigate('SignUp')}>
              <RegisterText>Novo por aqui?</RegisterText>
              <RegisterTextBold>Criar uma conta</RegisterTextBold>
            </Touchable>
          </Content>
        </DecreasingContainer>
      </ScrollableContent>
    </WrapperPage>
  );
}
