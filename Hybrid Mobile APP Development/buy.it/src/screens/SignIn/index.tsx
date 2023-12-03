import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useForm, SubmitHandler, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import Toast from 'react-native-toast-message';

// Type import
import { MainNavigationRoutes } from "@routes/index";

// Validation import
import { SignInFormSchema } from "@validations/index";

// Theme import
import theme from "@theme/index";

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent
} from "@components/index";

// Style import
import {
  Container,
  Fieldset,
  RegisterText,
  Touchable,
  RegisterTextBold
} from './styles';

interface SignInForm {
  email: string;
  senha: string;
}

export function SignIn({
  navigation
}: NativeStackScreenProps<MainNavigationRoutes, 'SignIn'>) {
  // Hook
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<SignInForm>({
    resolver: yupResolver(SignInFormSchema),
  });

  const onSubmit: SubmitHandler<SignInForm> = (data) => {
    try {
      console.log(data)
      throw new Error();
    } catch (error) {
      Toast.show({
        type: 'error',
        text1: 'Erro',
        text2: 'Credenciais inválida.'
      });
    }
  }

  return (
    <Container>
      <DefaultComponent
        highlightProps={{ title: "Acesse sua conta", subtitle: "Bem vindo!" }}
        statusBarProps={{ backgroundColor: theme.COLORS.GRAY_700 }}
        key="default-component-sing-in"
      />

      <DecreasingContainer>
        <Fieldset>
          <Controller
            control={control}
            name="email"
            render={({ field: { value, onChange } }) => (
              <Input
                value={value}
                onChangeText={onChange}
                label="E-mail"
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
                placeholder="****"
                secureTextEntry
                error={errors.senha?.message}
              />
            )}
          />
        </Fieldset>

        <Button label="Entrar" onPress={handleSubmit(onSubmit)} />

        <Touchable onPress={() => navigation.navigate("SignUp")}>
          <RegisterText>Novo por aqui?</RegisterText>
          <RegisterTextBold>Criar uma conta</RegisterTextBold>
        </Touchable>
      </DecreasingContainer>
    </Container>
  );
}
