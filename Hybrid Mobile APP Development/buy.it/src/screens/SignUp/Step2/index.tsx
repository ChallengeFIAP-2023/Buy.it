// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  Chip,
  DefaultComponent
} from "@components/index";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";
import { ArrowRight } from "phosphor-react-native";

// Type import
import { MainNavigationRoutes } from "@routes/index";
import { SignUpRoutes } from "..";

// Theme import
import theme from "@theme/index";

// Style import
import { Container, Fieldset, WrapChip } from './styles';

export const Step2: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step2'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          title: "Dados da",
          subtitle: "Passo 2 de 5",
          highlightedText: "empresa"
        }}
        key="default-component-step1"
      />

      <DecreasingContainer>
        <Fieldset>
          <Input label="Nome da empresa" placeholder="Buy.it" />
        </Fieldset>

        <Fieldset>
          <Input label="CNPJ" placeholder="johndoe@example.com" />
        </Fieldset>

        <Fieldset>
          <Input label="Departamento" placeholder="Escritório" />
        </Fieldset>

        <Input label="Tags relacionadas" placeholder="Papelaria" />

        <WrapChip>
          <Chip value="material escolar" removable />
          <Chip value="suprimento" removable />
          <Chip value="papelaria" removable />
        </WrapChip>

      </DecreasingContainer>

      <Button
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate("Step3")}
      />
    </Container>
  );
}
