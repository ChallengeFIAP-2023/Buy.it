import { NativeStackScreenProps } from "@react-navigation/native-stack";

// Type import
import { MainNavigationRoutes } from "@routes/index";

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

export function SignIn({
  navigation
}: NativeStackScreenProps<MainNavigationRoutes, 'SignIn'>) {
  return (
    <Container>
      <DefaultComponent
        highlightProps={{ title: "Acesse sua conta", subtitle: "Bem vindo!" }}
        statusBarProps={{ backgroundColor: theme.COLORS.GRAY_700 }}
        key="default-component-sing-in"
      />

      <DecreasingContainer>
        <Fieldset>
          <Input
            label="E-mail ou CNPJ"
            placeholder="Johndoe@example.com"
          />
        </Fieldset>

        <Fieldset>
          <Input
            label="Senha"
            placeholder="****"
            secureTextEntry
          />
        </Fieldset>

        <Button label="Entrar" onPress={() => navigation.navigate("Profile")} />

        <Touchable onPress={() => navigation.navigate("SignUp")}>
          <RegisterText>Novo por aqui?</RegisterText>
          <RegisterTextBold>Criar uma conta</RegisterTextBold>
        </Touchable>
      </DecreasingContainer>
    </Container>
  );
}
