import { useState } from "react";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { CompositeScreenProps } from "@react-navigation/native";
import { ArrowRight, Check, X } from "phosphor-react-native";

// Type import
import { MainNavigationRoutes } from "@routes/index";
import { SignUpRoutes } from "..";

// Theme import
import theme from "@theme/index";

// Component import
import {
  DecreasingContainer,
  Input,
  Button,
  DefaultComponent,
} from "@components/index";

// Style import
import { Container, Fieldset, Requirement, RequirementText } from './styles';

export const Step5: React.FC<
  CompositeScreenProps<
    NativeStackScreenProps<SignUpRoutes, 'Step5'>,
    NativeStackScreenProps<MainNavigationRoutes>
  >
> = ({ navigation }) => {
  // State
  const [password, setPassword] = useState('')

  return (
    <Container>
      <DefaultComponent
        headerProps={{ goBack: () => navigation.goBack() }}
        highlightProps={{
          subtitle: "Passo 5 de 5",
          title: "Defina uma",
          highlightedText: "senha"
        }}
        key="default-component-step5"
      />

      <DecreasingContainer>
        <Fieldset>
          <Input
            label="Senha"
            placeholder="********"
            secureTextEntry
            value={password}
            onChange={value => console.log(String(value.target))}
          />
        </Fieldset>

        <Requirement>
          <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
          <RequirementText fulfilled>8 caracteres</RequirementText>
        </Requirement>

        <Requirement>
          <Check color={theme.COLORS.GREEN_700} weight="bold" size={theme.FONT_SIZE.MD} />
          <RequirementText fulfilled>pelo menos uma letra minúscula</RequirementText>
        </Requirement>

        <Requirement>
          <X color={theme.COLORS.RED} weight="bold" size={theme.FONT_SIZE.MD} />
          <RequirementText>pelo menos uma letra minúscula</RequirementText>
        </Requirement>

      </DecreasingContainer>

      <Button
        label="Criar conta"
        size="XL"
        icon={<ArrowRight color={theme.COLORS.WHITE} weight="bold" />}
        bottom
        onPress={() => navigation.navigate("Profile")}
      />
    </Container>
  );
}
